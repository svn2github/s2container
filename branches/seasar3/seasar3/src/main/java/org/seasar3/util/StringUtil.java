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

/**
 * A utility class for {@link String}.
 * 
 * @author higa
 * @version 3.0
 */
public final class StringUtil {

    private StringUtil() {
    }

    /**
     * Returns true if text is empty.
     * 
     * @param text
     *            the text.
     * @return whether text is empty.
     */
    public static boolean isEmpty(String text) {
        return text == null || text.length() == 0;
    }

    /**
     * Decapitalizes the text according to the Java Bean specification.
     * 
     * @param text
     *            the text.
     * 
     * @return the decapitalized text.
     */
    public static String decapitalize(String text) {
        if (isEmpty(text)) {
            return text;
        }
        char chars[] = text.toCharArray();
        if (chars.length >= 2 && Character.isUpperCase(chars[0])
                && Character.isUpperCase(chars[1])) {
            return text;
        }
        chars[0] = Character.toLowerCase(chars[0]);
        return new String(chars);
    }
}