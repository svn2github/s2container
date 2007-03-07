/**
 * 
 */
package org.seasar.employee.spring2.web.emp;

import org.apache.struts.action.Action;
import org.seasar.employee.spring2.service.EmpService;

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
