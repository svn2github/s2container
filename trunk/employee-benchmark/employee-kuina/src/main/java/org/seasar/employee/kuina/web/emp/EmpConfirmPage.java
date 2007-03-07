package org.seasar.employee.kuina.web.emp;

import java.util.Date;

import org.seasar.employee.kuina.web.CrudType;
import org.seasar.teeda.extension.annotation.convert.DateTimeConverter;
import org.seasar.teeda.extension.annotation.takeover.TakeOver;
import org.seasar.teeda.extension.annotation.takeover.TakeOverType;
import org.seasar.teeda.extension.annotation.validator.Required;
import org.seasar.teeda.extension.util.LabelHelper;

public class EmpConfirmPage extends AbstractEmpPage {

	private LabelHelper labelHelper;

	public EmpConfirmPage() {
	}

	public String initialize() {
		if (isComeFromList()) {
			getEmpService().findAndCopy(this);
		}
		return null;
	}

	public String prerender() {
		return null;
	}

	@TakeOver(type = TakeOverType.NEVER)
	public String doCreate() {
		getEmpService().persist(this);
		return "empList";
	}

	@TakeOver(type = TakeOverType.NEVER)
	public String doUpdate() {
		switch (getCrudType()) {
		case CrudType.UPDATE:
			getEmpService().update(this);
			break;
		case CrudType.DELETE:
			getEmpService().remove(this);
			break;
		default:
			break;
		}
		return "empList";
	}

	public boolean isComeFromList() {
		return getCrudType() == CrudType.READ
				|| getCrudType() == CrudType.DELETE;
	}

	@Override
	@Required(target = "doUpdate")
	public void setId(long id) {
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

	@Override
	@Required(target = "doUpdate")
	public void setVersionNo(Integer versionno) {
		super.setVersionNo(versionno);
	}

	public void setLabelHelper(LabelHelper labelHelper) {
		this.labelHelper = labelHelper;
	}

	public LabelHelper getLabelHelper() {
		return this.labelHelper;
	}

	public String getDoCreateValue() {
		return getLabelHelper().getLabelValue(CrudType.toString(getCrudType()));
	}
	
	public String getDoUpdateValue() {
		return getLabelHelper().getLabelValue(CrudType.toString(getCrudType()));
	}

	public String getJumpEmpEditStyle() {
		return isComeFromList() ? "display: none;" : "";
	}

	public String getDoUpdateStyle() {
		return isCreate() ? "display: none;" : "";
	}
}