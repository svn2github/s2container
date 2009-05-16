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
package org.seasar.diigu.eclipse.util;

import java.lang.reflect.Array;

/**
 * @author taichi
 * 
 */
public class ArrayUtil {

    /**
     * 
     */
    public ArrayUtil() {
        super();
    }

    public static Object[] add(final Object[] a, final Object[] b) {
        if (a != null && b != null) {
            if (a.length != 0 && b.length != 0) {
                Object[] array = (Object[]) Array.newInstance(a.getClass()
                        .getComponentType(), a.length + b.length);
                System.arraycopy(a, 0, array, 0, a.length);
                System.arraycopy(b, 0, array, a.length, b.length);
                return array;
            } else if (b.length == 0) {
                return a;
            } else {
                return b;
            }
        } else if (b == null) {
            return a;
        } else {
            return b;
        }
    }

}
