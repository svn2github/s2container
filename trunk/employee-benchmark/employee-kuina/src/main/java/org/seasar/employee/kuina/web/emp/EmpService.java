package org.seasar.employee.kuina.web.emp;

import java.util.List;

import org.seasar.employee.kuina.entity.Emp;

public interface EmpService {

	public List<Emp> findAll();

	public Emp find(long id, Integer versionNo);

	public void persist(AbstractEmpPage page);

	public void update(AbstractEmpPage page);

	public void remove(long id, Integer versionNo);

	public boolean contains(Emp emp);

}