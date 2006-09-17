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

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jdt.core.ElementChangedEvent;
import org.eclipse.jdt.core.IElementChangedListener;
import org.eclipse.jdt.core.IJavaElementDelta;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.seasar.diigu.eclipse.operation.DependencyAnalyzeJob;

/**
 * @author taichi
 * 
 */
public class DiiguStartup implements IStartup {

    private class ClasspathChangedListener implements IElementChangedListener {
        public void elementChanged(ElementChangedEvent event) {
            IJavaElementDelta[] children = event.getDelta()
                    .getAffectedChildren();
            for (int i = 0; children != null && i < children.length; i++) {
                IResourceDelta[] ary = children[i].getResourceDeltas();
                for (int j = 0; ary != null && j < ary.length; j++) {
                    IResource r = ary[j].getResource();
                    if (r != null && ".classpath".equals(r.getName())) {
                        Job job = new DependencyAnalyzeJob(r.getProject());
                        job.schedule();
                    }
                }
            }
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
                        new ClasspathChangedListener(),
                        ElementChangedEvent.POST_CHANGE);
            }
        });
    }

}
