/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
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
package org.seasar.framework.ejb.unit;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Employee3 {
	@Id
	private Long id;
	
	private long empno;
	
	private String name;
	
	@ManyToOne
	private Employee3 boss;

	public Employee3() {
	}

	public Employee3(Long id, long empno, String name, Employee3 boss) {
		this.id = id;
		this.empno = empno;
		this.name = name;
		this.boss = boss;
	}

	public Employee3 getBoss() {
		return boss;
	}

	public void setBoss(Employee3 boss) {
		this.boss = boss;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getEmpno() {
		return empno;
	}

	public void setEmpno(long empno) {
		this.empno = empno;
	}
	
	@Override
    public boolean equals(Object other) {
        if ( !(other instanceof Employee) ) return false;
        Employee castOther = (Employee) other;
        return this.getEmpno() == castOther.getEmpno();
    }

	@Override
    public int hashCode() {
        return (int) this.getEmpno();
    }
}
