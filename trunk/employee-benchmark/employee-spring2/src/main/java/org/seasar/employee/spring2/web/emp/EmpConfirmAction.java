/**
 * 
 */
package org.seasar.employee.spring2.web.emp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.employee.spring2.web.CrudType;

/**
 * @author taichi
 * 
 */
public class EmpConfirmAction extends AbstractEmpAction {
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
		if(CrudType.CREATE.equals(form.getCrudType())) {
			ActionMessages msgs = form.validate(am, req);
			saveMessages(req, msgs);
		} else {
			ActionMessages msgs = new ActionMessages();
			if (StringUtils.isEmpty(form.getId())) {
				ActionMessage msg = new ActionMessage("errors.required", "id");
				msgs.add("id", msg);
			}
			if (StringUtils.isEmpty(form.getVersionNo())) {
				ActionMessage msg = new ActionMessage("errors.required",
						"versionNo");
				msgs.add("versionNo", msg);
			}
			if(0 < msgs.size()) {
				saveMessages(req, msgs);
			}
		}
		return am.findForward("success");
	}
}