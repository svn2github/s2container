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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.seasar.diigu.eclipse.nls.Messages;
import org.seasar.diigu.eclipse.util.ProjectUtils;

/**
 * @author taichi
 * 
 */
public class DependencyAnalyzeJob extends WorkspaceJob {

    private IProject project;

    /**
     * @param name
     */
    public DependencyAnalyzeJob(IProject project) {
        super(Messages.ANALYZE_DEPENDENCY);
        if (project == null) {
            throw new IllegalArgumentException("project is null");
        }
        setPriority(Job.SHORT);
        this.project = project;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.resources.WorkspaceJob#runInWorkspace(org.eclipse.core.runtime.IProgressMonitor)
     */
    public IStatus runInWorkspace(IProgressMonitor monitor)
            throws CoreException {
        Set targets = new HashSet();
        Set allOf = new HashSet(Arrays.asList(ProjectUtils.getAllProjects()));
        analyze(this.project, targets, allOf);
        for (Iterator i = targets.iterator(); i.hasNext();) {
            IProject p = (IProject) i.next();
            Job job = new NameEnhanceJob(Messages.ENHANCE_FULLBUILD, p);
            job.schedule();
        }

        return Status.OK_STATUS;
    }

    protected void analyze(IProject current, Set targets, Set allOf) {
        allOf.remove(current);
        if (allOf.isEmpty()) {
            return;
        }
        IProject[] refs = current.getReferencingProjects();
        for (int i = 0; i < refs.length; i++) {
            IProject p = refs[i];
            if (allOf.contains(p)) {
                analyze(p, targets, allOf);
                targets.add(p);
            }
        }
    }
}
