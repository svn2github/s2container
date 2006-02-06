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
package org.seasar.framework.aop.javassist;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

import javassist.ClassPool;
import javassist.LoaderClassPath;

/**
 * @author koichik
 */
public class ClassPoolUtil {
    //static fields
    protected static final Map classPoolMap = Collections.synchronizedMap(new WeakHashMap());

    public static ClassPool getClassPool() {
        final ClassLoader classLoader = getClassLoader();

        ClassPool classPool = (ClassPool) classPoolMap.get(classLoader);
        if (classPool == null) {
            if (classLoader == null) {
                return ClassPool.getDefault();
            }
            classPool = new ClassPool();
            classPool.appendClassPath(new LoaderClassPath(classLoader));
            classPoolMap.put(classLoader, classPool);
        }
        return classPool;
    }

    protected static ClassLoader getClassLoader() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader != null) {
            return classLoader;
        }

        classLoader = ClassPoolUtil.class.getClassLoader();
        if (classLoader != null) {
            return classLoader;
        }

        return null;
    }
}