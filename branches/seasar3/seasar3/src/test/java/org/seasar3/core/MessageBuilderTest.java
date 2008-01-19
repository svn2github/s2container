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
package org.seasar3.core;

import java.util.Locale;

import junit.framework.TestCase;

/**
 * @author higa
 * 
 */
public class MessageBuilderTest extends TestCase {

    /**
     * 
     */
    public void testGetMessageForIllegalArgument() {
        assertEquals("1, 2", MessageBuilder.getMessage(Locale.getDefault(),
                null, 1, 2));
    }

    /**
     * 
     */
    public void testGetMessage() {
        assertEquals("foo(1)", MessageBuilder.getMessage(Locale.getDefault(),
                "ES3Test0002", 1));
    }

    /**
     * 
     */
    public void testGetMessageByArgs() {
        assertEquals("1, 2", MessageBuilder.getMessageByArgs(1, 2));
    }

    /**
     * 
     */
    public void testGetMessageByArgsForArgsIsNull() {
        assertEquals("", MessageBuilder.getMessageByArgs((Object[]) null));
    }

    /**
     * 
     */
    public void testGetMessageByArgsForArgsIsEmpty() {
        assertEquals("", MessageBuilder.getMessageByArgs(new Object[0]));
    }

    /**
     * 
     */
    public void testGetPattern() {
        assertEquals("hoge", MessageBuilder.getPattern(Locale.getDefault(),
                "ES3Test0001"));
    }

    /**
     * 
     */
    public void testGetPatternForIllegalMessageCode() {
        assertNull(MessageBuilder.getPattern(Locale.getDefault(), "E0001"));
    }

    /**
     * 
     */
    public void testGetMessageBundleName() {
        assertEquals("S3Messages", MessageBuilder
                .getMessageBundleName("ES30001"));
    }

    /**
     * 
     */
    public void testGetMessageBundleNameForIllegalMessageCode() {
        assertNull(MessageBuilder.getMessageBundleName("E1"));
    }

    /**
     * 
     */
    public void testGetKey() {
        assertEquals("E0001", MessageBuilder.getKey("ES30001"));
    }

    /**
     * 
     */
    public void testGetKeyForIllegalMessageCode() {
        assertNull(MessageBuilder.getKey("E1"));
    }
}