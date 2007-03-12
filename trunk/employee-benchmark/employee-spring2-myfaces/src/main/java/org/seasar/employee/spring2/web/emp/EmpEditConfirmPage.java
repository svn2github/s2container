package org.seasar.employee.spring2.web.emp;

import java.util.Map;

import javax.faces.context.FacesContext;

import org.seasar.employee.spring2.util.BeanUtil;

public class EmpEditConfirmPage extends AbstractEmpPage {

	public String doPrevious() {
		Map m = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		EmpDto emp = new EmpDto();
		BeanUtil.copy(this, emp);
		addRequestValue(EmpDto.class, emp);
		addRequestValue("crudType", getCrudType());
		return isUpdate() ? "empEdit" : "empList";
	}

	public String doUpdate() {
		EmpDto emp = new EmpDto();
		BeanUtil.copy(this, emp);
		getService().update(emp);
		return "empList";
	}

	public String doDelete() {
		getService().remove(getId(), getVersionNo());
		return "empList";
	}

}