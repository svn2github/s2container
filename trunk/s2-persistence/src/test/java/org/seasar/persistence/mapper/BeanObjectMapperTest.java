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
import java.util.HashMap;

import junit.framework.TestCase;

import org.seasar.persistence.PropertyMapper;
import org.seasar.persistence.entity.Employee;

/**
 * @author higa
 * 
 */
public class BeanObjectMapperTest extends TestCase {

	/**
	 * Test method for
	 * {@link org.seasar.persistence.mapper.BeanObjectMapper#setValues(Object, Object[])}.
	 * 
	 * @throws Exception
	 */
	public void testSetValues() throws Exception {
		Field field = Employee.class.getDeclaredField("id");
		field.setAccessible(true);
		FieldPropertyMapper propertyMapper = new FieldPropertyMapper(field, 0);
		Field field2 = Employee.class.getDeclaredField("employeeName");
		field2.setAccessible(true);
		FieldPropertyMapper propertyMapper2 = new FieldPropertyMapper(field2, 1);
		BeanObjectMapper objectMapper = new BeanObjectMapper(Employee.class,
				new PropertyMapper[] { propertyMapper, propertyMapper2 },
				new int[] { 0 }, new HashMap());
		objectMapper.setValues(new Object[] { new Long(1), "SCOTT" });
		Employee emp = (Employee) objectMapper.getTarget();
		assertEquals(new Long(1), emp.id);
		assertEquals("SCOTT", emp.employeeName);

		objectMapper.setValues(new Object[] { new Long(1), "SCOTT" });
		assertSame(emp, objectMapper.getTarget());
	}

	/**
	 * Test method for
	 * {@link org.seasar.persistence.mapper.BeanObjectMapper#setValues(Object, Object[])}.
	 * 
	 * @throws Exception
	 */
	public void testSetValues_multikey() throws Exception {
		Field field = Employee.class.getDeclaredField("id");
		field.setAccessible(true);
		FieldPropertyMapper propertyMapper = new FieldPropertyMapper(field, 0);
		Field field2 = Employee.class.getDeclaredField("employeeName");
		field2.setAccessible(true);
		FieldPropertyMapper propertyMapper2 = new FieldPropertyMapper(field2, 1);
		BeanObjectMapper objectMapper = new BeanObjectMapper(Employee.class,
				new PropertyMapper[] { propertyMapper, propertyMapper2 },
				new int[] { 0, 1 }, new HashMap());
		objectMapper.setValues(new Object[] { new Long(1), "SCOTT" });
		Employee emp = (Employee) objectMapper.getTarget();
		assertEquals(new Long(1), emp.id);
		assertEquals("SCOTT", emp.employeeName);

		objectMapper.setValues(new Object[] { new Long(1), "SCOTT" });
		assertSame(emp, objectMapper.getTarget());
	}
}