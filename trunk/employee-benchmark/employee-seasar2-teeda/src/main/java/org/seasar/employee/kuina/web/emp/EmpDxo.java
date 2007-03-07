package org.seasar.employee.kuina.web.emp;

import java.util.List;

import org.seasar.employee.kuina.dto.EmpDto;
import org.seasar.employee.kuina.entity.Emp;

public interface EmpDxo {

	public Emp convert(AbstractEmpPage src);
	
	public void convert(Emp src, AbstractEmpPage dest);

	public void convert(AbstractEmpPage src, Emp dest);
	
	public List<EmpDto> convert(List<Emp> list);
}