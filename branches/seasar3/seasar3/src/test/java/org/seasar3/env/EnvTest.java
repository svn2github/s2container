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
package org.seasar3.env;

import junit.framework.TestCase;

/**
 * @author higa
 * 
 */
public class EnvTest extends TestCase {

    private static final String PACKAGE = "org/seasar3/env/";

    private static final String ENV_PATH = PACKAGE + "env_test.properties";

    private static final String ENV_PATH2 = PACKAGE + "env_test2.properties";

    private static final String ENV_COOL_PATH = PACKAGE + "env_cool.properties";

    private static final String ENV_WARM_PATH = PACKAGE + "env_warm.properties";

    @Override
    protected void tearDown() throws Exception {
        Env.initialize();
    }

    /**
     * 
     */
    public void testInitializeForIllegalFilePath() {
        Env.initialize("illegal file path");
        assertNull(Env.getEnvName());
    }

    /**
     * 
     */
    public void testGetEnvName() {
        Env.initialize(ENV_PATH);
        assertEquals("test", Env.getEnvName());
    }

    /**
     * 
     */
    public void testGetEnvNameForKeyDoesNotExist() {
        Env.initialize(ENV_PATH2);
        assertNull(Env.getEnvName());
    }

    /**
     * 
     */
    public void testGetDeployment() {
        Env.initialize(ENV_PATH);
        assertEquals("hot", Env.getDeployment());
    }

    /**
     * 
     */
    public void testGetDeploymentForKeyDoesNotExist() {
        Env.initialize(ENV_PATH2);
        assertNull(Env.getDeployment());
    }

    /**
     * 
     */
    public void testGetStringValue() {
        Env.initialize(ENV_PATH);
        assertEquals("1", Env.getStringValue("app.number"));
    }

    /**
     * 
     */
    public void testGetStringValueForKeyDoesNotExist() {
        Env.initialize(ENV_PATH);
        assertNull(Env.getStringValue("illegal key"));
    }

    /**
     * 
     */
    public void testGetStringValueForIllegalFilePath() {
        Env.initialize("illegal file path");
        assertNull(Env.getStringValue("illegal key"));
    }

    /**
     * 
     */
    public void testGetStringValueForEnvName() {
        Env.initialize(ENV_PATH);
        assertEquals("aaa2", Env.getStringValue("app.hoge"));
    }

    /**
     * 
     */
    public void testGetStringValueForEnvName2() {
        Env.initialize(ENV_PATH2);
        assertEquals("aaa", Env.getStringValue("app.hoge"));
    }

    /**
     * 
     */
    public void testGetByteValue() {
        Env.initialize(ENV_PATH);
        assertEquals(Byte.valueOf("1"), Env.getByteValue("app.number"));
    }

    /**
     * 
     */
    public void testGetByteValueForKeyDoesNotExist() {
        Env.initialize(ENV_PATH);
        assertNull(Env.getByteValue("illegal key"));
    }

    /**
     * 
     */
    public void testGetByteValueForIllegalValue() {
        Env.initialize(ENV_PATH);
        try {
            Env.getByteValue("app.hoge");
            fail();
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    /**
     * 
     */
    public void testGetShortValue() {
        Env.initialize(ENV_PATH);
        assertEquals(Short.valueOf("1"), Env.getShortValue("app.number"));
    }

    /**
     * 
     */
    public void testGetShortValueForKeyDoesNotExist() {
        Env.initialize(ENV_PATH);
        assertNull(Env.getShortValue("illegal key"));
    }

    /**
     * 
     */
    public void testGetShortValueForIllegalValue() {
        Env.initialize(ENV_PATH);
        try {
            Env.getShortValue("app.hoge");
            fail();
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    /**
     * 
     */
    public void testGetIntegerValue() {
        Env.initialize(ENV_PATH);
        assertEquals(Integer.valueOf("1"), Env.getIntegerValue("app.number"));
    }

    /**
     * 
     */
    public void testGetIntegerValueForKeyDoesNotExist() {
        Env.initialize(ENV_PATH);
        assertNull(Env.getIntegerValue("illegal key"));
    }

    /**
     * 
     */
    public void testGetIntegerValueForIllegalValue() {
        Env.initialize(ENV_PATH);
        try {
            Env.getIntegerValue("app.hoge");
            fail();
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    /**
     * 
     */
    public void testGetLongValue() {
        Env.initialize(ENV_PATH);
        assertEquals(Long.valueOf("1"), Env.getLongValue("app.number"));
    }

    /**
     * 
     */
    public void testGetLongValueForKeyDoesNotExist() {
        Env.initialize(ENV_PATH);
        assertNull(Env.getIntegerValue("illegal key"));
    }

    /**
     * 
     */
    public void testGetLongValueForIllegalValue() {
        Env.initialize(ENV_PATH);
        try {
            Env.getLongValue("app.hoge");
            fail();
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    /**
     * 
     */
    public void testGetFloatValue() {
        Env.initialize(ENV_PATH);
        assertEquals(Float.valueOf("1"), Env.getFloatValue("app.number"));
    }

    /**
     * 
     */
    public void testGetFloatValueForKeyDoesNotExist() {
        Env.initialize(ENV_PATH);
        assertNull(Env.getFloatValue("illegal key"));
    }

    /**
     * 
     */
    public void testGetFloatValueForIllegalValue() {
        Env.initialize(ENV_PATH);
        try {
            Env.getFloatValue("app.hoge");
            fail();
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    /**
     * 
     */
    public void testGetDoubleValue() {
        Env.initialize(ENV_PATH);
        assertEquals(Double.valueOf("1"), Env.getDoubleValue("app.number"));
    }

    /**
     * 
     */
    public void testGetDoubleValueForKeyDoesNotExist() {
        Env.initialize(ENV_PATH);
        assertNull(Env.getDoubleValue("illegal key"));
    }

    /**
     * 
     */
    public void testGetDoubleValueForIllegalValue() {
        Env.initialize(ENV_PATH);
        try {
            Env.getDoubleValue("app.hoge");
            fail();
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    /**
     * 
     */
    public void testGetBooleanValue() {
        Env.initialize(ENV_PATH);
        assertTrue(Env.getBooleanValue("app.boolean"));
    }

    /**
     * 
     */
    public void testGetBooleanValueForKeyDoesNotExist() {
        Env.initialize(ENV_PATH);
        assertNull(Env.getBooleanValue("illegal key"));
    }

    /**
     * 
     */
    public void testGetBooleanValueForIllegalValue() {
        Env.initialize(ENV_PATH);
        assertFalse(Env.getBooleanValue("app.hoge"));
    }

    /**
     * 
     */
    public void testIsHotDeployment() {
        Env.initialize(ENV_PATH);
        assertTrue(Env.isHotDeployment());
    }

    /**
     * 
     */
    public void testIsHotDeployment_cool() {
        Env.initialize(ENV_COOL_PATH);
        assertFalse(Env.isHotDeployment());
    }

    /**
     * 
     */
    public void testIsCoolDeployment() {
        Env.initialize(ENV_COOL_PATH);
        assertTrue(Env.isCoolDeployment());
    }

    /**
     * 
     */
    public void testIsCoolDeployment_hot() {
        Env.initialize(ENV_PATH);
        assertFalse(Env.isCoolDeployment());
    }

    /**
     * 
     */
    public void testIsWarmDeployment() {
        Env.initialize(ENV_WARM_PATH);
        assertTrue(Env.isWarmDeployment());
    }

    /**
     * 
     */
    public void testIsWarmDeployment_hot() {
        Env.initialize(ENV_PATH);
        assertFalse(Env.isWarmDeployment());
    }
}