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
package org.seasar3.beanutil;

/**
 * An interface that converts string and object.
 * 
 * @author higa
 * @since 3.0
 * 
 */
public interface Converter {

    /**
     * Returns the string value.
     * 
     * @param value
     *            the value.
     * @return the string value.
     */
    String getAsString(Object value);

    /**
     * Returns the object value.
     * 
     * @param value
     *            the value.
     * @return the object value.
     */
    Object getAsObject(String value);

    /**
     * Determines if the class is target.
     * 
     * @param clazz
     *            the class.
     * @return whether the class is target.
     */
    boolean isTarget(Class<?> clazz);
}
