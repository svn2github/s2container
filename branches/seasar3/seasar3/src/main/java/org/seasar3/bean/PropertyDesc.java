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
package org.seasar3.bean;

import java.lang.reflect.Method;

/**
 * A PropertyDesc provides information about a "property".
 * 
 * @author higa
 * 
 */
public class PropertyDesc {

    private String name;

    private Class type;

    private Method readMethod;

    private Method writeMethod;

    /**
     * Returns name.
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns type.
     * 
     * @return type
     */
    public Class getType() {
        return type;
    }

    /**
     * Sets type.
     * 
     * @param type
     */
    public void setType(Class type) {
        this.type = type;
    }

    /**
     * Returns method to read.
     * 
     * @return readMethod
     */
    public Method getReadMethod() {
        return readMethod;
    }

    /**
     * Sets method to read.
     * 
     * @param readMethod
     */
    public void setReadMethod(Method readMethod) {
        this.readMethod = readMethod;
    }

    /**
     * Returns method to write.
     * 
     * @return writeMethod
     */
    public Method getWriteMethod() {
        return writeMethod;
    }

    /**
     * Sets method to write.
     * 
     * @param writeMethod
     */
    public void setWriteMethod(Method writeMethod) {
        this.writeMethod = writeMethod;
    }
}