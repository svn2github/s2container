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

/**
 * A class having environment information.
 * <p>
 * The most important element is stage. It represents where we are running. Its
 * standard values are as follows.
 * </p>
 * <table border="1">
 * <tr>
 * <th>value</th>
 * <th>description</th>
 * </tr>
 * <tr>
 * <td><code>ut</code></td>
 * <td>Unit testing using JUnit.</td>
 * </tr>
 * <tr>
 * <td><code>ct</code></td>
 * <td>Combination test using an application server such as tomcat that runs in
 * your local PC.</td>
 * </tr>
 * <tr>
 * <td><code>it</code></td>
 * <td>Integration test using an application server that runs in remote server.
 * </td>
 * </tr>
 * <tr>
 * <td><code>production</code></td>
 * <td>Production.</td>
 * </tr>
 * </table>
 * <p>
 * When we choose CT stage, Seasar3 runs in HOT deployment mode. HOT deployment
 * means Seasar3 recognizes the source code changes without restarting
 * application server. CT stage means stage value starts with ct.
 * </p>
 * 
 * @author higa
 * @since 3.0
 */
public final class Env {

    /** stage key */
    public static final String STAGE_KEY = "stage";

    /** Unit tesing */
    public static final String UT = "ut";

    /** Combination testing */
    public static final String CT = "ct";

    /** Integration testing */
    public static final String IT = "it";

    /** Product */
    public static final String PRODUCTION = "production";

    /** Default setting file path */
    public static final String DEFAULT_FILE_PATH = "env.properties";

    private static final String STAGE_SEPARATOR = "_";

    private static Properties envInfo;

    static {
        initialize(DEFAULT_FILE_PATH);
    }

    private Env() {
    }

    /**
     * Initializes environment information.
     * 
     * @param filePath
     * 
     * @see {@link PropertiesUtil#create(String)}
     */
    public static void initialize(String filePath) {
        envInfo = PropertiesUtil.create(filePath);
    }

    /**
     * Returns stage value. If value does not exist, returns
     * {@link #PRODUCTION production}.
     * 
     * @return stage value
     */
    public static String getStage() {
        if (envInfo == null) {
            return PRODUCTION;
        }
        String value = (String) envInfo.get(STAGE_KEY);
        if (value == null || value.length() == 0) {
            return PRODUCTION;
        }
        return value;
    }

    /**
     * Returns environment information.
     * 
     * @return environment information
     */
    public static Properties getEnvInfo() {
        return envInfo;
    }

    /**
     * Sets environment information
     * 
     * @param props
     *            environment information
     */
    public static void setEnvInfo(Properties props) {
        Env.envInfo = props;
    }

    /**
     * Returns value for key. If key_stage such as aaa_it exists, returns the
     * key's value. If value is empty string, returns null.
     * 
     * @param key
     * @return
     */
    public static String getStringValue(String key) {
        if (envInfo == null) {
            return null;
        }
        String value = null;
        String stage = getStage();
        if (!PRODUCTION.equals(stage)) {
            value = envInfo.getProperty(key + STAGE_SEPARATOR + stage);
            if (value != null && value.length() > 0) {
                return value;
            }
        }
        value = envInfo.getProperty(key);
        if ("".equals(value)) {
            return null;
        }
        return value;
    }

    /**
     * Returns byte value for key.
     * 
     * @param key
     * @return
     * @see {@link #getStringValue(String)}
     */
    public static Byte getByteValue(String key) {
        String value = getStringValue(key);
        if (value == null) {
            return null;
        }
        return Byte.valueOf(value);
    }

    /**
     * Returns byte value for key. If value is null, returns default value.
     * 
     * @param key
     * @param defaultValue
     * @return
     * @see {@link #getByteValue(String)}
     */
    public static byte getByteValue(String key, byte defaultValue) {
        Byte value = getByteValue(key);
        if (value == null) {
            return defaultValue;
        }
        return value.byteValue();
    }

    /**
     * Returns short value for key.
     * 
     * @param key
     * @return
     * @see {@link #getStringValue(String)}
     */
    public static Short getShortValue(String key) {
        String value = getStringValue(key);
        if (value == null) {
            return null;
        }
        return Short.valueOf(value);
    }

    /**
     * Returns short value for key. If value is null, returns default value.
     * 
     * @param key
     * @param defaultValue
     * @return
     * @see {@link #getShortValue(String)}
     */
    public static short getShortValue(String key, short defaultValue) {
        Short value = getShortValue(key);
        if (value == null) {
            return defaultValue;
        }
        return value.shortValue();
    }

    /**
     * Returns integer value for key.
     * 
     * @param key
     * @return
     * @see {@link #getStringValue(String)}
     */
    public static Integer getIntegerValue(String key) {
        String value = getStringValue(key);
        if (value == null) {
            return null;
        }
        return Integer.valueOf(value);
    }

    /**
     * Returns int value for key. If value is null, returns default value.
     * 
     * @param key
     * @param defaultValue
     * @return
     * @see {@link #getIntegerValue(String)}
     */
    public static int getIntValue(String key, int defaultValue) {
        Integer value = getIntegerValue(key);
        if (value == null) {
            return defaultValue;
        }
        return value.intValue();
    }

    /**
     * Returns long value for key.
     * 
     * @param key
     * @return
     * @see {@link #getStringValue(String)}
     */
    public static Long getLongValue(String key) {
        String value = getStringValue(key);
        if (value == null) {
            return null;
        }
        return Long.valueOf(value);
    }

    /**
     * Returns long value for key. If value is null, returns default value.
     * 
     * @param key
     * @param defaultValue
     * @return
     * @see {@link #getLongValue(String)}
     */
    public static long getLongValue(String key, long defaultValue) {
        Long value = getLongValue(key);
        if (value == null) {
            return defaultValue;
        }
        return value.longValue();
    }

    /**
     * Returns float value for key.
     * 
     * @param key
     * @return
     * @see {@link #getStringValue(String)}
     */
    public static Float getFloatValue(String key) {
        String value = getStringValue(key);
        if (value == null) {
            return null;
        }
        return Float.valueOf(value);
    }

    /**
     * Returns float value for key. If value is null, returns default value.
     * 
     * @param key
     * @param defaultValue
     * @return
     * @see {@link #getFloatValue(String)}
     */
    public static float getFloatValue(String key, float defaultValue) {
        Float value = getFloatValue(key);
        if (value == null) {
            return defaultValue;
        }
        return value.floatValue();
    }

    /**
     * Returns double value for key.
     * 
     * @param key
     * @return
     * @see {@link #getStringValue(String)}
     */
    public static Double getDoubleValue(String key) {
        String value = getStringValue(key);
        if (value == null) {
            return null;
        }
        return Double.valueOf(value);
    }

    /**
     * Returns double value for key. If value is null, returns default value.
     * 
     * @param key
     * @param defaultValue
     * @return
     * @see {@link #getDoubleValue(String)}
     */
    public static double getDoubleValue(String key, double defaultValue) {
        Double value = getDoubleValue(key);
        if (value == null) {
            return defaultValue;
        }
        return value.doubleValue();
    }

    /**
     * Returns boolean value for key.
     * 
     * @param key
     * @return
     * @see {@link #getStringValue(String)}
     */
    public static Boolean getBooleanValue(String key) {
        String value = getStringValue(key);
        if (value == null) {
            return null;
        }
        return Boolean.valueOf(value);
    }

    /**
     * Returns boolean value for key. If value is null, returns default value.
     * 
     * @param key
     * @param defaultValue
     * @return
     * @see {@link #getBooleanValue(String)}
     */
    public static boolean getBooleanValue(String key, boolean defaultValue) {
        Boolean value = getBooleanValue(key);
        if (value == null) {
            return defaultValue;
        }
        return value.booleanValue();
    }

    /**
     * Returns character value for key.
     * 
     * @param key
     * @return
     * @see {@link #getStringValue(String)}
     */
    public static Character getCharacterValue(String key) {
        String value = getStringValue(key);
        if (value == null) {
            return null;
        }
        return Character.valueOf(value.charAt(0));
    }

    /**
     * Returns char value for key. If value is null, returns default value.
     * 
     * @param key
     * @param defaultValue
     * @return
     * @see {@link #getCharacterValue(String)}
     */
    public static char getCharValue(String key, char defaultValue) {
        Character value = getCharacterValue(key);
        if (value == null) {
            return defaultValue;
        }
        return value.charValue();
    }

    /**
     * Determines if stage is {@link #UT}. Returns true if stage starts with
     * ut.
     * 
     * @return
     */
    public static boolean isUt() {
        return getStage().startsWith(UT);
    }

    /**
     * Determines if stage is {@link #CT}. Returns true if stage starts with
     * ct.
     * 
     * @return
     */
    public static boolean isCt() {
        return getStage().startsWith(CT);
    }

    /**
     * Determines if stage is {@link #IT}. Returns true if stage starts with
     * it.
     * 
     * @return
     */
    public static boolean isIt() {
        return getStage().startsWith(IT);
    }

    /**
     * Determines if stage is {@link #PRODUCTION}. Returns true if stage starts
     * with production.
     * 
     * @return
     */
    public static boolean isProduction() {
        return getStage().startsWith(PRODUCTION);
    }

    /**
     * Determines if stage is HOT deployment. Returns true if stage is
     * {@link #CT}.
     * 
     * @return
     */
    public static boolean isHotDeployment() {
        return isCt();
    }
}