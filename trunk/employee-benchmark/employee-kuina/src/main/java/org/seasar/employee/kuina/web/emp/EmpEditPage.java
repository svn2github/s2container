package org.seasar.employee.kuina.web.emp;

import org.seasar.employee.kuina.entity.Emp;
import org.seasar.employee.kuina.web.CrudType;
import org.seasar.teeda.core.exception.AppFacesException;
import org.seasar.teeda.extension.annotation.validator.Required;

public class EmpEditPage extends AbstractEmpPage {

	public EmpEditPage() {
	}
	
	public String initialize() {
		if(getCrudType() == CrudType.UPDATE) {
			Emp data = getEmpService().find(getId(), getVersionNo());
			if(data == null) {
				throw new AppFacesException("E0000001");
			}
			getEmpDxo().convert(data ,this);
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