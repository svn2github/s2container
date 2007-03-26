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
package org.seasar3.aop;

import java.io.Serializable;

import javassist.ClassPool;
import javassist.CtClass;
import junit.framework.TestCase;

import org.seasar3.util.ClassPoolUtil;
import org.seasar3.util.CtClassUtil;

/**
 * @author higa
 * 
 */
public class ClassGeneratorTest extends TestCase {

    private ClassGenerator generator;

    @Override
    protected void setUp() throws Exception {
        ClassPool classPool = ClassPoolUtil.getClassPool();
        CtClass ctClass = CtClassUtil.create(classPool, getClass().getName(),
                getClass().getName() + "2");
        generator = new ClassGenerator(classPool, ctClass);

    }

    /**
     * Test method for {@link ClassGenerator#toCtClassArray(Class)}.
     */
    public void testToCtClassClass() {
        assertNotNull(generator.toCtClass(int.class));
    }

    /**
     * Test method for {@link ClassGenerator#toCtClassArray(Class)}.
     */
    public void testToCtClassClassArray() {
        assertNotNull(generator.toCtClassArray(new Class[] { int.class }));
    }

    /**
     * Test method for {@link ClassGenerator#setInterface(Class)}.
     */
    public void testSetInterface() {
        generator.setInterface(Serializable.class);
    }

    /**
     * Test method for {@link ClassGenerator#setInterfaces(Class[])}.
     */
    public void testSetInterfaces() {
        generator.setInterfaces(new Class[] { Serializable.class });
    }

    /**
     * Test method for {@link ClassGenerator#createDefaultConstructor()}.
     */
    public void testCreateDefaultConstructor() {
        ClassPool classPool = ClassPoolUtil.getClassPool();
        CtClass ctClass = CtClassUtil.create(classPool, Hoge.class.getName(),
                Hoge.class.getName() + "2");
        generator = new ClassGenerator(classPool, ctClass);
        assertNotNull(generator.createDefaultConstructor());
    }

    /**
     * Test method for {@link ClassGenerator#createConstructor()}.
     */
    public void testCreateConstructor() {
        assertNotNull(generator.createConstructor(new Class[] { int.class },
                null));
    }

    /**
     * Test method for
     * {@link ClassGenerator#getDeclaredMethod(String, CtClass[])}.
     */
    public void testGetDeclaredMethod() {
        assertNotNull(generator
                .getDeclaredMethod("testGetDeclaredMethod", null));
    }

    /**
     * Test method for {@link ClassGenerator#createMethod(String)}.
     */
    public void testCreateMethod() {
        assertNotNull(generator.createMethod("public void foo(){}"));
    }

    private static class Hoge {
        @SuppressWarnings("unused")
        private Hoge(int i) {
        }
    }
}
