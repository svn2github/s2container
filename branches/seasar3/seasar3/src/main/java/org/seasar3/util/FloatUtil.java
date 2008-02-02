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
 * A utility class for {@link Float}.
 * 
 * @author higa
 * @version 3.0
 */
public final class FloatUtil {

    private FloatUtil() {
    }

    /**
     * Converts an object to the float value.
     * 
     * @param o
     *            an object.
     * @return the float value.
     */
    public static Float toFloat(Object o) {
        if (o == null) {
            return null;
        } else if (o instanceof Float) {
            return (Float) o;
        } else if (o instanceof Number) {
            return Float.valueOf(((Number) o).floatValue());
        } else if (o instanceof String) {
            String s = (String) o;
            if (StringUtil.isEmpty(s)) {
                return null;
            }
            return Float.valueOf(s);
        } else if (o instanceof Boolean) {
            return ((Boolean) o).booleanValue() ? Float.valueOf(1) : Float
                    .valueOf(0);
        } else {
            return Float.valueOf(o.toString());
        }
    }

    /**
     * Converts an object to the primitive float value.
     * 
     * @param o
     *            an object.
     * @return the primitive float value.
     */
    public static float toPrimitiveFloat(Object o) {
        Float f = toFloat(o);
        if (f == null) {
            return 0;
        }
        return f.floatValue();
    }
}
