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

import java.util.Properties;

import junit.framework.TestCase;

/**
 * @author higa
 * 
 */
public class DefaultMessageResourceBundleTest extends TestCase {

    /**
     * Test method for {@link DefaultMessageResourceBundle#get(String)}.
     */
    public void testGetForKeyIsNull() {
        DefaultMessageResourceBundle mrb = new DefaultMessageResourceBundle(
                null);
        assertNull(mrb.get(null));
    }

    /**
     * Test method for {@link DefaultMessageResourceBundle#get(String)}.
     */
    public void testGetForKeyExists() {
        Properties props = new Properties();
        props.put("aaa", "hoge");
        DefaultMessageResourceBundle mrb = new DefaultMessageResourceBundle(
                props);
        assertEquals("hoge", mrb.get("aaa"));
    }

    /**
     * Test method for {@link DefaultMessageResourceBundle#get(String)}.
     */
    public void testGetForKeyExistsAndValueIsNull() {
        try {
            Properties props = new Properties();
            props.put("aaa", null);
            fail();
        } catch (NullPointerException ignore) {
        }
    }

    /**
     * Test method for {@link DefaultMessageResourceBundle#get(String)}.
     */
    public void testGetForKeyDoesNotExist() {
        Properties props = new Properties();
        props.put("aaa", "hoge");
        DefaultMessageResourceBundle mrb = new DefaultMessageResourceBundle(
                props);
        assertNull(mrb.get("bbb"));
    }

    /**
     * Test method for {@link DefaultMessageResourceBundle#get(String)}.
     */
    public void testGetForParentHasSameKey() {
        Properties parentProps = new Properties();
        parentProps.put("aaa", "hogeParent");
        DefaultMessageResourceBundle parent = new DefaultMessageResourceBundle(
                parentProps);

        Properties selfProps = new Properties();
        selfProps.put("aaa", "hogeSelf");
        DefaultMessageResourceBundle self = new DefaultMessageResourceBundle(
                selfProps, parent);
        assertEquals("hogeSelf", self.get("aaa"));
    }

    /**
     * Test method for {@link DefaultMessageResourceBundle#get(String)}.
     */
    public void testGetForParentHasDifferentKey() {
        Properties parentProps = new Properties();
        parentProps.put("aaa", "hogeParent");
        DefaultMessageResourceBundle parent = new DefaultMessageResourceBundle(
                parentProps);

        Properties selfProps = new Properties();
        selfProps.put("bbb", "hogeSelf");
        DefaultMessageResourceBundle self = new DefaultMessageResourceBundle(
                selfProps, parent);
        assertEquals("hogeParent", self.get("aaa"));
    }
}