/**
 * 
 */
package org.seasar.employee.spring2.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.seasar.employee.spring2.dao.EmpDao;
import org.seasar.employee.spring2.entity.Emp;
import org.seasar.employee.spring2.service.EmpService;
import org.seasar.employee.spring2.util.BeanUtil;
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

	public EmpDto find(Long id, Integer versionNo) {
		Emp e = dao.findById(id, versionNo);
		return toDto(e);
	}

	private EmpDto toDto(Emp e) {
		EmpDto dto = new EmpDto();
		BeanUtil.copy(e, dto);
		return dto;
	}

	private Emp toEntity(EmpDto dto) {
		Emp e = new Emp();
		BeanUtil.copy(dto, e);
		return e;
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
		Emp emp = toEntity(dto);
		dao.persist(emp);
	}

	public void remove(Long id, Integer versionNo) {
		Emp emp = dao.findById(id, versionNo);
		dao.remove(emp);
	}

	public void update(EmpDto dto) {
		Emp e = dao.findById(new Long(dto.getId()), new Integer(dto
				.getVersionNo()));
		BeanUtil.copy(dto, e);
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
