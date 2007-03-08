/**
 * 
 */
package org.seasar.employee.spring2.web.emp;

import org.seasar.employee.spring2.service.EmpService;

/**
 * @author taedium
 * 
 */
public abstract class AbstractEmpPage {

	private EmpService service;

	public EmpService getService() {
		return service;
	}

	public void setService(EmpService service) {
		this.service = service;
	}

}
