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

import java.lang.reflect.Modifier;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

import org.seasar3.exception.CannotCompileRuntimeException;

/**
 * Utility for compile time method.
 * 
 * @author higa
 * @version 3.0
 */

public final class CtNewMethodUtil {

    private CtNewMethodUtil() {
    }

    /**
     * Creates new compile time method.
     * 
     * @param body
     *            the body.
     * @param ctClass
     *            the compile time class.
     * 
     * @return the compile time method.
     * @throws CannotCompileRuntimeException
     *             if CannotCompileException occurred.
     */
    public static CtMethod make(String body, CtClass ctClass) {
        try {
            return CtNewMethod.make(body, ctClass);
        } catch (CannotCompileException e) {
            throw new CannotCompileRuntimeException(e);
        }
    }

    /**
     * Creates new compile time method.
     * 
     * @param modifier
     *            the modifier.
     * @param returnClass
     *            the returned class.
     * @param methodName
     *            the method name.
     * @param parameterClasses
     *            the array of compile time parameter classes.
     * @param exceptionClasses
     *            the array of compile time exception classes.
     * @param body
     *            the body.
     * @param ctClass
     *            the compile time class.
     * 
     * @return the compile time method.
     * @throws CannotCompileRuntimeException
     *             if CannotCompileException occurred.
     */
    public static CtMethod make(int modifier, CtClass returnClass,
            String methodName, CtClass[] parameterClasses,
            CtClass[] exceptionClasses, String body, CtClass ctClass) {
        try {
            return CtNewMethod.make(modifier
                    & ~(Modifier.ABSTRACT | Modifier.NATIVE), returnClass,
                    methodName, parameterClasses, exceptionClasses, body,
                    ctClass);
        } catch (CannotCompileException e) {
            throw new CannotCompileRuntimeException(e);
        }
    }
}