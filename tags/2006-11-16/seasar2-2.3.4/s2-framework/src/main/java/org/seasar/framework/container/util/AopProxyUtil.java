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
package org.seasar.framework.container.util;

import java.util.HashMap;
import java.util.Map;

import org.seasar.framework.aop.Aspect;
import org.seasar.framework.aop.InterType;
import org.seasar.framework.aop.proxy.AopProxy;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.ContainerConstants;

/**
 * @author koichik
 */
public class AopProxyUtil {

    protected AopProxyUtil() {
    }

    public static Class getConcreteClass(final ComponentDef componentDef) {
        if (componentDef.getAspectDefSize() == 0
                && componentDef.getInterTypeDefSize() == 0) {
            return componentDef.getComponentClass();
        }

        final Map parameters = new HashMap();
        parameters.put(ContainerConstants.COMPONENT_DEF_NAME, componentDef);
        AopProxy proxy = new AopProxy(componentDef.getComponentClass(),
                getAspects(componentDef), getInterTypes(componentDef),
                parameters);
        return proxy.getEnhancedClass();
    }

    protected static Aspect[] getAspects(final ComponentDef componentDef) {
        final int size = componentDef.getAspectDefSize();
        Aspect[] aspects = new Aspect[size];
        for (int i = 0; i < size; ++i) {
            aspects[i] = componentDef.getAspectDef(i).getAspect();
        }
        return aspects;
    }

    protected static InterType[] getInterTypes(final ComponentDef componentDef) {
        final int size = componentDef.getInterTypeDefSize();
        InterType[] interTypes = new InterType[size];
        for (int i = 0; i < size; ++i) {
            interTypes[i] = componentDef.getInterTypeDef(i).getInterType();
        }
        return interTypes;
    }
}