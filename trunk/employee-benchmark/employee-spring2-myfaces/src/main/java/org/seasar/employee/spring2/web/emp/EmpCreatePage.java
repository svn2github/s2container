package org.seasar.employee.spring2.web.emp;

import org.seasar.employee.spring2.util.BeanUtil;

public class EmpCreatePage extends AbstractEmpPage {

	public String doPrevious() {
		return "empList";
	}

	public String doConfirm() {
		EmpDto emp = new EmpDto();
		BeanUtil.copy(this, emp);
		addRequestValue(EmpDto.class, emp);
		addSessionValue("crudType", getCrudType());
		return "empCreateConfirm";
	}

}
