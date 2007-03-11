package org.seasar.employee.spring2.web.emp;

import org.seasar.employee.spring2.util.BeanUtil;

public class EmpCreateConfirmPage extends AbstractEmpPage {

	public String doPrevious() {
		try {
			EmpDto emp = new EmpDto();
			BeanUtil.copy(this, emp);
			addRequestValue(EmpDto.class, emp);
			addRequestValue("crudType", getCrudType());
			return "empCreate";
		} finally {
			dispose();
		}
	}

	public String doCreate() {
		try {
			EmpDto emp = new EmpDto();
			BeanUtil.copy(this, emp);
			getService().persist(emp);
			return "empList";
		} finally {
			dispose();
		}
	}

	private void dispose() {
		removeSession("empCreateConfirmPage");
		removeSession("crudType");
	}

}