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
 * 
 * This class describes one parameterized class.
 * 
 * @author koichik
 * @author higa
 * @since 3.0
 */
public final class ParameterizedClassDesc {

    private Class<?> rawClass;

    private ParameterizedClassDesc[] arguments;

    /**
     * Constructor.
     * 
     * @param rawClass
     *            the row class.
     * @param arguments
     *            the array of {@link ParameterizedClassDesc}.
     */
    public ParameterizedClassDesc(Class<?> rawClass,
            ParameterizedClassDesc[] arguments) {
        this.rawClass = rawClass;
        this.arguments = arguments;
    }

    /**
     * Determines if the property is a parameterized class. Returns true if the
     * property is a parameterized class.
     * 
     * @return whether the property is a parameterized class.
     */
    public boolean isParameterizedClass() {
        return arguments != null;
    }

    /**
     * Returns the raw class.
     * 
     * @return the raw class.
     */
    public Class<?> getRawClass() {
        return rawClass;
    }

    /**
     * Returns the arguments.
     * 
     * @return the arguments.
     */
    public ParameterizedClassDesc[] getArguments() {
        return arguments;
    }
}