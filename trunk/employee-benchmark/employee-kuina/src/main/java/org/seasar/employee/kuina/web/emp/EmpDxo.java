package org.seasar.employee.kuina.web.emp;

import org.seasar.employee.kuina.entity.Emp;

public interface EmpDxo {

	public Emp convert(AbstractEmpPage src);
	
	public void convert(Emp src, AbstractEmpPage dest);
}