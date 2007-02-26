package org.seasar.employee.kuina.web.emp;

import java.util.Date;
import java.util.List;

import org.seasar.employee.kuina.dto.EmpDto;
import org.seasar.employee.kuina.web.CrudType;
import org.seasar.teeda.extension.annotation.convert.DateTimeConverter;
import org.seasar.teeda.extension.annotation.takeover.TakeOver;

public class EmpListPage extends AbstractEmpPage {
	
	private List<EmpDto> empItems;
	
	private int empIndex;
	
	public EmpListPage() {
	}
	
	public String initialize() {
		return null;
	}
	
	public String prerender() {
		empItems = getEmpService().findAll();
		return null;
	}
	
	public String getEmpRowClass() {
		if (getEmpIndex() % 2 == 0) {
			return "row_even";
		}
		return "row_odd";
	}

	@TakeOver(properties = "crudType")
	public String doCreate() {
		setCrudType(CrudType.CREATE);
		return "empEdit";
	}
	
	@Override
	@DateTimeConverter
	public Date getHiredate() {
		return super.getHiredate();
	}

	public List<EmpDto> getEmpItems() {
		return this.empItems;
	}

	public void setEmpItems(List<EmpDto> items) {
		this.empItems = items;
	}
	
	public int getEmpIndex() {
		return this.empIndex;
	}
	
	public void setEmpIndex(int empIndex) {
		this.empIndex = empIndex;
	}
}