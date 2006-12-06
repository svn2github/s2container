package org.seasar.employee.kuina.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import java.math.BigDecimal;
import java.util.Date;


@Entity
public class Emp {

	@Id
	private Integer id;

	@Column(name="EMP_NO")
	private Integer empNo;

	@Column(name="EMP_NAME")
	private String empName;

	@Column(name="MGR_ID")
	private Integer mgrId;

	private Date hiredate;

	private BigDecimal sal;

	@Column(name="DEPT_ID")
	private Integer deptId;

	@Column(name="VERSION_NO")
	private BigDecimal versionNo;

	public Emp() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getEmpNo() {
		return this.empNo;
	}

	public void setEmpNo(Integer empno) {
		this.empNo = empno;
	}
	public String getEmpName() {
		return this.empName;
	}

	public void setEmpName(String empname) {
		this.empName = empname;
	}
	public Integer getMgrId() {
		return this.mgrId;
	}

	public void setMgrId(Integer mgrid) {
		this.mgrId = mgrid;
	}
	public Date getHiredate() {
		return this.hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	public BigDecimal getSal() {
		return this.sal;
	}

	public void setSal(BigDecimal sal) {
		this.sal = sal;
	}
	public Integer getDeptId() {
		return this.deptId;
	}

	public void setDeptId(Integer deptid) {
		this.deptId = deptid;
	}
	public BigDecimal getVersionNo() {
		return this.versionNo;
	}

	public void setVersionNo(BigDecimal versionno) {
		this.versionNo = versionno;
	}
}