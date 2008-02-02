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
 * A utility class for {@link Short}.
 * 
 * @author higa
 * @version 3.0
 */
public final class ShortUtil {

    private ShortUtil() {
    }

    /**
     * Converts an object to the short value.
     * 
     * @param o
     *            an object.
     * @return the short value.
     */
    public static Short toShort(Object o) {
        if (o == null) {
            return null;
        } else if (o instanceof Short) {
            return (Short) o;
        } else if (o instanceof Number) {
            return Short.valueOf(((Number) o).shortValue());
        } else if (o instanceof String) {
            String s = (String) o;
            if (StringUtil.isEmpty(s)) {
                return null;
            }
            return Short.valueOf(s);
        } else if (o instanceof Boolean) {
            return ((Boolean) o).booleanValue() ? Short.valueOf((short) 1)
                    : Short.valueOf((short) 0);
        } else {
            return Short.valueOf(o.toString());
        }
    }

    /**
     * Converts an object to the primitive short value.
     * 
     * @param o
     *            an object.
     * @return the primitive short value.
     */
    public static short toPrimitiveShort(Object o) {
        Short s = toShort(o);
        if (s == null) {
            return 0;
        }
        return s.shortValue();
    }
}
