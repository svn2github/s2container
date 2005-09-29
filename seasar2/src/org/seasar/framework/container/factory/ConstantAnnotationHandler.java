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

import java.lang.reflect.Field;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.container.AspectDef;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.PropertyDef;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.impl.ComponentDefImpl;
import org.seasar.framework.container.impl.PropertyDefImpl;
import org.seasar.framework.exception.EmptyRuntimeException;
import org.seasar.framework.util.FieldUtil;
import org.seasar.framework.util.StringUtil;

public class ConstantAnnotationHandler extends AbstractAnnotationHandler {

    public ComponentDef createComponentDef(Class componentClass) {
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(componentClass);
        if (!beanDesc.hasField(COMPONENT)) {
            return new ComponentDefImpl(componentClass);
        }
        Field field = beanDesc.getField(COMPONENT);
        String componentStr = (String) FieldUtil.get(field, null);
        String[] array = StringUtil.split(componentStr, "=, ");
        ComponentDef componentDef = new ComponentDefImpl(componentClass);
        for (int i = 0; i < array.length; i += 2) {
            String key = array[i].trim();
            String value = array[i + 1].trim();
            if (NAME.equalsIgnoreCase(key)) {
                componentDef.setComponentName(value);
            } else if (INSTANCE.equalsIgnoreCase(key)) {
                componentDef.setInstanceMode(value);
            } else if (AUTO_BINDING.equalsIgnoreCase(key)) {
                componentDef.setAutoBindingMode(value);
            } else {
                throw new IllegalArgumentException(componentStr);
            }
        }
        return componentDef;
    }

    public PropertyDef createPropertyDef(S2Container container,
            BeanDesc beanDesc, PropertyDesc propertyDesc) {

        String fieldName = propertyDesc.getPropertyName() + INJECT_SUFFIX;
        if (beanDesc.hasField(fieldName)) {
            Field field = beanDesc.getField(fieldName);
            String value = (String) FieldUtil.get(field, null);
            if (value == null) {
                return null;
            }
            PropertyDef pd = new PropertyDefImpl(propertyDesc.getPropertyName());
            pd.setExpression(value);
            return pd;
        }
        fieldName = propertyDesc.getPropertyName() + NO_INJECT_SUFFIX;
        if (beanDesc.hasField(fieldName)) {
            Field field = beanDesc.getField(fieldName);
            Boolean value = (Boolean) FieldUtil.get(field, null);
            if (Boolean.TRUE.equals(value)) {
                return new PropertyDefImpl(propertyDesc.getPropertyName(), true);
            }
        }
        return null;
    }

    public void appendAspect(ComponentDef componentDef) {
        Class componentClass = componentDef.getComponentClass();
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(componentClass);
        if (!beanDesc.hasField(ASPECT)) {
            return;
        }
        String aspectStr = (String) beanDesc.getFieldValue(ASPECT, null);
        String[] array = StringUtil.split(aspectStr, "=, ");
        String interceptor = null;
        String pointcut = null;
        for (int i = 0; i < array.length; i += 2) {
            String key = array[i].trim();
            String value = array[i + 1].trim();
            if (INTERCEPTOR.equalsIgnoreCase(key)) {
                interceptor = value;
            } else if (POINTCUT.equalsIgnoreCase(key)) {
                pointcut = value;
            } else {
                throw new IllegalArgumentException(aspectStr);
            }
        }
        appendAspect(componentDef, interceptor, pointcut);
    }
    
    protected void appendAspect(ComponentDef componentDef,
            String interceptor, String pointcut) {
        
        if (interceptor == null) {
            throw new EmptyRuntimeException("interceptor");
        }
        AspectDef aspectDef = AspectDefFactory.createAspectDef(interceptor, pointcut);
        componentDef.addAspectDef(aspectDef);
    }
}
