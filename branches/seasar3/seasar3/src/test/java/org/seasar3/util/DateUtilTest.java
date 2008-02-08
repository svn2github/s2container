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

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import junit.framework.TestCase;

import org.seasar3.exception.ParseRuntimeException;

/**
 * @author higa
 * 
 */
public class DateUtilTest extends TestCase {

    /**
     * @throws Exception
     */
    public void testToDateForNull() throws Exception {
        assertNull(DateUtil.toDate(null));
    }

    /**
     * @throws Exception
     */
    public void testToDateForNull2() throws Exception {
        assertNull(DateUtil.toDate(null, "yyyyMMdd"));
    }

    /**
     * @throws Exception
     */
    public void testToDateForPatternNull() throws Exception {
        try {
            DateUtil.toDate("20080201", (String) null);
            fail();
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }

    /**
     * @throws Exception
     */
    public void testToDateForDate() throws Exception {
        Date date = new Date();
        assertEquals(date, DateUtil.toDate(date));
    }

    /**
     * @throws Exception
     */
    public void testToDateForCalendar() throws Exception {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        assertEquals(date, DateUtil.toDate(cal));
    }

    /**
     * @throws Exception
     */
    public void testToDateForString() throws Exception {
        assertNotNull(DateUtil.toDate("01/17/2008", Locale.ENGLISH));
    }

    /**
     * @throws Exception
     */
    public void testToDateForException() throws Exception {
        try {
            DateUtil.toDate("xx/17/2008", Locale.ENGLISH);
            fail();
        } catch (ParseRuntimeException e) {
            System.out.println(e);
            assertEquals("xx/17/2008", e.getText());
        }
    }

    /**
     * @throws Exception
     */
    public void testToDateForEmptyString() throws Exception {
        assertNull(DateUtil.toDate(""));
    }

    /**
     * @throws Exception
     */
    public void testToDateWithPattern() throws Exception {
        assertNotNull(DateUtil.toDate("20080201", "yyyyMMdd"));
    }

    /**
     * @throws Exception
     */
    public void testToDateWithPatternForException() throws Exception {
        try {
            DateUtil.toDate("xx/17/2008", "yyyyMMdd");
            fail();
        } catch (ParseRuntimeException e) {
            System.out.println(e);
            assertEquals("xx/17/2008", e.getText());
        }
    }

    /**
     * @throws Exception
     */
    public void testToString() throws Exception {
        assertEquals("19700101", DateUtil.toString(new Date(0), "yyyyMMdd"));
    }

    /**
     * @throws Exception
     */
    public void testToStringForPattenNull() throws Exception {
        try {
            DateUtil.toString(new Date(0), null);
            fail();
        } catch (NullPointerException t) {
            System.out.println(t);
        }
    }

    /**
     * @throws Exception
     */
    public void testToStringForException() throws Exception {
        try {
            DateUtil.toString(new Date(0), "xxx");
            fail();
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }
}