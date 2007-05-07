/**
 * 
 */
package org.seasar.persistence;

import java.sql.ResultSet;

import org.seasar.framework.exception.SQLRuntimeException;

/**
 * {@link ResultSet}の1行を<code>Object</code>の配列に変換するインターフェースです。
 * 
 * @author higa
 * 
 */
public interface ResultSetProcessor {

	/**
	 * {@link ResultSet}の1行を<code>Object</code>の配列に変換します。
	 * 
	 * @param rs
	 * @return
	 * @throws SQLRuntimeException
	 *             <code>SQLException</code>が発生したとき<code>SQLRuntimeException</code>にラップされます。
	 */
	Object[] getValues(ResultSet rs);
}
