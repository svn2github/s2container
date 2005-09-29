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
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.annotation.Aspect;
import org.seasar.framework.container.annotation.Component;
import org.seasar.framework.container.annotation.Inject;
import org.seasar.framework.container.annotation.NoInject;
import org.seasar.framework.container.impl.ComponentDefImpl;
import org.seasar.framework.container.impl.PropertyDefImpl;
import org.seasar.framework.util.StringUtil;

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
        String instanceMode = component.instance();
        if (instanceMode != null) {
            componentDef.setInstanceMode(instanceMode);
        }
        String autoBindingMode = component.autoBinding();
        if (autoBindingMode != null) {
            componentDef.setAutoBindingMode(autoBindingMode);
        }
        return componentDef;
    }

    public PropertyDef createPropertyDef(S2Container container,
            BeanDesc beanDesc, PropertyDesc propertyDesc) {

        if (!propertyDesc.hasWriteMethod()) {
            return super.createPropertyDef(container, beanDesc, propertyDesc);
        }
        Method method = propertyDesc.getWriteMethod();
        Inject inject = (Inject) Annotations
                .getAnnotation(Inject.class, method);
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
            NoInject noInject = (NoInject) Annotations.getAnnotation(
                    NoInject.class, method);
            if (noInject != null) {
                return new PropertyDefImpl(propName, true);
            }
        }
        return super.createPropertyDef(container, beanDesc, propertyDesc);
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
