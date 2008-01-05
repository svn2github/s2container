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

import org.seasar.dao.annotation.tiger.Query;
import org.seasar.dao.annotation.tiger.S2Dao;
import org.seasar.dao.annotation.tiger.Sql;
import org.seasar.dao.annotation.tiger.SqlFile;
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
    List<Employee> select();

    /**
     * 
     * @param employeeId
     * @return
     */
    @Sql("select employee_id employeeId, employee_no employeeNo, employee_name employeeName, manager_id managerId, hiredate hiredate, salary salary, department_id departmentId, address_id addressId, version version from Employee")
    List<Employee> selectBySql(int employeeId);

    /**
     * 
     * @param employeeId
     * @return
     */
    @SqlFile
    List<Employee> selectBySqlFile();

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
