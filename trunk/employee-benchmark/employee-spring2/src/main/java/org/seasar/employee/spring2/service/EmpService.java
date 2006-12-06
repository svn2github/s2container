package org.seasar.employee.spring2.service;

import java.util.List;

import org.seasar.employee.spring2.entity.Emp;

/**
 * @author taichi
 */
public interface EmpService {
	
	public List<Emp> findAll();
	
	public Emp find(Integer id);
	
	public void persist(Emp emp);

	public Emp merge(Emp emp);
	
	public void remove(Integer id);

	public boolean contains(Emp emp);

}
