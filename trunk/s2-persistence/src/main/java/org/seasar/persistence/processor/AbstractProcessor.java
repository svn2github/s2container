/**
 * 
 */
package org.seasar.persistence.processor;

import java.sql.Connection;

import org.seasar.framework.log.Logger;

/**
 * Processorの抽象クラスです。
 * 
 * @author higa
 * 
 */
public abstract class AbstractProcessor {

	protected static Logger logger = Logger.getLogger(AbstractProcessor.class);

	protected Connection connection;

	protected String sql;

	protected Object[] bindVariables;

	protected Class<?> daoClass;

	protected String concreteSql;

	/**
	 * <code>AbstractProcessor</code>を作成します。
	 * 
	 * @param connection
	 * @param sql
	 * @param bindVariables
	 * @param daoClass
	 * @param concreteSql
	 */
	public AbstractProcessor(Connection connection, String sql,
			Object[] bindVariables, Class<?> daoClass, String concreteSql) {
		this.connection = connection;
		this.sql = sql;
		this.bindVariables = bindVariables;
		this.daoClass = daoClass;
		this.concreteSql = concreteSql;
	}

	protected void logSql() {
		logger.debug(daoClass.getName() + "\n" + concreteSql);
	}
}