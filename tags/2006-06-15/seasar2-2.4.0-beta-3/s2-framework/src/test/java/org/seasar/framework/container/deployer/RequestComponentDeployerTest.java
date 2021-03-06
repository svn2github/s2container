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
package org.seasar.framework.container.deployer;

import javax.servlet.http.HttpServletRequest;

import junit.framework.TestCase;

import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.ComponentDeployer;
import org.seasar.framework.container.ExternalContext;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.impl.ComponentDefImpl;
import org.seasar.framework.container.impl.S2ContainerImpl;
import org.seasar.framework.container.impl.servlet.HttpServletExternalContext;
import org.seasar.framework.mock.servlet.MockServletContextImpl;

/**
 * @author higa
 * 
 */
public class RequestComponentDeployerTest extends TestCase {

    public void testDeployAutoAutoConstructor() throws Exception {
        MockServletContextImpl ctx = new MockServletContextImpl("s2jsf-example");
        HttpServletRequest request = ctx.createRequest("/hello.html");
        S2Container container = new S2ContainerImpl();
        ExternalContext extCtx = new HttpServletExternalContext();
        extCtx.setRequest(request);
        container.setExternalContext(extCtx);
        ComponentDef cd = new ComponentDefImpl(Foo.class, "foo");
        container.register(cd);
        ComponentDeployer deployer = new RequestComponentDeployer(cd);
        Foo foo = (Foo) deployer.deploy();
        assertSame("1", foo, request.getAttribute("foo"));
        assertSame("2", foo, deployer.deploy());
    }

    public static class Foo {

        private String message_;

        public void setMessage(String message) {
            message_ = message;
        }

        public String getMessage() {
            return message_;
        }
    }
}