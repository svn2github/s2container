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
import org.seasar.extension.jdbc.benchmark.SelectOneToOneFetchFromOwnerBenchmark;
import org.seasar.framework.container.SingletonS2Container;

/**
 * @author taedium
 * 
 */
public class S2JdbcSelectOneToOneFetchFromOwnerTest extends BenchmarkTestCase
        implements SelectOneToOneFetchFromOwnerBenchmark {

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
        entityMetaFactory.getEntityMeta(Address.class);
    }

    /**
     * 
     * @throws Exception
     */
    public void test() throws Exception {
        begin();
        List<Address> addresses =
            jdbcManager
                .from(Address.class)
                .leftOuterJoin("employee")
                .getResultList();
        end();
        assertEquals(10000, addresses.size());
        assertNotNull(addresses.get(0).addressId);
        assertNotNull(addresses.get(0).street);
        assertNotNull(addresses.get(0).version);
        assertNotNull(addresses.get(0).employee.employeeId);
        assertNotNull(addresses.get(0).employee.employeeNo);
        assertNotNull(addresses.get(0).employee.employeeName);
        assertNotNull(addresses.get(0).employee.managerId);
        assertNotNull(addresses.get(0).employee.hiredate);
        assertNotNull(addresses.get(0).employee.salary);
        assertNotNull(addresses.get(0).employee.departmentId);
        assertNotNull(addresses.get(0).employee.addressId);
        assertNotNull(addresses.get(0).employee.version);

    }

    @Override
    protected void tearDown() throws Exception {
        jdbcManager = null;
        super.tearDown();
    }

}
