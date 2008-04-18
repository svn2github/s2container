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
package org.seasar.persistence.manager;

import junit.framework.TestCase;

import org.seasar.framework.convention.impl.PersistenceConventionImpl;
import org.seasar.persistence.dto.EmployeeDto;
import org.seasar.persistence.exception.NoEntityRuntimeException;
import org.seasar.persistence.meta.ColumnMetaFactoryImpl;
import org.seasar.persistence.meta.EntityMetaFactoryImpl;
import org.seasar.persistence.meta.PropertyMetaFactoryImpl;
import org.seasar.persistence.meta.TableMetaFactoryImpl;

/**
 * @author higa
 * 
 */
public class PersistenceManagerImplTest extends TestCase {

	private PersistenceManagerImpl manager;

	@Override
	protected void setUp() throws Exception {
		manager = new PersistenceManagerImpl();
		PersistenceConventionImpl convention = new PersistenceConventionImpl();
		EntityMetaFactoryImpl factory = new EntityMetaFactoryImpl();
		TableMetaFactoryImpl tableMetaFactory = new TableMetaFactoryImpl();
		tableMetaFactory.setPersistenceConvention(convention);
		factory.setTableMetaFactory(tableMetaFactory);
		PropertyMetaFactoryImpl propertyMetaFactory = new PropertyMetaFactoryImpl();
		ColumnMetaFactoryImpl cmFactory = new ColumnMetaFactoryImpl();
		cmFactory.setPersistenceConvention(convention);
		propertyMetaFactory.setColumnMetaFactory(cmFactory);
		factory.setPropertyMetaFactory(propertyMetaFactory);
		manager.setEntityMetaFactory(factory);
	}

	/**
	 * Test method for
	 * {@link org.seasar.extension.persistence.manager.PersistenceManagerImpl#findAll(java.lang.Class, java.lang.Object)}.
	 */
	public void testFindAll_noentity() {
		try {
			manager.findAll(EmployeeDto.class, null);
			fail();
		} catch (NoEntityRuntimeException e) {
			System.out.println(e);
		}
	}

}
