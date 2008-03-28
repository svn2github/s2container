package org.seasar.employee.strtus.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;


@Entity
public class Emp {

	@Id
	@GeneratedValue
	private long id;

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

	@Version
	@Column(name="VERSION_NO")
	private Integer versionNo;

	public Emp() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
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
	public Integer getVersionNo() {
		return this.versionNo;
	}

	public void setVersionNo(Integer versionno) {
		this.versionNo = versionno;
	}
}