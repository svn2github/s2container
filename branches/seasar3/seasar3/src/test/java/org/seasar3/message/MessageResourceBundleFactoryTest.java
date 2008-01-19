/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
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
package org.seasar3.message;

import java.io.File;
import java.util.Locale;

import junit.framework.TestCase;

import org.seasar3.env.Env;

/**
 * @author higa
 * 
 */
public class MessageResourceBundleFactoryTest extends TestCase {

    private static final String PATH = "org/seasar3/message/foo.properties";

    private static final String ENV_HOT_PATH = "org/seasar3/message/env_hot.properties";

    private static final String ENV_COOL_PATH = "org/seasar3/message/env_cool.properties";

    private static final String BUNDLE_NAME = "org.seasar3.message.foo";

    @Override
    protected void tearDown() throws Exception {
        Env.initialize();
    }

    /**
     * 
     */
    public void testCreateBundle() {
        MessageResourceBundle bundle = MessageResourceBundleFactory
                .createBundle(PATH);
        assertNotNull(bundle);
        assertEquals("111", bundle.get("aaa"));
    }

    /**
     * 
     */
    public void testCreateBundleForIllegalPath() {
        MessageResourceBundle bundle = MessageResourceBundleFactory
                .createBundle("illegal path");
        assertNull(bundle);
    }

    /**
     * 
     */
    public void testGetFile() {
        Env.initialize(ENV_HOT_PATH);
        File file = MessageResourceBundleFactory.getFile(PATH);
        assertNotNull(file);
        assertTrue(file.exists());
    }

    /**
     * 
     */
    public void testGetFileForCoolDeployement() {
        Env.initialize(ENV_COOL_PATH);
        File file = MessageResourceBundleFactory.getFile(PATH);
        assertNull(file);
    }

    /**
     * 
     */
    public void testGetBundle() {
        MessageResourceBundle bundle = MessageResourceBundleFactory
                .getBundle(BUNDLE_NAME);
        assertNotNull(bundle);
        assertEquals("111", bundle.get("aaa"));
    }

    /**
     * 
     */
    public void testGetBundleForLocale() {
        MessageResourceBundle bundle = MessageResourceBundleFactory.getBundle(
                Locale.JAPAN, BUNDLE_NAME);
        assertNotNull(bundle);
        assertEquals("111ja", bundle.get("aaa"));
        assertEquals("222", bundle.get("bbb"));
    }
}
