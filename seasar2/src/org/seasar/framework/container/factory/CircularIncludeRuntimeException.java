/*
 * Copyright 2004-2005 the Seasar Foundation and the Others.
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
package org.seasar.framework.container.factory;

import java.util.Iterator;
import java.util.Set;

import org.seasar.framework.exception.SRuntimeException;

/**
 * @author koichik
 */
public class CircularIncludeRuntimeException extends SRuntimeException {
    protected String path_;
    protected Set paths_;

    /**
     * @param componentClasses
     */
    public CircularIncludeRuntimeException(final String path, final Set paths) {
        super("ESSR0076", new Object[] { path, toString(path, paths) });
        path_ = path;
        paths_ = paths;
    }

    public String getPath() {
        return path_;
    }

    public Set getPaths() {
        return paths_;
    }

    protected static String toString(final String path, final Set paths) {
        final StringBuffer buf = new StringBuffer(200);
        for (final Iterator it = paths.iterator(); it.hasNext();) {
            buf.append("\"").append(it.next()).append("\" includes ");
        }
        buf.append("\"").append(path).append("\"");
        return new String(buf);
    }
}
