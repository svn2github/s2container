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
package org.seasar.extension.jdbc.benchmark.s2jdbc;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * @author taedium
 * 
 */
@Entity
public class Employee {

    /** */
    @Id
    @SequenceGenerator(name = "EMPLOYEE_SEQ_GEN", sequenceName = "EMPLOYEE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPLOYEE_SEQ_GEN")
    public Integer employeeId;

    /** */
    public Integer employeeNo;

    /** */
    public String employeeName;

    /** */
    public Integer managerId;

    /** */
    @ManyToOne
    @JoinColumn(name = "MANAGER_ID", referencedColumnName = "EMPLOYEE_ID")
    public Employee manager;

    /** */
    @Temporal(TemporalType.DATE)
    public Date hiredate;

    /** */
    public BigDecimal salary;

    /** */
    public Integer departmentId;

    /** */
    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "DEPARTMENT_ID")
    public Department department;

    /** */
    public Integer addressId;

    /** */
    @OneToOne
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ADDRESS_ID")
    public Address address;

    /** */
    @Version
    public Integer version;
}
