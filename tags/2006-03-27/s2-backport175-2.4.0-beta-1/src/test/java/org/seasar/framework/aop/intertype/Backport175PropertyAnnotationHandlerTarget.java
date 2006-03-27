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
package org.seasar.framework.aop.intertype;

/**
 * @author y-komori
 * 
 * @org.seasar.framework.container.annotation.backport175.Property("none")
 */
public class Backport175PropertyAnnotationHandlerTarget implements PropertyInterTypeTarget {
    /**
     * @org.seasar.framework.container.annotation.backport175.Property("read")
     */
    int intReadField_ = 123;

    /**
     * @org.seasar.framework.container.annotation.backport175.Property("write")
     */
    int intWriteField_;

    /**
     * @org.seasar.framework.container.annotation.backport175.Property("readwrite")
     */
    int intReadWriteField_;

    /**
     * @org.seasar.framework.container.annotation.backport175.Property("none")
     */
    int intNoneField_;

    /**
     * @org.seasar.framework.container.annotation.backport175.Property("readwrite")
     */
    public int publicField_;

    /**
     * @org.seasar.framework.container.annotation.backport175.Property("readwrite")
     */
    protected int protectedField_;

    /**
     * @org.seasar.framework.container.annotation.backport175.Property("readwrite")
     */
    private int privateField_;

    int nonAnnotatedField_;

    /**
     * @org.seasar.framework.container.annotation.backport175.Property("readwrite")
     */
    Object objectField_;

    /**
     * @org.seasar.framework.container.annotation.backport175.Property("readwrite")
     */
    String[] stringArrayField_;

    /**
     * @org.seasar.framework.container.annotation.backport175.Property("readwrite")
     */
    long[][] longArrayField_;

    public int getIntWriteField() {
        return intWriteField_;
    }
}
