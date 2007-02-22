package org.seasar.employee.kuina.web.emp.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;

import org.seasar.employee.kuina.dao.EmpDao;
import org.seasar.employee.kuina.entity.Emp;
import org.seasar.employee.kuina.web.emp.AbstractEmpPage;
import org.seasar.employee.kuina.web.emp.EmpDxo;
import org.seasar.employee.kuina.web.emp.EmpService;

@Stateless
public class EmpServiceImpl implements EmpService {

	@Resource
	private EmpDao empDao;
	
	@Resource
	private EmpDxo empDxo;
	

	public EmpServiceImpl() {
	}

	public List<Emp> findAll() {
		return getEmpDao().findAll();
	}
	
	public Emp find(long id,Integer versionNo) {
		return getEmpDao().findById(id, versionNo);
	}
	
	public void persist(AbstractEmpPage page) {
		getEmpDao().persist(getEmpDxo().convert(page));
	}

	public void update(AbstractEmpPage page) {
		Emp emp = find(page.getId(), page.getVersionNo());
		getEmpDxo().convert(page, emp);
	}
	
	public void remove(long id,Integer versionNo) {
		Emp emp = find(id, versionNo);
		getEmpDao().remove(emp);
	}

	public boolean contains(Emp emp) {
		return getEmpDao().contains(emp);
	}

	public EmpDao getEmpDao() {
		return this.empDao;
	}

	public void setEmpDao(EmpDao empDao) {
		this.empDao = empDao;
	}

	public EmpDxo getEmpDxo() {
		return this.empDxo;
	}

	public void setEmpDxo(EmpDxo empDxo) {
		this.empDxo = empDxo;
	}
}