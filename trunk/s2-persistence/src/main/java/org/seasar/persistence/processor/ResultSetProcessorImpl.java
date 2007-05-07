/**
 * 
 */
package org.seasar.persistence.processor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.seasar.extension.jdbc.ValueType;
import org.seasar.framework.exception.SQLRuntimeException;
import org.seasar.persistence.ResultSetProcessor;

/**
 * {@link ResultSetProcessor}のデフォルトの実装クラスです。
 * 
 * @author higa
 * 
 */
public class ResultSetProcessorImpl implements ResultSetProcessor {

	private ValueType[] types;

	/**
	 * <code>ResultSetProcessorImpl</code>を作成します。
	 * 
	 * @param types
	 */
	public ResultSetProcessorImpl(ValueType[] types) {
		this.types = types;
	}

	public Object[] getValues(ResultSet rs) {
		Object[] values = new Object[types.length];
		try {
			for (int i = 0; i < values.length; i++) {
				values[i] = types[i].getValue(rs, i + 1);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
		return values;
	}
}