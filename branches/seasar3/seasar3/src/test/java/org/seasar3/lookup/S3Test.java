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
package org.seasar3.lookup;

import junit.framework.TestCase;

/**
 * @author higa
 * 
 */
public class S3Test extends TestCase {

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        S3.dispose();
    }

    /**
     * Test method for {@link S3#lookup(Class)}.
     */
    public void testLookupForConfigTypeIsNull() {
        try {
            S3.lookup(null);
            fail();
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }

    /**
     * Test method for {@link S3#lookup(Class)}.
     */
    public void testLookup() {
        assertNotNull(S3.lookup(MyConfig.class));
    }

    /**
     * Test method for {@link S3#lookup(Class)}.
     */
    public void testLookupForCache() {
        MyConfig config = S3.lookup(MyConfig.class);
        assertSame(config, S3.lookup(MyConfig.class));
    }

    /**
     * Test method for {@link S3#lookup(Class)}.
     */
    public void testLookupForProduction() {
        Client client = S3.lookup(MyConfig.class).client();
        assertEquals("production", client.go());
    }

    /**
     * Test method for {@link S3#lookup(Class)}.
     */
    public void testLookupForMockConfig() {
        Client client = S3.lookup(MyMockConfig.class).client();
        assertEquals("mock", client.go());
    }

    /**
     * Test method for {@link S3#lookup(Class)}.
     */
    public void testLookupForNestedConfig() {
        Client client = S3.lookup(ClientConfig.class).client();
        assertEquals("production", client.go());
    }

    /**
     * Test method for {@link S3#override(Class, Class)}.
     */
    public void testOverride() {
        S3.override(ServiceConfig.class, ServiceMockConfig.class);
        Client client = S3.lookup(ClientConfig.class).client();
        assertEquals("mock", client.go());
    }
}