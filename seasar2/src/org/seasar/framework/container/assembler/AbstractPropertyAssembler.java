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
package org.seasar.framework.container.assembler;

import org.seasar.framework.beans.IllegalPropertyRuntimeException;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.ComponentNotFoundRuntimeException;
import org.seasar.framework.container.PropertyDef;

/**
 * @author higa
 *
 */
public abstract class AbstractPropertyAssembler
	extends AbstractAssembler
	implements PropertyAssembler {

	public AbstractPropertyAssembler(ComponentDef componentDef) {
		super(componentDef);
	}

	protected Object getValue(PropertyDef propertyDef, Object component) {
		try {
			return propertyDef.getValue();
		} catch (ComponentNotFoundRuntimeException cause) {
			throw new IllegalPropertyRuntimeException(
				getComponentClass(component),
				propertyDef.getPropertyName(),
				cause);
		}

	}

	protected void setValue(
		PropertyDesc propertyDesc,
		Object component,
		Object value)
		throws IllegalPropertyRuntimeException {
		
		if (value == null) {
			return;
		}
		try {
			propertyDesc.setValue(component, value);
		} catch (NumberFormatException ex) {
			throw new IllegalPropertyRuntimeException(
				getComponentDef().getComponentClass(),
				propertyDesc.getPropertyName(),
				ex);
		}
	}
}