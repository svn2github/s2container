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
public class SingletonCustomizer implements ConfigurationCustomizer<Singleton> {

    private static final String FIELD_NAME = "$$SINGLETON_VALUES";

    private static final String FIELD_SRC = "protected java.util.Map "
            + FIELD_NAME + " = new java.util.concurrent.ConcurrentHashMap(37);";

    public void customize(ClassGenerator generator, Method method,
            Singleton annotation) {
        createField(generator);
        CtMethod ctMethod = generator.getDeclaredMethod(method);
        generator.setMethodBody(ctMethod, createMethodBody(method));
    }

    protected CtField createField(ClassGenerator generator) {
        return generator.createField(FIELD_SRC);
    }

    protected String createMethodBody(Method method) {
        String methodName = method.getName();
        StringBuilder sb = new StringBuilder(400);
        sb.append("{Object ret = " + FIELD_NAME + ".get(\"").append(methodName)
                .append("\");");
        sb.append("if (ret != null) return ret;");
        sb.append("ret = super." + methodName + "();");
        sb.append(FIELD_NAME).append(".put(\"").append(methodName).append(
                "\",ret);");
        sb.append("return ret;}");
        return sb.toString();
    }
}
