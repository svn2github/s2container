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
package org.seasar.diigu.eclipse.startup;

import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.jdt.core.ElementChangedEvent;
import org.eclipse.jdt.core.IElementChangedListener;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaElementDelta;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.seasar.diigu.eclipse.builder.DiiguNature;
import org.seasar.diigu.eclipse.nls.Messages;
import org.seasar.diigu.eclipse.operation.NameEnhanceJob;
import org.seasar.diigu.eclipse.util.JavaElementDeltaAcceptor;
import org.seasar.diigu.eclipse.util.ProjectUtils;

/**
 * @author taichi
 * 
 */
public class DiiguStartup implements IStartup {

    private class ClassImageChangedListener extends
            JavaElementDeltaAcceptor.Visitor implements IElementChangedListener {
        public void elementChanged(ElementChangedEvent event) {
            JavaElementDeltaAcceptor.accept(event.getDelta(), this);
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.seasar.diigu.eclipse.util.JavaElementDeltaAcceptor.Visitor#visit(org.eclipse.jdt.core.IJavaProject)
         */
        protected boolean visit(IJavaProject project) {
            return ProjectUtils.hasNature(project.getProject(),
                    DiiguNature.NATURE_ID);
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.seasar.diigu.eclipse.util.JavaElementDeltaAcceptor.Visitor#postVisit(org.eclipse.jdt.core.IJavaElementDelta)
         */
        protected boolean postVisit(IJavaElementDelta delta) {
            IJavaElement e = delta.getElement();
            if (e.getElementType() == IJavaElement.JAVA_PROJECT) {
                IResourceDelta[] deltas = delta.getResourceDeltas();
                for (int i = 0; deltas != null && i < deltas.length; i++) {
                    NameEnhanceJob job = new NameEnhanceJob(
                            Messages.ENHANCE_INCREMENTALBUILD, deltas[i]);
                    job.schedule(3L);
                }

            }
            return true;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IStartup#earlyStartup()
     */
    public void earlyStartup() {
        final IWorkbench workbench = PlatformUI.getWorkbench();
        workbench.getDisplay().asyncExec(new Runnable() {
            public void run() {
                JavaCore.addElementChangedListener(
                        new ClassImageChangedListener(),
                        ElementChangedEvent.POST_CHANGE);
            }
        });
    }

}
