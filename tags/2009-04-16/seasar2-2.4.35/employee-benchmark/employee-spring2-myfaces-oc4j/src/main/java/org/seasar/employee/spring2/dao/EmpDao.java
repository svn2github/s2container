/**
 * 
 */
package org.seasar.employee.spring2.dao;

import java.util.List;

import org.seasar.employee.spring2.entity.Emp;


/**
 * @author taedium
 *
 */
public interface EmpDao {

	public List<Emp> findAll();
	
	public Emp findById(Long id,Integer versionNo);
	
	public void persist(Emp emp);

	public Emp merge(Emp emp);
	
	public void remove(Emp emp);

	public boolean contains(Emp emp);

	public void refresh(Emp emp);

	public void readLock(Emp emp);

	public void writeLock(Emp emp);

}
