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
package org.seasar3.util;

import junit.framework.TestCase;

/**
 * @author higa
 * 
 */
public class StringUtilTest extends TestCase {

    /**
     * @throws Exception
     */
    public void testIsEmptyForNull() throws Exception {
        assertTrue(StringUtil.isEmpty(null));
    }

    /**
     * @throws Exception
     */
    public void testIsEmptyForEmptyString() throws Exception {
        assertTrue(StringUtil.isEmpty(""));
    }

    /**
     * @throws Exception
     */
    public void testIsEmptyForNotEmptyString() throws Exception {
        assertFalse(StringUtil.isEmpty("a"));
    }

    /**
     * @throws Exception
     */
    public void testDecapitalize() throws Exception {
        assertEquals("aaa", StringUtil.decapitalize("Aaa"));
    }

    /**
     * @throws Exception
     */
    public void testDecapitalizeForOneCharacter() throws Exception {
        assertEquals("a", StringUtil.decapitalize("A"));
    }

    /**
     * @throws Exception
     */
    public void testDecapitalizeForEmpty() throws Exception {
        assertEquals("", StringUtil.decapitalize(""));
    }

    /**
     * @throws Exception
     */
    public void testDecapitalizeForNoDecapitalize() throws Exception {
        assertEquals("URL", StringUtil.decapitalize("URL"));
    }
}