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
package org.seasar.extension.httpsession;

import java.util.HashMap;

import junit.framework.TestCase;

import org.seasar.extension.httpsession.S2HttpSession;
import org.seasar.extension.httpsession.SessionState;
import org.seasar.extension.httpsession.SessionStateManager;
import org.seasar.framework.mock.servlet.MockServletContextImpl;

/**
 * @author higa
 * 
 */
public class S2HttpSessionTest extends TestCase {

    /**
     * Test method for
     * {@link org.seasar.extension.httpsession.S2HttpSession#getSessionState()}.
     */
    public void testGetSessionState() {
        MockServletContextImpl servletContext = new MockServletContextImpl(
                "hoge");
        SessionStateManager sessionStateManager = new SessionStateManager() {
            public SessionState loadState(String sessionId) {
                return new SessionState(new HashMap());
            }

            public void updateState(String sessionId,
                    SessionState sessionState) {
            }

            public void removeState(String sessionId) {
            }
        };
        S2HttpSession sessionWrapper = new S2HttpSession("myid",
                sessionStateManager, servletContext, true);
        assertNull(sessionWrapper.getSessionState());
        sessionWrapper.getAttribute("hoge");
        assertNotNull(sessionWrapper.getSessionState());
    }

}
