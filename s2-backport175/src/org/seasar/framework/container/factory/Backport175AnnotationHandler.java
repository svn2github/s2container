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
import org.seasar.framework.container.InstanceDef;
import org.seasar.framework.container.PropertyDef;
import org.seasar.framework.container.annotation.Aspect;
import org.seasar.framework.container.annotation.Binding;
import org.seasar.framework.container.annotation.Component;
import org.seasar.framework.container.assembler.AutoBindingDefFactory;
import org.seasar.framework.container.deployer.InstanceDefFactory;

/**
 * @author higa
 * 
 */
public class Backport175AnnotationHandler extends ConstantAnnotationHandler {

    public ComponentDef createComponentDef(Class componentClass,
            InstanceDef instanceDef) {
        Annotation annotation = Annotations.getAnnotation(Component.class,
                componentClass);
        if (annotation == null) {
            return super.createComponentDef(componentClass, instanceDef);
        }
        Component component = (Component) annotation;
        ComponentDef componentDef = createComponentDefInternal(componentClass,
                instanceDef);
        componentDef.setComponentName(component.name());
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
        Aspect aspect = (Aspect) Annotations.getAnnotation(Aspect.class,
                componentClass);
        if (aspect != null) {
            String interceptor = aspect.interceptor();
            String pointcut = aspect.pointcut();
            appendAspect(componentDef, interceptor, pointcut);
        }
        Method[] methods = componentClass.getMethods();
        for (int i = 0; i < methods.length; ++i) {
            Aspect mAspect = (Aspect) Annotations.getAnnotation(Aspect.class,
                    methods[i]);
            if (mAspect != null) {
                String interceptor = mAspect.interceptor();
                String pointcut = methods[i].getName();
                appendAspect(componentDef, interceptor, pointcut);
            }
        }
        super.appendAspect(componentDef);

    }
}
