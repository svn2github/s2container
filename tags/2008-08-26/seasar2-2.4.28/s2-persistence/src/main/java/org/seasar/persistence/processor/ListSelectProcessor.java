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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.seasar.extension.sql.SqlContext;
import org.seasar.framework.exception.SQLRuntimeException;
import org.seasar.persistence.PreparedStatementPool;
import org.seasar.persistence.ResultSetProcessor;
import org.seasar.persistence.RowMapper;
import org.seasar.persistence.SelectProcessor;

/**
 * {@link List}を返す{@link SelectProcessor}です。
 * 
 * @author higa
 * 
 */
public class ListSelectProcessor extends AbstractSelectProcessor {

	/**
	 * {@link ListSelectProcessor}を作成します。
	 * 
	 * @param preparedStatementPool
	 * @param sqlContext
	 * @param daoClass
	 * @param resultSetProcessor
	 * @param rowMapper
	 */
	public ListSelectProcessor(PreparedStatementPool preparedStatementPool,
			SqlContext sqlContext, Class<?> daoClass,
			ResultSetProcessor resultSetProcessor, RowMapper rowMapper) {
		super(preparedStatementPool, sqlContext, daoClass, resultSetProcessor,
				rowMapper);
	}

	@SuppressWarnings("unchecked")
	protected Object doSelect(ResultSet rs) {
		List ret = new ArrayList();
		try {
			while (rs.next()) {
				Object[] values = resultSetProcessor.getValues(rs);
				rowMapper.setValues(values);
				ret.add(rowMapper.getTarget());
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
		return ret;
	}

}
