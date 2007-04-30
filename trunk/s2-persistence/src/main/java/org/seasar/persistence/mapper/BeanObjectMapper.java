/**
 * 
 */
package org.seasar.persistence.mapper;

import java.util.HashMap;
import java.util.Map;

import org.seasar.framework.util.ClassUtil;
import org.seasar.persistence.ObjectMapper;
import org.seasar.persistence.PropertyMapper;

/**
 * 行データをBeanにマッピングする実装クラスです。
 * 
 * @author higa
 * 
 */
public class BeanObjectMapper implements ObjectMapper {

	protected Class beanClass;

	protected PropertyMapper[] propertyMappers;

	protected int[] idIndices;

	protected Map context;

	protected Object target;

	/**
	 * <code>BeanObjectMapper</code>を作成します。
	 * 
	 * @param beanClass
	 * @param propertyMappers
	 * @param idIndices
	 * @param context
	 */
	public BeanObjectMapper(Class beanClass, PropertyMapper[] propertyMappers,
			int[] idIndices, Map context) {
		this.beanClass = beanClass;
		this.propertyMappers = propertyMappers;
		this.idIndices = idIndices;
		this.context = context;
	}

	public void setValues(Object[] values) {
		Object key = null;
		if (idIndices.length > 0) {
			key = getKey(values);
			if (key != null) {
				target = getCache(key);
				if (target != null) {
					return;
				}
			} else {
				return;
			}
		}
		target = ClassUtil.newInstance(beanClass);
		for (PropertyMapper propertyMapper : propertyMappers) {
			propertyMapper.setValue(target, values);
		}
		if (key != null) {
			setupCache(key);
		}
	}

	public Object getTarget() {
		return target;
	}

	protected Object getKey(Object[] values) {
		if (idIndices.length == 1) {
			return values[idIndices[0]];
		} else {
			Object[] objs = new Object[idIndices.length];
			for (int i = 0; i < idIndices.length; i++) {
				objs[i] = values[idIndices[i]];
				if (objs[i] == null) {
					return null;
				}
			}
			return new KeyItems(objs);
		}
	}

	protected Object getCache(Object key) {
		Map m = (Map) context.get(beanClass.getName());
		if (m == null) {
			return null;
		}
		return m.get(key);
	}

	@SuppressWarnings("unchecked")
	protected void setupCache(Object key) {
		Map m = (Map) context.get(beanClass.getName());
		if (m == null) {
			m = new HashMap<Object, Object>();
			context.put(beanClass.getName(), m);
		}
		m.put(key, target);
	}
}