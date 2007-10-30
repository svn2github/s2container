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
package org.seasar.extension.jdbc.benchmark.s2dao;

import java.util.List;

import org.seasar.dao.pager.DefaultPagerCondition;
import org.seasar.dao.pager.PagerCondition;
import org.seasar.extension.jdbc.benchmark.BenchmarkTestCase;
import org.seasar.extension.jdbc.benchmark.SelectEmployeePaging;
import org.seasar.framework.container.SingletonS2Container;

/**
 * @author taedium
 * 
 */
public class S2DaoSelectEmployeePagingTest extends BenchmarkTestCase implements
        SelectEmployeePaging {

    private EmployeeDao employeeDao;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        employeeDao = SingletonS2Container.getComponent(EmployeeDao.class);
        employeeDao.initialize();
    }

    /**
     * 
     * @throws Exception
     */
    public void test() throws Exception {
        begin();
        PagerCondition condition = new DefaultPagerCondition();
        condition.setOffset(9900);
        condition.setLimit(100);
        List<Employee> employees = employeeDao.selectPaging(condition);
        end();
        assertEquals(100, employees.size());
        assertEquals(new Integer(9901), employees.get(0).employeeId);
        assertNotNull(employees.get(0).employeeNo);
        assertNotNull(employees.get(0).employeeName);
        assertNotNull(employees.get(0).managerId);
        assertNotNull(employees.get(0).hiredate);
        assertNotNull(employees.get(0).salary);
        assertNotNull(employees.get(0).departmentId);
        assertNotNull(employees.get(0).addressId);
        assertNotNull(employees.get(0).versionNo);
    }

    @Override
    protected void tearDown() throws Exception {
        employeeDao = null;
        super.tearDown();
    }
}