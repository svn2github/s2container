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
import java.util.Iterator;

import org.seasar.extension.jdbc.util.ConnectionUtil;
import org.seasar.framework.util.StatementUtil;
import org.seasar.persistence.PreparedStatementPool;

/**
 * {@link PreparedStatementPool}の実装クラスです。
 * 
 * @author higa
 * 
 */
public class PreparedStatementPoolImpl implements PreparedStatementPool {

	private Connection connection;

	private PreparedStatementLruHashMap lru;

	/**
	 * <code>PreparedStatementPoolImpl</code>を作成します。
	 * 
	 * @param connection
	 * @param limitSize
	 */
	public PreparedStatementPoolImpl(Connection connection, int limitSize) {
		this.connection = connection;
		lru = new PreparedStatementLruHashMap(limitSize);
	}

	public void close() {
		for (Iterator i = lru.values().iterator(); i.hasNext();) {
			PreparedStatement ps = (PreparedStatement) i.next();
			StatementUtil.close(ps);
		}
		lru.clear();
		connection = null;
	}

	@SuppressWarnings("unchecked")
	public PreparedStatement get(String sql) {
		PreparedStatement ps = (PreparedStatement) lru.get(sql);
		if (ps != null) {
			return ps;
		}
		ps = ConnectionUtil.prepareStatement(connection, sql);
		lru.put(sql, ps);
		return ps;
	}
}