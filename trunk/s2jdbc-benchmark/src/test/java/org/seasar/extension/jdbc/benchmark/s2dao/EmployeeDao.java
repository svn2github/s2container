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

import org.seasar.dao.annotation.tiger.Query;
import org.seasar.dao.annotation.tiger.S2Dao;
import org.seasar.dao.annotation.tiger.Sql;
import org.seasar.dao.pager.PagerCondition;

/**
 * @author taedium
 * 
 */
@S2Dao(bean = Employee.class)
public interface EmployeeDao {

    /**
     * 
     * @return
     */
    @Sql("select * from dual")
    Object initialize();

    /**
     * 
     * @return
     */
    List<Employee> select();

    /**
     * 
     * @param condition
     * @return
     */
    @Query("order by employee_Id")
    List<Employee> selectPaging(PagerCondition condition);

    /**
     * 
     * @param employee
     * @return
     */
    int update(Employee employee);

    /**
     * 
     * @param employee
     * @return
     */
    int delete(Employee employee);

    /**
     * 
     * @param employees
     * @return
     */
    int[] updateBatch(List<Employee> employees);

    /**
     * 
     * @param employees
     * @return
     */
    int[] deleteBatch(List<Employee> employees);
}
