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

import javassist.NotFoundException;

/**
 * Wrapper runtime exception for <code>javassist.NotFoundException</code>.
 * 
 * @author higa
 * @since 3.0
 */
public class NotFoundRuntimeException extends SRuntimeException {

    static final long serialVersionUID = 1L;

    private String targetClassName;

    /**
     * Constructor.
     * 
     * @param targetClassName
     *            the target class name.
     * @param cause
     *            the cause.
     */
    public NotFoundRuntimeException(String targetClassName,
            NotFoundException cause) {
        super(cause, "ES30003", targetClassName);
        this.targetClassName = targetClassName;
    }

    /**
     * Returns the target class name.
     * 
     * @return the target class name.
     */
    public String getTargetClassName() {
        return targetClassName;
    }
}
