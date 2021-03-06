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
package org.seasar.extension.dataset.impl;

import javax.sql.DataSource;

import org.seasar.extension.dataset.DataTable;
import org.seasar.extension.jdbc.SelectHandler;
import org.seasar.extension.jdbc.impl.BasicSelectHandler;
import org.seasar.extension.unit.S2TestCase;

public class DataTableResultSetHandlerTest extends S2TestCase {

    private DataSource ds_;

    public DataTableResultSetHandlerTest(String arg0) {
        super(arg0);
    }

    public void testHandle() throws Exception {
        String sql = "select * from emp";
        SelectHandler handler = new BasicSelectHandler(ds_, sql,
                new DataTableResultSetHandler("emp"));
        DataTable ret = (DataTable) handler.execute(null);
        System.out.println(ret);
        assertNotNull("1", ret);
        assertEquals("2", true, ret.getColumn("EMPNO").isPrimaryKey());
    }

    public void testHandle2() throws Exception {
        String sql = "select ename, dname from emp, dept where emp.deptno = dept.deptno";
        SelectHandler handler = new BasicSelectHandler(ds_, sql,
                new DataTableResultSetHandler("emp"));
        DataTable ret = (DataTable) handler.execute(null);
        System.out.println(ret);
        assertNotNull("1", ret);
        assertEquals("2", true, ret.getColumn("ENAME").isWritable());
        assertEquals("3", false, ret.getColumn("DNAME").isWritable());
    }

    public void setUp() {
        include("j2ee.dicon");
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(DataTableResultSetHandlerTest.class);
    }

}
