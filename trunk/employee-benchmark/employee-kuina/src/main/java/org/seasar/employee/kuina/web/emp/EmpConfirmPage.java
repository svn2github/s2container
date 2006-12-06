package org.seasar.employee.kuina.web.emp;

import java.math.BigDecimal;
import java.util.Date;

import org.seasar.teeda.extension.annotation.convert.DateTimeConverter;
import org.seasar.teeda.extension.annotation.takeover.TakeOver;
import org.seasar.teeda.extension.annotation.takeover.TakeOverType;
import org.seasar.teeda.extension.annotation.validator.Required;
import org.seasar.teeda.core.exception.AppFacesException;
import org.seasar.teeda.extension.util.LabelHelper;

import org.seasar.employee.kuina.entity.Emp;
import org.seasar.employee.kuina.web.CrudType;

public class EmpConfirmPage extends AbstractEmpPage {
	
	private LabelHelper labelHelper;
	
	public EmpConfirmPage() {
	}
	
	public String initialize() {
		if(isComeFromList()) {
			Emp data = getEmpService().find(getId());
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

	@TakeOver(type = TakeOverType.NEVER)
	public String doFinish() {
		switch(getCrudType()) {
			case CrudType.CREATE:
				getEmpService().merge(getEmpDxo().convert(this));
				break;
			case CrudType.UPDATE:
				getEmpService().merge(getEmpDxo().convert(this));
				break;
			case CrudType.DELETE:
				getEmpService().remove(getId());
				break;
			default:
				break;
		}
		return "empList";
	}
	
	public boolean isComeFromList() {
		return getCrudType() == CrudType.READ || getCrudType() == CrudType.DELETE;
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

	@Override
	@DateTimeConverter
	public Date getHiredate() {
		return super.getHiredate();
	}

	public void setLabelHelper(LabelHelper labelHelper) {
		this.labelHelper = labelHelper;
	}
	
	public LabelHelper getLabelHelper() {
		return this.labelHelper;
	}
	
	public String getDoFinishValue() {
		return getLabelHelper().getLabelValue(CrudType.toString(getCrudType()));
	}
}