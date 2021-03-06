/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
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

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptors;
import javax.interceptor.InvocationContext;

import org.seasar.framework.ejb.EJB3BusinessMethodDesc;
import org.seasar.framework.ejb.EJB3Desc;
import org.seasar.framework.ejb.EJB3InterceptorDesc;
import org.seasar.framework.ejb.SEJBException;

/**
 * @author koichik
 * 
 */
public class EJB3DescImpl implements EJB3Desc {

    protected Class<?> beanClass;

    protected String beanClassName;

    protected boolean stateless;

    protected boolean stateful;

    protected String name;

    protected List<Class<?>> businessInterfaces = new ArrayList<Class<?>>();

    protected boolean cmt = true;

    protected List<EJB3InterceptorDesc> interceptors = new ArrayList<EJB3InterceptorDesc>();

    protected List<EJB3BusinessMethodDesc> businessMethods = new ArrayList<EJB3BusinessMethodDesc>();

    protected LinkedList<Method> aroundInvokeMethods = new LinkedList<Method>();

    protected LinkedList<Method> postConstructMethods = new LinkedList<Method>();

    public EJB3DescImpl(final Class<?> beanClass) {
        this.beanClass = beanClass;
        this.beanClassName = beanClass.getName();
        introspection();
    }

    public boolean isEJB3() {
        return stateless || stateful;
    }

    public boolean isStateless() {
        return stateless;
    }

    public boolean isStateful() {
        return stateful;
    }

    public String getName() {
        return name;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public List<Class<?>> getBusinessInterfaces() {
        return businessInterfaces;
    }

    public boolean isCMT() {
        return cmt;
    }

    public List<EJB3InterceptorDesc> getInterceptors() {
        return interceptors;
    }

    public EJB3BusinessMethodDesc getBusinessMethod(final Method method) {
        for (final EJB3BusinessMethodDesc businessMethodDesc : businessMethods) {
            final Method businessMethod = businessMethodDesc.getMethod();
            if (!method.getName().equals(businessMethod.getName())) {
                continue;
            }
            if (!Arrays.equals(method.getParameterTypes(), businessMethod
                    .getParameterTypes())) {
                continue;
            }
            return businessMethodDesc;
        }
        return null;
    }

    public List<EJB3BusinessMethodDesc> getBusinessMethods() {
        return businessMethods;
    }

    public List<Method> getAroundInvokeMethods() {
        return aroundInvokeMethods;
    }

    public List<Method> getPostConstructMethods() {
        return postConstructMethods;
    }

    protected void introspection() {
        final int modifiers = beanClass.getModifiers();
        if (beanClass.isInterface() || Modifier.isAbstract(modifiers)
                || Modifier.isFinal(modifiers) || !Modifier.isPublic(modifiers)) {
            return;
        }

        final Stateless slsb = beanClass.getAnnotation(Stateless.class);
        if (slsb != null) {
            stateless = true;
            name = slsb.name();
        }
        final Stateful sfsb = beanClass.getAnnotation(Stateful.class);
        if (sfsb != null) {
            stateful = true;
            name = sfsb.name();
        }
        if (!stateless && !stateful) {
            return;
        }
        if (stateless && stateful) {
            throw new SEJBException("ESSR0400", beanClassName);
        }

        detectLocalBusinessInterfaces();
        detectRemoteBusinessInterfaces();
        if (businessInterfaces.isEmpty()) {
            detectImplicitBusinessInterfaces();
            if (businessInterfaces.isEmpty()) {
                throw new SEJBException("ESSR0401", beanClassName);
            }
            if (businessInterfaces.size() > 1) {
                throw new SEJBException("ESSR0402", beanClassName);
            }
        }

        detectTransactionManagementType();
        detectInterceptors();
        detectBusinessMethods();
        detectAroundInvokeMethods();
        detectPostConstructMethods();
    }

    protected void detectLocalBusinessInterfaces() {
        final Local local = beanClass.getAnnotation(Local.class);
        if (local != null) {
            for (final Class<?> type : local.value()) {
                if (isBusinessInterface(type)) {
                    businessInterfaces.add(type);
                }
            }
            return;
        }

        for (final Class<?> type : beanClass.getInterfaces()) {
            final Local annotation = type.getAnnotation(Local.class);
            if (annotation != null) {
                businessInterfaces.add(type);
            }
        }
    }

    protected void detectRemoteBusinessInterfaces() {
        final Remote remote = beanClass.getAnnotation(Remote.class);
        if (remote != null) {
            for (final Class<?> type : remote.value()) {
                if (isBusinessInterface(type)) {
                    businessInterfaces.add(type);
                }
            }
        }

        for (final Class<?> type : beanClass.getInterfaces()) {
            final Remote annotation = type.getAnnotation(Remote.class);
            if (annotation != null) {
                businessInterfaces.add(type);
            }
        }
    }

    protected void detectImplicitBusinessInterfaces() {
        for (final Class<?> type : beanClass.getInterfaces()) {
            if (isBusinessInterface(type)) {
                businessInterfaces.add(type);
            }
        }
    }

    protected void detectTransactionManagementType() {
        final TransactionManagement txManegement = beanClass
                .getAnnotation(TransactionManagement.class);
        if (txManegement != null) {
            final TransactionManagementType type = txManegement.value();
            if (TransactionManagementType.BEAN.equals(type)) {
                cmt = false;
                return;
            }
        }
    }

    protected void detectInterceptors() {
        final Interceptors annotation = beanClass
                .getAnnotation(Interceptors.class);
        if (annotation == null) {
            return;
        }

        for (Class<?> interceptor : annotation.value()) {
            interceptors.add(new EJB3InterceptorDescImpl(this, interceptor));
        }
    }

    protected void detectBusinessMethods() {
        for (final Method method : beanClass.getMethods()) {
            final int modifier = method.getModifiers();
            if (Modifier.isStatic(modifier) || Modifier.isFinal(modifier)) {
                continue;
            }
            if (!isBusinessMethod(method)) {
                continue;
            }
            businessMethods.add(new EJB3BusinessMethodDescImpl(this, method));
        }
    }

    public void detectAroundInvokeMethods() {
        final Set<String> methods = new HashSet<String>();
        for (Class<?> clazz = beanClass; clazz != Object.class; clazz = clazz
                .getSuperclass()) {
            for (final Method method : clazz.getDeclaredMethods()) {
                if (method.isBridge()) {
                    continue;
                }
                final AroundInvoke aroundInvoke = method
                        .getAnnotation(AroundInvoke.class);

                final int modifiers = method.getModifiers();
                if (Modifier.isStatic(modifiers) || Modifier.isFinal(modifiers)) {
                    if (aroundInvoke != null) {
                        throw new SEJBException("ESSR0409", "AroundInvoke",
                                beanClassName, method);
                    }
                    continue;
                }

                final Class[] paramTypes = method.getParameterTypes();
                if (paramTypes.length != 1
                        || paramTypes[0] != InvocationContext.class) {
                    if (aroundInvoke != null) {
                        throw new SEJBException("ESSR0409", "AroundInvoke",
                                beanClassName, method);
                    }
                    continue;
                }
                if (method.getReturnType() != Object.class) {
                    if (aroundInvoke != null) {
                        throw new SEJBException("ESSR0409", "AroundInvoke",
                                beanClassName, method);
                    }
                    continue;
                }

                final String methodName = method.getName();
                if (methods.contains(methodName)) {
                    continue;
                }
                methods.add(methodName);

                if (aroundInvoke == null) {
                    continue;
                }

                if (isBusinessMethod(method)) {
                    throw new SEJBException("ESSR0409", "AroundInvoke",
                            beanClassName, method);
                }

                aroundInvokeMethods.addFirst(method);
            }
        }
    }

    public void detectPostConstructMethods() {
        final Set<String> methods = new HashSet<String>();
        for (Class<?> clazz = beanClass; clazz != Object.class; clazz = clazz
                .getSuperclass()) {
            for (final Method method : clazz.getDeclaredMethods()) {
                if (method.isBridge()) {
                    continue;
                }
                final PostConstruct postConstruct = method
                        .getAnnotation(PostConstruct.class);

                final int modifiers = method.getModifiers();
                if (Modifier.isStatic(modifiers) || Modifier.isFinal(modifiers)) {
                    if (postConstruct != null) {
                        throw new SEJBException("ESSR0409", "PostConstruct",
                                beanClassName, method);
                    }
                    continue;
                }

                final Class[] paramTypes = method.getParameterTypes();
                if (paramTypes.length != 0) {
                    if (postConstruct != null) {
                        throw new SEJBException("ESSR0409", "PostConstruct",
                                beanClassName, method);
                    }
                    continue;
                }
                if (method.getReturnType() != void.class) {
                    if (postConstruct != null) {
                        throw new SEJBException("ESSR0409", "PostConstruct",
                                beanClassName, method);
                    }
                    continue;
                }

                final String methodName = method.getName();
                if (methods.contains(methodName)) {
                    continue;
                }
                methods.add(methodName);

                if (postConstruct == null) {
                    continue;
                }

                if (isBusinessMethod(method)) {
                    throw new SEJBException("ESSR0409", "PostConstruct", method);
                }

                postConstructMethods.addFirst(method);
            }
        }
    }

    protected boolean isBusinessInterface(final Class<?> type) {
        if (!type.isInterface()) {
            throw new SEJBException("ESSR0407", beanClassName, type.getName());
        }
        if (!type.isAssignableFrom(beanClass)) {
            throw new SEJBException("ESSR0408", beanClassName, type.getName());
        }
        if (Serializable.class.equals(type)) {
            return false;
        }
        if (Exception.class.equals(type)) {
            return false;
        }
        if (type.getName().startsWith("javax.ejb")) {
            return false;
        }
        return true;
    }

    protected boolean isBusinessMethod(final Method method) {
        for (final Class<?> type : businessInterfaces) {
            try {
                type.getDeclaredMethod(method.getName(), method
                        .getParameterTypes());
                return true;
            } catch (final NoSuchMethodException ignore) {
            }
        }
        return false;
    }
}
