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
package org.seasar3.lookup;

import java.lang.reflect.Method;

import javassist.CtField;
import javassist.CtMethod;

import org.seasar3.aop.ClassGenerator;

/**
 * A customizer class that makes component singleton.
 * 
 * @author higa
 * 
 */
public class SingletonCustomizer implements ConfigurationCustomizer {

    private static final String LOCK_FIELD_PREFIX = "$$SINGLETON_LOCK_";

    private static final String LOCK_FIELD_SRC = "private static Object ";

    private static final String LOCK_FIELD_SRC2 = " = new Object();";

    private static final String FIELD_PREFIX = "$$SINGLETON_VALUE_";

    private static final String FIELD_SRC = "private volatile Object ";

    private static final String FIELD_SRC2 = ";";

    public void customize(ClassGenerator generator, Method method) {
        String methodName = method.getName();
        createLockField(generator, methodName);
        createField(generator, methodName);
        CtMethod ctMethod = generator.getDeclaredMethod(method);
        generator.setMethodBody(ctMethod, createMethodBody(methodName));
    }

    protected CtField createField(ClassGenerator generator) {
        return generator.createField(FIELD_SRC);
    }

    protected String createMethodBody(String methodName) {
        StringBuilder sb = new StringBuilder(400);
        String lockFieldName = getLockFieldName(methodName);
        String fieldName = getFieldName(methodName);
        sb.append("{if (").append(fieldName).append(" == null) {");
        sb.append("  synchronized (").append(lockFieldName).append(") {");
        sb.append("   if (").append(fieldName).append(" == null) ");
        sb.append(fieldName).append(" = super.").append(methodName).append(
                "();");
        sb.append("  }");
        sb.append(" }");
        sb.append(" return ($r) ").append(fieldName).append(";}");
        return sb.toString();
    }

    protected String getLockFieldName(String methodName) {
        return LOCK_FIELD_PREFIX + methodName;
    }

    protected String getLockFieldSrc(String methodName) {
        return LOCK_FIELD_SRC + getLockFieldName(methodName) + LOCK_FIELD_SRC2;
    }

    protected CtField createLockField(ClassGenerator generator,
            String methodName) {
        return generator.createField(getLockFieldSrc(methodName));
    }

    protected String getFieldName(String methodName) {
        return FIELD_PREFIX + methodName;
    }

    protected String getFieldSrc(String methodName) {
        return FIELD_SRC + getFieldName(methodName) + FIELD_SRC2;
    }

    protected CtField createField(ClassGenerator generator, String methodName) {
        return generator.createField(getFieldSrc(methodName));
    }
}
