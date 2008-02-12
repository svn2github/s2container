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
package org.seasar3.util;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;

import org.seasar3.exception.CannotCompileRuntimeException;
import org.seasar3.exception.NotFoundRuntimeException;

/**
 * Utility for compile time class.
 * 
 * @author higa
 * @version 3.0
 */

public final class CtClassUtil {

    private CtClassUtil() {
    }

    /**
     * Creates the compile time class.
     * 
     * @param classPool
     *            the class pool.
     * @param originalClassName
     *            the original class name.
     * @param newClassName
     *            the new class name.
     * @return the compile time class.
     */
    public static CtClass create(ClassPool classPool, String originalClassName,
            String newClassName) {
        CtClass parentCtClass = ClassPoolUtil.get(classPool, originalClassName);
        return classPool.makeClass(newClassName, parentCtClass);
    }

    /**
     * Sets the super class.
     * 
     * @param classPool
     *            the class pool.
     * @param ctClass
     *            the compile time class.
     * @param superClassName
     *            the super class name.
     * @throws CannotCompileRuntimeException
     *             if CannotCompileException occurred.
     */
    public static void setSuperclass(ClassPool classPool, CtClass ctClass,
            String superClassName) {
        try {
            ctClass.setSuperclass(ClassPoolUtil.get(classPool, superClassName));
        } catch (CannotCompileException e) {
            throw new CannotCompileRuntimeException(e);
        }
    }

    /**
     * Adds the compile time constructor.
     * 
     * @param ctClass
     *            the compile time class.
     * @param ctConstructor
     *            the compile time constructor.
     * @throws CannotCompileRuntimeException
     *             if CannotCompileException occurred.
     */
    public static void addConstructor(CtClass ctClass,
            CtConstructor ctConstructor) {
        try {
            ctClass.addConstructor(ctConstructor);
        } catch (CannotCompileException e) {
            throw new CannotCompileRuntimeException(e);
        }
    }

    /**
     * Adds the compile time method.
     * 
     * @param ctClass
     *            the compile time class.
     * @param ctMethod
     *            the compile time method.
     * @throws CannotCompileRuntimeException
     *             if CannotCompileException occurred.
     */
    public static void addMethod(CtClass ctClass, CtMethod ctMethod) {
        try {
            ctClass.addMethod(ctMethod);
        } catch (CannotCompileException e) {
            throw new CannotCompileRuntimeException(e);
        }
    }

    /**
     * Adds the compile time field.
     * 
     * @param ctClass
     *            the compile time class.
     * @param ctField
     *            the compile time field.
     * @throws CannotCompileRuntimeException
     *             if CannotCompileException occurred.
     */
    public static void addField(CtClass ctClass, CtField ctField) {
        try {
            ctClass.addField(ctField);
        } catch (CannotCompileException e) {
            throw new CannotCompileRuntimeException(e);
        }
    }

    /**
     * Returns the declared compile time method.
     * 
     * @param ctClass
     *            the compile time class.
     * @param methodName
     *            the method name.
     * @param parameterClasses
     *            the array of parameter classes.
     * @return the declared compile time method.
     * @throws NotFoundRuntimeException
     *             if NotFoundException occurred.
     */
    public static CtMethod getDeclaredMethod(CtClass ctClass,
            String methodName, CtClass[] parameterClasses) {
        try {
            return ctClass.getDeclaredMethod(methodName, parameterClasses);
        } catch (NotFoundException e) {
            throw new NotFoundRuntimeException(methodName, e);
        }
    }

    /**
     * Converts the compile time class to the class.
     * 
     * @param ctClass
     *            the compile time class.
     * @return the class.
     * @throws CannotCompileRuntimeException
     *             if CannotCompileException occurred.
     */
    public static Class<?> toClass(CtClass ctClass) {
        try {
            return ctClass.toClass();
        } catch (CannotCompileException e) {
            throw new CannotCompileRuntimeException(e);
        }
    }
}