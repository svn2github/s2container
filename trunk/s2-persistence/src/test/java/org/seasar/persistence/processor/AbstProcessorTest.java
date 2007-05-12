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

import junit.framework.TestCase;

import org.seasar.extension.sql.SqlContext;
import org.seasar.persistence.PreparedStatementPool;

/**
 * @author higa
 * 
 */
public class AbstProcessorTest extends TestCase {

	/**
	 * Test method for
	 * {@link org.seasar.persistence.processor.AbstractProcessor#log(String)}.
	 */
	public void testLog() {
		AbstractProcessor processor = new MyProcessor(null, null, getClass());
		processor.log("select * from emp");
	}

	private static class MyProcessor extends AbstractProcessor {

		/**
		 * @param psPool
		 * @param sqlContext
		 * @param daoClass
		 */
		public MyProcessor(PreparedStatementPool psPool, SqlContext sqlContext,
				Class<?> daoClass) {
			super(psPool, sqlContext, daoClass);
		}

	}
}
