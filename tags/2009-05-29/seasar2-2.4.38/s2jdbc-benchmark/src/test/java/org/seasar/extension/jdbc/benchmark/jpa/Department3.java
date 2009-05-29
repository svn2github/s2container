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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * @author taedium
 * 
 */
@Entity
@Table(name = "DEPARTMENT")
public class Department3 {

    /** */
    @Id
    @Column(name = "DEPARTMENT_ID")
    private Integer departmentId;

    /** */
    @Column(name = "DEPARTMENT_NO")
    private Integer departmentNo;

    /** */
    @Column(name = "DEPARTMENT_NAME")
    private String departmentName;

    /** */
    @Column(name = "LOCATION")
    private String location;

    /** */
    @Version
    @Column(name = "VERSION")
    private Integer version;

    /** */
    @OneToMany(mappedBy = "department")
    private List<Employee3> employees;

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
        this.departmentId = departmentId;
    }

    /**
     * @return Returns the departmentNo.
     */
    public Integer getDepartmentNo() {
        return departmentNo;
    }

    /**
     * @param departmentNo
     *            The departmentNo to set.
     */
    public void setDepartmentNo(Integer departmentNo) {
        this.departmentNo = departmentNo;
    }

    /**
     * @return Returns the departmentName.
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * @param departmentName
     *            The departmentName to set.
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * @return Returns the location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location
     *            The location to set.
     */
    public void setLocation(String location) {
        this.location = location;
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

    /**
     * @return Returns the employees.
     */
    public List<Employee3> getEmployees() {
        return employees;
    }

    /**
     * @param employees
     *            The employees to set.
     */
    public void setEmployees(List<Employee3> employees) {
        this.employees = employees;
    }

}
