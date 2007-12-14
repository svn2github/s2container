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
import java.net.URL;

import org.seasar.framework.exception.IORuntimeException;

/**
 * @author higa
 * 
 */
public final class FileUtil {

    private FileUtil() {
    }

    public static byte[] getBytes(File file) {
        return InputStreamUtil.getBytes(FileInputStreamUtil.create(file));
    }

    public static String getCanonicalPath(File file) {
        try {
            return file.getCanonicalPath();
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
    }

    public static URL toURL(final File file) {
        try {
            return file.toURL();
        } catch (final IOException e) {
            throw new IORuntimeException(e);
        }
    }

}
