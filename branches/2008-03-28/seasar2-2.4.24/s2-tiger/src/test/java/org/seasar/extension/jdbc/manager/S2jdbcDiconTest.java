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
package org.seasar.extension.jdbc.manager;

import java.util.List;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.entity.Emp;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.beans.util.BeanMap;

/**
 * @author higa
 * 
 */
public class S2jdbcDiconTest extends S2TestCase {

    private JdbcManager jdbcManager;

    @Override
    protected void setUp() {
        include("s2jdbc.dicon");
    }

    /**
     * @throws Exception
     */
    public void testGetResultList() throws Exception {
        assertTrue(jdbcManager.from(Emp.class).getResultList().size() > 1);
    }

    /**
     * @throws Exception
     */
    public void testGetResultList_map() throws Exception {
        List<BeanMap> results = jdbcManager.selectBySql(BeanMap.class,
                "select * from emp").getResultList();
        assertNotNull(results);

    }
}
