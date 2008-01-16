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

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.seasar3.lookup.Prototype;
import org.seasar3.lookup.Singleton;

/**
 * @author higa
 * 
 */
public class MultipleConfigurationAnnotationsExceptionTest extends TestCase {

    /**
     * Test method for
     * {@link MultipleConfigurationAnnotationsException#MultipleConfigurationAnnotationsException(String, java.util.List)}.
     * 
     * @throws Exception
     */
    public void testNotAnnotatedException() throws Exception {
        Method m = getClass().getMethod("testNotAnnotatedException",
                (Class[]) null);
        List<Class<? extends Annotation>> classes = new ArrayList<Class<? extends Annotation>>();
        classes.add(Singleton.class);
        classes.add(Prototype.class);
        MultipleConfigurationAnnotationsException e = new MultipleConfigurationAnnotationsException(
                m.toString(), classes);
        System.out.println(e);
        assertEquals(m.toString(), e.getTarget());
        assertEquals(classes, e.getAnnotationClasses());
    }
}