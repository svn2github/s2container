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

import java.util.Iterator;
import java.util.Map;

/**
 * This class is case insensitive to key.
 * 
 * @author higa
 * 
 */
public final class CaseInsensitiveMap extends ArrayMap<String, Object> {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     */
    public CaseInsensitiveMap() {
    }

    /**
     * Constructor.
     * 
     * @param capacity
     *            the capacity.
     */
    public CaseInsensitiveMap(int capacity) {
        super(capacity);
    }

    @Override
    public final Object get(Object key) {
        return super.get(convertKey(key));
    }

    @Override
    public final Object put(String key, Object value) {
        return super.put(convertKey(key), value);
    }

    @Override
    @SuppressWarnings("unchecked")
    public final void putAll(Map map) {
        for (Iterator<Map.Entry<String, Object>> i = map.entrySet().iterator(); i
                .hasNext();) {
            Map.Entry<String, Object> entry = i.next();
            put(convertKey(entry.getKey()), entry.getValue());
        }
    }

    @Override
    public final Object remove(Object key) {
        return super.remove(convertKey(key));
    }

    @Override
    public boolean containsKey(Object key) {
        return super.containsKey(convertKey(key));
    }

    private static String convertKey(Object key) {
        return ((String) key).toLowerCase();
    }

}
