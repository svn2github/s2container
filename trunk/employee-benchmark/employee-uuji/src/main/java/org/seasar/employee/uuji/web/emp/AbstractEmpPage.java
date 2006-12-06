package org.seasar.employee.uuji.web.emp;

import java.math.BigDecimal;
import java.util.Date;

import org.seasar.employee.uuji.dao.EmpDao;
import org.seasar.employee.uuji.web.AbstractCrudPage;

public abstract class AbstractEmpPage extends AbstractCrudPage {

	private EmpDao empDao;
	
	private EmpDxo empDxo;
	
	private Integer id;

	private Integer empNo;

	private String empName;

	private Integer mgrId;

	private Date hiredate;

	private BigDecimal sal;

	private Integer deptId;

	private BigDecimal versionNo;

	public AbstractEmpPage() {
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

	public EmpDao getEmpDao() {
		return this.empDao;
	}

	public void setEmpDao(EmpDao empDao) {
		this.empDao = empDao;
	}

	public EmpDxo getEmpDxo() {
		return this.empDxo;
	}

	public void setEmpDxo(EmpDxo empDxo) {
		this.empDxo = empDxo;
	}
}