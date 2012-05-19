/*
 * Copyright 2004-2012 the Seasar Foundation and the Others.
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
package org.seasar.extension.jdbc.it.entity;

import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Version;

/**
 * @author taedium
 * 
 */
@MappedSuperclass
public abstract class AbstractDepartment {

    /** */
    public int departmentNo;

    /** */
    public String departmentName;

    /** */
    public String location;

    /** */
    @Version
    public int version;

    /** */
    @OneToMany(mappedBy = "department")
    public List<ConcreteEmployee> employees;
}
