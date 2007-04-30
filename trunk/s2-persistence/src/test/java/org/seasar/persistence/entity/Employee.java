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
package org.seasar.persistence.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * @author higa
 * 
 */
@Entity
public class Employee {

	/**
	 * 
	 */
	@Column(name = "EMP_ID")
	@Id
	public Long id;

	/**
	 * 
	 */
	public String employeeName;

	/**
	 * 
	 */
	public BigDecimal salary;

	/**
	 * 
	 */
	@Version
	public Integer version;

	/**
	 * 
	 */
	public Employee manager;

	/**
	 * 
	 */
	public List<Employee> assistants;

	/**
	 * 
	 */
	public Department department;
}