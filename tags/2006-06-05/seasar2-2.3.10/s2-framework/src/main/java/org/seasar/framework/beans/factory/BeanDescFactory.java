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
package org.seasar.framework.beans.factory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.impl.BeanDescImpl;

/**
 * @author higa
 * 
 */
public final class BeanDescFactory {

    private static Map beanDescCache = Collections
            .synchronizedMap(new HashMap());

    private BeanDescFactory() {
    }

    public static BeanDesc getBeanDesc(Class clazz) {
        BeanDesc beanDesc = (BeanDesc) beanDescCache.get(clazz);
        if (beanDesc == null) {
            beanDesc = new BeanDescImpl(clazz);
            beanDescCache.put(clazz, beanDesc);
        }
        return beanDesc;
    }
}
