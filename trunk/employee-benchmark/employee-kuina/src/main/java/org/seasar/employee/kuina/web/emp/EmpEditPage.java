package org.seasar.employee.kuina.web.emp;

import org.seasar.employee.kuina.web.CrudType;
import org.seasar.teeda.extension.annotation.validator.Required;

public class EmpEditPage extends AbstractEmpPage {

	public EmpEditPage() {
	}

	public String initialize() {
		if (getCrudType() == CrudType.UPDATE) {
			getEmpService().findAndCopy(this);
		}
		return null;
	}

	public String prerender() {
		return null;
	}

	@Override
	@Required
	public void setEmpNo(Integer empno) {
		super.setEmpNo(empno);
	}

}