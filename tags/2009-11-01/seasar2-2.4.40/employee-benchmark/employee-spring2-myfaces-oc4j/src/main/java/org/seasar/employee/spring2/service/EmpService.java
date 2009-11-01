package org.seasar.employee.spring2.service;

import java.util.List;

import org.seasar.employee.spring2.web.emp.EmpDto;

/**
 * @author taedium
 */
public interface EmpService {

	public List<EmpDto> findAll();

	public EmpDto find(Long id, Integer versionNo);

	public void persist(EmpDto emp);

	public void update(EmpDto emp);

	public void remove(Long id, Integer versionNo);

}
