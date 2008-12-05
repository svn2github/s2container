package org.seasar.employee.strtus.web.emp;

import java.util.List;

public interface EmpService {

	public List<EmpForm> findAll();

	public EmpForm find(Long id, Integer versionNo);

	public void persist(EmpForm form);

	public void update(EmpForm form);

	public void remove(Long id, Integer versionNo);

}