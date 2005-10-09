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
import org.seasar.framework.container.annotation.Aspect;
import org.seasar.framework.container.annotation.AutoBindingType;
import org.seasar.framework.container.annotation.Binding;
import org.seasar.framework.container.annotation.Component;
import org.seasar.framework.container.annotation.InstanceType;
import org.seasar.framework.container.assembler.AutoBindingDefFactory;
import org.seasar.framework.container.deployer.InstanceDefFactory;
import org.seasar.framework.container.impl.ComponentDefImpl;

/**
 * @author higa
 *
 */
public class TigerAnnotationHandler extends ConstantAnnotationHandler {

    public ComponentDef createComponentDef(Class componentClass) {
        Class<?> clazz = componentClass;
        Component component = clazz.getAnnotation(Component.class);
        if (component == null) {
            return super.createComponentDef(componentClass);
        }
        String name = component.name();
        ComponentDef componentDef = new ComponentDefImpl(componentClass, name);
        InstanceType instanceType = component.instance();
        if (instanceType != null) {
            componentDef.setInstanceDef(
                    InstanceDefFactory.getInstanceDef(instanceType.getName()));
        }
        AutoBindingType autoBindingType = component.autoBinding();
        if (autoBindingType != null) {
            componentDef.setAutoBindingDef(
                    AutoBindingDefFactory.getAutoBindingDef(autoBindingType.getName()));
        }
        return componentDef;
    }

    public PropertyDef createPropertyDef(
            BeanDesc beanDesc, PropertyDesc propertyDesc) {

        if (!propertyDesc.hasWriteMethod()) {
            return super.createPropertyDef(beanDesc, propertyDesc);
        }
        Method method = propertyDesc.getWriteMethod();
        Binding binding = method.getAnnotation(Binding.class);
        String propName = propertyDesc.getPropertyName();
        if (binding != null) {
            String bindingTypeName = binding.bindingType().getName();
            String expression = binding.value();
            return createPropertyDef(propName, bindingTypeName, expression);
        }
        return super.createPropertyDef(beanDesc, propertyDesc);
    }
    
    public void appendAspect(ComponentDef componentDef) {
        Class<?> clazz = componentDef.getComponentClass();
        Aspect aspect = clazz.getAnnotation(Aspect.class);
        if (aspect == null) {
            super.appendAspect(componentDef);
            return;
        }
        String interceptor = aspect.interceptor();
        String pointcut = aspect.pointcut();
        appendAspect(componentDef, interceptor, pointcut);
    }
}
