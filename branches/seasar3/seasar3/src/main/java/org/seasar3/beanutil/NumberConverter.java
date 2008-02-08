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

import org.seasar3.exception.CastRuntimeException;
import org.seasar3.util.NumberUtil;
import org.seasar3.util.StringUtil;

/**
 * A converter for number.
 * 
 * @author higa
 * @since 3.0
 */
public class NumberConverter implements Converter {

    /**
     * The pattern of number.
     */
    protected String pattern;

    /**
     * Constructor.
     * 
     * @param pattern
     *            the pattern of number.
     */
    public NumberConverter(String pattern) {
        if (StringUtil.isEmpty(pattern)) {
            throw new NullPointerException("pattern");
        }
        this.pattern = pattern;
    }

    public Object getAsObject(String value) {
        return NumberUtil.toNumber(value, pattern);
    }

    public String getAsString(Object value) {
        if (value == null) {
            return null;
        }
        if (!(value instanceof Number)) {
            throw new CastRuntimeException(value.getClass(), Number.class);
        }
        return NumberUtil.toString((Number) value, pattern);
    }

    public boolean isTarget(Class<?> clazz) {
        return Number.class.isAssignableFrom(clazz);
    }

}
