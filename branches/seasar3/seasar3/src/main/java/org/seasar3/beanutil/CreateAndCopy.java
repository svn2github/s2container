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
package org.seasar3.beanutil;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import org.seasar3.util.ClassUtil;

/**
 * A class to create and copy objects.
 * 
 * @author higa
 * @since 3.0
 * @param <T>
 *            The destination type.
 * 
 */
public class CreateAndCopy<T> extends AbstractCopy<CreateAndCopy<T>> {

    /**
     * The destination class.
     */
    protected Class<T> destClass;

    /**
     * The source.
     */
    protected Object src;

    /**
     * インスタンスを構築します。
     * 
     * @param destClass
     *            作成対象クラス
     * @param src
     *            コピー元
     * @throws NullPointerException
     *             引数が<code>null</code>だった場合
     */
    public CreateAndCopy(Class<T> destClass, Object src)
            throws NullPointerException {
        if (destClass == null) {
            throw new NullPointerException("destClass");
        }
        if (src == null) {
            throw new NullPointerException("src");
        }
        this.destClass = destClass;
        this.src = src;
    }

    /**
     * Executes create and copy processes.
     * 
     * @return the executed result.
     */
    @SuppressWarnings("unchecked")
    public T execute() {
        if (Map.class.isAssignableFrom(destClass)) {
            Map dest = null;
            if (Modifier.isAbstract(destClass.getModifiers())) {
                dest = new HashMap();
            } else {
                dest = (Map) ClassUtil.newInstance(destClass);
            }
            if (src instanceof Map) {
                copyMapToMap((Map) src, dest);
            } else {
                copyBeanToMap(src, dest);
            }
            return (T) dest;
        }
        T dest = ClassUtil.newInstance(destClass);
        if (src instanceof Map) {
            copyMapToBean((Map) src, dest);
        } else {
            copyBeanToBean(src, dest);
        }
        return dest;
    }
}