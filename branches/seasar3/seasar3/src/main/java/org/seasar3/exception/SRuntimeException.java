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

import org.seasar3.message.MessageBuilder;

/**
 * A base exception class of Seasar3.
 * 
 * @author higa
 * @since 3.0
 */
public class SRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String messageCode;

    private Object[] args;

    /**
     * Constructor.
     * 
     * @param messageCode
     *            the message code.
     * @param args
     *            the arguments.
     */
    public SRuntimeException(String messageCode, Object... args) {
        this(null, messageCode, args);
    }

    /**
     * Constructor.
     * 
     * @param cause
     *            the cause.
     * @param messageCode
     *            the message code.
     * @param args
     *            the arguments.
     */
    public SRuntimeException(Throwable cause, String messageCode,
            Object... args) {

        super(MessageBuilder.getMessage(messageCode, args), cause);
        this.messageCode = messageCode;
        this.args = args;
    }

    /**
     * Returns the message code.
     * 
     * @return the message code.
     */
    public String getMessageCode() {
        return messageCode;
    }

    /**
     * Returns the arguments.
     * 
     * @return the arguments.
     */
    public Object[] getArgs() {
        return args;
    }
}