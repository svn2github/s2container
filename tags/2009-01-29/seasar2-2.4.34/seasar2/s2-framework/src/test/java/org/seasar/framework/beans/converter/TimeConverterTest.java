/*
 * Copyright 2004-2009 the Seasar Foundation and the Others.
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
package org.seasar.framework.beans.converter;

import junit.framework.TestCase;

/**
 * @author higa
 * 
 */
public class TimeConverterTest extends TestCase {

    /**
     * @throws Exception
     */
    public void testGetAsObjectAndGetAsString() throws Exception {
        TimeConverter converter = new TimeConverter("HH:mm:ss");
        java.sql.Time result = (java.sql.Time) converter
                .getAsObject("12:34:56");
        System.out.println(result);
        assertEquals("12:34:56", converter.getAsString(result));
    }

    /**
     * @throws Exception
     */
    public void testIsTarget() throws Exception {
        TimeConverter converter = new TimeConverter("yyyy/MM/dd");
        assertTrue(converter.isTarget(java.sql.Time.class));
        assertFalse(converter.isTarget(java.util.Date.class));
    }
}