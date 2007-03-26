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
package org.seasar3.aop;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;
import javassist.CtNewConstructor;
import javassist.CtNewMethod;
import javassist.NotFoundException;

import org.seasar3.exception.CannotCompileRuntimeException;
import org.seasar3.exception.NotFoundRuntimeException;
import org.seasar3.util.ClassPoolUtil;

/**
 * A base class to generate {@link Class}.
 * 
 * @author koichik
 * @author higa
 */
public class ClassGenerator {

    protected ClassPool classPool;

    protected CtClass ctClass;

    /**
     * Creates new {@link ClassGenerator}.
     * 
     * @param classPool
     * @param ctClass
     */
    public ClassGenerator(ClassPool classPool, CtClass ctClass) {
        if (classPool == null) {
            throw new NullPointerException("classPool");
        }
        if (ctClass == null) {
            throw new NullPointerException("ctClass");
        }
        this.classPool = classPool;
        this.ctClass = ctClass;
    }

    protected CtClass toCtClass(Class type) {
        return ClassPoolUtil.toCtClass(classPool, type);
    }

    protected CtClass[] toCtClass(Class[] types) {
        return ClassPoolUtil.toCtClass(classPool, types);
    }

    protected void setInterface(Class interfaceType) {
        ctClass.setInterfaces(new CtClass[] { toCtClass(interfaceType) });
    }

    protected void setInterfaces(Class[] interfaces) {
        ctClass.setInterfaces(toCtClass(interfaces));
    }

    protected CtConstructor createDefaultConstructor() {
        try {
            CtConstructor ctConstructor = CtNewConstructor
                    .defaultConstructor(ctClass);
            ctClass.addConstructor(ctConstructor);
            return ctConstructor;
        } catch (final CannotCompileException e) {
            throw new CannotCompileRuntimeException(e);
        }
    }

    protected CtConstructor createConstructor(Constructor constructor) {
        return createConstructor(toCtClass(constructor.getParameterTypes()),
                toCtClass(constructor.getExceptionTypes()));
    }

    protected CtConstructor createConstructor(CtClass[] parameterTypes,
            CtClass[] exceptionTypes) {
        try {
            final CtConstructor ctConstructor = CtNewConstructor.make(
                    parameterTypes, exceptionTypes, ctClass);
            ctClass.addConstructor(ctConstructor);
            return ctConstructor;
        } catch (final CannotCompileException e) {
            throw new CannotCompileRuntimeException(e);
        }
    }

    protected CtMethod getDeclaredMethod(String name, CtClass[] argTypes) {
        try {
            return ctClass.getDeclaredMethod(name, argTypes);
        } catch (final NotFoundException e) {
            throw new NotFoundRuntimeException(name, e);
        }
    }

    protected CtMethod createMethod(final CtClass clazz, final String src) {
        try {
            final CtMethod ctMethod = CtNewMethod.make(src, clazz);
            clazz.addMethod(ctMethod);
            return ctMethod;
        } catch (final CannotCompileException e) {
            throw new CannotCompileRuntimeException(e);
        }
    }

    protected CtMethod createMethod(final CtClass clazz, final Method method,
            final String body) {
        return createMethod(clazz, method.getModifiers(), method
                .getReturnType(), method.getName(), method.getParameterTypes(),
                method.getExceptionTypes(), body);
    }

    protected CtMethod createMethod(final CtClass clazz, final int modifier,
            final Class returnType, final String methodName,
            final Class[] parameterTypes, final Class[] exceptionTypes,
            final String body) {
        try {
            final CtMethod ctMethod = CtNewMethod.make(modifier
                    & ~(Modifier.ABSTRACT | Modifier.NATIVE),
                    toCtClass(returnType), methodName,
                    toCtClass(parameterTypes), toCtClass(exceptionTypes), body,
                    clazz);
            clazz.addMethod(ctMethod);
            ctMethod.setBody(body);
            return ctMethod;
        } catch (final CannotCompileException e) {
            throw new CannotCompileRuntimeException(e);
        }
    }

    protected void setMethodBody(final CtMethod method, final String src) {
        try {
            method.setBody(src);
        } catch (final CannotCompileException e) {
            throw new CannotCompileRuntimeException(e);
        }
    }
}