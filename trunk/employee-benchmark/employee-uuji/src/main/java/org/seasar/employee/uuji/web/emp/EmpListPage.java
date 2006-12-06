package org.seasar.employee.uuji.web.emp;

import java.util.Map;
import java.math.BigDecimal;
import java.util.Date;

import org.seasar.teeda.extension.annotation.convert.DateTimeConverter;
import org.seasar.teeda.extension.annotation.takeover.TakeOver;

import org.seasar.employee.uuji.web.CrudType;

public class EmpListPage extends AbstractEmpPage {
	
	private Map[] empItems;
	
	private int empIndex;
	
	public EmpListPage() {
	}
	
	public String initialize() {
		return null;
	}
	
	public String prerender() {
		empItems = getEmpDao().findAll();
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

	public Map[] getEmpItems() {
		return this.empItems;
	}

	public void setEmpItems(Map[] items) {
		this.empItems = items;
	}
	
	public int getEmpIndex() {
		return this.empIndex;
	}
	
	public void setEmpIndex(int empIndex) {
		this.empIndex = empIndex;
	}
}