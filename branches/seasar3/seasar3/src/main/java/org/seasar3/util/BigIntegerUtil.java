/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
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

import java.math.BigInteger;

/**
 * A utility class for {@link BigInteger}.
 * 
 * @author higa
 * @version 3.0
 */
public final class BigIntegerUtil {

    private BigIntegerUtil() {
    }

    /**
     * Converts an object to the big integer value.
     * 
     * @param o
     *            an object.
     * @return the big integer value.
     */
    public static BigInteger toBigInteger(Object o) {
        if (o == null) {
            return null;
        } else if (o instanceof BigInteger) {
            return (BigInteger) o;
        } else if (o instanceof String) {
            String s = (String) o;
            if (StringUtil.isEmpty(s)) {
                return null;
            }
            return new BigInteger(s);
        } else {
            return new BigInteger(o.toString());
        }
    }
}
