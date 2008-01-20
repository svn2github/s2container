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
public class DateConversionUtilTest extends TestCase {

    /**
     * @throws Exception
     */
    public void testToDateForNull() throws Exception {
        assertNull(DateConversionUtil.toDate(null));
    }

    /**
     * @throws Exception
     */
    public void testToDateForDate() throws Exception {
        Date date = new Date();
        assertEquals(date, DateConversionUtil.toDate(date));
    }

    /**
     * @throws Exception
     */
    public void testToDateForCalendar() throws Exception {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        assertEquals(date, DateConversionUtil.toDate(cal));
    }

    /**
     * @throws Exception
     */
    public void testToDateForString() throws Exception {
        assertNotNull(DateConversionUtil.toDate("01/17/2008", Locale.ENGLISH));
    }

    /**
     * @throws Exception
     */
    public void testToDateForException() throws Exception {
        try {
            DateConversionUtil.toDate("xx/17/2008", Locale.ENGLISH);
            fail();
        } catch (ParseRuntimeException e) {
            System.out.println(e);
            assertEquals("xx/17/2008", e.getText());
        }
    }
}