package org.seasar.employee.strtus.web.emp;

import java.util.List;

import org.seasar.employee.strtus.entity.Emp;

public interface EmpDxo {

	public Emp convert(EmpForm src);
	
	public EmpForm convert(Emp src);
	
	// TODO 型変換の定義が必要な感じ。
	public void convert(EmpForm src, Emp dest);
	
	public List<EmpForm> convert(List<Emp> list);
}