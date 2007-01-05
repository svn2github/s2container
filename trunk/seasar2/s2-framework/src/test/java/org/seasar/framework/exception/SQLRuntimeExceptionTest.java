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
package org.seasar.framework.exception;

import java.sql.SQLException;
import java.util.Locale;

import junit.framework.TestCase;

/**
 * @author manhole
 */
public class SQLRuntimeExceptionTest extends TestCase {

    private Locale defaultLocale;

    protected void setUp() throws Exception {
        super.setUp();
        defaultLocale = Locale.getDefault();
    }

    protected void tearDown() throws Exception {
        Locale.setDefault(defaultLocale);
        super.tearDown();
    }

    public void testErrorMessage_ja() throws Exception {
        // ## Arrange ##
        Locale.setDefault(Locale.JAPANESE);
        SQLException sqlException = new SQLException("some reason", "fooState",
                7650);

        SQLRuntimeException sqlRuntimeException = new SQLRuntimeException(
                sqlException);

        // ## Act ##
        String message = sqlRuntimeException.getMessage();

        // ## Assert ##
        System.out.println(message);
        assertContains(message, "ErrorCode=7650");
        assertContains(message, "SQLState=fooState");
        assertContains(message, "some reason");
    }

    private void assertContains(String s, String contained) {
        assertEquals(s, true, s.indexOf(contained) > -1);
    }

    public void testErrorMessage_en() throws Exception {
        // ## Arrange ##
        Locale.setDefault(Locale.ENGLISH);
        SQLException sqlException = new SQLException("manyReason", "barState",
                1234);

        SQLRuntimeException sqlRuntimeException = new SQLRuntimeException(
                sqlException);

        // ## Act ##
        String message = sqlRuntimeException.getMessage();

        // ## Assert ##
        System.out.println(message);
        assertContains(message, "ErrorCode=1234");
        assertContains(message, "SQLState=barState");
        assertContains(message, "manyReason");
    }

}
