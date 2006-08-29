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
package org.seasar.diigu.eclipse;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jdt.core.ElementChangedEvent;
import org.eclipse.jdt.core.IElementChangedListener;
import org.eclipse.jdt.core.IJavaElementDelta;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.seasar.diigu.eclipse.operation.DependencyAnalyzeJob;

/**
 * The main plugin class to be used in the desktop.
 */
public class DiiguPlugin extends AbstractUIPlugin {

    public static final String PLUGIN_ID = Constants.PLUGIN_ID;

    // The shared instance.
    private static DiiguPlugin plugin;

    /**
     * The constructor.
     */
    public DiiguPlugin() {
        plugin = this;
    }

    /**
     * This method is called upon plug-in activation
     */
    public void start(BundleContext context) throws Exception {
        super.start(context);
        JavaCore.addElementChangedListener(new IElementChangedListener() {
            public void elementChanged(ElementChangedEvent event) {
                IJavaElementDelta[] children = event.getDelta()
                        .getAffectedChildren();
                for (int i = 0; children != null && i < children.length; i++) {
                    IResourceDelta[] rdarray = children[i].getResourceDeltas();
                    for (int j = 0; rdarray != null && j < rdarray.length; j++) {
                        IResource r = rdarray[j].getResource();
                        if (r == null) {
                            continue;
                        }
                        if (".classpath".equals(r.getName())) {
                            Job job = new DependencyAnalyzeJob(r.getProject());
                            job.schedule();
                        }
                    }
                }
            }
        });
    }

    /**
     * This method is called when the plug-in is stopped
     */
    public void stop(BundleContext context) throws Exception {
        super.stop(context);
        plugin = null;
    }

    /**
     * Returns the shared instance.
     */
    public static DiiguPlugin getDefault() {
        return plugin;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in
     * relative path.
     * 
     * @param path
     *            the path
     * @return the image descriptor
     */
    public static ImageDescriptor getImageDescriptor(String path) {
        return AbstractUIPlugin.imageDescriptorFromPlugin("org.seasar.diigu",
                path);
    }

    public static void log(Throwable throwable) {
        IStatus status = new Status(IStatus.ERROR, PLUGIN_ID, IStatus.ERROR,
                throwable.getMessage(), throwable);
        getDefault().getLog().log(status);
    }

}
