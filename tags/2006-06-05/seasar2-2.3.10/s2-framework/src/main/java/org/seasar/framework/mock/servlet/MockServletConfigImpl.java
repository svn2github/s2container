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
package org.seasar.framework.mock.servlet;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.seasar.framework.util.EnumerationAdapter;

public class MockServletConfigImpl implements MockServletConfig, Serializable {

    private static final long serialVersionUID = 5515573574823840162L;

    private String servletName;

    private ServletContext servletContext;

    private Map initParameters = new HashMap();

    public MockServletConfigImpl() {
    }

    /**
     * @see javax.servlet.ServletConfig#getServletName()
     */
    public String getServletName() {
        return servletName;
    }

    public void setServletName(String servletName) {
        this.servletName = servletName;
    }

    /**
     * @see javax.servlet.ServletConfig#getServletContext()
     */
    public ServletContext getServletContext() {
        return servletContext;
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    /**
     * @see javax.servlet.ServletConfig#getInitParameter(java.lang.String)
     */
    public String getInitParameter(String name) {
        return (String) initParameters.get(name);
    }

    public void setInitParameter(String name, final String value) {
        initParameters.put(name, value);
    }

    /**
     * @see javax.servlet.ServletConfig#getInitParameterNames()
     */
    public Enumeration getInitParameterNames() {
        return new EnumerationAdapter(initParameters.keySet().iterator());
    }
}