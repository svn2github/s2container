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

import org.seasar3.exception.CannotCompileRuntimeException;

/**
 * Utility for <code>CtClass</code>
 * 
 * @author higa
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
     * 
     * @return
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
}