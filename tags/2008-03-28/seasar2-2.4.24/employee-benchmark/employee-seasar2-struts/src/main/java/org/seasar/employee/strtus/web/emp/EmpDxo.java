package org.seasar.employee.strtus.web.emp;

import java.util.List;

import org.seasar.employee.strtus.entity.Emp;
import org.seasar.extension.dxo.annotation.DatePattern;

@DatePattern("yyyy/MM/dd")
public interface EmpDxo {

	public Emp convert(EmpForm src);
	
	public EmpForm convert(Emp src);
	
	public void convert(EmpForm src, Emp dest);
	
	public List<EmpForm> convert(List<Emp> list);
}