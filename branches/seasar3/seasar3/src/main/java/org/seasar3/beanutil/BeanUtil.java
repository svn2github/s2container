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

import java.util.concurrent.ConcurrentHashMap;

import org.seasar3.util.Disposable;
import org.seasar3.util.DisposableUtil;

/**
 * A utility class for Java Bean.
 * 
 * @author higa
 * 
 */
public final class BeanUtil {

    private static ConcurrentHashMap<String, BeanDesc> beanDescCache = new ConcurrentHashMap<String, BeanDesc>(
            200);

    private static volatile boolean initialized = false;

    private BeanUtil() {
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
        beanDescCache.clear();
        initialized = false;
    }

    /**
     * Returns the bean descriptor.
     * 
     * @param beanClass
     *            the bean class.
     * @return the bean descriptor.
     */
    public static BeanDesc getBeanDesc(Class<?> beanClass) {
        if (beanClass == null) {
            throw new NullPointerException("beanClass");
        }
        if (!initialized) {
            initialize();
        }

        BeanDesc beanDesc = beanDescCache.get(beanClass.getName());
        if (beanDesc != null) {
            return beanDesc;
        }
        beanDesc = BeanDesc.create(beanClass);
        beanDescCache.put(beanClass.getName(), beanDesc);
        return beanDesc;
    }
}
