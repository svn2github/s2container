/*
 * Copyright 2004-2005 the Seasar Foundation and the Others.
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
package org.seasar.framework.aop.javassist;

import java.net.URL;
import java.net.URLClassLoader;

import junit.framework.TestCase;

/**
 * @author koichik
 */
public class ClassLoaderUtilTest extends TestCase {
    public void testGetClassLoader() throws Exception {
        assertSame("1", ClassLoaderUtil.class.getClassLoader(), ClassLoaderUtil
                .getClassLoader(Object.class));

        assertSame("2", TestCase.class.getClassLoader(), ClassLoaderUtil
                .getClassLoader(TestCase.class));

        ClassLoader context = Thread.currentThread().getContextClassLoader();
        try {
            ClassLoader cl = new URLClassLoader(new URL[0], getClass()
                    .getClassLoader());
            Thread.currentThread().setContextClassLoader(cl);
            assertSame("3", cl, ClassLoaderUtil.getClassLoader(TestCase.class));
        } finally {
            Thread.currentThread().setContextClassLoader(context);
        }
    }
}
