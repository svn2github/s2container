/**
 * 
 */
package org.seasar.employee.strtus.web.emp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.seasar.employee.strtus.web.CrudType;
import org.seasar.framework.container.annotation.tiger.Component;

/**
 * @author taichi
 * 
 */
@Component(name="/empFinish")
public class EmpFinishAction extends AbstractEmpAction {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward execute(ActionMapping am, ActionForm af,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		EmpForm form = (EmpForm) af;
		String type = form.getCrudType();
		if (CrudType.CREATE.equals(type)) {
			getService().persist(form);
		} else if (CrudType.UPDATE.equals(type)) {
			getService().update(form);
		} else if (CrudType.DELETE.equals(type)) {
			getService().remove(new Long(form.getId()),
					new Integer(form.getVersionNo()));
		}
		return am.findForward("success");
	}
}
