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

import java.util.Date;

import org.seasar3.exception.CastRuntimeException;
import org.seasar3.util.DateUtil;
import org.seasar3.util.StringUtil;

/**
 * The converter for date.
 * 
 * @author higa
 * @since 3.0
 */
public class DateConverter implements Converter {

    /**
     * The pattern of date.
     */
    protected String pattern;

    /**
     * Constructor.
     * 
     * @param pattern
     *            the pattern of date.
     */
    public DateConverter(String pattern) {
        if (StringUtil.isEmpty(pattern)) {
            throw new NullPointerException("pattern");
        }
        this.pattern = pattern;
    }

    public Object getAsObject(String value) {
        return DateUtil.toDate(value, pattern);
    }

    public String getAsString(Object value) {
        if (value == null) {
            return null;
        }
        if (!(value instanceof Date)) {
            throw new CastRuntimeException(value.getClass(), Date.class);
        }
        return DateUtil.toString((Date) value, pattern);
    }

    public boolean isTarget(Class<?> clazz) {
        return clazz == Date.class;
    }

}
