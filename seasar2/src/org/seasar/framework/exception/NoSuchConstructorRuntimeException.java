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
package org.seasar.framework.exception;

import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.MethodUtil;

/**
 * @author higa
 *
 */
public class NoSuchConstructorRuntimeException extends SRuntimeException {

	private Class targetClass_;
	private Class[] argTypes_;

	public NoSuchConstructorRuntimeException(
		Class targetClass,
		Class[] argTypes,
		NoSuchMethodException cause) {

		super(
			"ESSR0064",
			new Object[] { targetClass.getName(),
				MethodUtil.getSignature(ClassUtil.getShortClassName(targetClass), argTypes), cause},
			cause);
		targetClass_ = targetClass;
		argTypes_ = argTypes;
	}
	
	public Class getTargetClass() {
		return targetClass_;
	}
	
	public Class[] getArgTypes() {
		return argTypes_;
	}
}
