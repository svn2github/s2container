/**
 * 
 */
package org.seasar.employee.spring2.service.impl;

import java.util.List;

import org.seasar.employee.spring2.dao.EmpDao;
import org.seasar.employee.spring2.entity.Emp;
import org.seasar.employee.spring2.service.EmpService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author taichi
 * 
 */
@Repository
@Transactional
public class EmpServiceImpl implements EmpService {

	private EmpDao dao;

	public boolean contains(Emp emp) {
		return dao.contains(emp);
	}

	public Emp find(Long id, Integer versionNo) {
		return dao.findById(id, versionNo);
	}

	public List<Emp> findAll() {
		return dao.findAll();
	}

	public void persist(Emp emp) {
		dao.persist(emp);
	}

	public void remove(Long id, Integer versionNo) {
		Emp emp = dao.findById(id, versionNo);
		dao.remove(emp);
	}

	public void update(Emp emp) {
		Emp e = find(emp.getId(), emp.getVersionNo());
		e.setEmpNo(emp.getEmpNo());
		e.setEmpName(emp.getEmpName());
		e.setMgrId(emp.getMgrId());
		e.setHiredate(emp.getHiredate());
		e.setSal(emp.getSal());
		e.setDeptId(emp.getDeptId());
	}

	/**
	 * @return the dao
	 */
	public EmpDao getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(EmpDao dao) {
		this.dao = dao;
	}

}
