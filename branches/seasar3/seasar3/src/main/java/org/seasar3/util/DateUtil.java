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
package org.seasar3.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.seasar3.exception.ParseRuntimeException;

/**
 * A utility class for {@link Date}.
 * 
 * @author higa
 * @version 3.0
 */
public final class DateUtil {

    private DateUtil() {
    }

    /**
     * Converts an object to the date value.
     * 
     * @param o
     *            an object
     * @return the date value
     */
    public static Date toDate(Object o) {
        if (o == null) {
            return null;
        } else if (o instanceof String) {
            return toDate((String) o, Locale.getDefault());
        } else if (o instanceof Date) {
            return (Date) o;
        } else if (o instanceof Calendar) {
            return ((Calendar) o).getTime();
        } else {
            return toDate(o.toString(), Locale.getDefault());
        }
    }

    /**
     * Converts text to the date value.
     * 
     * @param text
     *            the text
     * @param locale
     *            the locale.
     * @return the date value.
     */
    protected static Date toDate(String text, Locale locale) {
        if (StringUtil.isEmpty(text)) {
            return null;
        }
        try {
            DateFormat df = DateFormat
                    .getDateInstance(DateFormat.SHORT, locale);
            return df.parse(text);
        } catch (ParseException e) {
            throw new ParseRuntimeException(text, e);
        }
    }
}
