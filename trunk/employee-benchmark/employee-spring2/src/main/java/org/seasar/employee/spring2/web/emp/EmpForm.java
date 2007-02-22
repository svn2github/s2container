/**
 * 
 */
package org.seasar.employee.spring2.web.emp;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.ValidatorForm;
import org.seasar.employee.spring2.web.CrudType;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping,
	 *      javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public ActionErrors validate(ActionMapping am, HttpServletRequest req) {
		ActionErrors errors = new ActionErrors();
		if(CrudType.CREATE.equals(getCrudType()) == false) {
			if(StringUtils.isEmpty(getId())) {
				ActionMessage msg = new ActionMessage("errors.required","id");
				errors.add("id", msg);
			}
			if(StringUtils.isEmpty(getVersionNo())) {
				ActionMessage msg = new ActionMessage("errors.required","versionNo");
				errors.add("versionNo", msg);
			}
		}
		if(StringUtils.isEmpty(getEmpNo())) {
			ActionMessage msg = new ActionMessage("errors.required","empNo");
			errors.add("empNo", msg);
		}
		return errors;
	}
}
