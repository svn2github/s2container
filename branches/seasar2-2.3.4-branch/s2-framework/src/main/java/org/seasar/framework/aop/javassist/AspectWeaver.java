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
package org.seasar.framework.aop.javassist;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javassist.ClassPool;

import org.aopalliance.intercept.MethodInterceptor;
import org.seasar.framework.exception.NoSuchFieldRuntimeException;
import org.seasar.framework.message.MessageFormatter;
import org.seasar.framework.util.FieldUtil;
import org.seasar.framework.util.MethodUtil;

/**
 * @author koichik
 */
public class AspectWeaver {
    //constants
    public static final String PREFIX_ENHANCED_CLASS = "$$";
    public static final String SUFFIX_ENHANCED_CLASS = "$$EnhancedByS2AOP$$";
    public static final String SUFFIX_METHOD_INVOCATION_CLASS = "$$MethodInvocation$$";
    public static final String SUFFIX_INVOKE_SUPER_METHOD = "$$invokeSuperMethod$$";

    //static fields
    protected static final Set enhancedClassNames = Collections.synchronizedSet(new HashSet());

    //instance fields
    protected final Class targetClass;
    protected final Map parameters;
    protected final String enhancedClassName;
    protected final EnhancedClassGenerator enhancedClassGenerator;
    protected final List methodInvocationClassList = new ArrayList();
    protected Class enhancedClass;
    protected ClassPool classPool;

    public AspectWeaver(final Class targetClass, final Map parameters) {
        this.targetClass = targetClass;
        this.parameters = parameters;

        classPool = ClassPoolUtil.getClassPool();
        enhancedClassName = getEnhancedClassName();
        enhancedClassGenerator = new EnhancedClassGenerator(classPool, targetClass,
                enhancedClassName);
    }

    public void setInterceptors(final Method method, final MethodInterceptor[] interceptors) {
        final String methodInvocationClassName = getMethodInvocationClassName(method);
        final MethodInvocationClassGenerator methodInvocationGenerator = new MethodInvocationClassGenerator(
                classPool, methodInvocationClassName, enhancedClassName);

        final String invokeSuperMethodName = createInvokeSuperMethod(method);
        methodInvocationGenerator.createProceedMethod(method, invokeSuperMethodName);
        enhancedClassGenerator.createTargetMethod(method, methodInvocationClassName);

        final Class methodInvocationClass = methodInvocationGenerator.toClass(getClassLoader());
        setStaticField(methodInvocationClass, "method", method);
        setStaticField(methodInvocationClass, "interceptors", interceptors);
        setStaticField(methodInvocationClass, "parameters", parameters);
        methodInvocationClassList.add(methodInvocationClass);
    }

    public Class generateClass() {
        if (enhancedClass == null) {
            enhancedClass = enhancedClassGenerator.toClass(getClassLoader());

            for (int i = 0; i < methodInvocationClassList.size(); ++i) {
                final Class methodInvocationClass = (Class) methodInvocationClassList.get(i);
                setStaticField(methodInvocationClass, "targetClass", targetClass);
            }
        }

        return enhancedClass;
    }

    public String getEnhancedClassName() {
        final StringBuffer buf = new StringBuffer(200);
        final String targetClassName = targetClass.getName();
        final Package pkg = targetClass.getPackage();
        if (targetClassName.startsWith("java.") || (pkg != null && pkg.isSealed())) {
            buf.append(PREFIX_ENHANCED_CLASS);
        }
        buf.append(targetClassName).append(SUFFIX_ENHANCED_CLASS).append(
                Integer.toHexString(hashCode()));

        final int length = buf.length();
        for (int i = 0; enhancedClassNames.contains(new String(buf)); ++i) {
            buf.setLength(length);
            buf.append("_").append(i);
        }

        String name = new String(buf);
        enhancedClassNames.add(name);
        return name;
    }

    public String getMethodInvocationClassName(final Method method) {
        return enhancedClassName + SUFFIX_METHOD_INVOCATION_CLASS + method.getName()
                + methodInvocationClassList.size();
    }

    public String createInvokeSuperMethod(final Method method) {
        final String invokeSuperMethodName = method.getName() + SUFFIX_INVOKE_SUPER_METHOD;
        if (!MethodUtil.isAbstract(method)) {
            enhancedClassGenerator.createInvokeSuperMethod(method, invokeSuperMethodName);
        }
        return invokeSuperMethodName;
    }

    public void setStaticField(final Class clazz, final String name, final Object value) {
        try {
            final Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);
            FieldUtil.set(field, name, value);
            field.setAccessible(false);
        }
        catch (final NoSuchFieldException e) {
            throw new NoSuchFieldRuntimeException(enhancedClass, name, e);
        }
    }

    public ClassLoader getClassLoader() {
        ClassLoader cl = targetClass.getClassLoader();
        if (cl == null) {
            cl = Thread.currentThread().getContextClassLoader();
        }
        if (cl == null) {
            cl = getClass().getClassLoader();
        }
        if (cl == null) {
            throw new IllegalStateException(MessageFormatter.getMessage("ESSR0001",
                    new Object[] { "ClassLoader" }));
        }
        return cl;
    }
}