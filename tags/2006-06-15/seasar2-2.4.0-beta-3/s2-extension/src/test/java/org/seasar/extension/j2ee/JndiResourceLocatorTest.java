/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
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
package org.seasar.extension.j2ee;

import java.util.Hashtable;

import javax.naming.Context;

import org.seasar.extension.unit.S2TestCase;

public class JndiResourceLocatorTest extends S2TestCase {
    Hashtable env;

    public JndiResourceLocatorTest() {
    }

    public JndiResourceLocatorTest(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
        include("j2ee.dicon");
        env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, JndiContextFactory.class
                .getName());
    }

    public void testLookup() throws Exception {
        Object userTransaction = JndiResourceLocator.lookup(
                "java:comp/UserTransaction", env);
        assertNotNull("1", userTransaction);
    }
}
