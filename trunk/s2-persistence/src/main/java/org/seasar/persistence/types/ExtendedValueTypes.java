/**
 * 
 */
package org.seasar.persistence.types;

import org.seasar.extension.jdbc.ValueType;
import org.seasar.extension.jdbc.types.ValueTypes;

/**
 * {@link ValueTypes}を拡張するクラスです。
 * 
 * @author higa
 * 
 */
public abstract class ExtendedValueTypes extends ValueTypes {

	protected ExtendedValueTypes() {
	}

	public static ValueType getValueType(Class clazz) {
		for (Class c = clazz; c != null; c = c.getSuperclass()) {
			ValueType valueType = getValueType0(c);
			if (valueType != null) {
				return valueType;
			}
		}
		return OBJECT;
	}

	protected static ValueType getValueType0(Class clazz) {
		if (clazz.isEnum()) {
			return new EnumType((Class<? extends Enum>) clazz);
		}
		return ValueTypes.getValueType0(clazz);
	}
}
