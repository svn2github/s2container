package org.seasar.employee.spring2.web.emp;

import java.math.BigDecimal;

public class EmpConfirmPage extends AbstractEmpPage {

	private long id;

	private Integer empNo;

	private String empName;

	private Integer mgrId;

	private String hiredate;

	private BigDecimal sal;

	private Integer deptId;

	private Integer versionNo;

	public EmpConfirmPage() {
		setupRequestParams();
		initialize();
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Integer getEmpNo() {
		return empNo;
	}

	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}

	public String getHiredate() {
		return hiredate;
	}

	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getMgrId() {
		return mgrId;
	}

	public void setMgrId(Integer mgrId) {
		this.mgrId = mgrId;
	}

	public BigDecimal getSal() {
		return sal;
	}

	public void setSal(BigDecimal sal) {
		this.sal = sal;
	}

	public Integer getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(Integer versionNo) {
		this.versionNo = versionNo;
	}

	private void initialize() {
		EmpDto emp = getRequestValue(EmpDto.class);
		this.id = emp.getId();
		this.empNo = emp.getEmpNo();
		this.empName = emp.getEmpName();
		this.hiredate = emp.getHiredate();
		this.sal = emp.getSal();
		this.mgrId = emp.getMgrId();
		this.deptId = emp.getDeptId();
		this.versionNo = emp.getVersionNo();
	}

	public String doPrevious() {
		return "empEdit";
	}

	public String doUpdate() {
		EmpDto emp = new EmpDto();
		emp.setId(id);
		emp.setEmpNo(empNo);
		emp.setEmpName(empName);
		emp.setHiredate(hiredate);
		emp.setSal(sal);
		emp.setMgrId(mgrId);
		emp.setDeptId(deptId);
		emp.setVersionNo(versionNo);
		getService().update(emp);
		return "empList";
	}

	private void setupRequestParams() {
		String type = getRequestParameter("crudType", String
				.valueOf(CrudType.CREATE));
		setCrudType(Integer.valueOf(type));
	}

}