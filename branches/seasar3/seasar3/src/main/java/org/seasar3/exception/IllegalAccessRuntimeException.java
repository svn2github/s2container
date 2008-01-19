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


/**
 * Wrapper runtime exception for {@link IllegalAccessException}.
 * 
 * @author higa
 * @since 3.0
 */
public class IllegalAccessRuntimeException extends SRuntimeException {

    static final long serialVersionUID = 1L;

    private Class targetClass;

    /**
     * Creates new {@link IllegalAccessRuntimeException}.
     * 
     * @param targetClass
     * @param cause
     */
    public IllegalAccessRuntimeException(Class targetClass,
            IllegalAccessException cause) {

        super(cause, "ES30002", new Object[] { targetClass.getName(),
                cause.getMessage() });
        this.targetClass = targetClass;
    }

    /**
     * Returns target class.
     * 
     * @return
     */
    public Class getTargetClass() {
        return targetClass;
    }
}
