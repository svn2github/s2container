/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
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
package org.seasar.framework.container.assembler;

import java.util.HashMap;
import java.util.Map;

import org.seasar.framework.container.BindingTypeDef;
import org.seasar.framework.container.IllegalBindingTypeDefRuntimeException;

/**
 * バインディングタイプ定義のファクトリです。
 * 
 * @author higa
 *
 */
public class BindingTypeDefFactory {

    public static final BindingTypeDef MUST = new BindingTypeMustDef(
            BindingTypeDef.MUST_NAME);

    public static final BindingTypeDef SHOULD = new BindingTypeShouldDef(
            BindingTypeDef.SHOULD_NAME);

    public static final BindingTypeDef MAY = new BindingTypeMayDef(
            BindingTypeDef.MAY_NAME);

    public static final BindingTypeDef NONE = new BindingTypeNoneDef(
            BindingTypeDef.NONE_NAME);

    private static Map bindingTypeDefs = new HashMap();

    static {
        addBindingTypeDef(MUST);
        addBindingTypeDef(SHOULD);
        addBindingTypeDef(MAY);
        addBindingTypeDef(NONE);
    }

    protected BindingTypeDefFactory() {
    }

    public static void addBindingTypeDef(BindingTypeDef bindingTypeDef) {
        bindingTypeDefs.put(bindingTypeDef.getName(), bindingTypeDef);
    }

    public static boolean existBindingTypeDef(String name) {
        return bindingTypeDefs.containsKey(name);
    }

    public static BindingTypeDef getBindingTypeDef(String name) {
        if (!existBindingTypeDef(name)) {
            throw new IllegalBindingTypeDefRuntimeException(name);
        }
        return (BindingTypeDef) bindingTypeDefs.get(name);
    }
}