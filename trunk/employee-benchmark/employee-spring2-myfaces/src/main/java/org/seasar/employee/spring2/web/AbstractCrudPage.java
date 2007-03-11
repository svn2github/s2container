package org.seasar.employee.spring2.web;

public abstract class AbstractCrudPage {

	private Integer crudType;

	public AbstractCrudPage() {
	}

	public Integer getCrudType() {
		return this.crudType != null ? this.crudType : 0;
	}

	public void setCrudType(Integer type) {
		this.crudType = type;
	}

	public boolean isCreate() {
		return getCrudType() == CrudType.CREATE;
	}

	public boolean isRead() {
		return getCrudType() == CrudType.READ;
	}

	public boolean isUpdate() {
		return getCrudType() == CrudType.UPDATE;
	}

	public boolean isDelete() {
		return getCrudType() == CrudType.DELETE;
	}

}
