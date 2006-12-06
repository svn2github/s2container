/**
 * 
 */
package org.seasar.employee.spring2.web.emp;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * @author taichi
 *
 */
public class EmployeeController extends MultiActionController implements
		InitializingBean {

	public void afterPropertiesSet() throws Exception {
		
	}

}
