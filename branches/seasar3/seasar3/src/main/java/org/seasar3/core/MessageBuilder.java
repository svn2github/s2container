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

import java.text.MessageFormat;
import java.util.Locale;

/**
 * A class building for message.
 * 
 * @author higa
 * @since 3.0
 */
public abstract class MessageBuilder {

    private static final String MESSAGES = "Messages";

    protected MessageBuilder() {
    }

    /**
     * Returns message.
     * 
     * @param messageCode
     * @param args
     * @return
     */
    public static String getMessage(String messageCode, Object... args) {
        return getMessage(null, messageCode, args);
    }

    /**
     * Returns message.
     * 
     * @param locale
     * @param messageCode
     * @param args
     * @return
     */
    public static String getMessage(Locale locale, String messageCode,
            Object... args) {
        if (messageCode == null) {
            return getMessageByArgs(args);
        }
        if (locale == null) {
            locale = Locale.getDefault();
        }
        try {
            String pattern = getPattern(locale, messageCode);
            if (pattern != null) {
                return MessageFormat.format(pattern, args);
            }
        } catch (Throwable ignore) {
        }
        return getMessageByArgs(args);
    }

    protected static String getMessageByArgs(Object... args) {
        if (args == null || args.length == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder(100);
        for (Object arg : args) {
            builder.append(arg + ", ");
        }
        builder.setLength(builder.length() - 2);
        return builder.toString();
    }

    protected static String getPattern(Locale locale, String messageCode) {
        String bundleName = getMessageBundleName(messageCode);
        if (bundleName == null) {
            return null;
        }
        MessageResourceBundle bundle = MessageResourceBundleFactory.getBundle(
                locale, bundleName);
        if (bundle == null) {
            return null;
        }
        String key = getKey(messageCode);
        return bundle.get(key);
    }

    protected static String getMessageBundleName(String messageCode) {
        if (messageCode.length() <= 5) {
            return null;
        }
        return messageCode.substring(1, messageCode.length() - 4) + MESSAGES;
    }

    protected static String getKey(String messageCode) {
        if (messageCode.length() <= 5) {
            return null;
        }
        return messageCode.charAt(0)
                + messageCode.substring(messageCode.length() - 4);
    }
}