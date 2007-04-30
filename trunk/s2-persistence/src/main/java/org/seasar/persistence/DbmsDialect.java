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
package org.seasar.persistence;

/**
 * DBMSごとの方言を扱うクラスです。
 * 
 * @author higa
 * 
 */
public class DbmsDialect {

	protected String name;

	protected boolean supportsBooleanColumn = true;

	protected boolean hasFullWidthTildeBug = false;

	/**
	 * 名前を返します。
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Boolean型の列をサポートするかどうか返します。
	 * 
	 * @return supportsBooleanColumn
	 */
	public boolean isSupportsBooleanColumn() {
		return supportsBooleanColumn;
	}

	/**
	 * Boolean型の列をサポートするかどうか設定します。
	 * 
	 * @param supportsBooleanColumn
	 */
	public void setSupportsBooleanColumn(boolean supportsBooleanColumn) {
		this.supportsBooleanColumn = supportsBooleanColumn;
	}

	/**
	 * Full Width Tilde(0xff5e)の変換にバグがあるかどうかを返します。 バグがある場合、Wave
	 * Dash(0x301c)に変換されます。
	 * 
	 * @return hasFullWidthTildeBug
	 */
	public boolean isHasFullWidthTildeBug() {
		return hasFullWidthTildeBug;
	}

	/**
	 * Full Width Tilde(0xff5e)の変換にバグがあるかどうかを設定します。。
	 * 
	 * @param hasFullWidthTildeBug
	 */
	public void setHasFullWidthTildeBug(boolean hasFullWidthTildeBug) {
		this.hasFullWidthTildeBug = hasFullWidthTildeBug;
	}
}