/*
 * Copyright 2004-2009 the Seasar Foundation and the Others.
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
package org.seasar.extension.jdbc.meta;

import org.seasar.extension.jdbc.EntityMetaFactory;
import org.seasar.extension.jdbc.entity.Aaa;
import org.seasar.extension.unit.S2TestCase;

/**
 * @author higa
 * 
 */
public class MetaDiconTest extends S2TestCase {

    private EntityMetaFactory entityMetaFactory;

    @Override
    protected void setUp() {
        include("s2jdbc-internal.dicon");
    }

    /**
     * @throws Exception
     */
    public void testGetComponent() throws Exception {
        assertNotNull(entityMetaFactory.getEntityMeta(Aaa.class));
    }
}
