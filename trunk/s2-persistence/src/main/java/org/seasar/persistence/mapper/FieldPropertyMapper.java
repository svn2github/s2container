/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
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
package org.seasar.persistence.mapper;

import java.lang.reflect.Field;

import org.seasar.framework.util.FieldUtil;
import org.seasar.persistence.PropertyMapper;

/**
 * <code>Field</code>を使ってプロパティの値を設定するクラスです。
 * 
 * @author higa
 * 
 */
public class FieldPropertyMapper implements PropertyMapper {

	protected Field field;

	protected int propertyIndex;

	/**
	 * <code>FieldPropertyMapper</code>を作成します。
	 * 
	 * @param field
	 * @param propertyIndex
	 */
	public FieldPropertyMapper(Field field, int propertyIndex) {
		this.field = field;
		this.propertyIndex = propertyIndex;
	}

	public void setValue(Object target, Object[] values) {
		setFieldValue(target, values[propertyIndex]);
	}

	protected void setFieldValue(Object target, Object value) {
		if (value == null) {
			return;
		}
		FieldUtil.set(field, target, value);
	}
}
