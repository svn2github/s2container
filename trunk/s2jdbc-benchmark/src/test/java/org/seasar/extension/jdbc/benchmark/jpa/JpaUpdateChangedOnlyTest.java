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
package org.seasar.extension.jdbc.benchmark.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import org.seasar.extension.jdbc.benchmark.BenchmarkTestCase;
import org.seasar.extension.jdbc.benchmark.UpdateChangedOnlyBenchmark;
import org.seasar.framework.container.SingletonS2Container;

/**
 * @author taedium
 * 
 */
public class JpaUpdateChangedOnlyTest extends BenchmarkTestCase implements
        UpdateChangedOnlyBenchmark {

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
        userTransaction.begin();
        @SuppressWarnings("unchecked")
        List<Employee3> employees =
            entityManager
                .createQuery("select e from Employee3 e order by employeeId")
                .getResultList();
        assertEquals(10000, employees.size());
        for (int i = 0; i < employees.size(); i++) {
            employees.get(i).setEmployeeName("HOGE");
        }
        startTime = System.nanoTime();
        entityManager.flush();
        endTime = System.nanoTime();
        userTransaction.rollback();
    }

    @Override
    protected void tearDown() throws Exception {
        entityManager = null;
        super.tearDown();
    }

}
