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
package org.seasar.framework.container.autoregister;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.exception.ResourceNotFoundRuntimeException;
import org.seasar.framework.unit.S2FrameworkTestCase;

/**
 * @author higa
 */
public class JarComponentAutoRegisterTest extends S2FrameworkTestCase {

    private S2Container child;

    private JarComponentAutoRegister autoRegister;

    protected void setUp() {
        include("JarComponentAutoRegisterTest.dicon");
    }

    public void testGetBaseDir() throws Exception {
        String file = autoRegister.getBaseDir();
        System.out.println(file);
        assertNotNull("1", file);
    }

    public void testRegisterAll() throws Exception {
        assertTrue("1", child.hasComponentDef("testSuite"));
    }

    public void testInvalidBaseDir() throws Exception {
        try {
            autoRegister.setBaseDir("Not Found");
            autoRegister.registerAll();
        } catch (ResourceNotFoundRuntimeException expected) {
        }
    }
}