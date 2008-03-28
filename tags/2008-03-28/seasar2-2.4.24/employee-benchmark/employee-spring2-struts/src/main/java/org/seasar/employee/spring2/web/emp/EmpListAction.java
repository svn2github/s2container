/**
 * 
 */
package org.seasar.employee.spring2.web.emp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author taichi
 * 
 */
public class EmpListAction extends AbstractEmpAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		EmpListForm input = (EmpListForm) form;
		input.setEmpItems(getService().findAll());
		return mapping.findForward("success");
	}
}
