/**
 * 
 */
package org.seasar.employee.spring2.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.seasar.employee.spring2.dao.EmpDao;
import org.seasar.employee.spring2.entity.Emp;
import org.springframework.orm.jpa.support.JpaDaoSupport;

/**
 * @author taichi
 * 
 */
@SuppressWarnings("unchecked")
public class EmpDaoImpl extends JpaDaoSupport implements EmpDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.seasar.employee.spring2.dao.EmpDao#contains(org.seasar.employee.spring2.entity.Emp)
	 */
	public boolean contains(Emp emp) {
		return getJpaTemplate().contains(emp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.seasar.employee.spring2.dao.EmpDao#findAll()
	 */
	public List<Emp> findAll() {
		return getJpaTemplate().find(
				"SELECT emp FROM Emp AS emp ORDER BY emp.id");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.seasar.employee.spring2.dao.EmpDao#findById(java.lang.Long,
	 *      java.lang.Integer)
	 */
	public Emp findById(Long id, Integer versionNo) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("id", id);
		m.put("versionNo", versionNo);
		List l = getJpaTemplate()
				.findByNamedParams(
						"SELECT emp FROM Emp AS emp WHERE ((emp.id = :id) AND (emp.versionNo = :versionNo))",
						m);
		if (l.isEmpty()) {
			throw new NoResultException();
		} else if (1 < l.size()) {
			throw new NonUniqueResultException();
		}
		return (Emp) l.get(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.seasar.employee.spring2.dao.EmpDao#merge(org.seasar.employee.spring2.entity.Emp)
	 */
	public Emp merge(Emp emp) {
		return getJpaTemplate().merge(emp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.seasar.employee.spring2.dao.EmpDao#persist(org.seasar.employee.spring2.entity.Emp)
	 */
	public void persist(Emp emp) {
		getJpaTemplate().persist(emp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.seasar.employee.spring2.dao.EmpDao#readLock(org.seasar.employee.spring2.entity.Emp)
	 */
	public void readLock(Emp emp) {
		getJpaTemplate().getEntityManager().lock(emp, LockModeType.READ);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.seasar.employee.spring2.dao.EmpDao#refresh(org.seasar.employee.spring2.entity.Emp)
	 */
	public void refresh(Emp emp) {
		getJpaTemplate().getEntityManager().refresh(emp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.seasar.employee.spring2.dao.EmpDao#remove(org.seasar.employee.spring2.entity.Emp)
	 */
	public void remove(Emp emp) {
		getJpaTemplate().remove(emp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.seasar.employee.spring2.dao.EmpDao#writeLock(org.seasar.employee.spring2.entity.Emp)
	 */
	public void writeLock(Emp emp) {
		getJpaTemplate().getEntityManager().lock(emp, LockModeType.WRITE);
	}

}
