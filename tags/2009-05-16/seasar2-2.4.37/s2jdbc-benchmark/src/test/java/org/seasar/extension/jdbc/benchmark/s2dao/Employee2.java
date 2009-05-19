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
package org.seasar.extension.jdbc.benchmark.s2dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.seasar.dao.annotation.tiger.Bean;
import org.seasar.dao.annotation.tiger.Column;
import org.seasar.dao.annotation.tiger.Id;

/**
 * @author taedium
 * 
 */
@Bean(table = "EMPLOYEE")
public class Employee2 {

    private Set<Object> modifiedPropertySet = new java.util.HashSet<Object>();

    /** */
    @Id
    @Column("EMPLOYEE_ID")
    private Integer employeeId;

    /** */
    @Column("EMPLOYEE_NO")
    private Integer employeeNo;

    /** */
    @Column("EMPLOYEE_NAME")
    private String employeeName;

    /** */
    @Column("MANAGER_ID")
    private Integer managerId;

    /** */
    @Column("HIREDATE")
    private Date hiredate;

    /** */
    @Column("SALARY")
    private BigDecimal salary;

    /** */
    @Column("DEPARTMENT_ID")
    private Integer departmentId;

    /** */
    @Column("ADDRESS_ID")
    private Integer addressId;

    /** */
    @Column("VERSION")
    private Integer versionNo;

    /**
     * @return Returns the employeeId.
     */
    public Integer getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeId
     *            The employeeId to set.
     */
    public void setEmployeeId(Integer employeeId) {
        modifiedPropertySet.add("employeeId");
        this.employeeId = employeeId;
    }

    /**
     * @return Returns the employeeNo.
     */
    public Integer getEmployeeNo() {
        return employeeNo;
    }

    /**
     * @param employeeNo
     *            The employeeNo to set.
     */
    public void setEmployeeNo(Integer employeeNo) {
        modifiedPropertySet.add("employeeNo");
        this.employeeNo = employeeNo;
    }

    /**
     * @return Returns the employeeName.
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * @param employeeName
     *            The employeeName to set.
     */
    public void setEmployeeName(String employeeName) {
        modifiedPropertySet.add("employeeName");
        this.employeeName = employeeName;
    }

    /**
     * @return Returns the managerId.
     */
    public Integer getManagerId() {
        return managerId;
    }

    /**
     * @param managerId
     *            The managerId to set.
     */
    public void setManagerId(Integer managerId) {
        modifiedPropertySet.add("managerId");
        this.managerId = managerId;
    }

    /**
     * @return Returns the hiredate.
     */
    public Date getHiredate() {
        return hiredate;
    }

    /**
     * @param hiredate
     *            The hiredate to set.
     */
    public void setHiredate(Date hiredate) {
        modifiedPropertySet.add("hiredate");
        this.hiredate = hiredate;
    }

    /**
     * @return Returns the salary.
     */
    public BigDecimal getSalary() {
        return salary;
    }

    /**
     * @param salary
     *            The salary to set.
     */
    public void setSalary(BigDecimal salary) {
        modifiedPropertySet.add("salary");
        this.salary = salary;
    }

    /**
     * @return Returns the departmentId.
     */
    public Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * @param departmentId
     *            The departmentId to set.
     */
    public void setDepartmentId(Integer departmentId) {
        modifiedPropertySet.add("departmentId");
        this.departmentId = departmentId;
    }

    /**
     * @return Returns the addressId.
     */
    public Integer getAddressId() {
        return addressId;
    }

    /**
     * @param addressId
     *            The addressId to set.
     */
    public void setAddressId(Integer addressId) {
        modifiedPropertySet.add("addressId");
        this.addressId = addressId;
    }

    /**
     * @return Returns the versionNo.
     */
    public Integer getVersionNo() {
        return versionNo;
    }

    /**
     * @param versionNo
     *            The versionNo to set.
     */
    public void setVersionNo(Integer versionNo) {
        modifiedPropertySet.add("versionNo");
        this.versionNo = versionNo;
    }

    /**
     * 
     * @return
     */
    public Set<Object> getModifiedPropertyNames() {
        return this.modifiedPropertySet;
    }
}
