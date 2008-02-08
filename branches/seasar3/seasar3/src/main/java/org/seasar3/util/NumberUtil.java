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

import java.text.DecimalFormat;
import java.text.ParseException;

import org.seasar3.exception.ParseRuntimeException;

/**
 * A utility class for {@link Number}.
 * 
 * @author higa
 * @version 3.0
 */
public final class NumberUtil {

    private NumberUtil() {
    }

    /**
     * Converts text to the number value.
     * 
     * @param text
     *            the text
     * @param pattern
     *            the pattern.
     * @return the number value.
     */
    public static Number toNumber(String text, String pattern) {
        if (StringUtil.isEmpty(text)) {
            return null;
        }
        if (StringUtil.isEmpty(pattern)) {
            throw new NullPointerException("pattern");
        }
        try {
            DecimalFormat df = new DecimalFormat(pattern);
            return df.parse(text);
        } catch (ParseException e) {
            throw new ParseRuntimeException(text, e);
        }
    }

    /**
     * Converts the number value to text.
     * 
     * @param number
     *            the number value.
     * @param pattern
     *            the pattern.
     * @return the text.
     */
    public static String toString(Number number, String pattern) {
        if (number == null) {
            return null;
        }
        if (StringUtil.isEmpty(pattern)) {
            throw new NullPointerException("pattern");
        }
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(number);
    }
}
