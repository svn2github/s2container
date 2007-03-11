package org.seasar.employee.spring2.web.emp;

import org.seasar.employee.spring2.util.BeanUtil;

public class EmpEditConfirmPage extends AbstractEmpPage {

	public String doPrevious() {
		try {
			EmpDto emp = new EmpDto();
			BeanUtil.copy(this, emp);
			addRequestValue(EmpDto.class, emp);
			addRequestValue("crudType", getCrudType());
			dispose();
			return isUpdate() ? "empEdit" : "empList";
		} finally {
			dispose();
		}
	}

	public String doUpdate() {
		try {
			EmpDto emp = new EmpDto();
			BeanUtil.copy(this, emp);
			getService().update(emp);
			return "empList";
		} finally {
			dispose();
		}
	}

	public String doDelete() {
		try {
			getService().remove(getId(), getVersionNo());
			return "empList";
		} finally {
			dispose();
		}
	}

	private void dispose() {
		removeSession("empEditConfirmPage");
		removeSession("crudType");
	}
}