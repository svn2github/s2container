/*
 * Copyright 2004-2009 the Seasar Foundation and the Others.
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
package org.seasar.extension.jdbc.benchmark.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.seasar.extension.jdbc.benchmark.BatchInsertAssignBenchmark;
import org.seasar.extension.jdbc.benchmark.BenchmarkTestCase;
import org.seasar.framework.container.SingletonS2Container;

/**
 * @author taedium
 * 
 */
public class JdbcBatchInsertAssignTest extends BenchmarkTestCase implements
        BatchInsertAssignBenchmark {

    private static final String INSERT =
        "insert into Department (department_id, department_no, department_name, location, version) values (?, ?, ?, ?, ?)";

    private DataSource dataSource;

    private int id = 5;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        dataSource = SingletonS2Container.getComponent(DataSource.class);
    }

    /**
     * 
     * @throws Exception
     */
    public void test() throws Exception {
        List<Department> departments = new ArrayList<Department>();
        for (int i = 0; i < 10000; i++) {
            Department department = new Department();
            department.departmentNo = 90;
            department.departmentName = "HOGE";
            department.location = "FOO";
            department.version = 1;
            departments.add(department);
        }
        begin();
        Connection con = dataSource.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(INSERT);
            try {
                for (Department department : departments) {
                    ps.setInt(1, id++);
                    ps.setInt(2, department.departmentNo);
                    ps.setString(3, department.departmentName);
                    ps.setString(4, department.location);
                    ps.setInt(5, department.version);
                    ps.addBatch();
                }
                ps.executeBatch();
            } finally {
                ps.close();
            }
        } finally {
            con.close();
        }
        end();
    }

    @Override
    protected void tearDown() throws Exception {
        dataSource = null;
        super.tearDown();
    }

}