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
package org.seasar.framework.container.binding;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.PropertyDef;

/**
 * @author higa
 *
 */
public class ManualPropertyAssembler extends AbstractPropertyAssembler {

	/**
	 * @param componentDef
	 */
	public ManualPropertyAssembler(ComponentDef componentDef) {
		super(componentDef);
	}

	public void assemble(Object component) {
		BeanDesc beanDesc = getBeanDesc(component);
		int size = getComponentDef().getPropertyDefSize();
		for (int i = 0; i < size; ++i) {
			PropertyDef propDef = getComponentDef().getPropertyDef(i);
			Object value = getValue(propDef, component);
			PropertyDesc propDesc =
				beanDesc.getPropertyDesc(propDef.getPropertyName());
			setValue(propDesc, component, value);
		}
	}
}