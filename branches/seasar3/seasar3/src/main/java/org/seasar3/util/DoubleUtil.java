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
 * A utility class for {@link Double}.
 * 
 * @author higa
 * 
 */
public final class DoubleUtil {

    private DoubleUtil() {
    }

    /**
     * Converts an object to the double value.
     * 
     * @param o
     *            an object.
     * @return the double value.
     */
    public static Double toDouble(Object o) {
        if (o == null) {
            return null;
        } else if (o instanceof Double) {
            return (Double) o;
        } else if (o instanceof Number) {
            return Double.valueOf(((Number) o).doubleValue());
        } else if (o instanceof String) {
            return Double.valueOf((String) o);
        } else if (o instanceof Boolean) {
            return ((Boolean) o).booleanValue() ? Double.valueOf(1) : Double
                    .valueOf(0);
        } else {
            return Double.valueOf(o.toString());
        }
    }

    /**
     * Converts an object to the primitive double value.
     * 
     * @param o
     *            an object.
     * @return the primitive double value.
     */
    public static double toPrimitiveDouble(Object o) {
        if (o == null) {
            return 0;
        }
        return toDouble(o).doubleValue();
    }
}
