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
package org.seasar.framework.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.seasar.framework.exception.IORuntimeException;

/**
 * @author higa
 * 
 */
public final class InputStreamUtil {

    private InputStreamUtil() {
    }

    public static void close(InputStream is) {
        try {
            is.close();
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
    }

    public static final byte[] getBytes(InputStream is) {
        byte[] bytes = null;
        byte[] buf = new byte[8192];
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int n = 0;
            while ((n = is.read(buf, 0, buf.length)) != -1) {
                baos.write(buf, 0, n);
            }
            bytes = baos.toByteArray();
        } catch (IOException e) {
            throw new IORuntimeException(e);
        } finally {
            if (is != null) {
                close(is);
            }
        }
        return bytes;
    }

    public static int available(InputStream is) {
        try {
            return is.available();
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
    }
}