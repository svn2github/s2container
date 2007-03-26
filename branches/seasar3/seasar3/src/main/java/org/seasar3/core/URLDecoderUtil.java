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

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Utility class for {@link URLDecoder}.
 * 
 * @author higa
 * @since 3.0
 */
public abstract class URLDecoderUtil {

    protected URLDecoderUtil() {
    }

    /**
     * Returns decoded string.
     * 
     * @param s
     * @param enc
     * @return decoded string
     * @throws NullPointerException
     *             if enc is null.
     * @throws IllegalArgumentException
     *             if {@link UnsupportedEncodingException} occurs.
     */
    public static String decode(String s, String enc) {
        if (enc == null) {
            throw new NullPointerException("enc");
        }
        try {
            return URLDecoder.decode(s, enc);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("enc[" + enc + "]", e);
        }
    }
}