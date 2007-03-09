/**
 * 
 */
package org.seasar.employee.spring2.web.emp;

import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;

import org.seasar.employee.spring2.service.EmpService;

/**
 * @author taedium
 * 
 */
public abstract class AbstractEmpPage extends AbstractCrudPage {

	private EmpService service;

	public EmpService getService() {
		return service;
	}

	public void setService(EmpService service) {
		this.service = service;
	}

	protected String getRequestParameter(String name) {
		return (String) FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get(name);
	}

	protected String getRequestParameter(String name, String defaultValue) {
		String value = getRequestParameter(name);
		return value != null ? value : defaultValue;
	}

	@SuppressWarnings("unchecked")
	protected void addRequestValue(Class<?> key, Object value) {
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put(key, value);
	}

	@SuppressWarnings("unchecked")
	protected <T> T getRequestValue(Class<T> clazz) {
		Object value = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestMap().get(clazz);
		return clazz.cast(value);
	}

	protected Object getValue(UIComponent component, String id) {
		UIParameter param = (UIParameter) component.findComponent(id);
		return param.getValue();
	}

}
