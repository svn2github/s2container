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
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewConstructor;
import javassist.NotFoundException;

/**
 * @author higa
 * 
 */
public class CtClassUtilTest extends JavassistTestCase {

    /**
     * Test method for {@link CtClassUtil#create(ClassPool, String, String)}.
     * 
     * @throws NotFoundException
     * 
     */
    public void testCreate() throws NotFoundException {
        CtClass ctClass = CtClassUtil.create(classPool, getClass().getName(),
                "Hoge");
        assertNotNull(ctClass);
        assertEquals("Hoge", ctClass.getName());
        assertEquals(getClass().getName(), ctClass.getSuperclass().getName());
    }

    /**
     * Test method for
     * {@link CtClassUtil#setSuperclass(ClassPool, CtClass, String)}.
     * 
     * @throws NotFoundException
     * 
     */
    public void testSetSuperclass() throws NotFoundException {
        CtClass ctClass = ClassPoolUtil.get(classPool, getClass().getName());
        CtClassUtil.setSuperclass(classPool, ctClass, getClass().getName());
        assertEquals(getClass().getName(), ctClass.getSuperclass().getName());
    }

    /**
     * Test method for
     * {@link CtClassUtil#addConstructor(CtClass, javassist.CtConstructor)}.
     * 
     * @throws Exception
     * 
     */
    public void testAddConstuctor() throws Exception {
        CtClass ctClass = ClassPoolUtil.get(classPool, getClass().getName());
        CtConstructor ctConstructor = CtNewConstructor.make(
                new CtClass[] { classPool.get("int") }, null, ctClass);
        CtClassUtil.addConstructor(ctClass, ctConstructor);
    }

    /**
     * Test method for
     * {@link CtClassUtil#getDeclaredMethod(CtClass, String, CtClass[])}.
     * 
     * @throws Exception
     * 
     */
    public void testGetDeclaredMethod() throws Exception {
        CtClass ctClass = ClassPoolUtil.get(classPool, getClass().getName());
        assertNotNull(CtClassUtil.getDeclaredMethod(ctClass,
                "testGetDeclaredMethod", null));
    }

    /**
     * Test method for
     * {@link CtClassUtil#addMethod(CtClass, javassist.CtMethod)}.
     * 
     * @throws Exception
     * 
     */
    public void testAddMethod() throws Exception {
        CtClass ctClass = ClassPoolUtil.get(classPool, getClass().getName());
        CtMethod ctMethod = CtNewMethodUtil
                .make("public void foo(){}", ctClass);
        CtClassUtil.addMethod(ctClass, ctMethod);
    }

    /**
     * Test method for {@link CtClassUtil#addField(CtClass, javassist.CtField)}.
     * 
     * @throws Exception
     * 
     */
    public void testAddField() throws Exception {
        CtClass ctClass = ClassPoolUtil.get(classPool, getClass().getName());
        CtField ctField = CtFieldUtil.make("private int aaa;", ctClass);
        CtClassUtil.addField(ctClass, ctField);
    }

    /**
     * Test method for {@link CtClassUtil#toClass(CtClass)}.
     * 
     * @throws Exception
     * 
     */
    public void testToClass() throws Exception {
        CtClass ctClass = ClassPoolUtil.get(classPool, getClass().getName());
        ctClass.setName(getClass().getName() + System.currentTimeMillis());
        assertNotNull(CtClassUtil.toClass(ctClass));
    }
}