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
import java.net.URLConnection;

/**
 * Utility class for {@link URL}.
 * 
 * @author higa
 * @since 3.0
 */
public abstract class URLUtil {

    private static final String UTF8 = "UTF8";

    protected URLUtil() {
    }

    /**
     * Returns {@link InputStream}.
     * 
     * @param url
     * 
     * @return {@link InputStream}
     * @throws NullPointerException
     *             if url is null.
     * @throws IllegalArgumentException
     *             if I/O exception occurs.
     */
    public static InputStream getInputStream(URL url) {
        if (url == null) {
            throw new NullPointerException("url");
        }
        try {
            URLConnection connection = url.openConnection();
            connection.setUseCaches(false);
            return connection.getInputStream();
        } catch (IOException e) {
            throw new IllegalArgumentException(url.toString(), e);
        }
    }

    /**
     * Returns file name decoded by UTF8.
     * 
     * @param url
     * @return
     * @throws NullPointerException
     *             if url is null.
     */
    public static String getFile(URL url) {
        if (url == null) {
            throw new NullPointerException("url");
        }
        String s = url.getFile();
        return URLDecoderUtil.decode(s, UTF8);
    }
}
