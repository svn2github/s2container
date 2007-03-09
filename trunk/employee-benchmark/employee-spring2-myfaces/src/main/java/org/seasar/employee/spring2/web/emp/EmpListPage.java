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

	public List<EmpDto> getEmpItems() {
		return getService().findAll();
	}

	public String doCreate() {
		return "empEdit";
	}

}
