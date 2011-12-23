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
package org.seasar.persistence.pspool;

import org.seasar.extension.unit.S2TestCase;
import org.seasar.persistence.PreparedStatementPool;

/**
 * @author higa
 * 
 */
public class PreparedStatementPoolFactoryImplTest extends S2TestCase {

	protected void setUp() throws Exception {
		super.setUp();
		include("jdbc.dicon");
	}

	/**
	 * Test method for
	 * {@link org.seasar.persistence.pspool.PreparedStatementPoolFactoryImpl#createPreparedStatementPool()}.
	 */
	public void testCreatePreparedStatementPool() {
		PreparedStatementPoolFactoryImpl factory = new PreparedStatementPoolFactoryImpl();
		factory.setConnection(getConnection());
		factory.setPoolSize(1);
		PreparedStatementPool pool = factory.createPreparedStatementPool();
		pool.close();
		assertNotNull(pool);
	}
}