/**
 * 
 */
package org.seasar.persistence.processor;

import java.sql.Connection;

import junit.framework.TestCase;

/**
 * @author higa
 * 
 */
public class AbstractProcessorTest extends TestCase {

	/**
	 * Test method for
	 * {@link org.seasar.persistence.processor.AbstractProcessor#logSql()}.
	 */
	public void testLogSql() {
		AbstractProcessor processor = new MyProcessor(null, null, null,
				getClass(), "select * from emp");
		processor.logSql();
	}

	private static class MyProcessor extends AbstractProcessor {

		/**
		 * @param connection
		 * @param sql
		 * @param bindVariables
		 * @param daoClass
		 * @param concreteSql
		 */
		public MyProcessor(Connection connection, String sql,
				Object[] bindVariables, Class<?> daoClass, String concreteSql) {
			super(connection, sql, bindVariables, daoClass, concreteSql);
		}

	}
}
