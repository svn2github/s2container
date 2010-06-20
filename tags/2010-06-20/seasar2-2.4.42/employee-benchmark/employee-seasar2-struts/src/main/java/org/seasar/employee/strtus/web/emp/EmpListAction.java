/**
 * 
 */
package org.seasar.employee.strtus.web.emp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.seasar.framework.container.annotation.tiger.Component;

/**
 * @author taichi
 * 
 */
@Component(name="/empList")
public class EmpListAction extends AbstractEmpAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		EmpListForm input = (EmpListForm) form;
		input.setEmpItems(getService().findAll());
		return mapping.findForward("success");
	}
}
