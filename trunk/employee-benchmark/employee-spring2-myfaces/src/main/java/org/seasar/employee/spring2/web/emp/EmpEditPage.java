package org.seasar.employee.spring2.web.emp;

import java.math.BigDecimal;

public class EmpEditPage extends AbstractEmpPage {

	private long id;

	private Integer empNo;

	private String empName;

	private Integer mgrId;

	private String hiredate;

	private BigDecimal sal;

	private Integer deptId;

	private Integer versionNo;

	private boolean initialized;

	public EmpEditPage() {
	}

	public Integer getDeptId() {
		initializeIfNecessary();
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getEmpName() {
		initializeIfNecessary();
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Integer getEmpNo() {
		initializeIfNecessary();
		return empNo;
	}

	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}

	public String getHiredate() {
		initializeIfNecessary();
		return hiredate;
	}

	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}

	public long getId() {
		initializeIfNecessary();
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getMgrId() {
		initializeIfNecessary();
		return mgrId;
	}

	public void setMgrId(Integer mgrId) {
		this.mgrId = mgrId;
	}

	public BigDecimal getSal() {
		initializeIfNecessary();
		return sal;
	}

	public void setSal(BigDecimal sal) {
		this.sal = sal;
	}

	public Integer getVersionNo() {
		initializeIfNecessary();
		return versionNo;
	}

	public void setVersionNo(Integer versionNo) {
		this.versionNo = versionNo;
	}

	private void initialize() {
		setupRequestParams();
		if (getCrudType() == CrudType.CREATE) {
			return;
		}
		EmpDto emp = getService().find(id, versionNo);
		this.empNo = emp.getEmpNo();
		this.empName = emp.getEmpName();
		this.hiredate = emp.getHiredate();
		this.sal = emp.getSal();
		this.mgrId = emp.getMgrId();
		this.deptId = emp.getDeptId();
		this.initialized = true;
	}

	private void initializeIfNecessary() {
		if (initialized) {
			return;
		}
		initialize();
	}

	public String doPrevious() {
		return "empList";
	}

	public String doConfirm() {
		EmpDto emp = new EmpDto();
		emp.setId(id);
		emp.setEmpNo(empNo);
		emp.setEmpName(empName);
		emp.setHiredate(hiredate);
		emp.setSal(sal);
		emp.setMgrId(mgrId);
		emp.setDeptId(deptId);
		emp.setVersionNo(versionNo);
		addRequestValue(EmpDto.class, emp);
		return "empConfirm";
	}

	private void setupRequestParams() {
		String type = getRequestParameter("crudType", String
				.valueOf(CrudType.CREATE));
		setCrudType(Integer.valueOf(type));
		if (getCrudType() == CrudType.CREATE) {
			return;
		}
		id = Long.valueOf(getRequestParameter("id"));
		versionNo = Integer.valueOf(getRequestParameter("versionNo"));
	}

}