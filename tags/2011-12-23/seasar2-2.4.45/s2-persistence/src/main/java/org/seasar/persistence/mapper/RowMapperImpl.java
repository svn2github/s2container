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

import org.seasar.persistence.ObjectMapper;
import org.seasar.persistence.RowMapper;

/**
 * 行をBeanにマッピングするクラスです。
 * 
 * @author higa
 * 
 */
public class RowMapperImpl implements RowMapper {

	private ObjectMapper[] objectMappers;

	/**
	 * <code>RowMapperImpl</code>を作成します。
	 * 
	 * @param objectMappers
	 */
	public RowMapperImpl(ObjectMapper[] objectMappers) {
		this.objectMappers = objectMappers;
	}

	public void setValues(Object[] values) {
		for (ObjectMapper objectMapper : objectMappers) {
			objectMapper.setValues(values);
		}
	}

	public Object getTarget() {
		return objectMappers[0].getTarget();
	}
}