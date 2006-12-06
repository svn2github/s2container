package org.seasar.employee.uuji.web.emp;

import java.util.Map;
import java.math.BigDecimal;
import java.util.Date;

import org.seasar.teeda.extension.annotation.convert.DateTimeConverter;
import org.seasar.teeda.extension.annotation.validator.Required;

import org.seasar.teeda.core.exception.AppFacesException;

import org.seasar.employee.uuji.web.CrudType;

public class EmpEditPage extends AbstractEmpPage {

	public EmpEditPage() {
	}
	
	public String initialize() {
		if(getCrudType() == CrudType.UPDATE) {
			Map data = getEmpDao().find(getId());
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
	public void setId(Integer id) {
		super.setId(id);
	}

	@Override
	@Required
	public void setEmpNo(Integer empno) {
		super.setEmpNo(empno);
	}

}