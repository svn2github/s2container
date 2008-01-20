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
public class ByteConversionUtilTest extends TestCase {

    /**
     * @throws Exception
     */
    public void testToByteForNull() throws Exception {
        assertNull(ByteConversionUtil.toByte(null));
    }

    /**
     * @throws Exception
     */
    public void testToByteForByte() throws Exception {
        Byte value = Byte.valueOf("1");
        assertEquals(value, ByteConversionUtil.toByte(value));
    }

    /**
     * @throws Exception
     */
    public void testToByteForNumber() throws Exception {
        Integer i = Integer.valueOf(1);
        assertEquals((byte) 1, ByteConversionUtil.toByte(i).byteValue());
    }

    /**
     * @throws Exception
     */
    public void testToByteForString() throws Exception {
        assertEquals((byte) 1, ByteConversionUtil.toByte("1").byteValue());
    }

    /**
     * @throws Exception
     */
    public void testToByteForTrue() throws Exception {
        assertEquals((byte) 1, ByteConversionUtil.toByte(Boolean.TRUE)
                .byteValue());
    }

    /**
     * @throws Exception
     */
    public void testToByteForFalse() throws Exception {
        assertEquals((byte) 0, ByteConversionUtil.toByte(Boolean.FALSE)
                .byteValue());
    }

    /**
     * @throws Exception
     */
    public void testToByteForException() throws Exception {
        try {
            ByteConversionUtil.toByte("xx");
            fail();
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
    }

    /**
     * @throws Exception
     */
    public void testToPrimitiveByteForNull() throws Exception {
        assertEquals(0, ByteConversionUtil.toPrimitiveByte(null));
    }

    /**
     * @throws Exception
     */
    public void testToPrimitiveByteForNumber() throws Exception {
        Integer i = Integer.valueOf(1);
        assertEquals((byte) 1, ByteConversionUtil.toPrimitiveByte(i));
    }

    /**
     * @throws Exception
     */
    public void testToPrimitiveByteForString() throws Exception {
        assertEquals((byte) 1, ByteConversionUtil.toPrimitiveByte("1"));
    }

    /**
     * @throws Exception
     */
    public void testToPrimitiveByteForTrue() throws Exception {
        assertEquals((byte) 1, ByteConversionUtil.toPrimitiveByte(Boolean.TRUE));
    }

    /**
     * @throws Exception
     */
    public void testToPrimitiveByteForFalse() throws Exception {
        assertEquals((byte) 0, ByteConversionUtil
                .toPrimitiveByte(Boolean.FALSE));
    }

    /**
     * @throws Exception
     */
    public void testToPrimitiveByteForException() throws Exception {
        try {
            ByteConversionUtil.toPrimitiveByte("xx");
            fail();
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
    }
}