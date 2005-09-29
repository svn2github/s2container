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
package org.seasar.framework.beans;

import org.seasar.framework.exception.SRuntimeException;

/**
 * @author higa
 *
 */
public class FieldNotFoundRuntimeException
	extends SRuntimeException {

	private Class targetClass_;
	private String fieldName_;

	public FieldNotFoundRuntimeException(
		Class componentClass,
		String fieldName) {
		super(
			"ESSR0070",
			new Object[] { componentClass.getName(), fieldName});
		targetClass_ = componentClass;
		fieldName_ = fieldName;
	}

	public Class getTargetClass() {
		return targetClass_;
	}
	
	public String getFieldName() {
		return fieldName_;
	}
}