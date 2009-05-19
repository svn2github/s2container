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
import java.util.Map;

import junit.framework.TestCase;

import org.seasar.persistence.PropertyMapper;
import org.seasar.persistence.entity.Department;
import org.seasar.persistence.entity.Employee;

/**
 * @author higa
 * 
 */
public class SingleAssociationBeanObjectMapperTest extends TestCase {

	/**
	 * @throws Exception
	 */
	public void testSetValues() throws Exception {
		Field field = Employee.class.getDeclaredField("id");
		field.setAccessible(true);
		FieldPropertyMapper propertyMapper = new FieldPropertyMapper(field, 0);
		Field field2 = Employee.class.getDeclaredField("employeeName");
		field2.setAccessible(true);
		FieldPropertyMapper propertyMapper2 = new FieldPropertyMapper(field2, 1);
		Map context = new HashMap();
		Object[] values = new Object[] { new Long(1), "SCOTT", new Long(2),
				"ACCOUNT" };
		BeanObjectMapper objectMapper = new BeanObjectMapper(Employee.class,
				new PropertyMapper[] { propertyMapper, propertyMapper2 },
				new int[] { 0 }, context);
		objectMapper.setValues(values);
		Field field3 = Department.class.getDeclaredField("id");
		field3.setAccessible(true);
		FieldPropertyMapper propertyMapper3 = new FieldPropertyMapper(field3, 2);
		Field field4 = Department.class.getDeclaredField("departmentName");
		field4.setAccessible(true);
		FieldPropertyMapper propertyMapper4 = new FieldPropertyMapper(field4, 3);
		Field field5 = Employee.class.getDeclaredField("department");
		field5.setAccessible(true);
		SingleAssociationBeanObjectMapper sabObjectMapper = new SingleAssociationBeanObjectMapper(
				Department.class, new PropertyMapper[] { propertyMapper3,
						propertyMapper4 }, new int[] { 2 }, context,
				objectMapper, field5);
		sabObjectMapper.setValues(values);
		Employee emp = (Employee) objectMapper.getTarget();
		Department dept = emp.department;
		assertEquals(new Long(2), dept.id);
		assertEquals("ACCOUNT", dept.departmentName);
	}

	/**
	 * @throws Exception
	 */
	public void testSetValues_multirow() throws Exception {
		Field field = Employee.class.getDeclaredField("id");
		field.setAccessible(true);
		FieldPropertyMapper propertyMapper = new FieldPropertyMapper(field, 0);
		Field field2 = Employee.class.getDeclaredField("employeeName");
		field2.setAccessible(true);
		FieldPropertyMapper propertyMapper2 = new FieldPropertyMapper(field2, 1);
		Map context = new HashMap();
		Object[] values = new Object[] { new Long(1), "SCOTT", new Long(2),
				"ACCOUNT" };
		Object[] values2 = new Object[] { new Long(2), "ALLEN", new Long(2),
				"ACCOUNT" };
		BeanObjectMapper objectMapper = new BeanObjectMapper(Employee.class,
				new PropertyMapper[] { propertyMapper, propertyMapper2 },
				new int[] { 0 }, context);
		objectMapper.setValues(values);
		Field field3 = Department.class.getDeclaredField("id");
		field3.setAccessible(true);
		FieldPropertyMapper propertyMapper3 = new FieldPropertyMapper(field3, 2);
		Field field4 = Department.class.getDeclaredField("departmentName");
		field4.setAccessible(true);
		FieldPropertyMapper propertyMapper4 = new FieldPropertyMapper(field4, 3);
		Field field5 = Employee.class.getDeclaredField("department");
		field5.setAccessible(true);
		SingleAssociationBeanObjectMapper sabObjectMapper = new SingleAssociationBeanObjectMapper(
				Department.class, new PropertyMapper[] { propertyMapper3,
						propertyMapper4 }, new int[] { 2 }, context,
				objectMapper, field5);
		sabObjectMapper.setValues(values);
		Employee emp = (Employee) objectMapper.getTarget();
		Department dept = emp.department;
		objectMapper.setValues(values2);
		sabObjectMapper.setValues(values2);
		Employee emp2 = (Employee) objectMapper.getTarget();
		Department dept2 = emp2.department;
		assertSame(dept, dept2);
		assertEquals("ACCOUNT", dept2.departmentName);
	}

	/**
	 * @throws Exception
	 */
	public void testSetValues_nest() throws Exception {
		Field field = Employee.class.getDeclaredField("id");
		field.setAccessible(true);
		FieldPropertyMapper propertyMapper = new FieldPropertyMapper(field, 0);
		Field field2 = Employee.class.getDeclaredField("employeeName");
		field2.setAccessible(true);
		FieldPropertyMapper propertyMapper2 = new FieldPropertyMapper(field2, 1);
		Map context = new HashMap();
		Object[] values = new Object[] { new Long(1), "SCOTT", new Long(2),
				"MANAGER", new Long(3), "MANAGER2" };
		BeanObjectMapper objectMapper = new BeanObjectMapper(Employee.class,
				new PropertyMapper[] { propertyMapper, propertyMapper2 },
				new int[] { 0 }, context);
		Field field3 = Employee.class.getDeclaredField("manager");
		FieldPropertyMapper propertyMapper3 = new FieldPropertyMapper(field, 2);
		FieldPropertyMapper propertyMapper4 = new FieldPropertyMapper(field2, 3);
		SingleAssociationBeanObjectMapper objectMapper2 = new SingleAssociationBeanObjectMapper(
				Employee.class, new PropertyMapper[] { propertyMapper3,
						propertyMapper4 }, new int[] { 2 }, context,
				objectMapper, field3);
		FieldPropertyMapper propertyMapper5 = new FieldPropertyMapper(field, 4);
		FieldPropertyMapper propertyMapper6 = new FieldPropertyMapper(field2, 5);
		SingleAssociationBeanObjectMapper objectMapper3 = new SingleAssociationBeanObjectMapper(
				Employee.class, new PropertyMapper[] { propertyMapper5,
						propertyMapper6 }, new int[] { 4 }, context,
				objectMapper2, field3);
		objectMapper.setValues(values);
		objectMapper2.setValues(values);
		objectMapper3.setValues(values);
		Employee emp = (Employee) objectMapper.getTarget();
		Employee manager = emp.manager;
		assertEquals(new Long(2), manager.id);
		assertEquals("MANAGER", manager.employeeName);
		Employee manager2 = manager.manager;
		assertEquals(new Long(3), manager2.id);
		assertEquals("MANAGER2", manager2.employeeName);
	}

}
