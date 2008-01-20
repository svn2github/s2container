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

import java.text.ParseException;

/**
 * Wrapper runtime exception for {@link ParseException}.
 * 
 * @author higa
 * @since 3.0
 */
public class ParseRuntimeException extends SRuntimeException {

    static final long serialVersionUID = 1L;

    private String text;

    /**
     * Constructor.
     * 
     * @param text
     *            the text.
     * @param cause
     *            the cause.
     */
    public ParseRuntimeException(String text, ParseException cause) {
        super(cause, "ES30007", text, cause);
        this.text = text;
    }

    /**
     * Returns the text.
     * 
     * @return the text.
     */
    public String getText() {
        return text;
    }
}