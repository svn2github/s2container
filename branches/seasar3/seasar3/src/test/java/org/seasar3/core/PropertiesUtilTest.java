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

import org.seasar3.core.PropertiesUtil;

import junit.framework.TestCase;

/**
 * @author higa
 * 
 */
public class PropertiesUtilTest extends TestCase {

    /**
     * Test method for
     * {@link org.seasar3.core.PropertiesUtil#create(java.lang.String)}.
     */
    public void testCreateForPathIsNull() {
        try {
            PropertiesUtil.create(null);
            fail();
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }

    /**
     * Test method for
     * {@link org.seasar3.core.PropertiesUtil#create(java.lang.String)}.
     */
    public void testCreateForPathIsIllegal() {
        assertNull(PropertiesUtil.create("illegal path"));
    }

    /**
     * Test method for
     * {@link org.seasar3.core.PropertiesUtil#create(java.lang.String)}.
     */
    public void testCreate() {
        assertNotNull(PropertiesUtil.create(getClass().getName().replace('.',
                '/')
                + ".properties"));
    }
}
