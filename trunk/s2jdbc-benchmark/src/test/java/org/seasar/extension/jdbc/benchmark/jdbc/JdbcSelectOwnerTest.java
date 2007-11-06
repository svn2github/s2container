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
package org.seasar.extension.jdbc.benchmark.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.seasar.extension.jdbc.benchmark.BenchmarkTestCase;
import org.seasar.extension.jdbc.benchmark.SelectOwnerBenchmark;
import org.seasar.framework.container.SingletonS2Container;

/**
 * @author taedium
 * 
 */
public class JdbcSelectOwnerTest extends BenchmarkTestCase implements
        SelectOwnerBenchmark {

    private static final String SQL =
        "select T.employee_id, T.employee_no, T.employee_name, T.manager_id, T.hiredate, T.salary, T.department_id, T.address_id, T.version FROM Employee T";

    private DataSource dataSource;

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
        begin();
        List<Employee> employees = new ArrayList<Employee>(10000);
        Connection con = dataSource.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(SQL);
            try {
                ps.setFetchSize(100);
                ResultSet rs = ps.executeQuery();
                try {
                    while (rs.next()) {
                        Employee employee = new Employee();
                        employee.employeeId = rs.getInt(1);
                        employee.employeeNo = rs.getInt(2);
                        employee.employeeName = rs.getString(3);
                        employee.managerId = rs.getInt(4);
                        employee.hiredate = rs.getTimestamp(5);
                        employee.salary = rs.getBigDecimal(6);
                        employee.departmentId = rs.getInt(7);
                        employee.addressId = rs.getInt(8);
                        employee.version = rs.getInt(9);
                        employees.add(employee);
                    }
                } finally {
                    rs.close();
                }
            } finally {
                ps.close();
            }
        } finally {
            con.close();
        }
        end();
        assertEquals(10000, employees.size());
        assertNotNull(employees.get(0).employeeId);
        assertNotNull(employees.get(0).employeeNo);
        assertNotNull(employees.get(0).employeeName);
        assertNotNull(employees.get(0).managerId);
        assertNotNull(employees.get(0).hiredate);
        assertNotNull(employees.get(0).salary);
        assertNotNull(employees.get(0).departmentId);
        assertNotNull(employees.get(0).addressId);
        assertNotNull(employees.get(0).version);
    }

    @Override
    protected void tearDown() throws Exception {
        dataSource = null;
        super.tearDown();
    }

    /**
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        BenchmarkTestCase.run(JdbcSelectOwnerTest.class, args);
    }
}