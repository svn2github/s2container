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
import org.seasar.employee.spring2.web.emp.EmpDto;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author taedium
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

	public EmpDto find(Long id, Integer versionNo) {
		Emp e = dao.findById(id, versionNo);
		return toDto(e);
	}

	private EmpDto toDto(Emp e) {
		EmpDto dto = new EmpDto();
		copy(e, dto);
		return dto;
	}

	private void copy(Emp emp, EmpDto dto) {
		copyProperties(emp, dto);
		dto.setHiredate(toString(emp.getHiredate()));
	}

	private void copy(EmpDto dto, Emp emp) {
		copyProperties(dto, emp);
		emp.setHiredate(toDate(dto.getHiredate()));
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

	public List<EmpDto> findAll() {
		List<Emp> all = dao.findAll();
		List<EmpDto> dtos = new ArrayList<EmpDto>(all.size());
		for (Emp e : all) {
			dtos.add(toDto(e));
		}
		return dtos;
	}

	public void persist(EmpDto dto) {
		Emp emp = new Emp();
		copy(dto, emp);
		dao.persist(emp);
	}

	public void remove(Long id, Integer versionNo) {
		Emp emp = dao.findById(id, versionNo);
		dao.remove(emp);
	}

	public void update(EmpDto dto) {
		Emp e = dao.findById(new Long(dto.getId()), new Integer(dto
				.getVersionNo()));
		copy(dto, e);
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
