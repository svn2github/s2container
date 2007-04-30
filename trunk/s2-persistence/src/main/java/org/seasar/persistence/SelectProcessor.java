/**
 * 
 */
package org.seasar.persistence;

/**
 * select文を実行するクラスです。
 * 
 * @author higa
 * 
 */
public interface SelectProcessor {

	/**
	 * select文を実行します。
	 * 
	 * @return
	 */
	Object select();
}