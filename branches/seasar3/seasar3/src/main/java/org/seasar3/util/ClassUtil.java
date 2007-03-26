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
package org.seasar3.util;

import org.seasar3.exception.IllegalAccessRuntimeException;
import org.seasar3.exception.InstantiationRuntimeException;

/**
 * Utility for {@link Class}.
 * 
 * @author higa
 * @since 3.0
 */
public final class ClassUtil {

    private ClassUtil() {
    }

    /**
     * Creates a new instance.
     * 
     * @param <T>
     * @param type
     * @return
     * @throws InstantiationRuntimeException
     *             if InstantiationException occurred.
     * @throws IllegalAccessRuntimeException
     *             if IllegalAccessException occurred.
     */
    public static <T> T newInstance(Class<? extends T> type) {
        try {
            return type.newInstance();
        } catch (InstantiationException e) {
            throw new InstantiationRuntimeException(type, e);
        } catch (IllegalAccessException e) {
            throw new IllegalAccessRuntimeException(type, e);
        }
    }
}
