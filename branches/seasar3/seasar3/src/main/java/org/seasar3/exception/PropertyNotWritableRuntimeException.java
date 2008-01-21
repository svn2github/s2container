/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
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

/**
 * Thrown when an application tries to write the property, but it is not
 * writable.
 * 
 * @author higa
 * @since 3.0
 */
public class PropertyNotWritableRuntimeException extends SRuntimeException {

    static final long serialVersionUID = 1L;

    private Class<?> beanClass;

    private String propertyName;

    /**
     * Constructor.
     * 
     * @param beanClass
     *            the bean class.
     * @param propertyName
     *            the property name.
     */
    public PropertyNotWritableRuntimeException(Class<?> beanClass,
            String propertyName) {
        super("ES30010", beanClass.getName(), propertyName);
        this.beanClass = beanClass;
        this.propertyName = propertyName;
    }

    /**
     * Returns the bean class.
     * 
     * @return the bean class.
     */
    public Class<?> getBeanClass() {
        return beanClass;
    }

    /**
     * Returns the property name.
     * 
     * @return the property name.
     */
    public String getPropertyName() {
        return propertyName;
    }

}