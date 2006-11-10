/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
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
package org.seasar.extension.dxo.annotation.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.seasar.extension.dxo.DxoConstants;
import org.seasar.extension.dxo.annotation.AnnotationReader;
import org.seasar.extension.dxo.converter.Converter;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.beans.util.BeanUtil;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.util.ConstantAnnotationUtil;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.FieldUtil;

/**
 * @author Satoshi Kimura
 * @author koichik
 */
public class ConstantAnnotationReader implements AnnotationReader {

    protected S2Container container;

    protected Map convertersCache = Collections.synchronizedMap(new HashMap());

    public ConstantAnnotationReader(final S2Container container) {
        this.container = container.getRoot();
    }

    public String getDatePattern(final Class dxoClass, final Method method) {
        return getDatePattern(dxoClass, method, DxoConstants.DATE_PATTERN);
    }

    public String getTimePattern(final Class dxoClass, final Method method) {
        return getDatePattern(dxoClass, method, DxoConstants.TIME_PATTERN);
    }

    public String getTimestampPattern(final Class dxoClass, final Method method) {
        return getDatePattern(dxoClass, method, DxoConstants.TIMESTAMP_PATTERN);
    }

    public String getDatePattern(final Class dxoClass, final Method method,
            final String annotation) {
        final BeanDesc dxoBeanDesc = BeanDescFactory.getBeanDesc(dxoClass);
        String fieldName = getConstantAnnotationName(method, annotation);
        if (dxoBeanDesc.hasField(fieldName)) {
            return (String) dxoBeanDesc.getFieldValue(fieldName, null);
        }
        fieldName = method.getName() + "_" + annotation;
        if (dxoBeanDesc.hasField(fieldName)) {
            return (String) dxoBeanDesc.getFieldValue(fieldName, null);
        }
        fieldName = annotation;
        if (dxoBeanDesc.hasField(fieldName)) {
            return (String) dxoBeanDesc.getFieldValue(fieldName, null);
        }
        return null;
    }

    public String getConversionRule(final Class dxoClass, final Method method) {
        final BeanDesc dxoBeanDesc = BeanDescFactory.getBeanDesc(dxoClass);
        String fieldName = getConstantAnnotationName(method,
                DxoConstants.CONVERSION_RULE);
        if (dxoBeanDesc.hasField(fieldName)) {
            return (String) dxoBeanDesc.getFieldValue(fieldName, null);
        }
        fieldName = method.getName() + "_" + DxoConstants.CONVERSION_RULE;
        if (dxoBeanDesc.hasField(fieldName)) {
            return (String) dxoBeanDesc.getFieldValue(fieldName, null);
        }
        return null;
    }

    public Map getConverters(final Class destClass) {
        final Map converters = (Map) convertersCache.get(destClass);
        if (converters != null) {
            return converters;
        }
        return createConverters(destClass);
    }

    protected Map createConverters(final Class destClass) {
        final Map converters = new HashMap();
        final BeanDesc beanDesc = BeanDescFactory.getBeanDesc(destClass);
        for (int i = 0; i < beanDesc.getFieldSize(); ++i) {
            final Field field = beanDesc.getField(i);
            if (!ConstantAnnotationUtil.isConstantAnnotation(field)) {
                continue;
            }
            if (!field.getName().endsWith("DxoConverter")) {
                continue;
            }
            final String fieldName = field.getName();
            final int index = fieldName.lastIndexOf("_");
            final String propertyName = fieldName.substring(0, index);
            if (!beanDesc.hasPropertyDesc(propertyName)) {
                continue;
            }
            final String converterName = fieldName.substring(index + 1);
            if (!container.hasComponentDef(converterName)) {
                continue;
            }
            final Converter converter = (Converter) container
                    .getComponent(converterName);
            final String s = (String) FieldUtil.get(field, null);
            final Map props = ConstantAnnotationUtil.convertExpressionToMap(s);
            BeanUtil.copyProperties(props, converter);
            converters.put(propertyName, converter);
        }
        convertersCache.put(destClass, converters);
        return converters;
    }

    protected String getConstantAnnotationName(final Method method,
            final String suffix) {
        final StringBuffer buf = new StringBuffer(100).append(method.getName());
        final Class[] parameterTypes = method.getParameterTypes();
        for (int i = 0; i < parameterTypes.length; i++) {
            buf.append("_").append(getShortClassName(parameterTypes[i]));
        }
        buf.append("_").append(suffix);
        return new String(buf);
    }

    protected String getShortClassName(final Class clazz) {
        if (clazz.isArray()) {
            return ClassUtil.getShortClassName(clazz.getComponentType()) + "$";
        }
        return ClassUtil.getShortClassName(clazz);
    }

}