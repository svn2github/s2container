package org.seasar.employee.spring2.service;

import java.util.List;

import org.seasar.employee.spring2.entity.Emp;

/**
 * @author taichi
 */
public interface EmpService {

	public List<Emp> findAll();
	
	public Emp find(Long id,Integer versionNo);
	
	public void persist(Emp emp);

	public void update(Emp emp);
	
	public void remove(Long id,Integer versionNo);

	public boolean contains(Emp emp);

}
