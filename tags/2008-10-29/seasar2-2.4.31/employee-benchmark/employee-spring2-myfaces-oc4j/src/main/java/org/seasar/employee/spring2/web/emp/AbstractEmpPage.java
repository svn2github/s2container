/**
 * 
 */
package org.seasar.employee.spring2.web.emp;

import java.math.BigDecimal;
import java.util.Date;

import javax.faces.context.FacesContext;

import org.seasar.employee.spring2.service.EmpService;
import org.seasar.employee.spring2.util.BeanUtil;
import org.seasar.employee.spring2.web.AbstractCrudPage;

/**
 * @author taedium
 * 
 */
public abstract class AbstractEmpPage extends AbstractCrudPage {

	private EmpService service;

	private Long id;

	private Integer empNo;

	private String empName;

	private Integer mgrId;

	private Date hiredate;

	private BigDecimal sal;

	private Integer deptId;

	private Integer versionNo;

	public AbstractEmpPage() {
		initialize();
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Integer getEmpNo() {
		return empNo;
	}

	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}

	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getMgrId() {
		return mgrId;
	}

	public void setMgrId(Integer mgrId) {
		this.mgrId = mgrId;
	}

	public BigDecimal getSal() {
		return sal;
	}

	public void setSal(BigDecimal sal) {
		this.sal = sal;
	}

	public Integer getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(Integer versionNo) {
		this.versionNo = versionNo;
	}

	public EmpService getService() {
		return service;
	}

	public void setService(EmpService service) {
		this.service = service;
	}

	protected void initialize() {
		EmpDto emp = getRequestValue(EmpDto.class);
		if (emp != null) {
			BeanUtil.copy(emp, this);
		}
	}

	@SuppressWarnings("unchecked")
	protected void addRequestValue(Class<?> key, Object value) {
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put(key, value);
	}

	@SuppressWarnings("unchecked")
	protected void addRequestValue(String name, Object value) {
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put(name, value);
	}

	protected <T> T getRequestValue(Class<T> clazz) {
		Object value = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestMap().get(clazz);
		return clazz.cast(value);
	}

	protected EmpDto toDto() {
		EmpDto emp = new EmpDto();
		BeanUtil.copy(this, emp);
		return emp;
	}

}
