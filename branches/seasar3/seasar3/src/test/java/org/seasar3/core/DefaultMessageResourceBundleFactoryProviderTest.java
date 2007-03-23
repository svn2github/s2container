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
import java.util.Locale;
import java.util.Properties;

import junit.framework.TestCase;

/**
 * @author higa
 * 
 */
public class DefaultMessageResourceBundleFactoryProviderTest extends TestCase {

    static final String MESSAGE_BUNDLE_NAME = "org.seasar3.core.foo";

    private Properties envInfo;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        envInfo = Env.getEnvInfo();
    }

    @Override
    protected void tearDown() throws Exception {
        Env.setEnvInfo(envInfo);
        super.tearDown();
    }

    /**
     * Test method for
     * {@link DefaultMessageResourceBundleFactoryProvider#getBundle(java.util.Locale, String)}.
     */
    public void testGetBundleForLocaleIsNull() {
        DefaultMessageResourceBundleFactoryProvider provider = new DefaultMessageResourceBundleFactoryProvider();
        try {
            provider.getBundle(null, "hoge");
            fail();
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }

    /**
     * Test method for
     * {@link DefaultMessageResourceBundleFactoryProvider#getBundle(java.util.Locale, String)}.
     */
    public void testGetBundleForMessageBundleNameIsNull() {
        DefaultMessageResourceBundleFactoryProvider provider = new DefaultMessageResourceBundleFactoryProvider();
        try {
            provider.getBundle(Locale.getDefault(), null);
            fail();
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }

    /**
     * Test method for
     * {@link DefaultMessageResourceBundleFactoryProvider#createBundle(String)}.
     */
    public void testCreateBundleForBundleDoesNotExist() {
        DefaultMessageResourceBundleFactoryProvider provider = new DefaultMessageResourceBundleFactoryProvider();
        assertNull(provider.createBundle("bundle does not exist"));
    }

    /**
     * Test method for
     * {@link DefaultMessageResourceBundleFactoryProvider#createBundle(String)}.
     */
    public void testCreateBundleForBundleExists() {
        DefaultMessageResourceBundleFactoryProvider provider = new DefaultMessageResourceBundleFactoryProvider();
        MessageResourceBundle bundle = provider
                .createBundle(MESSAGE_BUNDLE_NAME);
        assertNotNull(bundle);
    }

    /**
     * Test method for
     * {@link DefaultMessageResourceBundleFactoryProvider#fromMessageBundleNameToPath(String, String)}.
     */
    public void testFromMessageBundleNameToPath() {
        DefaultMessageResourceBundleFactoryProvider provider = new DefaultMessageResourceBundleFactoryProvider();
        assertEquals("aaa/bbb.properties", provider
                .fromMessageBundleNameToPath("aaa.bbb"));
    }

    /**
     * Test method for
     * {@link DefaultMessageResourceBundleFactoryProvider#getBundle(java.util.Locale, String)}.
     */
    public void testGetBundleForSpecificLocale() {
        DefaultMessageResourceBundleFactoryProvider provider = new DefaultMessageResourceBundleFactoryProvider();
        MessageResourceBundle bundle = provider.getBundle(Locale.JAPAN,
                MESSAGE_BUNDLE_NAME);
        assertNotNull(bundle);
        assertEquals("222", bundle.get("bbb"));
        assertEquals("111ja", bundle.get("aaa"));
    }

    /**
     * Test method for
     * {@link DefaultMessageResourceBundleFactoryProvider#getBundle(String)}.
     */
    public void testGetBundleForCache() {
        DefaultMessageResourceBundleFactoryProvider provider = new DefaultMessageResourceBundleFactoryProvider();
        MessageResourceBundle bundle = provider.getBundle(MESSAGE_BUNDLE_NAME);
        assertSame(bundle, provider.getBundle(MESSAGE_BUNDLE_NAME));
    }

    /**
     * Test method for
     * {@link DefaultMessageResourceBundleFactoryProvider#getBundle(String)}.
     */
    public void testGetBundleForCacheAndHotDeploy() {
        Env.initialize(EnvTest.ENV_CT_PATH);
        DefaultMessageResourceBundleFactoryProvider provider = new DefaultMessageResourceBundleFactoryProvider();
        MessageResourceBundle bundle = provider.getBundle(MESSAGE_BUNDLE_NAME);
        assertSame(bundle, provider.getBundle(MESSAGE_BUNDLE_NAME));
        File file = provider.getFile(MESSAGE_BUNDLE_NAME);
        file.setLastModified(file.lastModified() + 1);
        assertNotSame(bundle, provider.getBundle(MESSAGE_BUNDLE_NAME));
    }

    /**
     * Test method for
     * {@link DefaultMessageResourceBundleFactoryProvider#getBundle(Locale, String)}.
     */
    public void testGetBundleForParentCache() {
        DefaultMessageResourceBundleFactoryProvider provider = new DefaultMessageResourceBundleFactoryProvider();
        MessageResourceBundle bundleJa = provider.getBundle(Locale.JAPAN,
                MESSAGE_BUNDLE_NAME);
        MessageResourceBundle bundleEn = provider.getBundle(Locale.ENGLISH,
                MESSAGE_BUNDLE_NAME);
        assertSame(bundleJa.getParent(), bundleEn.getParent());
    }

    /**
     * Test method for
     * {@link DefaultMessageResourceBundleFactoryProvider#getFile(String)}.
     */
    public void testGetFileForNotHotDeployment() {
        DefaultMessageResourceBundleFactoryProvider provider = new DefaultMessageResourceBundleFactoryProvider();
        assertNull(provider.getFile(MESSAGE_BUNDLE_NAME));
    }

    /**
     * Test method for
     * {@link DefaultMessageResourceBundleFactoryProvider#getFile(String)}.
     */
    public void testGetFileForHotDeployment() {
        Env.initialize(EnvTest.ENV_CT_PATH);
        DefaultMessageResourceBundleFactoryProvider provider = new DefaultMessageResourceBundleFactoryProvider();
        assertNotNull(provider.getFile(MESSAGE_BUNDLE_NAME));
    }

    /**
     * Test method for
     * {@link DefaultMessageResourceBundleFactoryProvider#getFile(String)}.
     */
    public void testGetFileForHotDeploymentAndIllegalName() {
        Env.initialize(EnvTest.ENV_CT_PATH);
        DefaultMessageResourceBundleFactoryProvider provider = new DefaultMessageResourceBundleFactoryProvider();
        assertNull(provider.getFile("illegal name"));
    }
}