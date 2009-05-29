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
package org.seasar.diigu.eclipse.util;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.seasar.diigu.eclipse.DiiguPlugin;

/**
 * @author taichi
 * 
 */
public class JavaProjectClassLoader extends URLClassLoader {

    /**
     * 
     */
    public JavaProjectClassLoader(IJavaProject project) {
        super(new URL[0]);
        Set already = new HashSet();
        addClasspathEntries(project, already, true);
    }

    /**
     * @param urls
     * @param parent
     */
    public JavaProjectClassLoader(IJavaProject project, ClassLoader parent) {
        super(new URL[0], parent);
        Set already = new HashSet();
        addClasspathEntries(project, already, true);
    }

    protected void addClasspathEntries(IJavaProject project, Set already,
            boolean atFirst) {
        already.add(project);

        try {
            IContainer workspaceroot = project.getProject().getParent();
            IPath path = project.getOutputLocation();
            addURL(toURL(workspaceroot.getFolder(path).getLocation()));

            IClasspathEntry[] entries = project.getResolvedClasspath(true);
            for (int i = 0; i < entries.length; i++) {
                IClasspathEntry entry = entries[i];
                switch (entry.getEntryKind()) {
                case IClasspathEntry.CPE_SOURCE:
                    IPath dist = entry.getOutputLocation();
                    if (dist != null) {
                        addURL(toURL(workspaceroot.getFolder(dist)
                                .getLocation()));
                    }
                    break;
                case IClasspathEntry.CPE_LIBRARY:
                case IClasspathEntry.CPE_CONTAINER:
                case IClasspathEntry.CPE_VARIABLE:
                    IPath p = entry.getPath();
                    if (p.toFile().exists()) {
                        addURL(toURL(p));
                    } else {
                        addURL(toURL(workspaceroot.getFile(p).getLocation()));
                    }
                    break;
                case IClasspathEntry.CPE_PROJECT:
                    IJavaProject proj = ProjectUtils.getJavaProject(entry
                            .getPath().segment(0));
                    if (proj != null && proj.exists()
                            && already.contains(proj) == false
                            && (atFirst || entry.isExported())) {
                        addClasspathEntries(proj, already, false);
                    }
                    break;
                default:
                    break;
                }
            }
        } catch (Exception e) {
            DiiguPlugin.log(e);
        }
    }

    protected URL toURL(IPath path) throws Exception {
        return path.toFile().toURI().toURL();
    }

}
