/**
 * 
 */
package org.seasar.employee.spring2.web.emp;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.seasar.employee.spring2.entity.Emp;
import org.seasar.employee.spring2.web.CrudType;

/**
 * @author taichi
 * 
 */
public class EmpEditAction extends AbstractEmpAction {
	
	private static final Set<String> NOT_CREATE = new HashSet<String>();
	{
		NOT_CREATE.add(CrudType.READ);
		NOT_CREATE.add(CrudType.UPDATE);
		NOT_CREATE.add(CrudType.DELETE);
	}
	
	@Override
	public ActionForward execute(ActionMapping am, ActionForm af,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		EmpForm form = (EmpForm)af;
		if(CrudType.CREATE.equals(form.getCrudType())) {
			form.clear();
		} else if(NOT_CREATE.contains(form.getCrudType())) {
			Emp e = getService().find(new Long(form.getId()), new Integer(form.getVersionNo()));
			BeanUtils.copyProperties(form, e);
		} else {
			return am.findForward("fail");
		}
		return am.findForward("success");
	}
}
