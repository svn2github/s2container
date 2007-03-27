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
package org.seasar3.lookup;

import java.util.concurrent.ConcurrentHashMap;

import org.seasar3.util.ClassUtil;

/**
 * A S3 provides "lookup" function.
 * 
 * @author higa
 * 
 */
public class S3 {

    private static ConcurrentHashMap<String, Object> configs = new ConcurrentHashMap<String, Object>(
            17);

    private static ConcurrentHashMap<String, Class> overrides = new ConcurrentHashMap<String, Class>(
            17);

    private S3() {
    }

    /**
     * Looks up configuration object. configuration object is managed as
     * singleton.
     * 
     * @param <T>
     * @param configClass
     * @return configuration object
     */
    @SuppressWarnings("unchecked")
    public static final <T> T lookup(Class<? extends T> configClass) {
        if (configClass == null) {
            throw new NullPointerException("configClass");
        }
        String name = configClass.getName();
        Class dest = overrides.get(name);
        if (dest != null) {
            configClass = dest;
        }
        T config = (T) configs.get(name);
        if (config != null) {
            return config;
        }
        config = ClassUtil.newInstance(configClass);
        T config2 = (T) configs.putIfAbsent(name, config);
        return config2 != null ? config2 : config;
    }

    /**
     * Overrides src configuration.
     * 
     * @param <T>
     * 
     * @param src
     * @param dest
     * @return
     */
    public static <T> void override(Class<T> src, Class<? extends T> dest) {
        if (src == null) {
            throw new NullPointerException("src");
        }
        if (dest == null) {
            throw new NullPointerException("dest");
        }
        overrides.put(src.getName(), dest);
    }

    /**
     * Disposes all resources.
     */
    public static final void dispose() {
        configs.clear();
        overrides.clear();
    }
}