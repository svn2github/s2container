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
package org.seasar.framework.container.factory;

import java.lang.reflect.Method;

import org.codehaus.backport175.reader.Annotations;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.container.AutoBindingDef;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.IllegalDestroyMethodAnnotationRuntimeException;
import org.seasar.framework.container.IllegalInitMethodAnnotationRuntimeException;
import org.seasar.framework.container.InstanceDef;
import org.seasar.framework.container.PropertyDef;
import org.seasar.framework.container.annotation.backport175.Aspect;
import org.seasar.framework.container.annotation.backport175.Binding;
import org.seasar.framework.container.annotation.backport175.Component;
import org.seasar.framework.container.annotation.backport175.DestroyMethod;
import org.seasar.framework.container.annotation.backport175.InitMethod;
import org.seasar.framework.container.annotation.backport175.InterType;

/**
 * @author higa
 * 
 */
public class Backport175AnnotationHandler extends ConstantAnnotationHandler {

    public ComponentDef createComponentDef(Class componentClass, InstanceDef defaultInstanceDef,
            AutoBindingDef defaultAutoBindingDef) {

        String name = null;
        InstanceDef instanceDef = null;
        AutoBindingDef autoBindingDef = null;
        Component component = (Component) Annotations
                .getAnnotation(Component.class, componentClass);
        if (component == null) {
            return super.createComponentDef(componentClass, defaultInstanceDef,
                    defaultAutoBindingDef);
        }
        name = component.name();
        instanceDef = getInstanceDef(component.instance(), defaultInstanceDef);
        autoBindingDef = getAutoBindingDef(component.autoBinding());
        return createComponentDef(componentClass, name, instanceDef, autoBindingDef);
    }

    public PropertyDef createPropertyDef(BeanDesc beanDesc, PropertyDesc propertyDesc) {

        if (!propertyDesc.hasWriteMethod()) {
            return super.createPropertyDef(beanDesc, propertyDesc);
        }
        Method method = propertyDesc.getWriteMethod();
        String propName = propertyDesc.getPropertyName();
        Binding binding = (Binding) Annotations.getAnnotation(Binding.class, method);
        if (binding != null) {
            String bindingTypeName = binding.bindingType();
            String expression = binding.value();
            return createPropertyDef(propName, expression, bindingTypeName);
        }
        return super.createPropertyDef(beanDesc, propertyDesc);
    }

    public void appendAspect(ComponentDef componentDef) {
        Class componentClass = componentDef.getComponentClass();
        Aspect aspect = (Aspect) Annotations.getAnnotation(Aspect.class, componentClass);
        if (aspect != null) {
            String interceptor = aspect.value();
            String pointcut = aspect.pointcut();
            appendAspect(componentDef, interceptor, pointcut);
        }
        Method[] methods = componentClass.getMethods();
        for (int i = 0; i < methods.length; ++i) {
            Aspect mAspect = (Aspect) Annotations.getAnnotation(Aspect.class, methods[i]);
            if (mAspect != null) {
                String interceptor = mAspect.value();
                appendAspect(componentDef, interceptor, methods[i]);
            }
        }
        super.appendAspect(componentDef);
    }

    public void appendInterType(ComponentDef componentDef) {
        Class componentClass = componentDef.getComponentClass();
        InterType interType = (InterType) Annotations
                .getAnnotation(InterType.class, componentClass);
        if (interType != null) {
            String[] interTypeNames = interType.value();
            for (int i = 0; i < interTypeNames.length; ++i) {
                appendInterType(componentDef, interTypeNames[i]);
            }
        }
        super.appendInterType(componentDef);
    }

    public void appendInitMethod(ComponentDef componentDef) {
        Class componentClass = componentDef.getComponentClass();
        if (componentClass == null) {
            return;
        }
        Method[] methods = componentClass.getMethods();
        for (int i = 0; i < methods.length; ++i) {
            Method method = methods[i];
            InitMethod initMethod = (InitMethod) Annotations
                    .getAnnotation(InitMethod.class, method);
            if (initMethod == null) {
                continue;
            }
            if (method.getParameterTypes().length != 0) {
                throw new IllegalInitMethodAnnotationRuntimeException(componentClass, method
                        .getName());
            }
            if (!isInitMethodRegisterable(componentDef, method.getName())) {
                continue;
            }
            appendInitMethod(componentDef, method.getName());
        }
        super.appendInitMethod(componentDef);
    }

    public void appendDestroyMethod(ComponentDef componentDef) {
        Class componentClass = componentDef.getComponentClass();
        if (componentClass == null) {
            return;
        }
        Method[] methods = componentClass.getMethods();
        for (int i = 0; i < methods.length; ++i) {
            Method method = methods[i];
            DestroyMethod destroyMethod = (DestroyMethod) Annotations.getAnnotation(
                    DestroyMethod.class, method);
            if (destroyMethod == null) {
                continue;
            }
            if (method.getParameterTypes().length != 0) {
                throw new IllegalDestroyMethodAnnotationRuntimeException(componentClass, method
                        .getName());
            }
            if (!isDestroyMethodRegisterable(componentDef, method.getName())) {
                continue;
            }
            appendDestroyMethod(componentDef, method.getName());
        }
        super.appendDestroyMethod(componentDef);
    }
}
