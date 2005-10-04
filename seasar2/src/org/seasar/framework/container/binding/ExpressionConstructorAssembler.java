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

import org.seasar.framework.container.ClassUnmatchRuntimeException;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.IllegalConstructorRuntimeException;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.util.OgnlUtil;
/**
 * @author higa
 *
 */
public class ExpressionConstructorAssembler
	extends AbstractConstructorAssembler {

	/**
	 * @param componentDef
	 */
	public ExpressionConstructorAssembler(ComponentDef componentDef) {
		super(componentDef);
	}

	public Object assemble()
		throws IllegalConstructorRuntimeException {

		ComponentDef cd = getComponentDef();
		S2Container container = cd.getContainer();
		String expression = cd.getExpression();
		Class componentClass = cd.getComponentClass();
		Object component = null;
		Object exp = OgnlUtil.parseExpression(expression);
		component = OgnlUtil.getValue(exp, container);
		if (componentClass != null) {
			if (!componentClass.isInstance(component)) {
				throw new ClassUnmatchRuntimeException(componentClass,
					component != null ? component.getClass() : null);
			}
		}
		return component;

	}
}
