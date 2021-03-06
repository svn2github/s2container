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

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;
import org.seasar.diigu.eclipse.util.LogUtil;

/**
 * The main plugin class to be used in the desktop.
 */
public class DiiguPlugin extends Plugin {

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

    public static void log(Throwable throwable) {
        LogUtil.log(getDefault(), throwable);
    }

}
