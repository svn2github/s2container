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
 * @org.seasar.framework.container.annotation.backport175.InterType({"aop.propertyInterType"})
 */
public class Backport175PropertyAnnotationHandlerTarget2 {
    int defaultField;

    /**
     * @org.seasar.framework.container.annotation.backport175.Property("read")
     */
    int readField;

    /**
     * @org.seasar.framework.container.annotation.backport175.Property("write")
     */
    int writeField;

    /**
     * @org.seasar.framework.container.annotation.backport175.Property("readwrite")
     */
    int readWriteField;

    /**
     * @org.seasar.framework.container.annotation.backport175.Property("none")
     */
    int noneField;

    int hasGetter;

    int hasSetter;

    int hasGetterSetter;

    public Backport175PropertyAnnotationHandlerTarget2() {
    }

    public int getHasGetter() {
        return hasGetter;
    }

    public void setHasSetter(int hasSetter) {
        this.hasSetter = hasSetter;
    }

    public int getHasGetterSetter() {
        return hasGetterSetter;
    }

    public void setHasGetterSetter(int hasGetterSetter) {
        this.hasGetterSetter = hasGetterSetter;
    }
}
