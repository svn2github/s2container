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
import org.seasar.extension.jdbc.benchmark.BenchmarkTestCase;
import org.seasar.extension.jdbc.benchmark.InsertSequenceBenchmark;
import org.seasar.framework.container.SingletonS2Container;

/**
 * @author taedium
 * 
 */
public class S2DaoInsertSequenceTest extends BenchmarkTestCase implements
        InsertSequenceBenchmark {

    private DepartmentDao departmentDao;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        departmentDao = SingletonS2Container.getComponent(DepartmentDao.class);
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
        List<Department> departments = new ArrayList<Department>();
        for (int i = 0; i < 10000; i++) {
            Department department = new Department();
            department.departmentNo = 90;
            department.departmentName = "HOGE";
            department.location = "FOO";
            department.versionNo = 1;
            departments.add(department);
        }
        begin();
        for (Department department : departments) {
            departmentDao.insert(department);
        }
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
        BenchmarkTestCase.run(S2DaoInsertSequenceTest.class, args);
    }
}