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

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

/**
 * A class to have environment information.
 * 
 * @author higa
 * @since 3.0
 */
public final class Env {

    /**
     * The deployment key.
     */
    public static final String DEPLOYMENT_KEY = "s3.deployment";

    /**
     * The environment name key.
     */
    public static final String ENV_NAME_KEY = "s3.env.name";

    /**
     * HOT value.
     */
    public static final String HOT = "hot";

    /**
     * COOL value.
     */
    public static final String COOL = "cool";

    /**
     * The default path.
     */
    public static final String DEFAULT_PATH = "env.properties";

    private static final String ENV_SEPARATOR = "_";

    private static Properties envInfo;

    static {
        initialize();
    }

    private Env() {
    }

    /**
     * Initializes the environment information.
     */
    public static void initialize() {
        initialize(DEFAULT_PATH);
    }

    /**
     * Initializes the environment information.
     * 
     * @param path
     *            the path.
     */
    public static void initialize(String path) {
        if (path == null) {
            throw new NullPointerException("path");
        }
        envInfo = new Properties();
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            URL url = loader.getResource(path);
            if (url == null) {
                return;
            }
            URLConnection connection = url.openConnection();
            connection.setUseCaches(false);
            InputStream in = connection.getInputStream();
            try {
                envInfo.load(in);
            } finally {
                in.close();
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(path, e);
        }
    }

    /**
     * Returns the environment name.
     * 
     * @return the environment name.
     */
    public static String getEnvName() {
        return envInfo.getProperty(ENV_NAME_KEY);
    }

    /**
     * Returns the deployment value.
     * 
     * @return the deployment value.
     */
    public static String getDeployment() {
        return envInfo.getProperty(DEPLOYMENT_KEY);
    }

    /**
     * Returns value for key. If key_ + "environment name" exists, returns the
     * value.
     * 
     * @param key
     *            the key.
     * @return the value.
     */
    public static String getStringValue(String key) {
        String value = null;
        String envName = getEnvName();
        if (envName != null && envName.length() > 0) {
            value = envInfo.getProperty(key + ENV_SEPARATOR + envName);
            if (value != null) {
                return value;
            }
        }
        return envInfo.getProperty(key);
    }

    /**
     * Returns the byte value for key.
     * 
     * @param key
     *            the key.
     * @return the byte value.
     * @see {@link #getStringValue(String)}
     */
    public static Byte getByteValue(String key) {
        String value = getStringValue(key);
        if (value != null && value.length() > 0) {
            try {
                return Byte.valueOf(value);
            } catch (Throwable t) {
                throw new IllegalArgumentException("\"" + value + "\"(" + key
                        + ") can not be converted to byte.", t);
            }
        }
        return null;
    }

    /**
     * Returns the short value for key.
     * 
     * @param key
     *            the key.
     * @return the short value.
     */
    public static Short getShortValue(String key) {
        String value = getStringValue(key);
        if (value != null && value.length() > 0) {
            try {
                return Short.valueOf(value);
            } catch (Throwable t) {
                throw new IllegalArgumentException("\"" + value + "\"(" + key
                        + ") can not be converted to short.", t);
            }
        }
        return null;
    }

    /**
     * Returns the integer value for key.
     * 
     * @param key
     *            the key.
     * @return the integer value.
     */
    public static Integer getIntegerValue(String key) {
        String value = getStringValue(key);
        if (value != null && value.length() > 0) {
            try {
                return Integer.valueOf(value);
            } catch (Throwable t) {
                throw new IllegalArgumentException("\"" + value + "\"(" + key
                        + ") can not be converted to integer.", t);
            }
        }
        return null;
    }

    /**
     * Returns the long value for key.
     * 
     * @param key
     *            the key.
     * @return the long value.
     */
    public static Long getLongValue(String key) {
        String value = getStringValue(key);
        if (value != null && value.length() > 0) {
            try {
                return Long.valueOf(value);
            } catch (Throwable t) {
                throw new IllegalArgumentException("\"" + value + "\"(" + key
                        + ") can not be converted to long.", t);
            }
        }
        return null;
    }

    /**
     * Returns the float value for key.
     * 
     * @param key
     *            the key.
     * @return the float value.
     */
    public static Float getFloatValue(String key) {
        String value = getStringValue(key);
        if (value != null && value.length() > 0) {
            try {
                return Float.valueOf(value);
            } catch (Throwable t) {
                throw new IllegalArgumentException("\"" + value + "\"(" + key
                        + ") can not be converted to float.", t);
            }
        }
        return null;
    }

    /**
     * Returns the double value for key.
     * 
     * @param key
     *            the key.
     * @return the float value.
     */
    public static Double getDoubleValue(String key) {
        String value = getStringValue(key);
        if (value != null && value.length() > 0) {
            try {
                return Double.valueOf(value);
            } catch (Throwable t) {
                throw new IllegalArgumentException("\"" + value + "\"(" + key
                        + ") can not be converted to double.", t);
            }
        }
        return null;
    }

    /**
     * Returns the boolean value for key.
     * 
     * @param key
     *            the key.
     * @return the boolean value.
     */
    public static Boolean getBooleanValue(String key) {
        String value = getStringValue(key);
        if (value != null && value.length() > 0) {
            try {
                return Boolean.valueOf(value);
            } catch (Throwable t) {
                throw new IllegalArgumentException("\"" + value + "\"(" + key
                        + ") can not be converted to boolean.", t);
            }
        }
        return null;
    }

    /**
     * Determines if the deployment is HOT. Returns true if the deployment is
     * HOT.
     * 
     * @return whether the deployment is HOT.
     */
    public static boolean isHotDeployment() {
        return HOT.equalsIgnoreCase(getDeployment());
    }

    /**
     * Determines if the deployment is COOL. Returns true if the deployment is
     * not HOT.
     * 
     * @return whether the deployment is COOL.
     */
    public static boolean isCoolDeployment() {
        return !isHotDeployment();
    }
}