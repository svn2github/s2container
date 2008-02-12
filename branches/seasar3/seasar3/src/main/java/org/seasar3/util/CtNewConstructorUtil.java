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
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtNewConstructor;

import org.seasar3.exception.CannotCompileRuntimeException;

/**
 * Utility for compile time constructor.
 * 
 * @author higa
 * @version 3.0
 */

public final class CtNewConstructorUtil {

    private CtNewConstructorUtil() {
    }

    /**
     * Creates default constructor.
     * 
     * @param ctClass
     *            the compile time class.
     * @return the default compile time constructor.
     * @throws CannotCompileRuntimeException
     *             if CannotCompileException occurred.
     */
    public static CtConstructor defaultConstructor(CtClass ctClass) {
        try {
            return CtNewConstructor.defaultConstructor(ctClass);
        } catch (CannotCompileException e) {
            throw new CannotCompileRuntimeException(e);
        }
    }

    /**
     * Creates new compile time constructor.
     * 
     * @param parameterClasses
     *            the array of compile time parameter classes.
     * @param exceptionClasses
     *            the array of compile time exception classes.
     * @param ctClass
     *            the compile time class.
     * @return the compile time constructor.
     * @throws CannotCompileRuntimeException
     *             if CannotCompileException occurred.
     */
    public static CtConstructor make(CtClass[] parameterClasses,
            CtClass[] exceptionClasses, CtClass ctClass) {
        try {
            return CtNewConstructor.make(parameterClasses, exceptionClasses,
                    ctClass);
        } catch (CannotCompileException e) {
            throw new CannotCompileRuntimeException(e);
        }
    }
}