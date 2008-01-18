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
package org.seasar.diigu.eclipse.builder;

import java.util.regex.Pattern;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.seasar.diigu.eclipse.Constants;
import org.seasar.diigu.eclipse.DiiguPlugin;

public class DiiguNature implements IProjectNature {

    /**
     * ID of this project nature
     */
    public static final String NATURE_ID = "org.seasar.diigu.eclipse.diiguNature";

    private IProject project;

    private Pattern selectExpression;

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.resources.IProjectNature#configure()
     */
    public void configure() throws CoreException {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.resources.IProjectNature#deconfigure()
     */
    public void deconfigure() throws CoreException {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.resources.IProjectNature#getProject()
     */
    public IProject getProject() {
        return project;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.resources.IProjectNature#setProject(org.eclipse.core.resources.IProject)
     */
    public void setProject(IProject project) {
        this.project = project;
        IPreferenceStore store = new ScopedPreferenceStore(new ProjectScope(
                project), DiiguPlugin.PLUGIN_ID);
        this.selectExpression = Pattern.compile(store
                .getString(Constants.CONFIG_SELECT_EXPRESSION));
    }

    public Pattern getSelectExpression() {
        return this.selectExpression;
    }

    public static DiiguNature getInstance(IProject project) {
        try {
            if (project != null && project.hasNature(NATURE_ID)) {
                IProjectNature nature = project.getNature(NATURE_ID);
                if (nature instanceof DiiguNature) {
                    return (DiiguNature) nature;
                }
            }
        } catch (CoreException e) {
            DiiguPlugin.log(e);
        }
        return null;
    }
}