/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
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

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.seasar.framework.exception.IllegalAccessRuntimeException;

/**
 * @author higa
 * 
 */
public final class FieldUtil {

    private FieldUtil() {
    }

    public static Object get(Field field, Object target)
            throws IllegalAccessRuntimeException {

        try {
            return field.get(target);
        } catch (IllegalAccessException ex) {
            throw new IllegalAccessRuntimeException(field.getDeclaringClass(),
                    ex);
        }
    }

    public static int getInt(Field field) throws IllegalAccessRuntimeException {
        return getInt(field, null);
    }

    public static int getInt(Field field, Object target)
            throws IllegalAccessRuntimeException {
        try {
            return field.getInt(target);
        } catch (IllegalAccessException ex) {
            throw new IllegalAccessRuntimeException(field.getDeclaringClass(),
                    ex);
        }
    }

    public static String getString(Field field)
            throws IllegalAccessRuntimeException {
        return getString(field, null);
    }

    public static String getString(Field field, Object target)
            throws IllegalAccessRuntimeException {

        try {
            return (String) field.get(target);
        } catch (IllegalAccessException ex) {
            throw new IllegalAccessRuntimeException(field.getDeclaringClass(),
                    ex);
        }
    }

    public static void set(Field field, Object target, Object value)
            throws IllegalAccessRuntimeException {

        try {
            field.set(target, value);
        } catch (IllegalAccessException ex) {
            throw new IllegalAccessRuntimeException(field.getDeclaringClass(),
                    ex);
        }

    }

    public static boolean isInstanceField(Field field) {
        int mod = field.getModifiers();
        return !Modifier.isStatic(mod) && !Modifier.isFinal(mod);
    }

    public static boolean isPublicField(Field field) {
        int mod = field.getModifiers();
        return Modifier.isPublic(mod);
    }
}
