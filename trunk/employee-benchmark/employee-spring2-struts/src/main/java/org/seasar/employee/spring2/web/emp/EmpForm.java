/**
 * 
 */
package org.seasar.employee.spring2.web.emp;

import org.apache.struts.validator.ValidatorForm;

/**
 * @author taichi
 * 
 */
public class EmpForm extends ValidatorForm {

	private static final long serialVersionUID = 1L;

	private String crudType;

	private String id;

	private String empNo;

	private String empName;

	private String mgrId;

	private String hiredate;

	private String sal;

	private String deptId;

	private String versionNo;

	public void clear() {
		id = "";
		empNo = "";
		empName = "";
		mgrId = "";
		hiredate = "";
		sal = "";
		deptId = "";
		versionNo = "0";
	}

	public String getCrudType() {
		return crudType;
	}

	public void setCrudType(String crudType) {
		this.crudType = crudType;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getHiredate() {
		return hiredate;
	}

	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMgrId() {
		return mgrId;
	}

	public void setMgrId(String mgrId) {
		this.mgrId = mgrId;
	}

	public String getSal() {
		return sal;
	}

	public void setSal(String sal) {
		this.sal = sal;
	}

	public String getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}
}
