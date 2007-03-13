package org.seasar.employee.spring2.web.emp;

/**
 * @author taedium
 * 
 */
public class EmpEditPage extends AbstractEmpPage {

	public String doPrevious() {
		return "empList";
	}

	public String doConfirm() {
		addRequestValue(EmpDto.class, toDto());
		addRequestValue("crudType", getCrudType());
		return "empEditConfirm";
	}

}