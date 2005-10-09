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

import org.codehaus.backport175.reader.Annotation;
import org.codehaus.backport175.reader.Annotations;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.PropertyDef;
import org.seasar.framework.container.annotation.Aspect;
import org.seasar.framework.container.annotation.Binding;
import org.seasar.framework.container.annotation.Component;
import org.seasar.framework.container.assembler.AutoBindingDefFactory;
import org.seasar.framework.container.deployer.InstanceDefFactory;
import org.seasar.framework.container.impl.ComponentDefImpl;

/**
 * @author higa
 * 
 */
public class Backport175AnnotationHandler extends ConstantAnnotationHandler {

    public ComponentDef createComponentDef(Class componentClass) {
        Annotation annotation = Annotations.getAnnotation(Component.class,
                componentClass);
        if (annotation == null) {
            return super.createComponentDef(componentClass);
        }
        Component component = (Component) annotation;
        String name = component.name();
        ComponentDef componentDef = new ComponentDefImpl(componentClass, name);
        String instanceName = component.instance();
        if (instanceName != null) {
            componentDef.setInstanceDef(InstanceDefFactory
                    .getInstanceDef(instanceName));
        }
        String autoBindingName = component.autoBinding();
        if (autoBindingName != null) {
            componentDef.setAutoBindingDef(AutoBindingDefFactory
                    .getAutoBindingDef(autoBindingName));
        }
        return componentDef;
    }

    public PropertyDef createPropertyDef(BeanDesc beanDesc,
            PropertyDesc propertyDesc) {

        if (!propertyDesc.hasWriteMethod()) {
            return super.createPropertyDef(beanDesc, propertyDesc);
        }
        Method method = propertyDesc.getWriteMethod();
        String propName = propertyDesc.getPropertyName();
        Binding binding = (Binding) Annotations.getAnnotation(Binding.class,
                method);
        if (binding != null) {
            String bindingTypeName = binding.bindingType();
            String expression = binding.value();
            return createPropertyDef(propName, bindingTypeName, expression);
        }
        return super.createPropertyDef(beanDesc, propertyDesc);
    }

    public void appendAspect(ComponentDef componentDef) {
        Class componentClass = componentDef.getComponentClass();
        Annotation annotation = Annotations.getAnnotation(Aspect.class,
                componentClass);
        if (annotation == null) {
            super.appendAspect(componentDef);
            return;
        }
        Aspect aspect = (Aspect) annotation;
        String interceptor = aspect.interceptor();
        String pointcut = aspect.pointcut();
        appendAspect(componentDef, interceptor, pointcut);
    }
}
