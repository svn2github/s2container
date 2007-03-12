package org.seasar.employee.spring2.web.emp;

import java.util.Map;

import javax.faces.context.FacesContext;

import org.seasar.employee.spring2.util.BeanUtil;

public class EmpCreateConfirmPage extends AbstractEmpPage {

	public String doPrevious() {
		Map m = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		EmpDto emp = new EmpDto();
		BeanUtil.copy(this, emp);
		addRequestValue(EmpDto.class, emp);
		addRequestValue("crudType", getCrudType());
		return "empCreate";
	}

	public String doCreate() {
		EmpDto emp = new EmpDto();
		BeanUtil.copy(this, emp);
		getService().persist(emp);
		return "empList";
	}

}