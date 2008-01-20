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

import java.sql.Timestamp;

/**
 * A utility class for {@link Timestamp}.
 * 
 * @author higa
 * 
 */
public final class TimestampConversionUtil {

    private TimestampConversionUtil() {
    }

    /**
     * Converts an object to the timestamp value.
     * 
     * @param o
     *            an object.
     * @return the timestamp value.
     */
    public static Timestamp toTimestamp(Object o) {
        if (o instanceof Timestamp) {
            return (Timestamp) o;
        }
        java.util.Date date = DateConversionUtil.toDate(o);
        if (date != null) {
            return new Timestamp(date.getTime());
        }
        return null;
    }
}