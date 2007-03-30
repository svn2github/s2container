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

import org.seasar3.core.SRuntimeException;

/**
 * An NotAnnotatedException is thrown when a class is not annotated by an
 * annotation.
 * 
 * @author higa
 * @since 3.0
 */
public class NotAnnotatedException extends SRuntimeException {

    static final long serialVersionUID = 1L;

    private Class targetClass;

    private Class<? extends Annotation> annotationClass;

    /**
     * Creates new {@link NotAnnotatedException}.
     * 
     * @param targetClass
     * @param annotationClass
     * 
     */
    public NotAnnotatedException(Class targetClass,
            Class<? extends Annotation> annotationClass) {
        super("ES30005", targetClass.getName(), annotationClass.getName());
        this.targetClass = targetClass;
        this.annotationClass = annotationClass;
    }

    /**
     * Returns the target class.
     * 
     * @return the target class
     */
    public Class getTargetClass() {
        return targetClass;
    }

    /**
     * Returns the annotation class.
     * 
     * @return the annotation Class
     */
    public Class<? extends Annotation> getAnnotationClass() {
        return annotationClass;
    }
}
