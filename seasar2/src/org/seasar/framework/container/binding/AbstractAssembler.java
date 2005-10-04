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
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.ComponentNotFoundRuntimeException;
import org.seasar.framework.log.Logger;

/**
 * @author higa
 *
 */
public abstract class AbstractAssembler {
	
	private static Logger logger_ = Logger.getLogger(AbstractAssembler.class);

	private ComponentDef componentDef_;

	public AbstractAssembler(ComponentDef componentDef) {
		componentDef_ = componentDef;
	}

	protected final ComponentDef getComponentDef() {
		return componentDef_;
	}

	protected final BeanDesc getBeanDesc() {
		return BeanDescFactory.getBeanDesc(
			getComponentDef().getComponentClass());
	}
	
	protected final BeanDesc getBeanDesc(Object component) {
		return BeanDescFactory.getBeanDesc(
			getComponentClass(component));
	}
	
	protected final Class getComponentClass(Object component) {
		Class clazz = componentDef_.getComponentClass();
		if (clazz != null) {
			return clazz;
		}
		return component.getClass();
	}
	
	protected Object[] getArgs(Class[] argTypes) {
		Object[] args = new Object[argTypes.length];
		for (int i = 0; i < argTypes.length; ++i) {
			try {
				args[i] =
					getComponentDef().getContainer().getComponent(argTypes[i]);
			} catch (ComponentNotFoundRuntimeException ex) {
				logger_.log("WSSR0007",
						new Object[] {
							getComponentDef().getComponentClass().getName(),
							ex.getComponentKey()});
				args[i] = null;
			}
		}
		return args;
	}
}