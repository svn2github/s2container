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
package org.seasar.diigu.eclipse.plugin.marker;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolutionGenerator2;
import org.seasar.diigu.eclipse.Constants;
import org.seasar.diigu.eclipse.DiiguPlugin;
import org.seasar.diigu.eclipse.nls.Messages;

/**
 * @author taichi
 * 
 */
public class MarkerResolutionGenerator implements IMarkerResolutionGenerator2 {

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IMarkerResolutionGenerator2#hasResolutions(org.eclipse.core.resources.IMarker)
     */
    public boolean hasResolutions(IMarker marker) {
        try {
            return Constants.MARKER_ID.equals(marker.getType());
        } catch (CoreException e) {
            DiiguPlugin.log(e);
            return false;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IMarkerResolutionGenerator#getResolutions(org.eclipse.core.resources.IMarker)
     */
    public IMarkerResolution[] getResolutions(IMarker marker) {
        return new IMarkerResolution[] { new IMarkerResolution() {
            /*
             * (non-Javadoc)
             * 
             * @see org.eclipse.ui.IMarkerResolution#getLabel()
             */
            public String getLabel() {
                return Messages.REBUILD_PROJECT;
            }

            /*
             * (non-Javadoc)
             * 
             * @see org.eclipse.ui.IMarkerResolution#run(org.eclipse.core.resources.IMarker)
             */
            public void run(IMarker marker) {
                final IProject project = marker.getResource().getProject();
                Job job = new WorkspaceJob(Messages.REBUILD_PROJECT) {
                    public IStatus runInWorkspace(IProgressMonitor monitor)
                            throws CoreException {
                        project.build(IncrementalProjectBuilder.FULL_BUILD,
                                monitor);
                        return Status.OK_STATUS;
                    }
                };
                job.setPriority(Job.BUILD);
                job.schedule();
            }
        } };
    }

}
