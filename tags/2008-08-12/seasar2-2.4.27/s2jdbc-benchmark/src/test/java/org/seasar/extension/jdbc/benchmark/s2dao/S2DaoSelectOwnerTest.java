/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
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

import org.seasar.dao.DaoMetaDataFactory;
import org.seasar.extension.jdbc.benchmark.BenchmarkTestCase;
import org.seasar.extension.jdbc.benchmark.SelectOwnerBenchmark;
import org.seasar.framework.container.SingletonS2Container;

/**
 * @author taedium
 * 
 */
public class S2DaoSelectOwnerTest extends BenchmarkTestCase implements
        SelectOwnerBenchmark {

    private EmployeeDao employeeDao;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        employeeDao = SingletonS2Container.getComponent(EmployeeDao.class);
        initializeMeta();
    }

    /**
     * 
     * @throws Exception
     */
    protected void initializeMeta() throws Exception {
        DaoMetaDataFactory dmdf =
            SingletonS2Container.getComponent(DaoMetaDataFactory.class);
        dmdf.getDaoMetaData(EmployeeDao.class);
    }

    /**
     * 
     * @throws Exception
     */
    public void test() throws Exception {
        begin();
        List<Employee> employees = employeeDao.select();
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
        assertNotNull(employees.get(0).versionNo);
    }

    @Override
    protected void tearDown() throws Exception {
        employeeDao = null;
        super.tearDown();
    }

}