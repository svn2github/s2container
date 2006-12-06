/**
 * 
 */
package org.seasar.employee.spring2.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

	/* (non-Javadoc)
	 * @see org.seasar.employee.spring2.service.EmpService#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Emp> findAll() {
		return em.createNativeQuery("SELECT emp FROM Emp AS emp",Emp.class).getResultList();
	}

	/* (non-Javadoc)
	 * @see org.seasar.employee.spring2.service.EmpService#find(java.lang.Integer)
	 */
	public Emp find(Integer id) {
		return em.find(Emp.class,id);
	}

	/* (non-Javadoc)
	 * @see org.seasar.employee.spring2.service.EmpService#persist(org.seasar.employee.spring2.entity.Emp)
	 */
	public void persist(Emp emp) {
		em.persist(emp);
	}

	/* (non-Javadoc)
	 * @see org.seasar.employee.spring2.service.EmpService#merge(org.seasar.employee.spring2.entity.Emp)
	 */
	public Emp merge(Emp emp) {
		return em.merge(emp);
	}

	/* (non-Javadoc)
	 * @see org.seasar.employee.spring2.service.EmpService#remove(java.lang.Integer)
	 */
	public void remove(Integer id) {
		em.remove(id);
	}

	/* (non-Javadoc)
	 * @see org.seasar.employee.spring2.service.EmpService#contains(org.seasar.employee.spring2.entity.Emp)
	 */
	public boolean contains(Emp emp) {
		return em.contains(emp);
	}

}
