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
package org.seasar.extension.jdbc.exception;

import org.seasar.framework.exception.SRuntimeException;

/**
 * エンティティではない場合の例外です。
 * 
 * @author higa
 * 
 */
public class NonEntityRuntimeException extends SRuntimeException {

    private static final long serialVersionUID = 1L;

    private Class<?> targetClass;

    /**
     * {@link NonEntityRuntimeException}を作成します。
     * 
     * @param targetClass
     *            クラス
     */
    public NonEntityRuntimeException(Class<?> targetClass) {
        super("ESSR0704", new Object[] { targetClass.getName() });
        this.targetClass = targetClass;
    }

    /**
     * 対象クラスを返します。
     * 
     * @return 対象クラス
     */
    public Class<?> getTargetClass() {
        return targetClass;
    }
}