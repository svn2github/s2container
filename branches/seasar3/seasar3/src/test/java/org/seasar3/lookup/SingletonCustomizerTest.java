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
        ClassPool classPool = ClassPoolUtil.getClassPool();
        CtClass ctClass = CtClassUtil.create(classPool, MyConfig.class
                .getName(), MyConfig.class.getName()
                + System.currentTimeMillis());
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
        customizer.customize(generator, m, null);
        Class clazz = generator.generate();
        MyConfig config = (MyConfig) clazz.newInstance();
        assertSame(config.service(), config.service());
    }

    /**
     * Test method for {@link SingletonCustomizer#createField(ClassGenerator)}.
     * 
     * @throws Exception
     */
    public void testCreateField() throws Exception {
        assertNotNull(customizer.createField(generator));
    }

    /**
     * Test method for {@link SingletonCustomizer#createMethodBody(Method)}.
     * 
     * @throws Exception
     */
    public void testCreateMethodBody() throws Exception {
        Method m = MyConfig.class.getDeclaredMethod("service", (Class[]) null);
        String body = customizer.createMethodBody(m);
        System.out.println(body);
        String expected = "{Object ret = $$SINGLETON_VALUES.get(\"service\");if (ret != null) return ret;ret = super.service();$$SINGLETON_VALUES.put(\"service\",ret);return ret;}";
        assertEquals(expected, body);
    }
}