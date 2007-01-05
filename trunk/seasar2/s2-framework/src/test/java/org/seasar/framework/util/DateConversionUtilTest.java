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

import java.text.SimpleDateFormat;
import java.util.Locale;

import junit.framework.TestCase;

public class DateConversionUtilTest extends TestCase {

    public void testRemoveDelimiter() throws Exception {
        assertEquals("1", "yyyyMMdd", DateConversionUtil
                .removeDelimiter("yyyy/MM/dd"));
    }

    public void testGetDateFormat() throws Exception {
        SimpleDateFormat sdf = DateConversionUtil.getDateFormat("2004/11/7",
                Locale.JAPAN);
        assertEquals("1", "yyyy/MM/dd", sdf.toPattern());
    }

    public void testGetDateFormat2() throws Exception {
        SimpleDateFormat sdf = DateConversionUtil.getDateFormat("04/11/7",
                Locale.JAPAN);
        assertEquals("1", "yy/MM/dd", sdf.toPattern());
    }

    public void testGetDateFormat3() throws Exception {
        SimpleDateFormat sdf = DateConversionUtil.getDateFormat("20041107",
                Locale.JAPAN);
        assertEquals("1", "yyyyMMdd", sdf.toPattern());
    }

    public void testGetDateFormat4() throws Exception {
        SimpleDateFormat sdf = DateConversionUtil.getDateFormat("041107",
                Locale.JAPAN);
        assertEquals("1", "yyMMdd", sdf.toPattern());
    }

    public void testFindDelimeterFromPattern1() throws Exception {
        String delim = DateConversionUtil
                .findDelimiterFromPattern("yyyy/MM/dd");
        assertEquals("/", delim);
    }

    public void testFindDelimeterFromPattern2() throws Exception {
        String delim = DateConversionUtil
                .findDelimiterFromPattern("yyyy/MM-dd");
        assertEquals("/", delim);
    }

    public void testGetPattern() throws Exception {
        System.out.println(DateConversionUtil.getPattern(Locale.JAPAN));
    }

    public void testGetDefaultPattern() throws Exception {
        System.out.println(DateConversionUtil.getY4Pattern(Locale.JAPAN));
    }
}