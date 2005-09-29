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

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.ComponentNotFoundRuntimeException;
import org.seasar.framework.container.IllegalConstructorRuntimeException;

/**
 * @author higa
 *
 */
public class ManualConstructorAssembler
	extends AbstractConstructorAssembler {

	/**
	 * @param componentDef
	 */
	public ManualConstructorAssembler(ComponentDef componentDef) {
		super(componentDef);
	}

	public Object assemble()
		throws IllegalConstructorRuntimeException {

		Object[] args = new Object[getComponentDef().getArgDefSize()];
		for (int i = 0; i < args.length; ++i) {
			try {
				args[i] = getComponentDef().getArgDef(i).getValue();
			} catch (ComponentNotFoundRuntimeException cause) {
				throw new IllegalConstructorRuntimeException(
					getComponentDef().getComponentClass(),
					cause);
			}

		}
		BeanDesc beanDesc =
			BeanDescFactory.getBeanDesc(getComponentDef().getConcreteClass());
		return beanDesc.newInstance(args);
	}
}
