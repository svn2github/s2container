/*
 * Copyright 2004-2005 the Seasar Foundation and the Others.
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

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.seasar.framework.exception.ClassNotFoundRuntimeException;
import org.seasar.framework.exception.IllegalAccessRuntimeException;
import org.seasar.framework.exception.InstantiationRuntimeException;
import org.seasar.framework.exception.NoSuchConstructorRuntimeException;
import org.seasar.framework.exception.NoSuchFieldRuntimeException;
import org.seasar.framework.exception.NoSuchMethodRuntimeException;

/**
 * @author higa
 *
 */
public final class ClassUtil {

	private static Map wrapperToPrimitiveMap_ = new HashMap();
	private static Map primitiveToWrapperMap_ = new HashMap();

	static {
		wrapperToPrimitiveMap_.put(Character.class, Character.TYPE);
		wrapperToPrimitiveMap_.put(Byte.class, Byte.TYPE);
		wrapperToPrimitiveMap_.put(Short.class, Short.TYPE);
		wrapperToPrimitiveMap_.put(Integer.class, Integer.TYPE);
		wrapperToPrimitiveMap_.put(Long.class, Long.TYPE);
		wrapperToPrimitiveMap_.put(Double.class, Double.TYPE);
		wrapperToPrimitiveMap_.put(Float.class, Float.TYPE);
		wrapperToPrimitiveMap_.put(Boolean.class, Boolean.TYPE);

		primitiveToWrapperMap_.put(Character.TYPE, Character.class);
		primitiveToWrapperMap_.put(Byte.TYPE, Byte.class);
		primitiveToWrapperMap_.put(Short.TYPE, Short.class);
		primitiveToWrapperMap_.put(Integer.TYPE, Integer.class);
		primitiveToWrapperMap_.put(Long.TYPE, Long.class);
		primitiveToWrapperMap_.put(Double.TYPE, Double.class);
		primitiveToWrapperMap_.put(Float.TYPE, Float.class);
		primitiveToWrapperMap_.put(Boolean.TYPE, Boolean.class);
	}

	/**
	 * 
	 */
	private ClassUtil() {
	}

	public static Class forName(String className)
		throws ClassNotFoundRuntimeException {

		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try {
			return Class.forName(className, true, loader);
		} catch (ClassNotFoundException ex) {
			throw new ClassNotFoundRuntimeException(ex);
		}
	}

	public static Object newInstance(Class clazz)
		throws InstantiationRuntimeException, IllegalAccessRuntimeException {

		try {
			return clazz.newInstance();
		} catch (InstantiationException ex) {
			throw new InstantiationRuntimeException(clazz, ex);
		} catch (IllegalAccessException ex) {
			throw new IllegalAccessRuntimeException(clazz, ex);
		}
	}

	public static Object newInstance(String className)
		throws
			ClassNotFoundRuntimeException,
			InstantiationRuntimeException,
			IllegalAccessRuntimeException {

		return newInstance(forName(className));
	}

	public static boolean isAssignableFrom(Class toClass, Class fromClass) {
		if (toClass == Object.class && !fromClass.isPrimitive()) {
			return true;
		}
		if (toClass.isPrimitive()) {
			fromClass = getPrimitiveClassIfWrapper(fromClass);
		}
		return toClass.isAssignableFrom(fromClass);
	}

	public static Class getPrimitiveClass(Class clazz) {
		return (Class) wrapperToPrimitiveMap_.get(clazz);
	}

	public static Class getPrimitiveClassIfWrapper(Class clazz) {
		Class ret = getPrimitiveClass(clazz);
		if (ret != null) {
			return ret;
		}
		return clazz;
	}

	public static Class getWrapperClass(Class clazz) {
		return (Class) primitiveToWrapperMap_.get(clazz);
	}

	public static Class getWrapperClassIfPrimitive(Class clazz) {
		Class ret = getWrapperClass(clazz);
		if (ret != null) {
			return ret;
		}
		return clazz;
	}

	public static Constructor getConstructor(Class clazz, Class[] argTypes) {
		try {
			return clazz.getConstructor(argTypes);
		} catch (NoSuchMethodException ex) {
			throw new NoSuchConstructorRuntimeException(clazz, argTypes, ex);
		}
	}

	public static Method getMethod(
		Class clazz,
		String methodName,
		Class[] argTypes) {

		try {
			return clazz.getMethod(methodName, argTypes);
		} catch (NoSuchMethodException ex) {
			throw new NoSuchMethodRuntimeException(
				clazz,
				methodName,
				argTypes,
				ex);
		}
	}
	
	public static Field getField(
		Class clazz,
		String fieldName) {

		try {
			return clazz.getField(fieldName);
		} catch (NoSuchFieldException ex) {
			throw new NoSuchFieldRuntimeException(
				clazz,
				fieldName,
				ex);
		}
	}

	public static String getShortClassName(Class clazz) {
		String s = clazz.getName();
		int i = s.lastIndexOf('.');
		if (i > 0) {
			return s.substring(i + 1);
		}
		return s;
	}

    public static String getSimpleClassName(final Class clazz) {
        if (clazz.isArray()) {
            return getSimpleClassName(clazz.getComponentType()) + "[]";
        }
        return clazz.getName();
    }
    
    public static String concatName(String s1, String s2) {
        return s1 != null ? s1 + '.' + s2 : s2;
    }
}
