/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
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

import java.lang.reflect.Field;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.container.AutoBindingDef;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.InstanceDef;
import org.seasar.framework.container.PropertyDef;

public interface AnnotationHandler {

    ComponentDef createComponentDef(String className, InstanceDef instanceDef);

    ComponentDef createComponentDef(String className, InstanceDef instanceDef,
            AutoBindingDef autoBindingDef);

    ComponentDef createComponentDef(String className, InstanceDef instanceDef,
            AutoBindingDef autoBindingDef, boolean externalBinding);

    ComponentDef createComponentDef(Class componentClass,
            InstanceDef instanceDef);

    ComponentDef createComponentDef(Class componentClass,
            InstanceDef instanceDef, AutoBindingDef autoBindingDef);

    ComponentDef createComponentDef(Class componentClass,
            InstanceDef instanceDef, AutoBindingDef autoBindingDef,
            boolean externalBinding);

    void appendDI(ComponentDef componentDef);

    void appendAspect(ComponentDef componentDef);

    void appendInterType(ComponentDef componentDef);

    void appendInitMethod(ComponentDef componentDef);

    boolean isInitMethodRegisterable(ComponentDef cd, String methodName);

    void appendDestroyMethod(ComponentDef componentDef);

    boolean isDestroyMethodRegisterable(ComponentDef cd, String methodName);

    PropertyDef createPropertyDef(BeanDesc beanDesc, PropertyDesc propertyDesc);

    PropertyDef createPropertyDef(BeanDesc beanDesc, Field field);

}
