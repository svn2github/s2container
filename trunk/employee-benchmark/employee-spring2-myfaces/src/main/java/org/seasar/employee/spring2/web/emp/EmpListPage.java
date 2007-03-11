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
		addRequestValue("crudType", getCrudType());
		return "empCreate";
	}

	public String doDetail() {
		EmpDto emp = getService().find(getId(), getVersionNo());
		addRequestValue(EmpDto.class, emp);
		if (isUpdate()) {
			addRequestValue("crudType", getCrudType());
			return "empEdit";
		}
		addSessionValue("crudType", getCrudType());
		return "empEditConfirm";
	}

}
