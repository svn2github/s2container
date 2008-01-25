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

import java.sql.PreparedStatement;

import org.seasar.framework.util.LruHashMap;
import org.seasar.framework.util.StatementUtil;

/**
 * {@link PreparedStatement}用の{@link LruHashMap}です。
 * 
 * @author higa
 * 
 */
public class PreparedStatementLruHashMap extends LruHashMap {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * <code>PreparedStatementLruHashMap</code>を作成します。
	 * 
	 * @param limitSize
	 */
	public PreparedStatementLruHashMap(int limitSize) {
		super(limitSize);
	}

	/**
	 * <code>PreparedStatementLruHashMap</code>を作成します。
	 * 
	 * @param limitSize
	 * @param initialCapacity
	 * @param loadFactor
	 */
	public PreparedStatementLruHashMap(int limitSize, int initialCapacity,
			float loadFactor) {
		super(limitSize, initialCapacity, loadFactor);
	}

	@Override
	protected boolean removeEldestEntry(java.util.Map.Entry entry) {
		boolean ret = super.removeEldestEntry(entry);
		if (ret) {
			PreparedStatement ps = (PreparedStatement) entry.getValue();
			StatementUtil.close(ps);
		}
		return ret;
	}
}