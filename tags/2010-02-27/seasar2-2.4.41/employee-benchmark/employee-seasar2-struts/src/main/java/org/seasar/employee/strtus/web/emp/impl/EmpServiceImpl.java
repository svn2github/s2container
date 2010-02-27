package org.seasar.employee.strtus.web.emp.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;

import org.seasar.employee.strtus.dao.EmpDao;
import org.seasar.employee.strtus.entity.Emp;
import org.seasar.employee.strtus.web.emp.EmpDxo;
import org.seasar.employee.strtus.web.emp.EmpForm;
import org.seasar.employee.strtus.web.emp.EmpService;

@Stateless
public class EmpServiceImpl implements EmpService {

	@Resource
	private EmpDao empDao;

	@Resource
	private EmpDxo empDxo;

	public EmpServiceImpl() {
	}

	public List<EmpForm> findAll() {
		return getEmpDxo().convert(getEmpDao().findAll("id"));
	}

	public EmpForm find(Long id, Integer versionNo) {
		Emp src = getEmpDao().findById(id, versionNo);
		return getEmpDxo().convert(src);
	}

	public void persist(EmpForm form) {
		getEmpDao().persist(getEmpDxo().convert(form));
	}

	public void update(EmpForm form) {
		Emp emp = empDao.findById(new Long(form.getId()), new Integer(form.getVersionNo()));
		getEmpDxo().convert(form, emp);
	}

	public void remove(Long id, Integer versionNo) {
		Emp emp = empDao.findById(id, versionNo);
		getEmpDao().remove(emp);
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