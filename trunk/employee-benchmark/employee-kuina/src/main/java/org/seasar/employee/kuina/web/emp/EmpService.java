package org.seasar.employee.kuina.web.emp;

import java.util.List;

import org.seasar.employee.kuina.entity.Emp;

public interface EmpService {

	public List<Emp> findAll();
	
	public Emp find(Integer id);
	
	public void persist(Emp emp);

	public Emp merge(Emp emp);
	
	public void remove(Integer id);

	public boolean contains(Emp emp);

}