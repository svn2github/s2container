package org.seasar.employee.spring2.service;

import java.util.List;

import org.seasar.employee.spring2.web.emp.EmpForm;

/**
 * @author taichi
 */
public interface EmpService {

	public List<EmpForm> findAll();

	public EmpForm find(Long id, Integer versionNo);

	public void persist(EmpForm emp);

	public void update(EmpForm emp);

	public void remove(Long id, Integer versionNo);

}
