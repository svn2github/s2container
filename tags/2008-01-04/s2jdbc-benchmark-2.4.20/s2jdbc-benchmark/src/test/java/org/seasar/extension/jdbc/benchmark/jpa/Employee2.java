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

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * @author taedium
 * 
 */
@Entity
@Table(name = "EMPLOYEE")
public class Employee2 {

    /** */
    @Id
    @SequenceGenerator(name = "EMPLOYEE_SEQ_GEN", sequenceName = "EMPLOYEE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPLOYEE_SEQ_GEN")
    @Column(name = "EMPLOYEE_ID")
    private Integer employeeId;

    /** */
    @Column(name = "EMPLOYEE_NO")
    private Integer employeeNo;

    /** */
    @Column(name = "EMPLOYEE_NAME")
    private String employeeName;

    /** */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MANAGER_ID", referencedColumnName = "EMPLOYEE_ID")
    private Employee2 manager;

    /** */
    @Column(name = "HIREDATE")
    private Date hiredate;

    /** */
    @Column(name = "SALARY")
    private BigDecimal salary;

    /** */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", referencedColumnName = "DEPARTMENT_ID")
    private Department2 department;

    /** */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ADDRESS_ID")
    private Address2 address;

    /** */
    @Version
    @Column(name = "VERSION")
    private Integer version;

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
        this.employeeName = employeeName;
    }

    /**
     * @return Returns the manager.
     */
    public Employee2 getManager() {
        return manager;
    }

    /**
     * @param manager
     *            The manager to set.
     */
    public void setManager(Employee2 manager) {
        this.manager = manager;
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
        this.salary = salary;
    }

    /**
     * @return Returns the department.
     */
    public Department2 getDepartment() {
        return department;
    }

    /**
     * @param department
     *            The department to set.
     */
    public void setDepartment(Department2 department) {
        this.department = department;
    }

    /**
     * @return Returns the address.
     */
    public Address2 getAddress() {
        return address;
    }

    /**
     * @param address
     *            The address to set.
     */
    public void setAddress(Address2 address) {
        this.address = address;
    }

    /**
     * @return Returns the version.
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version
     *            The version to set.
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

}
