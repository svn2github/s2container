/*
 * Copyright 2004-2005 the Seasar Foundation and the Others.
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
package org.seasar.extension.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OracleResultSetFactory extends BasicResultSetFactory {

	/**
	 * @see org.seasar.extension.jdbc.ResultSetFactory#createResultSet(java.sql.PreparedStatement)
	 */
	public ResultSet createResultSet(PreparedStatement ps) {
		return new OracleResultSet(super.createResultSet(ps));
	}

}
