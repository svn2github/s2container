package org.seasar.employee.spring2.web.emp;

import org.seasar.employee.spring2.util.BeanUtil;

public class EmpEditPage extends AbstractEmpPage {

	public String doPrevious() {
		return "empList";
	}

	public String doConfirm() {
		EmpDto emp = new EmpDto();
		BeanUtil.copy(this, emp);
		addRequestValue(EmpDto.class, emp);
		addRequestValue("crudType", getCrudType());
		return "empEditConfirm";
	}

}