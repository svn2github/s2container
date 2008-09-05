/**
 * 
 */
package org.seasar.employee.strtus.web.emp;

import org.apache.struts.action.Action;

/**
 * @author taichi
 * 
 */
public abstract class AbstractEmpAction extends Action {

	private EmpService service;

	public EmpService getService() {
		return service;
	}

	public void setService(EmpService service) {
		this.service = service;
	}

}
