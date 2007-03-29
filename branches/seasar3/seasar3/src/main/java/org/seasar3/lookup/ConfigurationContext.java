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
package org.seasar3.lookup;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * A context class for configuration.
 * 
 * @author higa
 * 
 */
public class ConfigurationContext {

    private Class configurationClass;

    private Method[] methods;

    private Annotation[] annotations;

    /**
     * Creates new <code>ConfigurationContext</code>
     * 
     * @param configurationClass
     */
    public ConfigurationContext(Class configurationClass) {
        if (configurationClass == null) {
            throw new NullPointerException("configurationClass");
        }
        this.configurationClass = configurationClass;
        methods = configurationClass.getMethods();
        annotations = configurationClass.getAnnotations();
    }

    /**
     * @return the configurationClass
     */
    public Class getConfigurationClass() {
        return configurationClass;
    }

    /**
     * @return the annotations
     */
    public Annotation[] getAnnotations() {
        return annotations;
    }

    /**
     * @return the methods
     */
    public Method[] getMethods() {
        return methods;
    }
}
