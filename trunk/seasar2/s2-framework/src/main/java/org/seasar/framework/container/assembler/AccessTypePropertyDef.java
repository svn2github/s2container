/*
 * Copyright 2004-2013 the Seasar Foundation and the Others.
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
package org.seasar.framework.container.assembler;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.container.AccessTypeDef;
import org.seasar.framework.container.BindingTypeDef;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.PropertyDef;
import org.seasar.framework.container.util.BindingUtil;

/**
 * プロパティに対するアクセスタイプ定義です。
 * 
 * @author higa
 * 
 */
public class AccessTypePropertyDef implements AccessTypeDef {

    public String getName() {
        return PROPERTY_NAME;
    }

    public void bind(ComponentDef componentDef, PropertyDef propertyDef,
            Object component) {
        final BindingTypeDef bindingTypeDef = propertyDef.getBindingTypeDef();
        bind(componentDef, propertyDef, bindingTypeDef, component);
    }

    public void bind(ComponentDef componentDef, PropertyDef propertyDef,
            BindingTypeDef bindingTypeDef, Object component) {
        final BeanDesc beanDesc = BindingUtil.getBeanDesc(componentDef,
                component);
        final PropertyDesc propertyDesc = beanDesc.getPropertyDesc(propertyDef
                .getPropertyName());
        bindingTypeDef.bind(componentDef, propertyDef, propertyDesc, component);
    }

}
