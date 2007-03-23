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
package org.seasar3.core;

import java.util.Properties;

import junit.framework.TestCase;

/**
 * @author higa
 * 
 */
public class EnvTest extends TestCase {

    static final String PACKAGE = "org/seasar3/core/";

    static final String ENV_UT_PATH = PACKAGE + "env_ut.properties";

    static final String ENV_CT_PATH = PACKAGE + "env_ct.properties";

    static final String ENV_IT_PATH = PACKAGE + "env_it.properties";

    static final String ENV_IT2_PATH = PACKAGE + "env_it2.properties";

    static final String ENV_PATH = PACKAGE + "env.properties";

    private Properties envInfo;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        envInfo = Env.getEnvInfo();
    }

    @Override
    protected void tearDown() throws Exception {
        Env.setEnvInfo(envInfo);
        super.tearDown();
    }

    /**
     * Test method for {@link Env#initialize(String)}.
     */
    public void testInitializeForIllegalFilePath() {
        Env.initialize("illegal file path");
    }

    /**
     * Test method for {@link Env#initialize(String)} and {@link Env#getStage()}.
     */
    public void testInitializeAndGetStageForIllegalFilePath() {
        Env.initialize("illegal file path");
        assertEquals(Env.PRODUCTION, Env.getStage());
    }

    /**
     * Test method for {@link Env#getStage()}.
     */
    public void testGetStageForStageValueExist() {
        Env.initialize(ENV_IT_PATH);
        assertEquals(Env.IT, Env.getStage());
    }

    /**
     * Test method for {@link Env#getStage()}.
     */
    public void testGetStageForStageValueDoesNotExist() {
        Env.initialize(ENV_PATH);
        assertEquals(Env.PRODUCTION, Env.getStage());
    }

    /**
     * Test method for {@link Env#getStringValue(String)}.
     */
    public void testGetStringValueForKeyDoesNotExist() {
        Env.initialize(ENV_IT_PATH);
        assertNull(Env.getStringValue("does not exist key"));
    }

    /**
     * Test method for {@link Env#getStringValue(String)}.
     */
    public void testGetStringValueForIllegalFilePath() {
        Env.initialize("illegal file path");
        assertNull(Env.getStringValue("does not exist key"));
    }

    /**
     * Test method for {@link Env#getStringValue(String)}.
     */
    public void testGetStringValueForKeyExists() {
        Env.initialize(ENV_IT_PATH);
        String value = Env.getStringValue("aaa");
        assertEquals("111", value);
    }

    /**
     * Test method for {@link Env#getStringValue(String)}.
     */
    public void testGetStringValueForStage() {
        Env.initialize(ENV_IT_PATH);
        assertEquals("222it", Env.getStringValue("bbb"));
    }

    /**
     * Test method for {@link Env#getStringValue(String)}.
     */
    public void testGetStringValueForEmptyValue() {
        Env.initialize(ENV_IT_PATH);
        assertNull(Env.getStringValue("ccc"));
    }

    /**
     * Test method for {@link Env#getStringValue(String)}.
     */
    public void testGetStringValueForStageAndEmptyValue() {
        Env.initialize(ENV_IT_PATH);
        assertEquals("444", Env.getStringValue("ddd"));
    }

    /**
     * Test method for {@link Env#getByteValue(String)}.
     */
    public void testGetByteValueForKeyExists() {
        Env.initialize(ENV_IT_PATH);
        byte value = Env.getByteValue("eee");
        assertEquals(1, value);
    }

    /**
     * Test method for {@link Env#getByteValue(String)}.
     */
    public void testGetByteValueForKeyDoesNotExist() {
        Env.initialize(ENV_PATH);
        assertNull(Env.getByteValue("eee"));
    }

    /**
     * Test method for {@link Env#getByteValue(String, byte)}.
     */
    public void testGetByteValueForKeyDoesNotExistAndDefaultValue() {
        Env.initialize(ENV_PATH);
        assertEquals(2, Env.getByteValue("eee", (byte) 2));
    }

    /**
     * Test method for {@link Env#getShortValue(String)}.
     */
    public void testGetShortValueForKeyExists() {
        Env.initialize(ENV_IT_PATH);
        short value = Env.getShortValue("eee");
        assertEquals(1, value);
    }

    /**
     * Test method for {@link Env#getShortValue(String)}.
     */
    public void testGetShortValueForKeyDoesNotExist() {
        Env.initialize(ENV_PATH);
        assertNull(Env.getShortValue("eee"));
    }

    /**
     * Test method for {@link Env#getShortValue(String, short)}.
     */
    public void testGetShortValueForKeyDoesNotExistAndDefaultValue() {
        Env.initialize(ENV_PATH);
        assertEquals(2, Env.getShortValue("eee", (short) 2));
    }

    /**
     * Test method for {@link Env#getIntegerValue(String)}.
     */
    public void testGetIntegerValueForKeyExists() {
        Env.initialize(ENV_IT_PATH);
        int value = Env.getIntegerValue("eee");
        assertEquals(1, value);
    }

    /**
     * Test method for {@link Env#getIntegerValue(String)}.
     */
    public void testGetIntegerValueForKeyDoesNotExist() {
        Env.initialize(ENV_PATH);
        assertNull(Env.getIntegerValue("eee"));
    }

    /**
     * Test method for {@link Env#getIntValue(String, int)}.
     */
    public void testGetIntValueForKeyDoesNotExistAndDefaultValue() {
        Env.initialize(ENV_PATH);
        assertEquals(2, Env.getIntValue("eee", 2));
    }

    /**
     * Test method for {@link Env#getLongValue(String)}.
     */
    public void testGetLongValueForKeyExists() {
        Env.initialize(ENV_IT_PATH);
        long value = Env.getLongValue("eee");
        assertEquals(1, value);
    }

    /**
     * Test method for {@link Env#getLongValue(String)}.
     */
    public void testGetLongValueForKeyDoesNotExist() {
        Env.initialize(ENV_PATH);
        assertNull(Env.getLongValue("eee"));
    }

    /**
     * Test method for {@link Env#getLongValue(String, long)}.
     */
    public void testGetLongValueForKeyDoesNotExistAndDefaultValue() {
        Env.initialize(ENV_PATH);
        assertEquals(2, Env.getLongValue("eee", 2));
    }

    /**
     * Test method for {@link Env#getFloatValue(String)}.
     */
    public void testGetFloatValueForKeyExists() {
        Env.initialize(ENV_IT_PATH);
        float value = Env.getFloatValue("eee");
        assertEquals(1f, value);
    }

    /**
     * Test method for {@link Env#getFloatValue(String)}.
     */
    public void testGetFloatValueForKeyDoesNotExist() {
        Env.initialize(ENV_PATH);
        assertNull(Env.getFloatValue("eee"));
    }

    /**
     * Test method for {@link Env#getFloatValue(String, float)}.
     */
    public void testGetFloatValueForKeyDoesNotExistAndDefaultValue() {
        Env.initialize(ENV_PATH);
        assertEquals(2f, Env.getFloatValue("eee", 2));
    }

    /**
     * Test method for {@link Env#getDoubleValue(String)}.
     */
    public void testGetDoubleValueForKeyExists() {
        Env.initialize(ENV_IT_PATH);
        double value = Env.getDoubleValue("eee");
        assertEquals(1d, value);
    }

    /**
     * Test method for {@link Env#getDoubleValue(String)}.
     */
    public void testGetDoubleValueForKeyDoesNotExist() {
        Env.initialize(ENV_PATH);
        assertNull(Env.getDoubleValue("eee"));
    }

    /**
     * Test method for {@link Env#getDoubleValue(String, double)}.
     */
    public void testGetDoubleValueForKeyDoesNotExistAndDefaultValue() {
        Env.initialize(ENV_PATH);
        assertEquals(2d, Env.getDoubleValue("eee", 2));
    }

    /**
     * Test method for {@link Env#getBooleanValue(String)}.
     */
    public void testGetBooleanValueForKeyExists() {
        Env.initialize(ENV_IT_PATH);
        boolean value = Env.getBooleanValue("fff");
        assertTrue(value);
    }

    /**
     * Test method for {@link Env#getBooleanValue(String)}.
     */
    public void testGetBooleanValueForKeyDoesNotExist() {
        Env.initialize(ENV_PATH);
        assertNull(Env.getBooleanValue("fff"));
    }

    /**
     * Test method for {@link Env#getBooleanValue(String, boolean)}.
     */
    public void testGetBooleanValueForKeyDoesNotExistAndDefaultValue() {
        Env.initialize(ENV_PATH);
        assertFalse(Env.getBooleanValue("fff", false));
    }

    /**
     * Test method for {@link Env#getCharacterValue(String)}.
     */
    public void testGetCharacterValueForKeyExists() {
        Env.initialize(ENV_IT_PATH);
        char value = Env.getCharacterValue("ggg");
        assertEquals('a', value);
    }

    /**
     * Test method for {@link Env#getCharacterValue(String)}.
     */
    public void testGetCharacterValueForKeyDoesNotExist() {
        Env.initialize(ENV_PATH);
        assertNull(Env.getCharacterValue("ggg"));
    }

    /**
     * Test method for {@link Env#getCharValue(String, char)}.
     */
    public void testGetCharValueForKeyDoesNotExistAndDefaultValue() {
        Env.initialize(ENV_PATH);
        assertEquals('b', Env.getCharValue("ggg", 'b'));
    }

    /**
     * Test method for {@link Env#isUt()}.
     */
    public void testIsUtForUt() {
        Env.initialize(ENV_UT_PATH);
        assertTrue(Env.isUt());
    }

    /**
     * Test method for {@link Env#isUt()}.
     */
    public void testIsUtForNotUt() {
        Env.initialize(ENV_PATH);
        assertFalse(Env.isUt());
    }

    /**
     * Test method for {@link Env#isCt()}.
     */
    public void testIsCtForCt() {
        Env.initialize(ENV_CT_PATH);
        assertTrue(Env.isCt());
    }

    /**
     * Test method for {@link Env#isCt()}.
     */
    public void testIsCtForNotCt() {
        Env.initialize(ENV_PATH);
        assertFalse(Env.isCt());
    }

    /**
     * Test method for {@link Env#isIt()}.
     */
    public void testIsItForIt() {
        Env.initialize(ENV_IT2_PATH);
        assertTrue(Env.isIt());
    }

    /**
     * Test method for {@link Env#isIt()}.
     */
    public void testIsItForNotIt() {
        Env.initialize(ENV_PATH);
        assertFalse(Env.isIt());
    }

    /**
     * Test method for {@link Env#isProduction()}.
     */
    public void testIsProductionForProduction() {
        Env.initialize(ENV_PATH);
        assertTrue(Env.isProduction());
    }

    /**
     * Test method for {@link Env#isProduction()}.
     */
    public void testIsProductionForNotProduction() {
        Env.initialize(ENV_UT_PATH);
        assertFalse(Env.isProduction());
    }

    /**
     * Test method for {@link Env#isHotDeployment()}.
     */
    public void testIsHotDeploymentForCt() {
        Env.initialize(ENV_CT_PATH);
        assertTrue(Env.isHotDeployment());
    }

    /**
     * Test method for {@link Env#isHotDeployment()}.
     */
    public void testIsHotDeploymentForNotCt() {
        Env.initialize(ENV_PATH);
        assertFalse(Env.isHotDeployment());
    }
}