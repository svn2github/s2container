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
package org.seasar.framework.container.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.ContainerNotRegisteredRuntimeException;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.util.StringUtil;

public class S2ContainerServlet extends HttpServlet {

    private static final long serialVersionUID = 407266935204779128L;

    public static final String CONFIG_PATH_KEY = "configPath";

    public static final String DEBUG_KEY = "debug";

    public static final String COMMAND = "command";

    public static final String RESTART = "restart";

    public static final String LIST = "list";

    public static final String PATH = "path";

    private static S2ContainerServlet instance;

    private boolean debug;

    public S2ContainerServlet() {
        instance = this;
    }

    public static S2ContainerServlet getInstance() {
        return instance;
    }

    public void init() {
        String configPath = null;
        String debugStr = null;
        ServletConfig servletConfig = getServletConfig();
        if (servletConfig != null) {
            configPath = servletConfig.getInitParameter(CONFIG_PATH_KEY);
            debugStr = servletConfig.getInitParameter(DEBUG_KEY);
        }
        if (!StringUtil.isEmpty(configPath)) {
            SingletonS2ContainerFactory.setConfigPath(configPath);
        }
        if (!StringUtil.isEmpty(debugStr)) {
            debug = Boolean.valueOf(debugStr).booleanValue();
        }
        SingletonS2ContainerFactory.setServletContext(getServletContext());
        SingletonS2ContainerFactory.init();
    }

    public void destroy() {
        SingletonS2ContainerFactory.destroy();
    }

    public static S2Container getContainer() {
        return SingletonS2ContainerFactory.getContainer();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String command = request.getParameter(COMMAND);
        if (debug && RESTART.equalsIgnoreCase(command)) {
            destroy();
            init();
            response.getWriter().write("S2ContainerServlet is restarted.");
        } else if (debug && LIST.equalsIgnoreCase(command)) {
            list(request, response);
        } else {
            response.getWriter().write("S2ContainerServlet is running.");
        }
    }

    protected void list(final HttpServletRequest request,
            final HttpServletResponse response) throws IOException {
        final PrintWriter out = response.getWriter();

        final String path = request.getParameter(PATH);
        final S2Container container = getContainer(path);
        if (container == null) {
            out.write("S2Container[" + path + "] is not found.");
            return;
        }

        out.write("<html><head><title>S2 Components</title></head><body>");
        try {
            out.write("<h1>S2Container</h1>");
            out.write("<p>PATH:<code>" + path + "</code></p>");
            listInclude(container, request, out);
            listComponent(container, out);
        } finally {
            out.write("</body></html>");
        }
    }

    protected void listInclude(final S2Container container,
            final HttpServletRequest request, final PrintWriter out)
            throws IOException {
        if (container.getChildSize() == 0) {
            return;
        }
        out.write("<h2>Includes</h2>");
        out.write("<p><ul>");
        try {
            final String requestUri = request.getRequestURI();
            final String queryString = "?" + COMMAND + "=" + LIST + "&" + PATH
                    + "=";
            for (int i = 0; i < container.getChildSize(); ++i) {
                final S2Container child = container.getChild(i);
                out.write("<li><a href='" + requestUri + queryString + child
                        + "'><code>" + child + "</code></a></li>");
            }
        } finally {
            out.write("</ul></p>");
        }
    }

    protected void listComponent(final S2Container container,
            final PrintWriter out) throws IOException {
        if (container.getComponentDefSize() == 0) {
            return;
        }
        out.write("<h2>Components</h2>");
        out.write("<p><ul>");
        try {
            for (int i = 0; i < container.getComponentDefSize(); ++i) {
                final ComponentDef cd = container.getComponentDef(i);
                printComponent(cd, out);
            }
        } finally {
            out.write("</ul></p>");
        }
    }

    protected void printComponent(final ComponentDef cd, final PrintWriter out)
            throws IOException {
    }

    protected S2Container getContainer(final String path) {
        final S2Container root = SingletonS2ContainerFactory.getContainer();
        try {
            return StringUtil.isEmpty(path) ? root : root.getDescendant(path);
        } catch (final ContainerNotRegisteredRuntimeException e) {
            return null;
        }
    }
}