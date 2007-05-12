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
import java.sql.Statement;

import org.seasar.extension.jdbc.ValueType;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.persistence.entity.JobType;
import org.seasar.persistence.types.ExtendedValueTypes;

/**
 * @author higa
 * 
 */
public class ResultSetProcessorImplTest extends S2TestCase {

	protected void setUp() throws Exception {
		include("jdbc.dicon");
	}

	/**
	 * Test method for
	 * {@link org.seasar.persistence.processor.ResultSetProcessorImpl#getValues(java.sql.ResultSet)}.
	 * 
	 * @throws Exception
	 */
	public void testGetValues() throws Exception {
		ValueType[] types = new ValueType[] {
				ExtendedValueTypes.getValueType(Long.class),
				ExtendedValueTypes.getValueType(String.class),
				ExtendedValueTypes.getValueType(JobType.class) };
		ResultSetProcessorImpl processor = new ResultSetProcessorImpl(types);
		Statement stmt = getConnection().createStatement();
		Object[] values = null;
		try {
			ResultSet rs = stmt
					.executeQuery("select id, employee_name, job from employee where id=8");
			try {
				rs.next();
				values = processor.getValues(rs);
			} finally {
				rs.close();
			}
		} finally {
			stmt.close();
		}
		assertEquals(new Long(8), values[0]);
		assertEquals("SCOTT", values[1]);
		assertEquals(JobType.ANALYST, values[2]);
	}

}
