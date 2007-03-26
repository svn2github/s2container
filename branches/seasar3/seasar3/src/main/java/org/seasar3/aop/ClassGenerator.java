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

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;

import org.seasar3.util.ClassPoolUtil;
import org.seasar3.util.CtClassUtil;
import org.seasar3.util.CtNewConstructorUtil;
import org.seasar3.util.CtNewMethodUtil;

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

    /**
     * Converts {@link Class} to <code>CtClass</code>
     * 
     * @param type
     * @return <code>CtClass</code>
     * @see ClassPoolUtil#toCtClass(ClassPool, Class)
     */
    public CtClass toCtClass(Class type) {
        return ClassPoolUtil.toCtClass(classPool, type);
    }

    /**
     * Converts array of {@link Class} to array of <code>CtClass</code>
     * 
     * @param types
     * @return array of <code>CtClass</code>
     * @see ClassPoolUtil#toCtClass(ClassPool, Class[])
     */
    public CtClass[] toCtClassArray(Class[] types) {
        return ClassPoolUtil.toCtClassArray(classPool, types);
    }

    /**
     * Sets interface.
     * 
     * @param interfaceType
     */
    public void setInterface(Class interfaceType) {
        ctClass.setInterfaces(new CtClass[] { toCtClass(interfaceType) });
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
     * @param parameterTypes
     * @param exceptionTypes
     * @return
     */
    public CtConstructor createConstructor(Class[] parameterTypes,
            Class[] exceptionTypes) {
        CtConstructor ctConstructor = CtNewConstructorUtil.make(
                toCtClassArray(parameterTypes), toCtClassArray(exceptionTypes),
                ctClass);
        CtClassUtil.addConstructor(ctClass, ctConstructor);
        return ctConstructor;
    }

    /**
     * Gets declared method.
     * 
     * @param methodName
     * @param parameterTypes
     * @return
     * @see CtClassUtil#getDeclaredMethod(CtClass, String, CtClass[])
     */
    public CtMethod getDeclaredMethod(String methodName,
            CtClass[] parameterTypes) {
        return CtClassUtil.getDeclaredMethod(ctClass, methodName,
                parameterTypes);
    }

    protected CtMethod createMethod(String src) {
        CtMethod ctMethod = CtNewMethodUtil.make(src, ctClass);
        CtClassUtil.addMethod(ctClass, ctMethod);
        return ctMethod;
    }
}