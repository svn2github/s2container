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

import org.seasar.persistence.PreparedStatementPool;
import org.seasar.persistence.PreparedStatementPoolFactory;

/**
 * {@link PreparedStatementPoolFactory}の実装クラスです。
 * 
 * @author higa
 * 
 */
public class PreparedStatementPoolFactoryImpl implements
		PreparedStatementPoolFactory {

	private Connection connection;

	private int poolSize;

	public PreparedStatementPool createPreparedStatementPool() {
		return new PreparedStatementPoolImpl(connection, poolSize);
	}

	/**
	 * connectionを返します。
	 * 
	 * @return connection
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * connectionを設定します。
	 * 
	 * @param connection
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	/**
	 * poolSizeを返します。
	 * 
	 * @return poolSize
	 */
	public int getPoolSize() {
		return poolSize;
	}

	/**
	 * poolSizeを設定します。
	 * 
	 * @param poolSize
	 */
	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
	}
}