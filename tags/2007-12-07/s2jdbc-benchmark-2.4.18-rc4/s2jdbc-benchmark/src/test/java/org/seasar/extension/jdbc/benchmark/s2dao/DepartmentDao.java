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

import org.seasar.dao.annotation.tiger.S2Dao;
import org.seasar.dao.annotation.tiger.Sql;
import org.seasar.dao.annotation.tiger.Sqls;

/**
 * @author taedium
 * 
 */
@S2Dao(bean = Department.class)
public interface DepartmentDao {

    /**
     * 
     * @param department
     * @return
     */
    int insert(Department department);

    /**
     * 
     * @param departments
     * @return
     */
    int[] insertBatch(List<Department> departments);

    /**
     * 
     * @return
     */
    @Sqls( { @Sql("select DEPARTMENT_SEQ.nextval from dual"),
        @Sql(value = "values nextval for DEPARTMENT_SEQ", dbms = "db2") })
    int getSequenceNextValue();
}
