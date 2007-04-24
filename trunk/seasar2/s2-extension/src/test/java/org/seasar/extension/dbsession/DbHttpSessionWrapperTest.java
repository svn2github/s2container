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
package org.seasar.extension.dbsession;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import junit.framework.TestCase;

import org.seasar.framework.mock.servlet.MockHttpServletRequestImpl;
import org.seasar.framework.mock.servlet.MockServletContextImpl;

/**
 * @author higa
 * 
 */
public class DbHttpSessionWrapperTest extends TestCase {

    /**
     * Test method for
     * {@link org.seasar.extension.dbsession.DbHttpSessionWrapper#getSessionState()}.
     */
    public void testGetSessionState() {
        MockServletContextImpl servletContext = new MockServletContextImpl(
                "hoge");
        MockHttpServletRequestImpl request = new MockHttpServletRequestImpl(
                servletContext, "foo");
        DbSessionStateManager sessionStateManager = new DbSessionStateManager() {
            public DbSessionState loadState(String sessionId) {
                return new DbSessionState(new HashMap());
            }

            public void updateState(String sessionId,
                    DbSessionState sessionState) {
            }

            public void removeState(String sessionId) {
            }
        };
        HttpSession session = request.getSession();
        DbHttpSessionWrapper sessionWrapper = new DbHttpSessionWrapper("myid",
                session, sessionStateManager);
        assertNull(sessionWrapper.getSessionState());
        sessionWrapper.getAttribute("hoge");
        assertNotNull(sessionWrapper.getSessionState());
    }

}
