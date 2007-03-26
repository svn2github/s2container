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

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.LoaderClassPath;
import javassist.NotFoundException;

import org.seasar3.exception.NotFoundRuntimeException;

/**
 * Utility for <code>ClassPool</code>
 * 
 * @author koichik
 * @author higa
 */

public final class ClassPoolUtil {

    protected static final Map<ClassLoader, ClassPool> classPoolMap = Collections
            .synchronizedMap(new WeakHashMap<ClassLoader, ClassPool>());

    private ClassPoolUtil() {
    }

    /**
     * Returns class pool.
     * 
     * @return
     * @see #getClassPool(ClassLoader)
     */
    public static ClassPool getClassPool() {
        return getClassPool(Thread.currentThread().getContextClassLoader());
    }

    /**
     * Returns cached class pool by {@link ClassLoader}.
     * 
     * @param classLoader
     * @return
     */
    public static ClassPool getClassPool(ClassLoader classLoader) {
        if (classLoader == null) {
            throw new NullPointerException("classLoader");
        }
        ClassPool classPool = (ClassPool) classPoolMap.get(classLoader);
        if (classPool == null) {
            classPool = new ClassPool();
            classPool.appendClassPath(new LoaderClassPath(classLoader));
            classPoolMap.put(classLoader, classPool);
        }
        return classPool;
    }

    /**
     * Converts {@link Class} to <code>CtClass</code>
     * 
     * @param classPool
     * @param type
     * @return
     * @see #get(ClassPool, String)
     */
    public static CtClass toCtClass(ClassPool classPool, Class type) {
        if (classPool == null) {
            throw new NullPointerException("classPool");
        }
        if (type == null) {
            return null;
        }
        return get(classPool, type.getName());
    }

    /**
     * Converts array of {@link Class} to array of <code>CtClass</code>
     * 
     * @param classPool
     * @param classes
     * @return
     */
    public static CtClass[] toCtClass(ClassPool classPool, Class[] classes) {
        if (classes == null) {
            return null;
        }
        final CtClass[] result = new CtClass[classes.length];
        for (int i = 0; i < result.length; ++i) {
            result[i] = toCtClass(classPool, classes[i]);
        }
        return result;
    }

    /**
     * Returns <code>CtClass</code>.
     * 
     * @param classPool
     * @param className
     * @return
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
        } catch (final NotFoundException e) {
            throw new NotFoundRuntimeException(className, e);
        }
    }

    /**
     * Returns and renames <code>CtClass</code>.
     * 
     * @param classPool
     * @param originalClassName
     * @param newClassName
     * 
     * @return
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
        } catch (final NotFoundException e) {
            throw new NotFoundRuntimeException(e.getMessage(), e);
        }
    }
}