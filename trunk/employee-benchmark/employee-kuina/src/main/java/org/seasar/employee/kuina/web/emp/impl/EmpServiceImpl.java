package org.seasar.employee.kuina.web.emp.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;

import org.seasar.employee.kuina.dao.EmpDao;
import org.seasar.employee.kuina.entity.Emp;
import org.seasar.employee.kuina.web.emp.EmpService;

@Stateless
public class EmpServiceImpl implements EmpService {

	@Resource
	private EmpDao empDao;

	public EmpServiceImpl() {
	}

	public List<Emp> findAll() {
		return getEmpDao().findAll();
	}
	
	public Emp find(Integer id) {
		return getEmpDao().find(id);
	}
	
	public void persist(Emp emp) {
		getEmpDao().persist(emp);
	}

	public Emp merge(Emp emp) {
		return getEmpDao().merge(emp);
	}
	
	public void remove(Integer id) {
		Emp emp = find(id);
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

}