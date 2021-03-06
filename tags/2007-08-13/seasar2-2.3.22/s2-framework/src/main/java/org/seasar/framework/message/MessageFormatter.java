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
package org.seasar.framework.message;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * @author higa
 * 
 */

public final class MessageFormatter {

    private static final String MESSAGES = "Messages";

    private MessageFormatter() {
    }

    public static String getMessage(String messageCode, Object[] args) {
        if (messageCode == null) {
            messageCode = "";
        }
        return "[" + messageCode + "]" + getSimpleMessage(messageCode, args);
    }

    public static String getSimpleMessage(String messageCode, Object[] arguments) {

        try {
            String pattern = getPattern(messageCode);
            if (pattern != null) {
                return MessageFormat.format(pattern, arguments);
            }
        } catch (Throwable ignore) {
        }
        return getNoPatternMessage(arguments);
    }

    private static String getPattern(String messageCode) {
        ResourceBundle resourceBundle = getMessages(getSystemName(messageCode));
        if (resourceBundle != null) {
            return resourceBundle.getString(messageCode);
        }
        return null;
    }

    private static String getSystemName(String messageCode) {
        return messageCode.substring(1, Math.min(4, messageCode.length()));
    }

    private static ResourceBundle getMessages(String systemName) {
        return ResourceBundle.getBundle(systemName + MESSAGES);
    }

    private static String getNoPatternMessage(Object[] args) {
        if (args == null || args.length == 0) {
            return "";
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < args.length; i++) {
            buffer.append(args[i] + ", ");
        }
        buffer.setLength(buffer.length() - 2);
        return buffer.toString();
    }
}