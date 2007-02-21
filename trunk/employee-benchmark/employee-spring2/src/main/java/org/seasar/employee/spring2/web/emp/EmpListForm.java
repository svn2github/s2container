/**
 * 
 */
package org.seasar.employee.spring2.web.emp;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.seasar.employee.spring2.entity.Emp;

/**
 * @author taichi
 * 
 */
public class EmpListForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private List<Emp> empItems = new ArrayList<Emp>();

	public List<Emp> getEmpItems() {
		return empItems;
	}

	public void setEmpItems(List<Emp> empItems) {
		this.empItems = empItems;
	}

}
