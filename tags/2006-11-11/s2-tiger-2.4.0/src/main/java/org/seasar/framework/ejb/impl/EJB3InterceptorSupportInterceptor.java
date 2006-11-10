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
package org.seasar.framework.ejb.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.interceptor.InvocationContext;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.FieldUtil;
import org.seasar.framework.util.MethodUtil;

/**
 * @author koichik
 * 
 */
public class EJB3InterceptorSupportInterceptor implements MethodInterceptor {
    protected Class<?> interceptorClass;

    protected Method interceptorMethod;

    protected Field interceptorField;

    public EJB3InterceptorSupportInterceptor(final Class<?> interceptorClass,
            final Method interceptorMethod) {
        this.interceptorClass = interceptorClass;
        this.interceptorMethod = interceptorMethod;
        this.interceptorMethod.setAccessible(true);
    }

    public Object invoke(final MethodInvocation invocation) throws Throwable {
        if (interceptorField == null) {
            final Class<?> targetClass = invocation.getThis().getClass();
            final String fieldName = EJB3InterceptorSupportInterType
                    .getFieldName(interceptorClass);
            interceptorField = ClassUtil.getDeclaredField(targetClass,
                    fieldName);
            interceptorField.setAccessible(true);
        }
        final Object interceptor = FieldUtil.get(interceptorField, invocation
                .getThis());
        final InvocationContext context = new InvocationContextImpl(invocation);
        return MethodUtil.invoke(interceptorMethod, interceptor,
                new Object[] { context });
    }
}
