/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
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
package org.seasar.diigu;

import junit.framework.TestCase;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;

/**
 * @author manhole
 */
public class ProductCodeEnhanceTest extends TestCase {

    public void testConcreteClass() throws Exception {
        final BeanDesc beanDesc = BeanDescFactory
            .getBeanDesc(FooConcreteClass.class);
        final String[] parameterNames = beanDesc.getMethodParameterNames(
            "doSomething", new Class[] { String.class, Integer.class });
        assertNotNull(parameterNames);
        assertEquals("aaa", parameterNames[0]);
        assertEquals("bbbb", parameterNames[1]);
    }

    public void testInterface() throws Exception {
        final BeanDesc beanDesc = BeanDescFactory
            .getBeanDesc(FooInterface.class);
        final String[] parameterNames = beanDesc.getMethodParameterNames(
            "aaaaa", new Class[] { String.class, Double.class });
        assertNotNull(parameterNames);
        assertEquals("bb", parameterNames[0]);
        assertEquals("ccc", parameterNames[1]);
    }

}
