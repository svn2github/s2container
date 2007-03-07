package org.seasar.employee.kuina.web.emp;

import java.util.List;

import org.seasar.employee.kuina.dto.EmpDto;
import org.seasar.employee.kuina.entity.Emp;

public interface EmpService {

	public List<EmpDto> findAll();

	public void findAndCopy(AbstractEmpPage page);

	public void persist(AbstractEmpPage page);

	public void update(AbstractEmpPage page);

	public void remove(AbstractEmpPage page);

	public boolean contains(Emp emp);

}