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
package org.seasar3.util;

import javassist.CtClass;

/**
 * @author higa
 * 
 */
public class ClassPoolUtilTest extends AbstJavassistTestCase {

    /**
     * 
     * @throws Exception
     */
    public void testGet() {
        assertNotNull(ClassPoolUtil.get(classPool, getClass().getName()));
    }

    /**
     * 
     */
    public void testGetForIllegalClassName() {
        try {
            ClassPoolUtil.get(classPool, "...");
            fail();
        } catch (RuntimeException e) {
            System.out.println(e);
        }
    }

    /**
     * 
     */
    public void testGetForArray() {
        assertNotNull(ClassPoolUtil.get(classPool, new Object[0].getClass()
                .getName()));
    }

    /**
     * 
     */
    public void testGetClassPool() {
        assertNotNull(ClassPoolUtil.getClassPool());
    }

    /**
     * 
     */
    public void testGetClassPoolForCache() {
        assertSame(ClassPoolUtil.getClassPool(), ClassPoolUtil.getClassPool());
    }

    /**
     * 
     */
    public void testToCtClass() {
        assertNotNull(ClassPoolUtil.toCtClass(classPool, getClass()));
    }

    /**
     * 
     */
    public void testToCtClassArray() {
        assertNotNull(ClassPoolUtil.toCtClassArray(classPool,
                new Class[] { String.class }));
    }

    /**
     * 
     */
    public void testGetAndRename() {
        CtClass ctClass = ClassPoolUtil.getAndRename(classPool, getClass()
                .getName(), "Hoge");
        assertNotNull(ctClass);
        assertEquals("Hoge", ctClass.getName());
    }
}