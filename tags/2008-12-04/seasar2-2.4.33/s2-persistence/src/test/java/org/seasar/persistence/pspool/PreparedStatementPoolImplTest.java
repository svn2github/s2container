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

import java.sql.PreparedStatement;

import org.seasar.extension.unit.S2TestCase;

/**
 * @author higa
 * 
 */
public class PreparedStatementPoolImplTest extends S2TestCase {

	private static final String SQL = "select * from employee";

	protected void setUp() {
		include("jdbc.dicon");
	}

	/**
	 * Test method for
	 * {@link org.seasar.persistence.pspool.PreparedStatementPoolImpl#close()}.
	 */
	public void testClose() {
		PreparedStatementPoolImpl pool = new PreparedStatementPoolImpl(
				getConnection(), 1);
		try {
			pool.get(SQL);
		} finally {
			pool.close();
		}
	}

	/**
	 * Test method for
	 * {@link org.seasar.persistence.pspool.PreparedStatementPoolImpl#get(java.lang.String)}.
	 */
	public void testGet() {
		PreparedStatementPoolImpl pool = new PreparedStatementPoolImpl(
				getConnection(), 1);
		try {
			PreparedStatement ps = pool.get(SQL);
			assertNotNull(ps);
			assertSame(ps, pool.get(SQL));
		} finally {
			pool.close();
		}
	}

}
