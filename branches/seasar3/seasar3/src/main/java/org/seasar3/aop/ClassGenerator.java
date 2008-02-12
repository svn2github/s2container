/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
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
import java.lang.reflect.Modifier;

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
 */
public class ClassGenerator {

    /**
     * The class pool.
     */
    protected ClassPool classPool;

    /**
     * The compile time class.
     */
    protected CtClass ctClass;

    /**
     * Constructor.
     * 
     * @param classPool
     *            the class pool.
     * @param ctClass
     *            the compile time class.
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
     * Returns the class pool.
     * 
     * @return the class pool.
     */
    public ClassPool getClassPool() {
        return classPool;
    }

    /**
     * Returns the compile time class.
     * 
     * @return the compile time class.
     */
    public CtClass getCtClass() {
        return ctClass;
    }

    /**
     * Converts class to compile time class.
     * 
     * @param clazz
     *            the class
     * @return the compile time class.
     */
    public CtClass toCtClass(Class<?> clazz) {
        return ClassPoolUtil.toCtClass(classPool, clazz);
    }

    /**
     * Converts array of classes to array of compile time classes.
     * 
     * @param classes
     *            array of classes.
     * @return array of compile time classes.
     */
    public CtClass[] toCtClassArray(Class<?>[] classes) {
        return ClassPoolUtil.toCtClassArray(classPool, classes);
    }

    /**
     * Sets interface class.
     * 
     * @param interfaceClass
     *            interface class.
     */
    public void setInterface(Class<?> interfaceClass) {
        ctClass.setInterfaces(new CtClass[] { toCtClass(interfaceClass) });
    }

    /**
     * Sets array of interface classes.
     * 
     * @param interfaces
     *            array of interface classes.
     */
    public void setInterfaces(Class<?>[] interfaces) {
        ctClass.setInterfaces(toCtClassArray(interfaces));
    }

    /**
     * Creates default constructor.
     * 
     * @return default constructor.
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
     *            array of parameter classes.
     * @param exceptionClasses
     *            array of exception classes.
     * @return constructor.
     */
    public CtConstructor createConstructor(Class<?>[] parameterClasses,
            Class<?>[] exceptionClasses) {
        CtConstructor ctConstructor = CtNewConstructorUtil.make(
                toCtClassArray(parameterClasses),
                toCtClassArray(exceptionClasses), ctClass);
        CtClassUtil.addConstructor(ctClass, ctConstructor);
        return ctConstructor;
    }

    /**
     * Returns the declared method.
     * 
     * @param method
     *            the method.
     * @return the declared method.
     */
    public CtMethod getDeclaredMethod(Method method) {
        return getDeclaredMethod(method.getName(), toCtClassArray(method
                .getParameterTypes()));
    }

    /**
     * Returns the declared method.
     * 
     * @param methodName
     *            the method name.
     * @param parameterClasses
     *            the parameter classes.
     * @return the declared method.
     */
    public CtMethod getDeclaredMethod(String methodName,
            CtClass[] parameterClasses) {
        return CtClassUtil.getDeclaredMethod(ctClass, methodName,
                parameterClasses);
    }

    /**
     * Creates the compile time method.
     * 
     * @param src
     *            the source.
     * @return the compile time method.
     */
    public CtMethod createMethod(String src) {
        CtMethod ctMethod = CtNewMethodUtil.make(src, ctClass);
        CtClassUtil.addMethod(ctClass, ctMethod);
        return ctMethod;
    }

    /**
     * Creates the compile time method.
     * 
     * @param method
     *            the method.
     * @param src
     *            the source.
     * @return the compile time method.
     */
    public CtMethod createMethod(Method method, String src) {
        CtMethod ctMethod = CtNewMethodUtil.make(method.getModifiers()
                & ~(Modifier.ABSTRACT | Modifier.NATIVE), toCtClass(method
                .getReturnType()), method.getName(), toCtClassArray(method
                .getParameterTypes()), toCtClassArray(method
                .getExceptionTypes()), src, ctClass);
        CtClassUtil.addMethod(ctClass, ctMethod);
        return ctMethod;
    }

    /**
     * Creates the compile time field.
     * 
     * @param src
     *            the source.
     * @return the compile time field.
     */
    public CtField createField(String src) {
        CtField ctField = CtFieldUtil.make(src, ctClass);
        CtClassUtil.addField(ctClass, ctField);
        return ctField;
    }

    /**
     * Sets the method body.
     * 
     * @param ctMethod
     *            the compile time method.
     * @param body
     *            the body.
     */
    public void setMethodBody(CtMethod ctMethod, String body) {
        CtMethodUtil.setBody(ctMethod, body);
    }

    /**
     * Generates class.
     * 
     * @return generated class
     */
    public Class<?> generate() {
        Class<?> clazz = CtClassUtil.toClass(ctClass);
        ctClass.detach();
        return clazz;
    }
}