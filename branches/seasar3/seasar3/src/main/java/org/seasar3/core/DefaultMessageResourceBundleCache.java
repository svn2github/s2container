/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
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
package org.seasar3.core;

import java.io.File;

/**
 * A class caching {@link DefaultMessageResourceBundle}.
 * 
 * @author higa
 * 
 */
public class DefaultMessageResourceBundleCache {

    private DefaultMessageResourceBundle bundle;

    private File file;

    private long lastModified;

    /**
     * Creates new {@link DefaultMessageResourceBundleCache}.
     * 
     * @param bundle
     * @param file
     */
    public DefaultMessageResourceBundleCache(
            DefaultMessageResourceBundle bundle, File file) {
        this.bundle = bundle;
        this.file = file;
        if (file != null) {
            lastModified = file.lastModified();
        }
    }

    /**
     * Determines if bundle is modified.
     * 
     * @return
     */
    public boolean isModified() {
        if (file == null) {
            return false;
        }
        return file.lastModified() > lastModified;
    }

    /**
     * Returns bundle.
     * 
     * @return
     */
    public DefaultMessageResourceBundle getBundle() {
        return bundle;
    }

    /**
     * Sets bundle.
     * 
     * @param bundle
     */
    public void setBundle(DefaultMessageResourceBundle bundle) {
        this.bundle = bundle;
    }
}