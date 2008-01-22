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
 * A utility class for {@link Long}.
 * 
 * @author higa
 * @version 3.0
 */
public final class LongUtil {

    private LongUtil() {
    }

    /**
     * Converts an object to the long value.
     * 
     * @param o
     *            an object.
     * @return the long value.
     */
    public static Long toLong(Object o) {
        if (o == null) {
            return null;
        } else if (o instanceof Long) {
            return (Long) o;
        } else if (o instanceof Number) {
            return Long.valueOf(((Number) o).longValue());
        } else if (o instanceof String) {
            String s = (String) o;
            if (StringUtil.isEmpty(s)) {
                return null;
            }
            return Long.valueOf(s);
        } else if (o instanceof Boolean) {
            return ((Boolean) o).booleanValue() ? Long.valueOf(1) : Long
                    .valueOf(0);
        } else {
            return Long.valueOf(o.toString());
        }
    }

    /**
     * Converts an object to the primitive long value.
     * 
     * @param o
     *            an object.
     * @return the primitive long value.
     */
    public static long toPrimitiveLong(Object o) {
        if (o == null) {
            return 0;
        }
        return toLong(o).longValue();
    }
}
