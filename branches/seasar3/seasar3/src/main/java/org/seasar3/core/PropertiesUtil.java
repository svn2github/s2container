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
package org.seasar3.core;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * Utility class for {@link Properties}.
 * 
 * @author higa
 * @since 3.0
 */
public abstract class PropertiesUtil {

    /** default properties suffix */
    public static final String PROPERTIES_SUFFIX = ".properties";

    protected PropertiesUtil() {
    }

    /**
     * Creates {@link Properties}. If path does not exist, returns null.
     * 
     * @param path
     *            such as aaa.bbb(WEB-INF/classes/aaa/bbb.properties)
     * @return {@link Properties}
     * @throws NullPointerException
     *             if path is null.
     * @throws IllegalArgumentException
     *             if I/O exception occurs.
     * @see {@link URLUtil#getInputStream(URL)}
     */
    public static Properties create(String path) {
        if (path == null) {
            throw new NullPointerException("path");
        }
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            URL url = loader.getResource(path);
            if (url == null) {
                return null;
            }
            Properties p = new Properties();
            InputStream in = URLUtil.getInputStream(url);
            try {
                p.load(in);
            } finally {
                in.close();
            }
            return p;
        } catch (IOException e) {
            throw new IllegalArgumentException(path, e);
        }
    }
}
