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
package org.seasar3.util;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;
import javassist.NotFoundException;

import org.seasar3.exception.CannotCompileRuntimeException;
import org.seasar3.exception.NotFoundRuntimeException;

/**
 * Utility for <code>CtClass</code>
 * 
 * @author higa
 * @version 3.0
 */

public final class CtClassUtil {

    private CtClassUtil() {
    }

    /**
     * Creates <code>CtClass</code>.
     * 
     * @param classPool
     * @param originalClassName
     * @param newClassName
     * @return <code>CtClass</code>
     */
    public static CtClass create(ClassPool classPool, String originalClassName,
            String newClassName) {
        CtClass ctClass = ClassPoolUtil.getAndRename(classPool,
                originalClassName, newClassName);
        setSuperclass(classPool, ctClass, originalClassName);
        return ctClass;
    }

    /**
     * Sets super class.
     * 
     * @param classPool
     * @param ctClass
     * @param originalClassName
     * @throws CannotCompileRuntimeException
     *             if CannotCompileException occurred.
     */
    public static void setSuperclass(ClassPool classPool, CtClass ctClass,
            String originalClassName) {
        try {
            ctClass.setSuperclass(ClassPoolUtil.get(classPool,
                    originalClassName));
        } catch (CannotCompileException e) {
            throw new CannotCompileRuntimeException(e);
        }
    }

    /**
     * Adds constructor.
     * 
     * @param ctClass
     * @param ctConstructor
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
     * Adds method.
     * 
     * @param ctClass
     * @param ctMethod
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
     * Gets declared method.
     * 
     * @param ctClass
     * @param methodName
     * @param parameterTypes
     * @return <code>CtMethod</code>
     * @throws NotFoundRuntimeException
     *             if NotFoundException occurred.
     */
    public static CtMethod getDeclaredMethod(CtClass ctClass,
            String methodName, CtClass[] parameterTypes) {
        try {
            return ctClass.getDeclaredMethod(methodName, parameterTypes);
        } catch (NotFoundException e) {
            throw new NotFoundRuntimeException(methodName, e);
        }
    }

    /**
     * Converts <code>CtClass</code> to {@link Class}.
     * 
     * @param ctClass
     * @return {@link Class}
     * @throws CannotCompileRuntimeException
     *             if CannotCompileException occurred.
     */
    public static Class toClass(CtClass ctClass) {
        try {
            return ctClass.toClass();
        } catch (CannotCompileException e) {
            throw new CannotCompileRuntimeException(e);
        }
    }
}