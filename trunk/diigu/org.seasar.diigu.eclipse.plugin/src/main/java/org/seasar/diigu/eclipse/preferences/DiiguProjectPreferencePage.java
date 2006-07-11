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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
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
public class DiiguProjectPreferencePage extends PropertyPage {

    private Button useBuilder;

    private Text selectExpression;

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

        this.useBuilder = new Button(createDefaultComposite(composite),
                SWT.CHECK);
        this.useBuilder.setText(Messages.USE_BUILDER);

        Label label = new Label(composite, SWT.NONE);
        label.setText(Messages.SELECT_EXPRESSION);
        this.selectExpression = new Text(composite, SWT.SINGLE | SWT.BORDER);
        data = new GridData(GridData.FILL_HORIZONTAL);
        this.selectExpression.setLayoutData(data);

        setUpPreferenceStore();
        setUpStoredValue();

        return composite;
    }

    private Composite createDefaultComposite(Composite parent) {
        Composite composite = new Composite(parent, SWT.NULL);
        GridLayout layout = new GridLayout();
        layout.numColumns = 1;
        composite.setLayout(layout);

        GridData data = new GridData();
        data.verticalAlignment = GridData.FILL;
        data.horizontalAlignment = GridData.FILL;
        data.horizontalSpan = 2;
        composite.setLayoutData(data);

        return composite;
    }

    protected void setUpPreferenceStore() {
        IProject project = getSelectedProject();
        if (project != null) {
            DiiguNature nature = DiiguNature.getInstance(project);
            if (nature != null) {
                setPreferenceStore(nature.getPreferenceStore());
            } else {
                setPreferenceStore(DiiguNature.createPreferenceStore(project));
            }
        }
    }

    /**
     * @return
     */
    private IProject getSelectedProject() {
        IAdaptable adaptor = getElement();
        IProject project = null;
        if (adaptor instanceof IJavaProject) {
            IJavaProject javap = (IJavaProject) adaptor;
            project = javap.getProject();
        } else if (adaptor instanceof IProject) {
            IProject p = (IProject) adaptor;
            IJavaProject javap = JavaCore.create(p);
            if (javap.exists()) {
                project = p;
            }
        }
        return project;
    }

    protected void setUpStoredValue() {
        IPreferenceStore store = getPreferenceStore();
        if (store != null) {
            this.selectExpression.setText(store
                    .getString(Constants.CONFIG_SELECT_EXPRESSION));
            this.useBuilder.setSelection(ProjectUtils.hasNature(
                    getSelectedProject(), DiiguNature.NATURE_ID));
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
     */
    protected void performDefaults() {
        IPreferenceStore store = getPreferenceStore();
        if (store != null) {
            this.selectExpression.setText(store
                    .getDefaultString(Constants.CONFIG_SELECT_EXPRESSION));
            this.useBuilder.setSelection(ProjectUtils.hasNature(
                    getSelectedProject(), DiiguNature.NATURE_ID));
        }
        super.performDefaults();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.preference.PreferencePage#performOk()
     */
    public boolean performOk() {
        try {
            IProject project = getSelectedProject();
            if (project != null) {
                if (this.useBuilder.getSelection()) {
                    ProjectUtils.addNature(project, DiiguNature.NATURE_ID);
                    DiiguNature nature = DiiguNature.getInstance(project);
                    if (nature != null) {
                        setPreferenceStore(nature.getPreferenceStore());
                    }
                } else {
                    ProjectUtils.removeNature(project, DiiguNature.NATURE_ID);
                }
            }
            IPreferenceStore store = getPreferenceStore();
            if (store != null) {
                store.setValue(Constants.CONFIG_SELECT_EXPRESSION,
                        this.selectExpression.getText());
            }
            return true;
        } catch (CoreException e) {
            DiiguPlugin.log(e);
            return false;
        }
    }

}
