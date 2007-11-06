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

import java.util.ArrayList;
import java.util.List;

import org.seasar.dao.DaoMetaDataFactory;
import org.seasar.extension.jdbc.benchmark.BatchInsertSequenceBenchmark;
import org.seasar.extension.jdbc.benchmark.BenchmarkTestCase;
import org.seasar.framework.container.SingletonS2Container;

/**
 * @author taedium
 * 
 */
public class S2DaoBatchInsertSequenceTest extends BenchmarkTestCase implements
        BatchInsertSequenceBenchmark {

    private Department2Dao departmentDao;

    private int id = 5;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        departmentDao = SingletonS2Container.getComponent(Department2Dao.class);
        initializeMeta();
    }

    /**
     * 
     * @throws Exception
     */
    protected void initializeMeta() throws Exception {
        DaoMetaDataFactory dmdf =
            SingletonS2Container.getComponent(DaoMetaDataFactory.class);
        dmdf.getDaoMetaData(DepartmentDao.class);
    }

    /**
     * 
     * @throws Exception
     */
    public void test() throws Exception {
        List<Department2> departments = new ArrayList<Department2>();
        for (int i = 0; i < 10000; i++) {
            Department2 department = new Department2();
            department.departmentId = id++;
            department.departmentNo = 90;
            department.departmentName = "HOGE";
            department.location = "FOO";
            department.versionNo = 1;
            departments.add(department);
        }
        begin();
        departmentDao.insertBatch(departments);
        end();
    }

    @Override
    protected void tearDown() throws Exception {
        departmentDao = null;
        super.tearDown();
    }

    /**
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        BenchmarkTestCase.run(S2DaoBatchInsertSequenceTest.class, args);
    }
}