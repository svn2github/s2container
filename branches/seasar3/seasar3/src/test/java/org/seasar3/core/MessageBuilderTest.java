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

import java.util.Locale;

import junit.framework.TestCase;

/**
 * @author higa
 * 
 */
public class MessageBuilderTest extends TestCase {

    /**
     * Test method for
     * {@link MessageBuilder#getMessage(Locale, String, Object[])}.
     */
    public void testGetMessageForIllegalArgument() {
        assertEquals("1, 2", MessageBuilder.getMessage(Locale.getDefault(),
                null, 1, 2));
    }

    /**
     * Test method for
     * {@link org.seasar3.core.MessageBuilder#getMessage(java.util.Locale, java.lang.String, java.lang.Object[])}.
     */
    public void testGetMessage() {
        assertEquals("foo(1)", MessageBuilder.getMessage(Locale.getDefault(),
                "ES3Test0002", 1));
    }

    /**
     * Test method for {@link MessageBuilder#getMessageByArgs(Object[])}.
     */
    public void testGetMessageByArgs() {
        assertEquals("1, 2", MessageBuilder.getMessageByArgs(1, 2));
    }

    /**
     * Test method for {@link MessageBuilder#getMessageByArgs(Object[])}.
     */
    public void testGetMessageByArgsForArgsIsNull() {
        assertEquals("", MessageBuilder.getMessageByArgs((Object[]) null));
    }

    /**
     * Test method for {@link MessageBuilder#getMessageByArgs(Object[])}.
     */
    public void testGetMessageByArgsForArgsIsEmpty() {
        assertEquals("", MessageBuilder.getMessageByArgs(new Object[0]));
    }

    /**
     * Test method for {@link MessageBuilder#getPattern(Locale, String)}.
     */
    public void testGetPattern() {
        assertEquals("hoge", MessageBuilder.getPattern(Locale.getDefault(),
                "ES3Test0001"));
    }

    /**
     * Test method for {@link MessageBuilder#getPattern(Locale, String)}.
     */
    public void testGetPatternForIllegalMessageCode() {
        assertNull(MessageBuilder.getPattern(Locale.getDefault(), "E0001"));
    }

    /**
     * Test method for {@link MessageBuilder#getMessageBundleName(String)}.
     */
    public void testGetMessageBundleName() {
        assertEquals("S3Messages", MessageBuilder
                .getMessageBundleName("ES30001"));
    }

    /**
     * Test method for {@link MessageBuilder#getMessageBundleName(String)}.
     */
    public void testGetMessageBundleNameForIllegalMessageCode() {
        assertNull(MessageBuilder.getMessageBundleName("E1"));
    }

    /**
     * Test method for {@link MessageBuilder#getKey(String)}.
     */
    public void testGetKey() {
        assertEquals("E0001", MessageBuilder.getKey("ES30001"));
    }

    /**
     * Test method for {@link MessageBuilder#getKey(String)}.
     */
    public void testGetKeyForIllegalMessageCode() {
        assertNull(MessageBuilder.getKey("E1"));
    }

}
