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

import java.lang.reflect.Method;

import junit.framework.TestCase;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;

/**
 * @author manhole
 */
public class JapaneseNamingTest extends TestCase {

    public void testJapaneseNaming1() throws Exception {
        final BeanDesc beanDesc = BeanDescFactory
            .getBeanDesc(JapaneseNamingInterface.class);
        final String[] parameterNames = beanDesc.getMethodParameterNames(
            "何かする", new Class[] { String.class, Double.class });
        assertNotNull(parameterNames);
        assertEquals("引数1", parameterNames[0]);
        assertEquals("引数2", parameterNames[1]);
    }

    public void testJapaneseNaming2() throws Exception {
        final BeanDesc beanDesc = BeanDescFactory
            .getBeanDesc(JapaneseNamingInterface.class);
        final Method method = beanDesc.getMethod("何かする", new Class[] {
            String.class, Double.class });
        assertNotNull(method);
        final String[] parameterNames = beanDesc
            .getMethodParameterNames(method);
        assertNotNull(parameterNames);
        assertEquals("引数1", parameterNames[0]);
        assertEquals("引数2", parameterNames[1]);
    }

}
