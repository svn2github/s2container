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
package org.seasar3.core;

/**
 * A base exception class of Seasar3.
 * 
 * @author higa
 * 
 */
public class SRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String messageCode;

    private Object[] args;

    /**
     * Creates {@link SRuntimeException}.
     * 
     * @param messageCode
     */
    public SRuntimeException(String messageCode) {
        this(null, messageCode, (Object[]) null);
    }

    /**
     * Creates {@link SRuntimeException}.
     * 
     * @param messageCode
     * @param args
     */
    public SRuntimeException(String messageCode, Object... args) {
        this(null, messageCode, args);
    }

    /**
     * Creates {@link SRuntimeException}.
     * 
     * @param cause
     * @param messageCode
     * @param args
     */
    public SRuntimeException(Throwable cause, String messageCode,
            Object... args) {

        super(MessageBuilder.getMessage(messageCode, args), cause);
        this.messageCode = messageCode;
        this.args = args;
    }

    /**
     * Returns message code.
     * 
     * @return
     */
    public String getMessageCode() {
        return messageCode;
    }

    /**
     * Returns arguments.
     * 
     * @return
     */
    public Object[] getArgs() {
        return args;
    }
}