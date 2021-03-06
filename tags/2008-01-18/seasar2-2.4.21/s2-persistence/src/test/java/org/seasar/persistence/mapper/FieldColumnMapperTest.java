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

import junit.framework.TestCase;

import org.seasar.persistence.entity.Employee;

/**
 * @author higa
 * 
 */
public class FieldColumnMapperTest extends TestCase {

	/**
	 * Test method for
	 * {@link org.seasar.extension.persistence.mapper.FieldPropertyMapper#setValue(java.lang.Object, java.lang.Object[])}.
	 * 
	 * @throws Exception
	 */
	public void testSetValue() throws Exception {
		Field field = Employee.class.getDeclaredField("id");
		field.setAccessible(true);
		FieldPropertyMapper mapper = new FieldPropertyMapper(field, 0);
		Employee emp = new Employee();
		mapper.setValue(emp, new Object[] { new Long(1) });
		assertEquals(new Long(1), emp.id);
	}

}
