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
package org.seasar.extension.jdbc.benchmark.s2jdbc;

import java.util.List;

import org.seasar.extension.jdbc.EntityMetaFactory;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.benchmark.BenchmarkTestCase;
import org.seasar.extension.jdbc.benchmark.UpdateBenchmark;
import org.seasar.framework.container.SingletonS2Container;

/**
 * @author taedium
 * 
 */
public class S2JdbcUpdateTest extends BenchmarkTestCase implements
        UpdateBenchmark {

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
        List<Employee> employees =
            jdbcManager
                .from(Employee.class)
                .orderBy("employeeId")
                .getResultList();
        assertEquals(10000, employees.size());
        for (Employee employee : employees) {
            employee.employeeName = "HOGE";
        }
        begin();
        for (Employee employee : employees) {
            jdbcManager.update(employee).execute();
        }
        end();
    }

    @Override
    protected void tearDown() throws Exception {
        jdbcManager = null;
        super.tearDown();
    }

}
