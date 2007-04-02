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

import java.lang.reflect.Method;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;

import org.seasar3.util.ClassPoolUtil;
import org.seasar3.util.CtClassUtil;
import org.seasar3.util.CtFieldUtil;
import org.seasar3.util.CtMethodUtil;
import org.seasar3.util.CtNewConstructorUtil;
import org.seasar3.util.CtNewMethodUtil;

/**
 * A base class to generate {@link Class}.
 * 
 * @author koichik
 * @author higa
 * @param <T>
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

    /**
     * Gets <code>ClassPool</code>.
     * 
     * @return
     */
    public ClassPool getClassPool() {
        return classPool;
    }

    /**
     * Gets <code>CtClass</code>.
     * 
     * @return
     */
    public CtClass getCtClass() {
        return ctClass;
    }

    /**
     * Converts {@link Class} to <code>CtClass</code>
     * 
     * @param clazz
     * @return <code>CtClass</code>
     * @see ClassPoolUtil#toCtClass(ClassPool, Class)
     */
    public CtClass toCtClass(Class<?> clazz) {
        return ClassPoolUtil.toCtClass(classPool, clazz);
    }

    /**
     * Converts array of {@link Class} to array of <code>CtClass</code>
     * 
     * @param classes
     * @return array of <code>CtClass</code>
     * @see ClassPoolUtil#toCtClass(ClassPool, Class[])
     */
    public CtClass[] toCtClassArray(Class[] classes) {
        return ClassPoolUtil.toCtClassArray(classPool, classes);
    }

    /**
     * Sets interface.
     * 
     * @param interfaceClass
     */
    public void setInterface(Class interfaceClass) {
        ctClass.setInterfaces(new CtClass[] { toCtClass(interfaceClass) });
    }

    /**
     * Sets interfaces.
     * 
     * @param interfaces
     */
    public void setInterfaces(Class[] interfaces) {
        ctClass.setInterfaces(toCtClassArray(interfaces));
    }

    /**
     * Creates default constructor.
     * 
     * @return <code>CtConstructor</code>
     */
    public CtConstructor createDefaultConstructor() {
        CtConstructor ctConstructor = CtNewConstructorUtil
                .defaultConstructor(ctClass);
        CtClassUtil.addConstructor(ctClass, ctConstructor);
        return ctConstructor;
    }

    /**
     * Creates constructor.
     * 
     * @param parameterClasses
     * @param exceptionClasses
     * @return
     */
    public CtConstructor createConstructor(Class[] parameterClasses,
            Class[] exceptionClasses) {
        CtConstructor ctConstructor = CtNewConstructorUtil.make(
                toCtClassArray(parameterClasses),
                toCtClassArray(exceptionClasses), ctClass);
        CtClassUtil.addConstructor(ctClass, ctConstructor);
        return ctConstructor;
    }

    /**
     * Gets declared method.
     * 
     * @param method
     * @return
     * @see {@link #getDeclaredMethod(String, CtClass[])}
     */
    public CtMethod getDeclaredMethod(Method method) {
        return getDeclaredMethod(method.getName(), toCtClassArray(method
                .getParameterTypes()));
    }

    /**
     * Gets declared method.
     * 
     * @param methodName
     * @param parameterClasses
     * @return
     * @see CtClassUtil#getDeclaredMethod(CtClass, String, CtClass[])
     */
    public CtMethod getDeclaredMethod(String methodName,
            CtClass[] parameterClasses) {
        return CtClassUtil.getDeclaredMethod(ctClass, methodName,
                parameterClasses);
    }

    /**
     * Creates <code>CtMethod</code>.
     * 
     * @param src
     * @return
     */
    public CtMethod createMethod(String src) {
        CtMethod ctMethod = CtNewMethodUtil.make(src, ctClass);
        CtClassUtil.addMethod(ctClass, ctMethod);
        return ctMethod;
    }

    /**
     * Creates <code>CtField</code>.
     * 
     * @param src
     * @return
     */
    public CtField createField(String src) {
        CtField ctField = CtFieldUtil.make(src, ctClass);
        CtClassUtil.addField(ctClass, ctField);
        return ctField;
    }

    /**
     * Sets method body.
     * 
     * @param ctMethod
     * @param body
     */
    public void setMethodBody(CtMethod ctMethod, String body) {
        CtMethodUtil.setBody(ctMethod, body);
    }

    /**
     * Generates {@link Class}.
     * 
     * @return generated class
     */
    public Class generate() {
        Class clazz = CtClassUtil.toClass(ctClass);
        ctClass.detach();
        return clazz;
    }
}