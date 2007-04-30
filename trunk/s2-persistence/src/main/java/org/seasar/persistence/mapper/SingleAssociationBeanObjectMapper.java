/**
 * 
 */
package org.seasar.persistence.mapper;

import java.lang.reflect.Field;
import java.util.Map;

import org.seasar.framework.util.FieldUtil;
import org.seasar.persistence.ObjectMapper;
import org.seasar.persistence.PropertyMapper;

/**
 * N:1関連のBeanをマッピングするクラスです。
 * 
 * @author higa
 * 
 */
public class SingleAssociationBeanObjectMapper extends BeanObjectMapper {

	protected ObjectMapper manySide;

	protected Field associationField;

	/**
	 * <code>SingleAssociationBeanObjectMapper</code>を作成します。
	 * 
	 * @param beanClass
	 * @param propertyMappers
	 * @param idIndices
	 * @param context
	 * @param manySide
	 * @param associationField
	 */
	public SingleAssociationBeanObjectMapper(Class beanClass,
			PropertyMapper[] propertyMappers, int[] idIndices, Map context,
			ObjectMapper manySide, Field associationField) {
		super(beanClass, propertyMappers, idIndices, context);
		this.manySide = manySide;
		this.associationField = associationField;
	}

	@Override
	public void setValues(Object[] values) {
		super.setValues(values);
		if (target != null) {
			FieldUtil.set(associationField, manySide.getTarget(), target);
		}
	}

}
