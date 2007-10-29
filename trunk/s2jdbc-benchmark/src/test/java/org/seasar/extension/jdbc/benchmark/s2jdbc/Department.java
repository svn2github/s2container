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
package org.seasar.extension.jdbc.benchmark.s2jdbc;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

/**
 * @author taedium
 * 
 */
@Entity
public class Department {

    /** */
    @Id
    @SequenceGenerator(name = "DEPARTMENT_SEQ_GEN")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEPARTMENT_SEQ_GEN")
    public Integer departmentId;

    /** */
    public Integer departmentNo;

    /** */
    public String departmentName;

    /** */
    public String location;

    /** */
    @Version
    public Integer version;

    /** */
    @OneToMany(mappedBy = "department")
    public List<Employee> employees;
}
