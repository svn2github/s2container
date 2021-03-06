/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
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
package org.seasar.framework.util;

import java.math.BigDecimal;
import java.math.BigInteger;

public final class NumberConversionUtil {

    private NumberConversionUtil() {
    }

    public static Object convertNumber(Class type, Object o) {
        if (type == Integer.class) {
            return IntegerConversionUtil.toInteger(o);
        } else if (type == BigDecimal.class) {
            return BigDecimalConversionUtil.toBigDecimal(o);
        } else if (type == Double.class) {
            return DoubleConversionUtil.toDouble(o);
        } else if (type == Long.class) {
            return LongConversionUtil.toLong(o);
        } else if (type == Float.class) {
            return FloatConversionUtil.toFloat(o);
        } else if (type == Short.class) {
            return ShortConversionUtil.toShort(o);
        } else if (type == BigInteger.class) {
            return BigIntegerConversionUtil.toBigInteger(o);
        }
        return o;
    }

    public static Object convertPrimitiveWrapper(Class type, Object o) {
        if (type == int.class) {
            Integer i = IntegerConversionUtil.toInteger(o);
            if (i != null) {
                return i;
            }
            return new Integer(0);
        } else if (type == double.class) {
            Double d = DoubleConversionUtil.toDouble(o);
            if (d != null) {
                return d;
            }
            return new Double(0);
        } else if (type == long.class) {
            Long l = LongConversionUtil.toLong(o);
            if (l != null) {
                return l;
            }
            return new Long(0);
        } else if (type == float.class) {
            Float f = FloatConversionUtil.toFloat(o);
            if (f != null) {
                return f;
            }
            return new Float(0);
        } else if (type == short.class) {
            Short s = ShortConversionUtil.toShort(o);
            if (s != null) {
                return s;
            }
            return new Short((short) 0);
        } else if (type == boolean.class) {
            Boolean b = BooleanConversionUtil.toBoolean(o);
            if (b != null) {
                return b;
            }
            return Boolean.FALSE;
        }
        return o;
    }
}