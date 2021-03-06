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
package org.seasar.framework.unit.impl;

import java.lang.reflect.Method;

import javax.servlet.Servlet;

import org.seasar.extension.dataset.DataSet;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.ExternalContext;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.framework.container.annotation.tiger.BindingType;
import org.seasar.framework.container.annotation.tiger.DestroyMethod;
import org.seasar.framework.container.annotation.tiger.InitMethod;
import org.seasar.framework.container.deployer.ComponentDeployerFactory;
import org.seasar.framework.container.deployer.ExternalComponentDeployerProvider;
import org.seasar.framework.container.external.servlet.HttpServletExternalContext;
import org.seasar.framework.container.external.servlet.HttpServletExternalContextComponentDefRegister;
import org.seasar.framework.container.factory.S2ContainerFactory;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.container.factory.TigerAnnotationHandler;
import org.seasar.framework.container.servlet.S2ContainerServlet;
import org.seasar.framework.convention.NamingConvention;
import org.seasar.framework.convention.impl.NamingConventionImpl;
import org.seasar.framework.mock.servlet.MockHttpServletRequest;
import org.seasar.framework.mock.servlet.MockHttpServletResponse;
import org.seasar.framework.mock.servlet.MockHttpServletResponseImpl;
import org.seasar.framework.mock.servlet.MockServletConfig;
import org.seasar.framework.mock.servlet.MockServletConfigImpl;
import org.seasar.framework.mock.servlet.MockServletContext;
import org.seasar.framework.mock.servlet.MockServletContextImpl;
import org.seasar.framework.unit.ConfigFileIncluder;
import org.seasar.framework.unit.ExpectedDataReader;
import org.seasar.framework.unit.InternalTestContext;
import org.seasar.framework.unit.TestDataPreparer;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.ResourceUtil;

/**
 * @author taedium
 * 
 */
public class InternalTestContextImpl implements InternalTestContext {

    protected final TigerAnnotationHandler handler = new TigerAnnotationHandler();

    protected S2Container container;

    protected MockServletContext servletContext;

    protected Servlet servlet;

    protected MockServletConfig servletConfig;

    protected MockHttpServletRequest request;

    protected MockHttpServletResponse response;

    protected NamingConvention namingConvention;

    protected Class<?> testClass;

    protected Method testMethod;

    protected boolean autoIncluding = true;

    protected boolean autoPreparing = true;

    protected boolean containerInitialized;

    @Binding(bindingType = BindingType.MUST)
    public void setContainer(final S2Container container) {
        this.container = container;
    }

    @Binding(bindingType = BindingType.MAY)
    public void setServletContext(MockServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @InitMethod
    public void init() throws Throwable {
        if (servletContext == null) {
            servletContext = new MockServletContextImpl("s2-example");
        }
        request = servletContext.createRequest("/hello.html");
        response = new MockHttpServletResponseImpl(request);
        servletConfig = new MockServletConfigImpl();
        servletConfig.setServletContext(servletContext);
        servlet = new S2ContainerServlet();
        servlet.init(servletConfig);
        ExternalContext externalContext = new HttpServletExternalContext();
        externalContext.setApplication(servletContext);
        externalContext.setRequest(request);
        externalContext.setResponse(response);
        container.setExternalContext(externalContext);
        container
                .setExternalContextComponentDefRegister(new HttpServletExternalContextComponentDefRegister());
        ComponentDeployerFactory
                .setProvider(new ExternalComponentDeployerProvider());
        namingConvention = new NamingConventionImpl();
        container.register(namingConvention);
    }

    @DestroyMethod
    public void destroy() {
        ComponentDeployerFactory
                .setProvider(new ComponentDeployerFactory.DefaultProvider());
        SingletonS2ContainerFactory.setContainer(null);
        S2ContainerServlet.clearInstance();
        servletContext = null;
        request = null;
        response = null;
        servletConfig = null;
        servlet = null;
        namingConvention = null;
    }

    public void initContainer() {
        containerInitialized = true;
        if (autoIncluding) {
            if (hasComponentDef(ConfigFileIncluder.class)) {
                final ConfigFileIncluder includer = getComponent(ConfigFileIncluder.class);
                includer.include(this);
            }
        }
        container.init();
    }

    public void destroyContainer() {
        container.destroy();
        container = null;
        containerInitialized = false;
    }

    public void include(final String path) {
        final String convertedPath = ResourceUtil.convertPath(path, testClass);
        S2ContainerFactory.include(container, convertedPath);
    }

    public void register(final Class<?> componentClass,
            final String componentName) {
        final ComponentDef cd = handler
                .createComponentDef(componentClass, null);
        if (componentName != null) {
            cd.setComponentName(componentName);
        }
        handler.appendDI(cd);
        handler.appendAspect(cd);
        handler.appendInterType(cd);
        handler.appendInitMethod(cd);
        container.register(cd);
    }

    public void register(final Class<?> componentClass) {
        final ComponentDef cd = handler
                .createComponentDef(componentClass, null);
        if (cd.getComponentName() == null) {
            cd.setComponentName(namingConvention
                    .fromClassNameToComponentName(componentClass.getName()));
        }
        handler.appendDI(cd);
        handler.appendAspect(cd);
        handler.appendInterType(cd);
        handler.appendInitMethod(cd);
        container.register(cd);
    }

    public void register(final ComponentDef componentDef) {
        container.register(componentDef);
    }

    public void register(final Object component, String componentName) {
        container.register(component, componentName);
    }

    public void register(final Object component) {
        container.register(component);
    }

    public void setAutoIncluding(final boolean autoIncluding) {
        this.autoIncluding = autoIncluding;
    }

    public void setAutoPreparing(final boolean autoPreparing) {
        this.autoPreparing = autoPreparing;
    }

    public String getTestClassPackagePath() {
        return testClass.getName().replace('.', '/')
                .replaceFirst("/[^/]+$", "");
    }

    public String getTestClassShortName() {
        return ClassUtil.getShortClassName(testClass);
    }

    public String getTestMethodName() {
        return testMethod.getName();
    }

    public void setTestClass(final Class<?> testClass) {
        this.testClass = testClass;
    }

    public void setTestMethod(final Method testMethod) {
        this.testMethod = testMethod;
    }

    public void prepareTestData() {
        if (autoPreparing) {
            if (hasComponentDef(TestDataPreparer.class)) {
                final TestDataPreparer preparer = getComponent(TestDataPreparer.class);
                preparer.prepare(this);
            }
        }
    }

    public DataSet getExpected() {
        if (hasComponentDef(ExpectedDataReader.class)) {
            final ExpectedDataReader reader = getComponent(ExpectedDataReader.class);
            final DataSet expected = reader.read(this);
            if (expected != null) {
                return expected;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> T getComponent(final Class<T> componentKey) {
        if (!containerInitialized) {
            throw new IllegalStateException();
        }
        return (T) container.getComponent(componentKey);
    }

    public Object getComponent(final Object componentKey) {
        if (!containerInitialized) {
            throw new IllegalStateException();
        }
        return container.getComponent(componentKey);
    }

    public boolean hasComponentDef(final Object componentKey) {
        if (!containerInitialized) {
            throw new IllegalStateException();
        }
        return container.hasComponentDef(componentKey);
    }

    public ComponentDef getComponentDef(final int index) {
        if (!containerInitialized) {
            throw new IllegalStateException();
        }
        return container.getComponentDef(index);
    }

    public ComponentDef getComponentDef(final Object componentKey) {
        if (!containerInitialized) {
            throw new IllegalStateException();
        }
        return container.getComponentDef(componentKey);
    }

}
