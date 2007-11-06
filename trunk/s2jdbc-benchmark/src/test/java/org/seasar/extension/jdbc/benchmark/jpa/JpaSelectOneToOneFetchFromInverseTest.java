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
package org.seasar.extension.jdbc.benchmark.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import org.seasar.extension.jdbc.benchmark.BenchmarkTestCase;
import org.seasar.extension.jdbc.benchmark.SelectOneToOneFetchFromInverseBenchmark;
import org.seasar.framework.container.SingletonS2Container;

/**
 * @author taedium
 * 
 */
public class JpaSelectOneToOneFetchFromInverseTest extends BenchmarkTestCase
        implements SelectOneToOneFetchFromInverseBenchmark {

    private EntityManager entityManager;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        entityManager = SingletonS2Container.getComponent(EntityManager.class);
    }

    /**
     * 
     * @throws Exception
     */
    public void test() throws Exception {
        begin();
        @SuppressWarnings("unchecked")
        List<Address> addresses =
            entityManager.createNamedQuery(
                "JpaSelectOneToOneFetchFromInverseTest").getResultList();
        end();
        assertEquals(10000, addresses.size());
        assertNotNull(addresses.get(0).getAddressId());
        assertNotNull(addresses.get(0).getStreet());
        assertNotNull(addresses.get(0).getVersion());
        assertNotNull(addresses.get(0).getEmployee().getEmployeeId());
        assertNotNull(addresses.get(0).getEmployee().getEmployeeNo());
        assertNotNull(addresses.get(0).getEmployee().getEmployeeName());
        assertNotNull(addresses.get(0).getEmployee().getHiredate());
        assertNotNull(addresses.get(0).getEmployee().getSalary());
        assertNotNull(addresses.get(0).getEmployee().getVersion());
        assertNotNull(addresses.get(0).getEmployee().getAddress());
        assertNotNull(addresses.get(0).getEmployee().getDepartment());
    }

    @Override
    protected void tearDown() throws Exception {
        entityManager = null;
        super.tearDown();
    }

    /**
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        BenchmarkTestCase
            .run(JpaSelectOneToOneFetchFromInverseTest.class, args);
    }
}
