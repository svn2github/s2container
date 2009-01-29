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
package org.seasar.persistence.processor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.seasar.extension.sql.SqlContext;
import org.seasar.framework.exception.SQLRuntimeException;
import org.seasar.framework.util.PreparedStatementUtil;
import org.seasar.framework.util.ResultSetUtil;
import org.seasar.persistence.PreparedStatementPool;
import org.seasar.persistence.ResultSetProcessor;
import org.seasar.persistence.RowMapper;
import org.seasar.persistence.SelectProcessor;

/**
 * {@link SelectProcessor}の抽象クラスです。
 * 
 * @author higa
 * 
 */
public abstract class AbstractSelectProcessor extends AbstractProcessor
		implements SelectProcessor {

	protected ResultSetProcessor resultSetProcessor;

	protected RowMapper rowMapper;

	/**
	 * <code>AbstractSelectProcessor</code>を作成します。
	 * 
	 * @param preparedStatementPool
	 * @param sqlContext
	 * @param daoClass
	 * @param resultSetProcessor
	 * @param rowMapper
	 */
	public AbstractSelectProcessor(PreparedStatementPool preparedStatementPool,
			SqlContext sqlContext, Class<?> daoClass,
			ResultSetProcessor resultSetProcessor, RowMapper rowMapper) {
		super(preparedStatementPool, sqlContext, daoClass);
		this.resultSetProcessor = resultSetProcessor;
		this.rowMapper = rowMapper;
	}

	public Object execute() {
		logSql();
		PreparedStatement ps = getPreparedStatement();
		bindVariables(ps);
		ResultSet rs = executeQuery(ps);
		Object ret = null;
		try {
			ret = doSelect(rs);
		} finally {
			ResultSetUtil.close(rs);
		}
		return ret;
	}

	/**
	 * @param rs
	 * @return {@link ResultSet}をオブジェクトに変換した結果
	 * @throws SQLRuntimeException
	 *             {@link SQLException}が起こった場合。
	 */
	protected abstract Object doSelect(ResultSet rs) throws SQLRuntimeException;

	protected ResultSet executeQuery(PreparedStatement ps) {
		return PreparedStatementUtil.executeQuery(ps);
	}
}
