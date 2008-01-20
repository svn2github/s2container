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

import junit.framework.TestCase;

/**
 * @author higa
 * 
 */
public class ShortConversionUtilTest extends TestCase {

    /**
     * @throws Exception
     */
    public void testToShortForNull() throws Exception {
        assertNull(ShortConversionUtil.toShort(null));
    }

    /**
     * @throws Exception
     */
    public void testToShortForShort() throws Exception {
        Short value = Short.valueOf("1");
        assertEquals(value, ShortConversionUtil.toShort(value));
    }

    /**
     * @throws Exception
     */
    public void testToShortForNumber() throws Exception {
        Integer i = Integer.valueOf(1);
        assertEquals((short) 1, ShortConversionUtil.toShort(i).shortValue());
    }

    /**
     * @throws Exception
     */
    public void testToShortForString() throws Exception {
        assertEquals((short) 1, ShortConversionUtil.toShort("1").shortValue());
    }

    /**
     * @throws Exception
     */
    public void testToShortForTrue() throws Exception {
        assertEquals((short) 1, ShortConversionUtil.toShort(Boolean.TRUE)
                .shortValue());
    }

    /**
     * @throws Exception
     */
    public void testToShortForFalse() throws Exception {
        assertEquals((short) 0, ShortConversionUtil.toShort(Boolean.FALSE)
                .shortValue());
    }

    /**
     * @throws Exception
     */
    public void testToShortForException() throws Exception {
        try {
            ShortConversionUtil.toShort("xx");
            fail();
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
    }

    /**
     * @throws Exception
     */
    public void testToPrimitiveShortForNull() throws Exception {
        assertEquals(0, ShortConversionUtil.toPrimitiveShort(null));
    }

    /**
     * @throws Exception
     */
    public void testToPrimitiveShortForNumber() throws Exception {
        Integer i = Integer.valueOf(1);
        assertEquals((short) 1, ShortConversionUtil.toPrimitiveShort(i));
    }

    /**
     * @throws Exception
     */
    public void testToPrimitiveShortForString() throws Exception {
        assertEquals((short) 1, ShortConversionUtil.toPrimitiveShort("1"));
    }

    /**
     * @throws Exception
     */
    public void testToPrimitiveShortForTrue() throws Exception {
        assertEquals((short) 1, ShortConversionUtil
                .toPrimitiveShort(Boolean.TRUE));
    }

    /**
     * @throws Exception
     */
    public void testToPrimitiveShortForFalse() throws Exception {
        assertEquals((short) 0, ShortConversionUtil
                .toPrimitiveShort(Boolean.FALSE));
    }

    /**
     * @throws Exception
     */
    public void testToPrimitiveShortForException() throws Exception {
        try {
            ShortConversionUtil.toPrimitiveShort("xx");
            fail();
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
    }
}