/**
 * 
 */
package org.seasar.employee.spring2.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

	@PersistenceContext
	private EntityManager em;

	public boolean contains(Emp emp) {
		return em.contains(emp);
	}

	public Emp find(Long id, Integer versionNo) {
		Query q = em
				.createNamedQuery("SELECT emp FROM Emp AS emp WHERE ((emp.id = :id) AND (emp.versionNo = :versionNo))");
		q.setParameter("id", id);
		q.setParameter("versionNo", versionNo);
		return (Emp) q.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Emp> findAll() {
		return em.createQuery("SELECT emp FROM Emp AS emp").getResultList();
	}

	public void persist(Emp emp) {
		em.persist(emp);
	}

	public void remove(Long id, Integer versionNo) {
		Emp e = find(id, versionNo);
		em.remove(e);
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

}
