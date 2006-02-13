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
package org.seasar.framework.container.impl;

import org.seasar.framework.beans.PropertyNotFoundRuntimeException;
import org.seasar.framework.container.ArgDef;
import org.seasar.framework.container.AspectDef;
import org.seasar.framework.container.AutoBindingDef;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.ComponentDeployer;
import org.seasar.framework.container.ContainerConstants;
import org.seasar.framework.container.DestroyMethodDef;
import org.seasar.framework.container.InitMethodDef;
import org.seasar.framework.container.InstanceDef;
import org.seasar.framework.container.MetaDef;
import org.seasar.framework.container.PropertyDef;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.assembler.AutoBindingDefFactory;
import org.seasar.framework.container.deployer.InstanceDefFactory;
import org.seasar.framework.container.util.AopProxyUtil;
import org.seasar.framework.container.util.ArgDefSupport;
import org.seasar.framework.container.util.AspectDefSupport;
import org.seasar.framework.container.util.DestroyMethodDefSupport;
import org.seasar.framework.container.util.InitMethodDefSupport;
import org.seasar.framework.container.util.MetaDefSupport;
import org.seasar.framework.container.util.PropertyDefSupport;
import org.seasar.framework.hotswap.Hotswap;

/**
 * @author higa
 * 
 */
public class ComponentDefImpl implements ComponentDef, ContainerConstants {

    private Class componentClass;

    private String componentName;

    private Class concreteClass;

    private S2Container container;

    private String expression;

    private ArgDefSupport argDefSupport = new ArgDefSupport();

    private PropertyDefSupport propertyDefSupport = new PropertyDefSupport();

    private InitMethodDefSupport initMethodDefSupport = new InitMethodDefSupport();

    private DestroyMethodDefSupport destroyMethodDefSupport = new DestroyMethodDefSupport();

    private AspectDefSupport aspectDefSupport = new AspectDefSupport();

    private MetaDefSupport metaDefSupport = new MetaDefSupport();

    private InstanceDef instanceDef = InstanceDefFactory.SINGLETON;

    private AutoBindingDef autoBindingDef = AutoBindingDefFactory.AUTO;

    private ComponentDeployer componentDeployer;

    private Hotswap hotswap;

    public ComponentDefImpl() {
    }

    public ComponentDefImpl(Class componentClass) {
        this(componentClass, null);
    }

    public ComponentDefImpl(Class componentClass, String componentName) {
        this.componentClass = componentClass;
        setComponentName(componentName);
    }

    /**
     * @see org.seasar.framework.container.ComponentDef#getComponent()
     */
    public Object getComponent() {
        return getComponentDeployer().deploy();
    }

    /**
     * @see org.seasar.framework.container.ComponentDef#injectDependency(java.lang.Object)
     */
    public void injectDependency(Object outerComponent) {
        getComponentDeployer().injectDependency(outerComponent);
    }

    /**
     * @see org.seasar.framework.container.ComponentDef#getComponentClass()
     */
    public final Class getComponentClass() {
        updateComponentClass();
        return componentClass;
    }

    protected synchronized void updateComponentClass() {
        if (hotswap != null && hotswap.isModified()) {
            componentClass = hotswap.updateTargetClass();
            concreteClass = null;
        }
    }

    /**
     * @see org.seasar.framework.container.ComponentDef#getComponentName()
     */
    public final String getComponentName() {
        return componentName;
    }

    /**
     * @see org.seasar.framework.container.ComponentDef#setComponentName(java.lang.String)
     */
    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    /**
     * @see org.seasar.framework.container.ComponentDef#getConcreteClass()
     */
    public synchronized final Class getConcreteClass() {
        updateComponentClass();
        if (concreteClass == null) {
            ClassLoader oldLoader = Thread.currentThread()
                    .getContextClassLoader();
            try {
                ClassLoader loader = (container != null ? container
                        .getClassLoader() : null);
                if (loader != null) {
                    Thread.currentThread().setContextClassLoader(loader);
                }
                concreteClass = AopProxyUtil.getConcreteClass(this);
            } finally {
                Thread.currentThread().setContextClassLoader(oldLoader);
            }
        }
        return concreteClass;
    }

    /**
     * @see org.seasar.framework.container.ComponentDef#getContainer()
     */
    public final S2Container getContainer() {
        return container;
    }

    /**
     * @see org.seasar.framework.container.ComponentDef#setContainer(org.seasar.framework.container.S2Container)
     */
    public final void setContainer(S2Container container) {
        this.container = container;
        argDefSupport.setContainer(container);
        metaDefSupport.setContainer(container);
        propertyDefSupport.setContainer(container);
        initMethodDefSupport.setContainer(container);
        destroyMethodDefSupport.setContainer(container);
        aspectDefSupport.setContainer(container);
    }

    /**
     * @see org.seasar.framework.container.ComponentDef#addArgDef(org.seasar.framework.container.ArgDef)
     */
    public void addArgDef(ArgDef argDef) {
        argDefSupport.addArgDef(argDef);
    }

    /**
     * @see org.seasar.framework.container.ComponentDef#addPropertyDef(org.seasar.framework.container.PropertyDef)
     */
    public void addPropertyDef(PropertyDef propertyDef) {
        propertyDefSupport.addPropertyDef(propertyDef);
    }

    /**
     * @see org.seasar.framework.container.InitMethodDefAware#addInitMethodDef(org.seasar.framework.container.InitMethodDef)
     */
    public void addInitMethodDef(InitMethodDef methodDef) {
        initMethodDefSupport.addInitMethodDef(methodDef);
    }

    /**
     * @see org.seasar.framework.container.DestroyMethodDefAware#addDestroyMethodDef(org.seasar.framework.container.DestroyMethodDef)
     */
    public void addDestroyMethodDef(DestroyMethodDef methodDef) {
        destroyMethodDefSupport.addDestroyMethodDef(methodDef);
    }

    /**
     * @see org.seasar.framework.container.ComponentDef#addAspectDef(org.seasar.framework.container.AspectDef)
     */
    public synchronized void addAspectDef(AspectDef aspectDef) {
        aspectDefSupport.addAspectDef(aspectDef);
        concreteClass = null;
    }

    /**
     * @see org.seasar.framework.container.ArgDefAware#getArgDefSize()
     */
    public int getArgDefSize() {
        return argDefSupport.getArgDefSize();
    }

    /**
     * @see org.seasar.framework.container.PropertyDefAware#getPropertyDefSize()
     */
    public int getPropertyDefSize() {
        return propertyDefSupport.getPropertyDefSize();
    }

    /**
     * @see org.seasar.framework.container.InitMethodDefAware#getInitMethodDefSize()
     */
    public int getInitMethodDefSize() {
        return initMethodDefSupport.getInitMethodDefSize();
    }

    /**
     * @see org.seasar.framework.container.DestroyMethodDefAware#getDestroyMethodDefSize()
     */
    public int getDestroyMethodDefSize() {
        return destroyMethodDefSupport.getDestroyMethodDefSize();
    }

    /**
     * @see org.seasar.framework.container.AspectDefAware#getAspectDefSize()
     */
    public int getAspectDefSize() {
        return aspectDefSupport.getAspectDefSize();
    }

    /*
     * @see org.seasar.framework.container.ComponentDef#getInstanceDef()
     */
    public InstanceDef getInstanceDef() {
        return instanceDef;
    }

    /*
     * @see org.seasar.framework.container.ComponentDef#setInstanceDef(org.seasar.framework.container.InstanceDef)
     */
    public void setInstanceDef(InstanceDef instanceDef) {
        this.instanceDef = instanceDef;
    }

    /**
     * @see org.seasar.framework.container.ComponentDef#getAutoBindingDef()
     */
    public AutoBindingDef getAutoBindingDef() {
        return autoBindingDef;
    }

    /**
     * @see org.seasar.framework.container.ComponentDef#setAutoBindingDef(org.seasar.framework.container.AutoBindingDef)
     */
    public void setAutoBindingDef(AutoBindingDef autoBindingDef) {
        this.autoBindingDef = autoBindingDef;
    }

    /**
     * @see org.seasar.framework.container.ComponentDef#init()
     */
    public void init() {
        if (hotswap == null && componentClass != null && container != null
                && container.getRoot().isHotswapMode()) {

            hotswap = new Hotswap(componentClass);
        }
        getComponentDeployer().init();
    }

    /**
     * @see org.seasar.framework.container.ComponentDef#destroy()
     */
    public void destroy() {
        getComponentDeployer().destroy();
    }

    /**
     * @see org.seasar.framework.container.ComponentDef#getExpression()
     */
    public String getExpression() {
        return expression;
    }

    /**
     * @see org.seasar.framework.container.ComponentDef#setExpression(java.lang.String)
     */
    public void setExpression(String expression) {
        this.expression = expression;
    }

    /**
     * @see org.seasar.framework.container.ArgDefAware#getArgDef(int)
     */
    public ArgDef getArgDef(int index) {
        return argDefSupport.getArgDef(index);
    }

    /**
     * @see org.seasar.framework.container.PropertyDefAware#getPropertyDef(int)
     */
    public PropertyDef getPropertyDef(int index) {
        return propertyDefSupport.getPropertyDef(index);
    }

    /**
     * @see org.seasar.framework.container.PropertyDefAware#getPropertyDef(java.lang.String)
     */
    public PropertyDef getPropertyDef(String propertyName) {
        if (hasPropertyDef(propertyName)) {
            return propertyDefSupport.getPropertyDef(propertyName);
        }
        throw new PropertyNotFoundRuntimeException(componentClass, propertyName);
    }

    /**
     * @see org.seasar.framework.container.PropertyDefAware#hasPropertyDef(java.lang.String)
     */
    public boolean hasPropertyDef(String propertyName) {
        return propertyDefSupport.hasPropertyDef(propertyName);
    }

    /**
     * @see org.seasar.framework.container.InitMethodDefAware#getInitMethodDef(int)
     */
    public InitMethodDef getInitMethodDef(int index) {
        return initMethodDefSupport.getInitMethodDef(index);
    }

    /**
     * @see org.seasar.framework.container.DestroyMethodDefAware#getDestroyMethodDef(int)
     */
    public DestroyMethodDef getDestroyMethodDef(int index) {
        return destroyMethodDefSupport.getDestroyMethodDef(index);
    }

    /**
     * @see org.seasar.framework.container.AspectDefAware#getAspectDef(int)
     */
    public AspectDef getAspectDef(int index) {
        return aspectDefSupport.getAspectDef(index);
    }

    /**
     * @see org.seasar.framework.container.MetaDefAware#addMetaDef(org.seasar.framework.container.MetaDef)
     */
    public void addMetaDef(MetaDef metaDef) {
        metaDefSupport.addMetaDef(metaDef);
    }

    /**
     * @see org.seasar.framework.container.MetaDefAware#getMetaDef(int)
     */
    public MetaDef getMetaDef(int index) {
        return metaDefSupport.getMetaDef(index);
    }

    /**
     * @see org.seasar.framework.container.MetaDefAware#getMetaDef(java.lang.String)
     */
    public MetaDef getMetaDef(String name) {
        return metaDefSupport.getMetaDef(name);
    }

    /**
     * @see org.seasar.framework.container.MetaDefAware#getMetaDefs(java.lang.String)
     */
    public MetaDef[] getMetaDefs(String name) {
        return metaDefSupport.getMetaDefs(name);
    }

    /**
     * @see org.seasar.framework.container.MetaDefAware#getMetaDefSize()
     */
    public int getMetaDefSize() {
        return metaDefSupport.getMetaDefSize();
    }

    public Hotswap getHotswap() {
        return hotswap;
    }

    public synchronized ComponentDeployer getComponentDeployer() {
        if (componentDeployer == null) {
            componentDeployer = instanceDef.createComponentDeployer(this);
        }
        return componentDeployer;
    }
}