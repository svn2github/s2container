/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.diigu.eclipse.operation;

import java.util.Hashtable;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.util.IClassFileReader;
import org.eclipse.jdt.core.util.IConstantPool;
import org.eclipse.jdt.core.util.IConstantPoolConstant;
import org.eclipse.jdt.core.util.IConstantPoolEntry;
import org.seasar.diigu.ParameterNameEnhancer;
import org.seasar.diigu.eclipse.Constants;
import org.seasar.diigu.eclipse.DiiguPlugin;
import org.seasar.diigu.eclipse.builder.DiiguNature;
import org.seasar.diigu.eclipse.nls.Messages;
import org.seasar.diigu.eclipse.util.ArrayUtil;
import org.seasar.diigu.eclipse.util.JavaProjectClassLoader;
import org.seasar.diigu.eclipse.util.ProjectUtils;
import org.seasar.diigu.eclipse.util.StatusUtil;

/**
 * @author taichi
 * 
 */
public class NameEnhanceJob extends WorkspaceJob {

    private static Map CLASS_FILE_TIMESTAMPS = new Hashtable();

    private IProject project;

    private IWorkspaceRunnable runnable;

    public NameEnhanceJob(String name, final IContainer container) {
        super(name);
        setPriority(Job.BUILD);
        this.project = container.getProject();
        this.runnable = new IWorkspaceRunnable() {
            public void run(final IProgressMonitor monitor)
                    throws CoreException {
                container.accept(new IResourceVisitor() {
                    public boolean visit(IResource resource)
                            throws CoreException {
                        enhance(resource, monitor);
                        return true;
                    }
                });
            }
        };
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.runtime.jobs.Job#belongsTo(java.lang.Object)
     */
    public boolean belongsTo(Object family) {
        return family == ResourcesPlugin.FAMILY_AUTO_BUILD
                || family == ResourcesPlugin.FAMILY_MANUAL_BUILD;

    }

    public NameEnhanceJob(String name, final IResourceDelta delta) {
        super(name);
        setPriority(Job.BUILD);
        this.project = delta.getResource().getProject();
        this.runnable = new IWorkspaceRunnable() {
            public void run(final IProgressMonitor monitor)
                    throws CoreException {
                delta.accept(new IResourceDeltaVisitor() {
                    public boolean visit(IResourceDelta delta)
                            throws CoreException {
                        final int MASK = IResourceDelta.ADDED
                                | IResourceDelta.CHANGED;
                        if ((MASK & delta.getKind()) != 0) {
                            enhance(delta.getResource(), monitor);
                        }
                        return true;
                    }
                });
            }
        };
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.resources.WorkspaceJob#runInWorkspace(org.eclipse.core.runtime.IProgressMonitor)
     */
    public IStatus runInWorkspace(IProgressMonitor monitor)
            throws CoreException {
        if (this.project != null
                && ProjectUtils.hasNature(this.project, DiiguNature.NATURE_ID)) {
            this.project.deleteMarkers(Constants.MARKER_ID, true,
                    IResource.DEPTH_ZERO);
            this.runnable.run(monitor);
        }
        return Status.OK_STATUS;
    }

    void enhance(IResource resource, IProgressMonitor monitor)
            throws CoreException {
        try {
            if (resource instanceof IFile) {
                if (monitor == null) {
                    monitor = new NullProgressMonitor();
                }

                if ("java".equals(resource.getFileExtension())) {
                    ICompilationUnit unit = JavaCore
                            .createCompilationUnitFrom((IFile) resource);
                    enhance(unit, monitor);
                } else if ("class".equals(resource.getFileExtension())) {
                    IJavaElement elem = JavaCore.create(resource);
                    if (elem.getElementType() == IJavaElement.CLASS_FILE) {
                        IResource src = toSource((IClassFile) elem);
                        if (src != null) {
                            enhance(src, monitor);
                        }
                    }
                }
            }
        } catch (Exception e) {
            DiiguPlugin.log(e);
            throw new CoreException(StatusUtil.createError(IStatus.ERROR, e));
        }
    }

    private static IResource toSource(IClassFile clazz) throws CoreException {
        IJavaProject project = clazz.getJavaProject();
        IClassFileReader reader = ToolFactory.createDefaultClassFileReader(
                clazz, IClassFileReader.CONSTANT_POOL);
        if (reader != null) {
            IConstantPool pool = reader.getConstantPool();
            for (int i = 0; i < pool.getConstantPoolCount(); i++) {
                if (pool.getEntryKind(i) == IConstantPoolConstant.CONSTANT_Class) {
                    IConstantPoolEntry entry = pool.decodeEntry(i);
                    char[] data = entry.getClassInfoName();
                    String name = String.valueOf(data);
                    name = name.replace('/', '.');
                    IType type = project.findType(name);
                    if (type != null && type.isBinary() == false) {
                        return type.getResource();
                    }
                }
            }
        }
        return null;
    }

    protected void enhance(ICompilationUnit unit, IProgressMonitor monitor)
            throws CoreException, Exception {
        monitor.beginTask(Messages.ENHANCE_BEGIN, 5);

        JavaProjectClassLoader loader = new JavaProjectClassLoader(unit
                .getJavaProject());
        monitor.worked(1);
        IProgressMonitor submonitor = new SubProgressMonitor(monitor, 3);
        IType[] types = unit.getAllTypes();
        submonitor.beginTask(Messages.ENHANCE_PROCEED, types.length);
        DiiguNature nature = DiiguNature.getInstance(this.project);
        Pattern ptn = null;
        if (nature != null) {
            ptn = nature.getSelectExpression();
        }
        if (ptn != null) {
            IPath[] locations = ProjectUtils.getOutputLocations(unit
                    .getJavaProject());
            IContainer root = this.project.getParent();
            for (int i = 0; i < types.length; i++) {
                IType type = types[i];
                String typename = type.getFullyQualifiedName();
                String typepath = typename.replace('.', '/') + ".class";
                submonitor.subTask(typename);
                if (ptn.matcher(typename).matches()) {
                    for (int index = 0; index < locations.length; index++) {
                        IPath outpath = locations[index];
                        IPath path = outpath.append(typepath);
                        IResource resource = root.getFile(path);
                        if (resource.exists()) {
                            if (processEnhance(loader, type, resource)) {
                                break;
                            }
                        }
                        if (submonitor.isCanceled()) {
                            throw new OperationCanceledException();
                        }
                    }
                }
                submonitor.worked(1);
            }

            submonitor.done();
            monitor.worked(1);
            monitor.setTaskName(Messages.ENHANCE_END);
        }

        monitor.done();
    }

    private synchronized boolean processEnhance(JavaProjectClassLoader loader,
            IType type, IResource classfile) throws CoreException {
        String location = classfile.getFullPath().toString();
        Long proceedTime = (Long) CLASS_FILE_TIMESTAMPS.get(location);
        if (proceedTime == null
                || proceedTime.longValue() < classfile.getLocalTimeStamp()) {
            ParameterNameEnhancer enhancer = new ParameterNameEnhancer(type
                    .getFullyQualifiedName(), loader);
            if (enhanceClassFile(type, enhancer)) {
                enhancer.save();
                classfile.refreshLocal(IResource.DEPTH_ZERO, null);
                CLASS_FILE_TIMESTAMPS.put(location, new Long(classfile
                        .getLocalTimeStamp()));
                return true;
            }
        }
        return false;
    }

    protected boolean enhanceClassFile(IType type,
            ParameterNameEnhancer enhancer) throws CoreException {
        boolean dirty = false;
        IMethod[] methods = type.getMethods();
        for (int i = 0; i < methods.length; i++) {
            dirty |= enhanceMethod(methods[i], enhancer);
        }
        return dirty;
    }

    protected boolean enhanceMethod(IMethod method,
            ParameterNameEnhancer enhancer) throws CoreException {
        if (method.getNumberOfParameters() < 1) {
            return false;
        }
        try {
            String[] typeSignatures = method.getParameterTypes();
            String[] parameterTypes = new String[typeSignatures.length];
            for (int i = 0; i < typeSignatures.length; i++) {
                parameterTypes[i] = getResolvedTypeName(typeSignatures[i],
                        method.getDeclaringType());
            }
            String[] parameterNames = method.getParameterNames();
            if (method.isConstructor()) {
                IType me = method.getDeclaringType();
                IType around = me.getDeclaringType();
                if (Flags.isStatic(me.getFlags()) == false && around != null
                        && around.isClass()) {
                    String[] newtypes = { resolveType(me, 0, around
                            .getElementName()) };
                    parameterTypes = (String[]) ArrayUtil.add(newtypes,
                            parameterTypes);
                    String[] newnames = { "this" };
                    parameterNames = (String[]) ArrayUtil.add(newnames,
                            parameterNames);
                }
                enhancer.setConstructorParameterNames(parameterTypes,
                        parameterNames);
            } else {
                String methodName = method.getElementName();
                enhancer.setMethodParameterNames(methodName, parameterTypes,
                        parameterNames);
            }
            return true;
        } catch (CoreException e) {
            DiiguPlugin.log(e);
            throw e;
        } catch (RuntimeException e) {
            String msg = Messages.bind(Messages.ENHANCE_ERROR, method
                    .getDeclaringType(), method);
            IStatus status = new Status(IStatus.ERROR, DiiguPlugin.PLUGIN_ID,
                    IStatus.ERROR, msg, e);
            DiiguPlugin.getDefault().getLog().log(status);
            return false;
        }
    }

    public static String getResolvedTypeName(String typeSignature, IType type)
            throws JavaModelException {
        int count = Signature.getArrayCount(typeSignature);
        if (Signature.C_UNRESOLVED == typeSignature.charAt(count)) {
            String name = null;
            if (0 < count) {
                name = typeSignature.substring(count + 1, typeSignature
                        .indexOf(';'));
            } else {
                name = Signature.toString(typeSignature);
            }
            return resolveType(type, count, name);
        } else {
            return Signature.toString(typeSignature);
        }
    }

    /**
     * @param type
     * @param count
     * @param name
     * @return
     * @throws JavaModelException
     */
    private static String resolveType(IType type, int count, String name)
            throws JavaModelException {
        String[][] resolvedNames = type.resolveType(name);
        if (resolvedNames != null && resolvedNames.length > 0) {
            StringBuffer stb = new StringBuffer();
            String pkg = resolvedNames[0][0];
            if (pkg != null && 0 < pkg.length()) {
                stb.append(resolvedNames[0][0]);
                stb.append('.');
            }
            stb.append(resolvedNames[0][1].replace('.', '$'));
            for (int i = 0; i < count; i++) {
                stb.append("[]");
            }
            return stb.toString();
        }
        return "";
    }

}
