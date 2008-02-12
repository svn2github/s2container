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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.seasar3.util.BigDecimalUtil;
import org.seasar3.util.BigIntegerUtil;
import org.seasar3.util.BooleanUtil;
import org.seasar3.util.ByteUtil;
import org.seasar3.util.CalendarUtil;
import org.seasar3.util.DateUtil;
import org.seasar3.util.Disposable;
import org.seasar3.util.DisposableUtil;
import org.seasar3.util.DoubleUtil;
import org.seasar3.util.FloatUtil;
import org.seasar3.util.IntegerUtil;
import org.seasar3.util.LongUtil;
import org.seasar3.util.ShortUtil;
import org.seasar3.util.SqlDateUtil;
import org.seasar3.util.TimeUtil;
import org.seasar3.util.TimestampUtil;

/**
 * A utility class for conversion.
 * 
 * @author higa
 * @since 3.0
 * 
 */
public final class ConversionUtil {

    static final Object NOT_FOUND = new Object();

    static Map<String, Object> constructorCache = new ConcurrentHashMap<String, Object>(
            200);

    private static volatile boolean initialized = false;

    static {
        initialize();
    }

    private ConversionUtil() {
    }

    private static void initialize() {
        DisposableUtil.add(new Disposable() {
            public void dispose() {
                clear();
            }
        });
        initialized = true;
    }

    private static void clear() {
        constructorCache.clear();
        initialized = false;
    }

    /**
     * Converts the value to the target class instance.
     * 
     * @param value
     *            the value.
     * @param targetClass
     *            the target class.
     * @return the target class instance.
     */
    @SuppressWarnings("unchecked")
    public static Object convert(Object value, Class<?> targetClass) {
        if (targetClass.isPrimitive()) {
            return convertToPrimitiveWrapper(value, targetClass);
        } else if (value == null) {
            return null;
        } else if (Number.class.isAssignableFrom(targetClass)) {
            return convertToNumber(value, targetClass);
        } else if (java.util.Date.class.isAssignableFrom(targetClass)) {
            return convertToDate(value, targetClass);
        } else if (targetClass == Boolean.class) {
            return BooleanUtil.toBoolean(value);
        } else if (targetClass.isEnum() && value.getClass() == String.class) {
            return Enum.valueOf((Class<Enum>) targetClass, (String) value);
        } else if (value.getClass() != String.class
                && targetClass == String.class) {
            return value.toString();
        } else if (value instanceof String && targetClass != String.class) {
            return convertUsingStringConstructor(value, targetClass);
        } else if (java.util.Calendar.class.isAssignableFrom(targetClass)) {
            return CalendarUtil.toCalendar(value);
        }
        return value;
    }

    private static Object convertToPrimitiveWrapper(Object value,
            Class<?> targetClass) {
        if (targetClass == int.class) {
            Integer i = IntegerUtil.toInteger(value);
            if (i != null) {
                return i;
            }
            return Integer.valueOf(0);
        } else if (targetClass == boolean.class) {
            Boolean b = BooleanUtil.toBoolean(value);
            if (b != null) {
                return b;
            }
            return Boolean.FALSE;
        } else if (targetClass == long.class) {
            Long l = LongUtil.toLong(value);
            if (l != null) {
                return l;
            }
            return Long.valueOf(0);
        } else if (targetClass == double.class) {
            Double d = DoubleUtil.toDouble(value);
            if (d != null) {
                return d;
            }
            return Double.valueOf(0);
        } else if (targetClass == short.class) {
            Short s = ShortUtil.toShort(value);
            if (s != null) {
                return s;
            }
            return Short.valueOf((short) 0);
        } else if (targetClass == float.class) {
            Float f = FloatUtil.toFloat(value);
            if (f != null) {
                return f;
            }
            return Float.valueOf(0);
        } else if (targetClass == byte.class) {
            Byte b = ByteUtil.toByte(value);
            if (b != null) {
                return b;
            }
            return Byte.valueOf((byte) 0);
        }
        throw new IllegalArgumentException("Unknown class: "
                + targetClass.getName());
    }

    private static Object convertToNumber(Object value, Class<?> targetClass) {
        if (targetClass == Integer.class) {
            return IntegerUtil.toInteger(value);
        } else if (targetClass == BigDecimal.class) {
            return BigDecimalUtil.toBigDecimal(value);
        } else if (targetClass == Long.class) {
            return LongUtil.toLong(value);
        } else if (targetClass == Double.class) {
            return DoubleUtil.toDouble(value);
        } else if (targetClass == Short.class) {
            return ShortUtil.toShort(value);
        } else if (targetClass == Byte.class) {
            return ByteUtil.toByte(value);
        } else if (targetClass == Float.class) {
            return FloatUtil.toFloat(value);
        } else if (targetClass == BigInteger.class) {
            return BigIntegerUtil.toBigInteger(value);
        }
        return value;
    }

    private static Object convertToDate(Object value, Class<?> targetClass) {
        if (targetClass == java.util.Date.class) {
            return DateUtil.toDate(value);
        } else if (targetClass == Timestamp.class) {
            return TimestampUtil.toTimestamp(value);
        } else if (targetClass == java.sql.Date.class) {
            return SqlDateUtil.toDate(value);
        } else if (targetClass == Time.class) {
            return TimeUtil.toTime(value);
        }
        return value;
    }

    private static Object convertUsingStringConstructor(Object value,
            Class<?> targetClass) {
        if (!initialized) {
            initialize();
        }
        Constructor<?> con = findStringConstructor(targetClass);
        if (con != null) {
            try {
                return con.newInstance(value);
            } catch (InvocationTargetException t) {
                Throwable cause = t.getTargetException();
                if (cause instanceof RuntimeException) {
                    throw (RuntimeException) cause;
                }
                if (cause instanceof Error) {
                    throw (Error) cause;
                }
                throw new RuntimeException(cause);
            } catch (Throwable t) {
                throw new RuntimeException(t);
            }
        }
        return value;
    }

    private static Constructor<?> findStringConstructor(Class<?> targetClass) {
        Object cache = constructorCache.get(targetClass.getName());
        if (cache != null) {
            if (cache == NOT_FOUND) {
                return null;
            }
            return (Constructor<?>) cache;
        }
        Constructor<?>[] cons = targetClass.getConstructors();
        for (int i = 0; i < cons.length; ++i) {
            Constructor<?> con = cons[i];
            if (con.getParameterTypes().length == 1
                    && con.getParameterTypes()[0].equals(String.class)) {
                if (Modifier.isPublic(con.getModifiers())) {
                    constructorCache.put(targetClass.getName(), con);
                    return con;
                }
                break;
            }
        }
        constructorCache.put(targetClass.getName(), NOT_FOUND);
        return null;
    }
}
