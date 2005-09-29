/*
 * Copyright 2004-2005 the Seasar Foundation and the Others.
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
package org.seasar.framework.container.factory;

import java.lang.reflect.Method;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.PropertyDef;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.annotation.Aspect;
import org.seasar.framework.container.annotation.AutoBindingType;
import org.seasar.framework.container.annotation.Component;
import org.seasar.framework.container.annotation.Inject;
import org.seasar.framework.container.annotation.InstanceType;
import org.seasar.framework.container.annotation.NoInject;
import org.seasar.framework.container.impl.ComponentDefImpl;
import org.seasar.framework.container.impl.PropertyDefImpl;
import org.seasar.framework.util.StringUtil;

/**
 * @author higa
 *
 */
public class TigerAnnotationHandler extends ConstantAnnotationHandler {

    public ComponentDef createComponentDef(Class componentClass) {
        Component component = componentClass.<Component>getAnnotation(Component.class);
        if (component == null) {
            return super.createComponentDef(componentClass);
        }
        String name = component.name();
        ComponentDef componentDef = new ComponentDefImpl(componentClass, name);
        InstanceType instanceType = component.instance();
        if (instanceType != null) {
            componentDef.setInstanceMode(instanceType.getInstanceMode());
        }
        AutoBindingType autoBindingType = component.autoBinding();
        if (autoBindingType != null) {
            componentDef.setAutoBindingMode(autoBindingType.getAutoBindingMode());
        }
        return componentDef;
    }

    public PropertyDef createPropertyDef(S2Container container,
            BeanDesc beanDesc, PropertyDesc propertyDesc) {

        if (!propertyDesc.hasWriteMethod()) {
            return super.createPropertyDef(container, beanDesc, propertyDesc);
        }
        Method method = propertyDesc.getWriteMethod();
        Inject inject = method.getAnnotation(Inject.class);
        String propName = propertyDesc.getPropertyName();
        if (inject != null) {
            String value = inject.value();
            if (StringUtil.isEmpty(value)) {
                if (container.hasComponentDef(propName)) {
                    value = propName;
                } else {
                    return null;
                }
            }
            PropertyDef pd = new PropertyDefImpl(propName);
            pd.setExpression(value);
            return pd;
        } else {
            NoInject noInject = method.getAnnotation(NoInject.class);
            if (noInject != null) {
                return new PropertyDefImpl(propName, true);
            }
        }
        return super.createPropertyDef(container, beanDesc, propertyDesc);
    }
    
    public void appendAspect(ComponentDef componentDef) {
        Class componentClass = componentDef.getComponentClass();
        Aspect aspect = componentClass.<Aspect>getAnnotation(Aspect.class);
        if (aspect == null) {
            super.appendAspect(componentDef);
            return;
        }
        String interceptor = aspect.interceptor();
        String pointcut = aspect.pointcut();
        appendAspect(componentDef, interceptor, pointcut);
    }
}
