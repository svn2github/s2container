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

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.seasar.extension.jdbc.util.ConnectionUtil;
import org.seasar.extension.unit.S2TestCase;

/**
 * @author higa
 * 
 */
public class PreparedStatementLruHashMapTest extends S2TestCase {

	private static String SQL = "select * from employee";

	private static String SQL2 = "select * from department";

	protected void setUp() {
		include("jdbc.dicon");
	}

	/**
	 * Test method for
	 * {@link org.seasar.persistence.pspool.PreparedStatementLruHashMap#removeEldestEntry(java.util.Map.Entry)}.
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void testRemoveEldestEntryEntry() throws Exception {
		PreparedStatementLruHashMap map = new PreparedStatementLruHashMap(1);
		Connection con = getConnection();
		PreparedStatement ps = ConnectionUtil.prepareStatement(con, SQL);
		PreparedStatement ps2 = ConnectionUtil.prepareStatement(con, SQL2);
		try {
			map.put(SQL, ps);
			assertNotNull((PreparedStatement) map.get(SQL));
			map.put(SQL2, ps2);
			assertNull((PreparedStatement) map.get(SQL));
		} finally {
			ps.close();
			ps2.close();
		}
	}

}
