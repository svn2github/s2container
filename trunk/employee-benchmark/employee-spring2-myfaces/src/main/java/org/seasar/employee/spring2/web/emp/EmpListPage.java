/**
 * 
 */
package org.seasar.employee.spring2.web.emp;

import java.util.List;

/**
 * @author taedium
 * 
 */
public class EmpListPage extends AbstractEmpPage {

	private List<EmpDto> empItems;

	public List<EmpDto> getEmpItems() {
		return getService().findAll();
	}

	public void setEmpItems(List<EmpDto> empItems) {
		this.empItems = empItems;
	}

}
