package org.seasar.employee.spring2.util;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;

public class BeanUtil {

	static {
		ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
	}

	private BeanUtil() {
	}

	public static void copy(Object src, Object dest) {
		try {
			BeanUtils.copyProperties(dest, src);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
}
