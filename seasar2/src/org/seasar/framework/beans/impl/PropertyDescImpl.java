/*
 * Copyright 2004-2005 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.framework.beans.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.Time;
import java.sql.Timestamp;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.IllegalPropertyRuntimeException;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.exception.EmptyRuntimeException;
import org.seasar.framework.util.BooleanConversionUtil;
import org.seasar.framework.util.ConstructorUtil;
import org.seasar.framework.util.DateConversionUtil;
import org.seasar.framework.util.MethodUtil;
import org.seasar.framework.util.NumberConversionUtil;
import org.seasar.framework.util.SqlDateConversionUtil;
import org.seasar.framework.util.TimeConversionUtil;
import org.seasar.framework.util.TimestampConversionUtil;

public final class PropertyDescImpl implements PropertyDesc {

	private String propertyName_;
	private Class propertyType_;
	private Method readMethod_;
	private Method writeMethod_;
	private BeanDesc beanDesc_;
	private Constructor stringConstructor_;
	
	public PropertyDescImpl(String propertyName, Class propertyType,
			Method readMethod, Method writeMethod, BeanDesc beanDesc) {
				
		if (propertyName == null) {
			throw new EmptyRuntimeException("propertyName");
		}
		if (propertyType == null) {
			throw new EmptyRuntimeException("propertyType");
		}
		
		propertyName_ = propertyName;
		propertyType_ = propertyType;
		readMethod_ = readMethod;
		writeMethod_ = writeMethod;
		beanDesc_ = beanDesc;
		setupStringConstructor();
	}
	
	private void setupStringConstructor() {
		Constructor[] cons = propertyType_.getConstructors();
		for (int i = 0; i < cons.length; ++i) {
			Constructor con = cons[i];
			if (con.getParameterTypes().length == 1 && con.getParameterTypes()[0].equals(String.class)) {
				stringConstructor_ = con;
				break;
			}
		}
	}
	
	public final String getPropertyName() {
		return propertyName_;
	}

	public final Class getPropertyType() {
		return propertyType_;
	}
	
	public final Method getReadMethod() {
		return readMethod_;
	}
	
	public final void setReadMethod(Method readMethod) {
		readMethod_ = readMethod;
	}
	
	public final boolean hasReadMethod() {
		return readMethod_ != null;
	}
	
	public final Method getWriteMethod() {
		return writeMethod_;
	}
	
	public final void setWriteMethod(Method writeMethod) {
		writeMethod_ = writeMethod;
	}
	
	public final boolean hasWriteMethod() {
		return writeMethod_ != null;
	}
	
	public final Object getValue(Object target) {
		return MethodUtil.invoke(readMethod_, target, null);
	}
	
	public final void setValue(Object target, Object value) {
		try {
			MethodUtil.invoke(writeMethod_, target, new Object[]{
				convertIfNeed(value)});
		} catch (Throwable t) {
			throw new IllegalPropertyRuntimeException(
					beanDesc_.getBeanClass(), propertyName_, t);
		}
	}
	
	public final BeanDesc getBeanDesc() {
		return beanDesc_;
	}
	
	public final String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("propertyName=");
		buf.append(propertyName_);
		buf.append(",propertyType=");
		buf.append(propertyType_.getName());
		buf.append(",readMethod=");
		buf.append(readMethod_ != null ?readMethod_.getName() : "null");
		buf.append(",writeMethod=");
		buf.append(writeMethod_ != null ?writeMethod_.getName() : "null");
		return buf.toString();
	}
	
	public Object convertIfNeed(Object arg) {
		if (propertyType_.isPrimitive()) {
			return convertPrimitiveWrapper(arg);
		} else if (Number.class.isAssignableFrom(propertyType_)) {
			return convertNumber(arg);
		} else if (java.util.Date.class.isAssignableFrom(propertyType_)) {
			return convertDate(arg);
		} else if (Boolean.class.isAssignableFrom(propertyType_)) {
			return BooleanConversionUtil.toBoolean(arg);
		} else if (arg instanceof String && !String.class.equals(propertyType_)) {
			return convertWithStringConstructor(arg);
		}
		return arg;
	}

	private Object convertPrimitiveWrapper(Object arg) {
		return NumberConversionUtil.convertPrimitiveWrapper(propertyType_, arg);
	}
	
	private Object convertNumber(Object arg) {
		return NumberConversionUtil.convertNumber(propertyType_, arg);
	}
	
	private Object convertDate(Object arg) {
		if (propertyType_ == java.util.Date.class) {
			return DateConversionUtil.toDate(arg);
		} else if (propertyType_ == Timestamp.class) {
			return TimestampConversionUtil.toTimestamp(arg);
		} else if (propertyType_ == java.sql.Date.class) {
			return SqlDateConversionUtil.toDate(arg);
		} else if (propertyType_ == Time.class) {
			return TimeConversionUtil.toTime(arg);
		}
		return arg;
	}
	
	private Object convertWithStringConstructor(Object arg) {
		if (stringConstructor_ == null || arg == null) {
			return arg;
		}
		return ConstructorUtil.newInstance(stringConstructor_, new Object[]{arg});
	}
	
	
}
