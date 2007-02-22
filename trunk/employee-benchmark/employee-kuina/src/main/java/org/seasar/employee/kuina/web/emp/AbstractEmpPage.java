package org.seasar.employee.kuina.web.emp;

import java.math.BigDecimal;
import java.util.Date;

import org.seasar.employee.kuina.web.AbstractCrudPage;

public abstract class AbstractEmpPage extends AbstractCrudPage {

	private EmpService empService;
	
	private EmpDxo empDxo;
	
	private long id;

	private Integer empNo;

	private String empName;

	private Integer mgrId;

	private Date hiredate;

	private BigDecimal sal;

	private Integer deptId;

	private Integer versionNo;

	public AbstractEmpPage() {
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

	public EmpService getEmpService() {
		return this.empService;
	}

	public void setEmpService(EmpService empService) {
		this.empService = empService;
	}

	public EmpDxo getEmpDxo() {
		return this.empDxo;
	}

	public void setEmpDxo(EmpDxo empDxo) {
		this.empDxo = empDxo;
	}
}