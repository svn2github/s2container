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

import java.lang.reflect.Method;

import javassist.ClassPool;
import javassist.CtClass;
import junit.framework.TestCase;

import org.seasar3.aop.ClassGenerator;
import org.seasar3.util.ClassPoolUtil;
import org.seasar3.util.CtClassUtil;

/**
 * @author higa
 * 
 */
public class SingletonCustomizerTest extends TestCase {

    private ClassGenerator generator;

    private SingletonCustomizer customizer = new SingletonCustomizer();

    @Override
    protected void setUp() throws Exception {
        setUpGenerator(MyConfig.class.getName());
    }

    protected void setUpGenerator(String className) throws Exception {
        ClassPool classPool = ClassPoolUtil.getClassPool();
        CtClass ctClass = CtClassUtil.create(classPool, className, className
                + System.nanoTime());
        generator = new ClassGenerator(classPool, ctClass);

    }

    /**
     * Test method for
     * {@link SingletonCustomizer#customize(ClassGenerator, Method, org.seasar3.lookup.Singleton)}.
     * 
     * @throws Exception
     */
    public void testCustomize() throws Exception {
        Method m = MyConfig.class.getDeclaredMethod("service", (Class[]) null);
        customizer.customize(generator, m);
        Class clazz = generator.generate();
        MyConfig config = (MyConfig) clazz.newInstance();
        assertSame(config.service(), config.service());
    }

    /**
     * Test method for
     * {@link SingletonCustomizer#customize(ClassGenerator, Method, org.seasar3.lookup.Singleton)}.
     * 
     * @throws Exception
     */
    public void testCustomizeForConstant() throws Exception {
        setUpGenerator(MyConstantConfig.class.getName());
        Method m = MyConstantConfig.class.getDeclaredMethod("aaa",
                (Class[]) null);
        customizer.customize(generator, m);
        Class clazz = generator.generate();
        MyConstantConfig config = (MyConstantConfig) clazz.newInstance();
        assertEquals(new Integer(1), config.aaa());
    }

    /**
     * Test method for {@link SingletonCustomizer#createMethodBody(Method)}.
     * 
     * @throws Exception
     */
    public void testCreateMethodBody() throws Exception {
        String body = customizer.createMethodBody("service");
        System.out.println(body);
        String expected = "{if ($$SINGLETON_VALUE_service == null) {  synchronized ($$SINGLETON_LOCK_service) {   if ($$SINGLETON_VALUE_service == null) $$SINGLETON_VALUE_service = super.service();  } } return ($r) $$SINGLETON_VALUE_service;}";
        assertEquals(expected, body);
    }

    /**
     * Test method for {@link SingletonCustomizer#getLockFieldName(String)}.
     * 
     * @throws Exception
     */
    public void testGetLockFieldName() throws Exception {
        assertEquals("$$SINGLETON_LOCK_service", customizer
                .getLockFieldName("service"));
    }

    /**
     * Test method for {@link SingletonCustomizer#getLockFieldSrc(String)}.
     * 
     * @throws Exception
     */
    public void testGetLockFieldSrc() throws Exception {
        assertEquals(
                "private static Object $$SINGLETON_LOCK_service = new Object();",
                customizer.getLockFieldSrc("service"));
    }

    /**
     * Test method for
     * {@link SingletonCustomizer#createLockField(ClassGenerator, String)}.
     * 
     * @throws Exception
     */
    public void testCreateLockField() throws Exception {
        assertNotNull(customizer.createLockField(generator, "service"));
    }

    /**
     * Test method for {@link SingletonCustomizer#getFieldName(String)}.
     * 
     * @throws Exception
     */
    public void testGetFieldName() throws Exception {
        assertEquals("$$SINGLETON_VALUE_service", customizer
                .getFieldName("service"));
    }

    /**
     * Test method for {@link SingletonCustomizer#getFieldSrc(String)}.
     * 
     * @throws Exception
     */
    public void testGetFieldSrc() throws Exception {
        assertEquals("private volatile Object $$SINGLETON_VALUE_service;",
                customizer.getFieldSrc("service"));
    }

    /**
     * Test method for
     * {@link SingletonCustomizer#createField(ClassGenerator, String)}.
     * 
     * @throws Exception
     */
    public void testCreateField() throws Exception {
        assertNotNull(customizer.createField(generator, "service"));
    }
}