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
package org.seasar3.util;

import java.util.HashMap;
import java.util.Map;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.LoaderClassPath;
import javassist.NotFoundException;

import org.seasar3.exception.NotFoundRuntimeException;

/**
 * Utility for class pool.
 * 
 * @author koichik
 * @author higa
 * @version 3.0
 */

public final class ClassPoolUtil {

    private static final Map<ClassLoader, ClassPool> classPoolCache = new HashMap<ClassLoader, ClassPool>();

    private static volatile boolean initialized = false;

    static {
        initialize();
    }

    private ClassPoolUtil() {
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
        classPoolCache.clear();
        initialized = false;
    }

    /**
     * Returns the class pool.
     * 
     * @return the class pool.
     * @see #getClassPool(ClassLoader)
     */
    public static ClassPool getClassPool() {
        return getClassPool(Thread.currentThread().getContextClassLoader());
    }

    /**
     * Returns the class pool.
     * 
     * @param classLoader
     *            the class loader.
     * @return the class pool.
     */
    public static ClassPool getClassPool(ClassLoader classLoader) {
        if (classLoader == null) {
            throw new NullPointerException("classLoader");
        }
        if (!initialized) {
            initialize();
        }
        ClassPool classPool = classPoolCache.get(classLoader);
        if (classPool == null) {
            classPool = new ClassPool();
            classPool.appendClassPath(new LoaderClassPath(classLoader));
            classPoolCache.put(classLoader, classPool);
        }
        return classPool;
    }

    /**
     * Converts the class to the compile time class.
     * 
     * @param classPool
     *            the class pool.
     * @param clazz
     *            the class.
     * @return the compile time class.
     */
    public static CtClass toCtClass(ClassPool classPool, Class<?> clazz) {
        if (classPool == null) {
            throw new NullPointerException("classPool");
        }
        if (clazz == null) {
            return null;
        }
        return get(classPool, clazz.getName());
    }

    /**
     * Converts array of classes to array of compile time classes.
     * 
     * @param classPool
     *            the class pool.
     * @param classes
     *            the array of classes.
     * @return the array of compile time classes.
     */
    public static CtClass[] toCtClassArray(ClassPool classPool,
            Class<?>[] classes) {
        if (classes == null) {
            return null;
        }
        CtClass[] result = new CtClass[classes.length];
        for (int i = 0; i < result.length; ++i) {
            result[i] = toCtClass(classPool, classes[i]);
        }
        return result;
    }

    /**
     * Returns the compile time class.
     * 
     * @param classPool
     *            the class pool.
     * @param className
     *            the class name.
     * @return the compile time class.
     * @throws NotFoundRuntimeException
     *             if NotFoundException occurred.
     */
    public static CtClass get(ClassPool classPool, String className) {
        if (classPool == null) {
            throw new NullPointerException("classPool");
        }
        if (className == null) {
            throw new NullPointerException("className");
        }
        try {
            return classPool.get(className);
        } catch (NotFoundException e) {
            throw new NotFoundRuntimeException(className, e);
        }
    }

    /**
     * Converts and renames the compile time class.
     * 
     * @param classPool
     *            the class pool.
     * @param originalClassName
     *            the original class name.
     * @param newClassName
     *            the new class name.
     * 
     * @return the compile time class.
     * @throws NotFoundRuntimeException
     *             if NotFoundException occurred.
     */
    public static CtClass getAndRename(ClassPool classPool,
            String originalClassName, String newClassName) {
        if (classPool == null) {
            throw new NullPointerException("classPool");
        }
        if (originalClassName == null) {
            throw new NullPointerException("originalClassName");
        }
        if (newClassName == null) {
            throw new NullPointerException("newClassName");
        }
        try {
            return classPool.getAndRename(originalClassName, newClassName);
        } catch (NotFoundException e) {
            throw new NotFoundRuntimeException(e.getMessage(), e);
        }
    }
}