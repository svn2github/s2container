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

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.seasar.diigu.eclipse.Constants;
import org.seasar.diigu.eclipse.DiiguPlugin;

/**
 * @author taichi
 * 
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

    /**
     * 
     */
    public PreferenceInitializer() {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
     */
    public void initializeDefaultPreferences() {
        IEclipsePreferences pref = new DefaultScope()
                .getNode(DiiguPlugin.PLUGIN_ID);
        pref.put(Constants.CONFIG_SELECT_EXPRESSION, ".*Dao");
    }

}
