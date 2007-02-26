/**
 * 
 */
package org.seasar.employee.spring2.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.seasar.employee.spring2.dao.EmpDao;
import org.seasar.employee.spring2.entity.Emp;
import org.seasar.employee.spring2.service.EmpService;
import org.seasar.employee.spring2.web.emp.EmpForm;
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
	
	static {
		ConvertUtils.register(new Converter() {
			public Object convert(Class type, Object value) {
				return null;
			}
		}, Date.class);
	}

	public EmpForm find(Long id, Integer versionNo) {
		Emp e = dao.findById(id, versionNo);
		return toForm(e);
	}

	private EmpForm toForm(Emp e) {
		EmpForm form = new EmpForm();
		copy(e, form);
		return form;
	}

	private void copy(Emp emp, EmpForm form) {
		copyProperties(emp, form);
		form.setHiredate(toString(emp.getHiredate()));
	}

	private void copy(EmpForm form, Emp emp) {
		copyProperties(form, emp);
		emp.setHiredate(toDate(form.getHiredate()));
	}

	private void copyProperties(Object src, Object dest) {
		try {
			BeanUtils.copyProperties(dest, src);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	private static final String FORMAT = "yyyy/MM/dd";

	private static Date toDate(String s) {
		try {
			return new SimpleDateFormat(FORMAT).parse(s);
		} catch (ParseException e) {
			return null;
		}
	}

	private static String toString(Date d) {
		return new SimpleDateFormat(FORMAT).format(d);
	}

	public List<EmpForm> findAll() {
		List<Emp> all = dao.findAll();
		List<EmpForm> forms = new ArrayList<EmpForm>(all.size());
		for (Emp e : all) {
			forms.add(toForm(e));
		}
		return forms;
	}

	public void persist(EmpForm form) {
		Emp emp = new Emp();
		copy(form, emp);
		dao.persist(emp);
	}

	public void remove(Long id, Integer versionNo) {
		Emp emp = dao.findById(id, versionNo);
		dao.remove(emp);
	}

	public void update(EmpForm form) {
		Emp e = dao.findById(new Long(form.getId()), new Integer(form
				.getVersionNo()));
		copy(form, e);
	}

	/**
	 * @return the dao
	 */
	public EmpDao getDao() {
		return dao;
	}

	/**
	 * @param dao
	 *            the dao to set
	 */
	public void setDao(EmpDao dao) {
		this.dao = dao;
	}

}
