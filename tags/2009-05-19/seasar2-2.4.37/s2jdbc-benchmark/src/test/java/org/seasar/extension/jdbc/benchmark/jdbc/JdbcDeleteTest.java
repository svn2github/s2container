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
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.seasar.extension.jdbc.benchmark.BenchmarkTestCase;
import org.seasar.extension.jdbc.benchmark.DeleteBenchmark;
import org.seasar.framework.container.SingletonS2Container;

/**
 * @author taedium
 * 
 */
public class JdbcDeleteTest extends BenchmarkTestCase implements
        DeleteBenchmark {

    private static final String SELECT =
        "select T.employee_id, T.employee_no, T.employee_name, T.manager_id, T.hiredate, T.salary, T.department_id, T.address_id, T.version FROM Employee T order by employee_Id";

    private static final String DELETE =
        "delete from Employee where employee_Id = ? and version = ?";

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
        List<Employee> employees = select();
        assertEquals(10000, employees.size());
        begin();
        Connection con = dataSource.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(DELETE);
            try {
                for (Employee employee : employees) {
                    ps.setInt(1, employee.employeeId);
                    ps.setInt(2, employee.version);
                    ps.executeUpdate();
                }
            } finally {
                ps.close();
            }
        } finally {
            con.close();
        }
        end();
    }

    private List<Employee> select() throws Exception {
        List<Employee> employees = new ArrayList<Employee>(10000);
        Connection con = dataSource.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(SELECT);
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
        return employees;
    }

    @Override
    protected void tearDown() throws Exception {
        dataSource = null;
        super.tearDown();
    }

}