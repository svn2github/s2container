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
package org.seasar.extension.dxo.command.impl;

import java.lang.reflect.Method;
import java.util.Map;

import org.seasar.extension.dxo.util.DxoUtil;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.util.OgnlUtil;

/**
 * @author koichik
 * 
 */
public class BeanToMapDxoCommand extends AbstractDxoCommand {

    protected Object parsedExpression;

    public BeanToMapDxoCommand(final Method method) {
        this(method, null);
    }

    public BeanToMapDxoCommand(final Method method, final String expression) {
        super(method);
        if (expression != null) {
            parsedExpression = DxoUtil.parseMap(expression);
        }
    }

    protected Object convertScalar(final Object source) {
        if (parsedExpression != null) {
            return OgnlUtil.getValue(parsedExpression, source);
        }
        final String expression = createConversionRule(source.getClass());
        return OgnlUtil.getValue(DxoUtil.parseMap(expression), source);
    }

    protected void convertScalar(final Object source, final Object dest) {
        final Map srcMap = (Map) convertScalar(source);
        final Map destMap = (Map) dest;
        destMap.clear();
        destMap.putAll(srcMap);
    }

    protected Class getDestElementType() {
        return Map.class;
    }

    protected String createConversionRule(final Class sourceType) {
        final StringBuffer buf = new StringBuffer(100);
        final BeanDesc beanDesc = BeanDescFactory.getBeanDesc(sourceType);
        final int propertySize = beanDesc.getPropertyDescSize();
        for (int i = 0; i < propertySize; ++i) {
            final PropertyDesc propertyDesc = beanDesc.getPropertyDesc(i);
            if (propertyDesc.hasReadMethod()) {
                final String propertyName = propertyDesc.getPropertyName();
                buf.append("'").append(propertyName).append("': ").append(
                        propertyName).append(", ");
            }
        }
        if (propertySize > 0) {
            buf.setLength(buf.length() - 2);
        }
        return new String(buf);
    }

}
