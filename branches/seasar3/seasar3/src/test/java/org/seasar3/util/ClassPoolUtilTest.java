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

import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

/**
 * @author higa
 * 
 */
public class ClassPoolUtilTest extends JavassistTestCase {

    /**
     * Test method for {@link ClassPoolUtil#get(ClassPool, String)}.
     * 
     * @throws Exception
     */
    public void testGet() {
        assertNotNull(ClassPoolUtil.get(classPool, getClass().getName()));
    }

    /**
     * Test method for {@link ClassPoolUtil#get(ClassPool, String)}.
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
     * Test method for {@link ClassPoolUtil#get(ClassPool, String)}.
     * 
     */
    public void testGetForArray() {
        assertNotNull(ClassPoolUtil.get(classPool, new Object[0].getClass()
                .getName()));
    }

    /**
     * Test method for {@link ClassPoolUtil#getClassPool()}.
     * 
     */
    public void testGetClassPool() {
        assertNotNull(ClassPoolUtil.getClassPool());
    }

    /**
     * Test method for {@link ClassPoolUtil#getClassPool()}.
     * 
     */
    public void testGetClassPoolForCache() {
        assertSame(ClassPoolUtil.getClassPool(), ClassPoolUtil.getClassPool());
    }

    /**
     * Test method for {@link ClassPoolUtil#toCtClass(ClassPool, Class)}.
     * 
     */
    public void testToCtClass() {
        assertNotNull(ClassPoolUtil.toCtClass(classPool, getClass()));
    }

    /**
     * Test method for {@link ClassPoolUtil#toCtClass(ClassPool, Class)}.
     * 
     */
    public void testToCtClassArray() {
        assertNotNull(ClassPoolUtil.toCtClassArray(classPool,
                new Class[] { String.class }));
    }

    /**
     * Test method for
     * {@link ClassPoolUtil#getAndRename(ClassPool, String, String)}.
     * 
     * @throws NotFoundException
     * 
     */
    public void testGetAndRename() throws NotFoundException {
        CtClass ctClass = ClassPoolUtil.getAndRename(classPool, getClass()
                .getName(), "Hoge");
        assertNotNull(ctClass);
        assertEquals("Hoge", ctClass.getName());
    }
}