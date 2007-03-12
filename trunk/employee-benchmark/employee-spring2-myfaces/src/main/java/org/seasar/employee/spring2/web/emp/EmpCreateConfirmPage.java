package org.seasar.employee.spring2.web.emp;

public class EmpCreateConfirmPage extends AbstractEmpPage {

	public String doPrevious() {
		addRequestValue(EmpDto.class, toDto());
		addRequestValue("crudType", getCrudType());
		return "empCreate";
	}

	public String doCreate() {
		getService().persist(toDto());
		return "empList";
	}

}