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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.exception.IORuntimeException;
import org.seasar.framework.exception.SRuntimeException;

/**
 * @author higa
 * 
 */
public class URLUtil {

    protected static final Map CANONICAL_PROTOCOLS = new HashMap();
    static {
        CANONICAL_PROTOCOLS.put("wsjar", "jar");
    }

    private URLUtil() {
    }

    public static InputStream openStream(URL url) {
        try {
            URLConnection connection = url.openConnection();
            connection.setUseCaches(false);
            return connection.getInputStream();
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
    }

    public static URLConnection openConnection(URL url) {
        try {
            URLConnection connection = url.openConnection();
            connection.setUseCaches(false);
            return connection;
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
    }

    public static URL create(String spec) {
        try {
            return new URL(spec);
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
    }

    public static String encode(final String s, final String enc) {
        try {
            return URLEncoder.encode(s, enc);
        } catch (final UnsupportedEncodingException e) {
            throw new IORuntimeException(e);
        }
    }

    public static String decode(final String s, final String enc) {
        try {
            return URLDecoder.decode(s, enc);
        } catch (final UnsupportedEncodingException e) {
            throw new IORuntimeException(e);
        }
    }

    public static String toCanonicalProtocol(final String protocol) {
        final String canonicalProtocol = (String) CANONICAL_PROTOCOLS
                .get(protocol);
        if (canonicalProtocol != null) {
            return canonicalProtocol;
        }
        return protocol;
    }

    public static File toFile(final URL fileUrl) {
        try {
            final String path = URLDecoder.decode(fileUrl.getPath(), "UTF-8");
            return new File(path).getAbsoluteFile();
        } catch (final Exception e) {
            throw new SRuntimeException("ESSR0091", new Object[] { fileUrl }, e);
        }
    }

    /**
     * 以下のバグに対する対応です。 <br/>
     * http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4167874
     * 
     */
    public static void disableURLCaches() {
        BeanDesc bd = BeanDescFactory.getBeanDesc(URLConnection.class);
        FieldUtil.set(bd.getField("defaultUseCaches"), null, Boolean.FALSE);
    }
}
