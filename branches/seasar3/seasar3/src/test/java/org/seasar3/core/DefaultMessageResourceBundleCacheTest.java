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

import junit.framework.TestCase;

/**
 * @author higa
 * 
 */
public class DefaultMessageResourceBundleCacheTest extends TestCase {

    /**
     * Test method for {@link DefaultMessageResourceBundleCache#isModified()}.
     */
    public void testIsModifiedForFileIsNull() {
        DefaultMessageResourceBundle mrb = new DefaultMessageResourceBundle(
                null);
        DefaultMessageResourceBundleCache cache = new DefaultMessageResourceBundleCache(
                mrb, null);
        assertFalse(cache.isModified());
    }

    /**
     * Test method for {@link DefaultMessageResourceBundleCache#isModified()}.
     * 
     * @throws Exception
     */
    public void testIsModifiedForFileIsNotModified() throws Exception {
        DefaultMessageResourceBundle mrb = new DefaultMessageResourceBundle(
                null);
        File file = new File("path");
        DefaultMessageResourceBundleCache cache = new DefaultMessageResourceBundleCache(
                mrb, file);
        assertFalse(cache.isModified());
    }

    /**
     * Test method for {@link DefaultMessageResourceBundleCache#getBundle()}.
     * 
     * @throws Exception
     */
    public void testGetBundle() throws Exception {
        DefaultMessageResourceBundle mrb = new DefaultMessageResourceBundle(
                null);
        DefaultMessageResourceBundleCache cache = new DefaultMessageResourceBundleCache(
                mrb, null);
        assertSame(mrb, cache.getBundle());
    }

    /**
     * Test method for {@link DefaultMessageResourceBundleCache#isModified()}.
     * 
     * @throws Exception
     */
    public void testIsModifiedForFileIsModified() throws Exception {
        DefaultMessageResourceBundle mrb = new DefaultMessageResourceBundle(
                null);
        File file = new FileMock();
        DefaultMessageResourceBundleCache cache = new DefaultMessageResourceBundleCache(
                mrb, file);
        file.setLastModified(file.lastModified() + 1);
        assertTrue(cache.isModified());
    }

    private static class FileMock extends File {

        private static final long serialVersionUID = 1L;

        private long time;

        FileMock() {
            super("path");
        }

        @Override
        public long lastModified() {
            return time;
        }

        @Override
        public boolean setLastModified(long time) {
            this.time = time;
            return true;
        }

    }
}