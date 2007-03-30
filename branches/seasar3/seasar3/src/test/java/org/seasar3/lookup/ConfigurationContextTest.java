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
package org.seasar3.lookup;

import junit.framework.TestCase;

/**
 * @author higa
 * 
 */
public class ConfigurationContextTest extends TestCase {

    /**
     * Test method for {@link ConfigurationContext#ConfigurationContext(Class)}.
     */
    public void testConfigurationContext() {
        ConfigurationContext ctx = new ConfigurationContext(MyConfig.class);
        assertNotNull(ctx.getConfigurationClass());
        assertNotNull(ctx.getMethods());
        assertNotNull(ctx.getAnnotations());
    }

    /**
     * Test method for {@link ConfigurationContext#ConfigurationContext(Class)}.
     */
    public void testConfigurationContextForClassIsNull() {
        try {
            new ConfigurationContext(null);
            fail();
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }

    /**
     * Test method for {@link ConfigurationContext#getSharedValue(String)} and
     * {@link ConfigurationContext#setSharedValue(String, Object)}.
     */
    public void testSharedValue() {
        ConfigurationContext ctx = new ConfigurationContext(MyConfig.class);
        ctx.setSharedValue("aaa", "hoge");
        assertEquals("hoge", ctx.getSharedValue("aaa"));
    }
}
