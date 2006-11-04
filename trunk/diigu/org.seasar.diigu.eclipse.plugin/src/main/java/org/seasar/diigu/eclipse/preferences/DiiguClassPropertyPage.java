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
package org.seasar.diigu.eclipse.preferences;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.dialogs.PropertyPage;
import org.seasar.diigu.eclipse.Constants;
import org.seasar.diigu.eclipse.DiiguPlugin;
import org.seasar.diigu.eclipse.builder.DiiguNature;
import org.seasar.diigu.eclipse.nls.Messages;
import org.seasar.diigu.eclipse.util.ProjectUtils;

/**
 * @author taichi
 * 
 */
public class DiiguClassPropertyPage extends PropertyPage {

    public DiiguClassPropertyPage() {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
     */
    protected Control createContents(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        composite.setLayout(layout);
        GridData data = new GridData(GridData.FILL);
        data.grabExcessHorizontalSpace = true;
        composite.setLayoutData(data);

        IAdaptable adaptable = getElement();
        if (adaptable != null) {
            String expression = "";
            String msg = "not ";
            boolean enhanced = false;
            EnhanceProperty p = getEnhanceProperty(adaptable);
            if (p.type != null) {
                DiiguNature nature = DiiguNature.getInstance(p.type
                        .getJavaProject().getProject());
                if (nature != null) {
                    String s = p.type.getFullyQualifiedName();
                    if (nature.getSelectExpression().matcher(s).matches()) {
                        msg = "";
                    }
                    expression = nature.getSelectExpression().pattern();
                    enhanced = p.enhanced;
                }
            }

            Label label = new Label(composite, SWT.NONE);
            label.setText(Messages.SELECT_EXPRESSION);
            label = new Label(composite, SWT.NONE);
            label.setText(expression);

            label = new Label(composite, SWT.NONE);
            label = new Label(composite, SWT.NONE);
            label.setText(NLS.bind(Messages.ENHANCE_TARGET, msg));

            label = new Label(composite, SWT.NONE);
            label.setText(Messages.ENHANCE_STATUS);
            label = new Label(composite, SWT.NONE);
            label.setText(enhanced ? Messages.ENHANCED : Messages.NOT_ENHANCE);
        }
        return composite;
    }

    private EnhanceProperty getEnhanceProperty(IAdaptable adaptable) {
        EnhanceProperty result = new EnhanceProperty();
        try {
            IJavaElement element = null;
            if (adaptable instanceof IJavaElement) {
                element = (IJavaElement) adaptable;

            } else {
                IResource resource = (IResource) adaptable
                        .getAdapter(IResource.class);
                element = JavaCore.create(resource);
            }

            if (element != null) {
                switch (element.getElementType()) {
                case IJavaElement.COMPILATION_UNIT:
                    result.type = ((ICompilationUnit) element)
                            .findPrimaryType();
                    result.enhanced = isEnhanced(findClassFile(result.type));
                    break;
                case IJavaElement.TYPE:
                    result.type = (IType) element;
                    result.enhanced = isEnhanced(findClassFile(result.type));
                    break;
                default:
                    break;
                }
            }
        } catch (CoreException e) {
            DiiguPlugin.log(e);
        }
        return result;
    }

    private class EnhanceProperty {
        IType type = null;

        boolean enhanced;
    }

    private boolean isEnhanced(IResource resource) throws CoreException {
        QualifiedName qn = new QualifiedName(Constants.PLUGIN_ID,
                Constants.ENHANCE_LOCALNAME);
        return resource != null && resource.getPersistentProperty(qn) != null;
    }

    private IResource findClassFile(IType type) throws CoreException {
        IResource result = null;
        IPath[] locations = ProjectUtils.getOutputLocations(type
                .getJavaProject());
        IContainer root = ProjectUtils.getWorkspaceRoot();
        String typename = type.getFullyQualifiedName();
        String typepath = typename.replace('.', '/') + ".class";
        for (int index = 0; index < locations.length; index++) {
            IPath outpath = locations[index];
            IPath path = outpath.append(typepath);
            IResource resource = root.getFile(path);
            if (resource.exists()) {
                result = resource;
                break;
            }
        }
        return result;
    }
}
