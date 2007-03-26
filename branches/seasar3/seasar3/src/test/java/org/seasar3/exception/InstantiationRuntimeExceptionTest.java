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

import junit.framework.TestCase;

/**
 * @author higa
 * 
 */
public class InstantiationRuntimeExceptionTest extends TestCase {

    /**
     * Test method for
     * {@link InstantiationRuntimeException#InstantiationRuntimeException(Class, InstantiationException)}.
     * 
     * @throws IllegalAccessException
     */
    public void testInstantiationRuntimeException()
            throws IllegalAccessException {
        try {
            Hoge.class.newInstance();
            fail();
        } catch (InstantiationException e) {
            InstantiationRuntimeException re = new InstantiationRuntimeException(
                    Hoge.class, e);
            System.out.println(re);
            assertEquals(Hoge.class, re.getTargetClass());
        }
    }

    private interface Hoge {

    }
}
