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
import java.sql.SQLException;

import org.seasar.extension.jdbc.ValueType;
import org.seasar.extension.jdbc.types.ValueTypes;
import org.seasar.extension.sql.SqlContext;
import org.seasar.framework.exception.SQLRuntimeException;
import org.seasar.framework.log.Logger;
import org.seasar.persistence.PreparedStatementPool;

/**
 * Processorの抽象クラスです。
 * 
 * @author higa
 * 
 */
public abstract class AbstractProcessor {

	protected static Logger logger = Logger.getLogger(AbstractProcessor.class);

	protected PreparedStatementPool preparedStatementPool;

	protected SqlContext sqlContext;

	protected Class<?> daoClass;

	/**
	 * <code>AbstractProcessor</code>を作成します。
	 * 
	 * @param preparedStatementPool
	 * @param sqlContext
	 * @param daoClass
	 */
	public AbstractProcessor(PreparedStatementPool preparedStatementPool,
			SqlContext sqlContext, Class<?> daoClass) {
		this.preparedStatementPool = preparedStatementPool;
		this.sqlContext = sqlContext;
		this.daoClass = daoClass;
	}

	protected void logSql() {
		log(sqlContext.getCompleteSql());
	}

	protected void log(String message) {
		logger.debug(daoClass.getName() + "\n" + message);
	}

	protected PreparedStatement getPreparedStatement() {
		return preparedStatementPool.get(sqlContext.getSql());
	}

	protected void bindVariables(PreparedStatement ps) {
		Object[] variables = sqlContext.getBindVariables();
		Class[] types = sqlContext.getBindVariableTypes();
		ValueType[] valueTypes = getValueTypes(types);
		bindVariables(ps, variables, valueTypes);
	}

	protected void bindVariables(PreparedStatement ps, Object[] variables,
			ValueType[] valueTypes) {
		try {
			for (int i = 0; i < valueTypes.length; i++) {
				valueTypes[i].bindValue(ps, i + 1, variables[i]);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}

	protected ValueType[] getValueTypes(Class[] types) {
		ValueType[] valueTypes = new ValueType[types.length];
		for (int i = 0; i < valueTypes.length; i++) {
			valueTypes[i] = ValueTypes.getValueType(types[i]);
		}
		return valueTypes;
	}
}