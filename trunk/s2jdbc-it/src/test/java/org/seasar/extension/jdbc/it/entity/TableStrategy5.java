/*
 * Copyright 2004-2013 the Seasar Foundation and the Others.
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

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * @author taedium
 * 
 */
@Entity
@Table(catalog = "scott", schema = "dbo")
public class TableStrategy5 {

    /** */
    @Id
    @TableGenerator(name = "aaa", catalog = "scott", schema = "dbo", table = "my_ID_GENERATOR", pkColumnName = "my_PK", valueColumnName = "my_VALUE", pkColumnValue = "TableStrategy5")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "aaa")
    public Integer id;

    /** */
    public String value;
}
