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
package org.seasar.extension.jdbc.benchmark.s2jdbc;

import java.util.List;

import org.seasar.extension.jdbc.EntityMetaFactory;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.benchmark.BenchmarkTestCase;
import org.seasar.extension.jdbc.benchmark.SqlSelectDtoBenchmark;
import org.seasar.framework.container.SingletonS2Container;

/**
 * @author taedium
 * 
 */
public class S2JdbcSqlSelectDtoTest extends BenchmarkTestCase implements
        SqlSelectDtoBenchmark {

    private static final String SQL =
        "select employee_id employeeId, employee_no employeeNo, employee_name employeeName, manager_id managerId, hiredate hiredate, salary salary, department_id departmentId, address_id addressId, version version from Employee";

    private JdbcManager jdbcManager;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        jdbcManager = SingletonS2Container.getComponent(JdbcManager.class);
        initializeMeta();
    }

    /**
     * 
     * @throws Exception
     */
    protected void initializeMeta() throws Exception {
        EntityMetaFactory entityMetaFactory =
            SingletonS2Container.getComponent(EntityMetaFactory.class);
        entityMetaFactory.getEntityMeta(Employee.class);
    }

    /**
     * 
     * @throws Exception
     */
    public void test() throws Exception {
        List<Employee> employees = null;
        begin();
        employees =
            jdbcManager.selectBySql(Employee.class, SQL).getResultList();
        end();
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
        jdbcManager = null;
        super.tearDown();
    }

}
