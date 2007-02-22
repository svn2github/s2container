/**
 * 
 */
package org.seasar.employee.spring2.web.emp;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

/**
 * @author taichi
 * 
 */
public class EmpListForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private List<EmpForm> empItems = new ArrayList<EmpForm>();

	public List<EmpForm> getEmpItems() {
		return empItems;
	}

	public void setEmpItems(List<EmpForm> empItems) {
		this.empItems = empItems;
	}

}
