package org.seasar.employee.spring2.web.emp;

public class EmpEditConfirmPage extends AbstractEmpPage {

	public String doPrevious() {
		addRequestValue(EmpDto.class, toDto());
		addRequestValue("crudType", getCrudType());
		return isUpdate() ? "empEdit" : "empList";
	}

	public String doUpdate() {
		getService().update(toDto());
		return "empList";
	}

	public String doDelete() {
		getService().remove(getId(), getVersionNo());
		return "empList";
	}

}