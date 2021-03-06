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
package org.seasar.framework.container.impl;

import org.seasar.framework.container.MetaDef;

/**
 * @author higa
 * 
 */
public class MetaDefImpl extends ArgDefImpl implements MetaDef {

    private String name;

    public MetaDefImpl() {
    }

    public MetaDefImpl(String name) {
        this.name = name;
    }

    public MetaDefImpl(String name, Object value) {
        super(value);
        this.name = name;
    }

    /**
     * @see org.seasar.framework.container.MetaDef#getName()
     */
    public String getName() {
        return name;
    }
}