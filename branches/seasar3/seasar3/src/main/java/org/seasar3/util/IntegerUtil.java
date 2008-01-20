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
 * A utility class for {@link Integer}.
 * 
 * @author higa
 * @version 3.0
 */
public final class IntegerUtil {

    private IntegerUtil() {
    }

    /**
     * Converts an object to the integer value.
     * 
     * @param o
     *            an object.
     * @return the integer value.
     */
    public static Integer toInteger(Object o) {
        if (o == null) {
            return null;
        } else if (o instanceof Integer) {
            return (Integer) o;
        } else if (o instanceof Number) {
            return Integer.valueOf(((Number) o).intValue());
        } else if (o instanceof String) {
            return Integer.valueOf((String) o);
        } else if (o instanceof Boolean) {
            return ((Boolean) o).booleanValue() ? Integer.valueOf(1) : Integer
                    .valueOf(0);
        } else {
            return Integer.valueOf(o.toString());
        }
    }

    /**
     * Converts an object to the primitive integer value.
     * 
     * @param o
     *            an object.
     * @return the primitive int value.
     */
    public static int toPrimitiveInt(Object o) {
        if (o == null) {
            return 0;
        }
        return toInteger(o).intValue();
    }
}
