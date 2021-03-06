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

import org.seasar.dao.annotation.tiger.Bean;
import org.seasar.dao.annotation.tiger.Column;
import org.seasar.dao.annotation.tiger.Id;
import org.seasar.dao.annotation.tiger.IdType;

/**
 * @author taedium
 * 
 */
@Bean
public class Department {

    /** */
    @Id(value = IdType.SEQUENCE, sequenceName = "DEPARTMENT_SEQ")
    @Column("DEPARTMENT_ID")
    public Integer departmentId;

    /** */
    @Column("DEPARTMENT_NO")
    public Integer departmentNo;

    /** */
    @Column("DEPARTMENT_NAME")
    public String departmentName;

    /** */
    @Column("LOCATION")
    public String location;

    /** */
    @Column("VERSION")
    public Integer versionNo;

}
