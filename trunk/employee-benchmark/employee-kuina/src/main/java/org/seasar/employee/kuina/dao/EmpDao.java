package org.seasar.employee.kuina.dao;

import java.util.List;

import org.seasar.employee.kuina.entity.Emp;

public interface EmpDao {

	public List<Emp> findAll(String orderby);

	public Emp findById(long id, Integer versionNo);

	public void persist(Emp emp);

	public Emp merge(Emp emp);

	public void remove(Emp emp);

	public boolean contains(Emp emp);

	public void refresh(Emp emp);

	public void readLock(Emp emp);

	public void writeLock(Emp emp);
}