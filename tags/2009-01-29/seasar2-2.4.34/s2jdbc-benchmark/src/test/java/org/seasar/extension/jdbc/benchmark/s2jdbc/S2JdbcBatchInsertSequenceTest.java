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

import java.util.ArrayList;
import java.util.List;

import org.seasar.extension.jdbc.EntityMetaFactory;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.benchmark.BatchInsertSequenceBenchmark;
import org.seasar.extension.jdbc.benchmark.BenchmarkTestCase;
import org.seasar.framework.container.SingletonS2Container;

/**
 * @author taedium
 * 
 */
public class S2JdbcBatchInsertSequenceTest extends BenchmarkTestCase implements
        BatchInsertSequenceBenchmark {

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
        jdbcManager.insertBatch(departments).execute();
        end();
    }

    @Override
    protected void tearDown() throws Exception {
        jdbcManager = null;
        super.tearDown();
    }

}
