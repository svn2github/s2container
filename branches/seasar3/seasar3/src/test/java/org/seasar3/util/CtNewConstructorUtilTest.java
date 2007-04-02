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
public class CtNewConstructorUtilTest extends JavassistTestCase {

    /**
     * Test method for
     * {@link CtNewConstructorUtil#defaultConstructor(javassist.CtClass)}.
     */
    public void testDefaultConstructor() {
        CtClass ctClass = ClassPoolUtil.get(classPool, getClass().getName());
        assertNotNull(CtNewConstructorUtil.defaultConstructor(ctClass));
    }

    /**
     * Test method for
     * {@link CtNewConstructorUtil#make(CtClass[], CtClass[], CtClass)}.
     * 
     * @throws Exception
     */
    public void testMake() throws Exception {
        CtClass ctClass = ClassPoolUtil.get(classPool, getClass().getName());
        CtClass[] paramTypes = new CtClass[] { classPool.get("int") };
        assertNotNull(CtNewConstructorUtil.make(paramTypes, null, ctClass));
    }
}