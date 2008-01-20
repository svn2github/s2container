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

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * A utility class for Java Bean.
 * 
 * @author higa
 * 
 */
public final class BeanUtil {

    private BeanUtil() {
    }

    /**
     * Returns the raw class.
     * 
     * @param type
     *            the type.
     * @return the raw class.
     */
    protected static Class<?> getRawClass(Type type) {
        if (Class.class.isInstance(type)) {
            return Class.class.cast(type);
        }
        if (ParameterizedType.class.isInstance(type)) {
            ParameterizedType parameterizedType = ParameterizedType.class
                    .cast(type);
            return getRawClass(parameterizedType.getRawType());
        }
        if (GenericArrayType.class.isInstance(type)) {
            GenericArrayType genericArrayType = GenericArrayType.class
                    .cast(type);
            Class<?> rawClass = getRawClass(genericArrayType
                    .getGenericComponentType());
            return Array.newInstance(rawClass, 0).getClass();
        }
        return null;
    }

    /**
     * Returns the type arguments.
     * 
     * @param type
     *            the type.
     * @return the type arguments.
     */
    protected static Type[] getTypeArguments(Type type) {
        if (ParameterizedType.class.isInstance(type)) {
            return ParameterizedType.class.cast(type).getActualTypeArguments();
        }
        if (GenericArrayType.class.isInstance(type)) {
            return getTypeArguments(GenericArrayType.class.cast(type)
                    .getGenericComponentType());
        }
        return null;
    }

    /**
     * Creates a {@link ParameterizedClassDesc}.
     * 
     * @param type
     *            the type.
     * @return a {@link ParameterizedClassDesc}.
     */
    protected static ParameterizedClassDesc createParameterizedClassDesc(
            Type type) {
        Class<?> rawClass = getRawClass(type);
        if (rawClass == null) {
            return null;
        }
        ParameterizedClassDesc[] arguments = null;
        Type[] typeArguments = getTypeArguments(type);
        if (typeArguments != null) {
            arguments = new ParameterizedClassDesc[typeArguments.length];
            for (int i = 0; i < arguments.length; ++i) {
                arguments[i] = createParameterizedClassDesc(typeArguments[i]);
            }
        }
        return new ParameterizedClassDesc(rawClass, arguments);
    }
}
