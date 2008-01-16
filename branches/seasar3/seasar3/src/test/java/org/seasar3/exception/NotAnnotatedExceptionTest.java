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
package org.seasar3.exception;

import java.lang.annotation.Target;

import junit.framework.TestCase;

import org.seasar3.lookup.ConfigurationCustomization;

/**
 * @author higa
 * 
 */
public class NotAnnotatedExceptionTest extends TestCase {

    /**
     * Test method for
     * {@link NotAnnotatedException#NotAnnotatedException(Class, Class)}.
     */
    public void testNotAnnotatedException() {
        NotAnnotatedException e = new NotAnnotatedException(Target.class,
                ConfigurationCustomization.class);
        System.out.println(e);
        assertEquals(Target.class, e.getTargetClass());
        assertEquals(ConfigurationCustomization.class, e.getAnnotationClass());
    }
}