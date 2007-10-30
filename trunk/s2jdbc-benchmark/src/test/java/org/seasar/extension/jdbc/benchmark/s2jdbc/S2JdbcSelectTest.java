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
package org.seasar.extension.jdbc.benchmark.s2jdbc;

import java.text.DecimalFormat;
import java.util.List;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.benchmark.AbstractSelectTest;
import org.seasar.framework.container.SingletonS2Container;

/**
 * @author taedium
 * 
 */
public class S2JdbcSelectTest extends AbstractSelectTest {

    private JdbcManager jdbcManager;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        jdbcManager = SingletonS2Container.getComponent(JdbcManager.class);
    }

    /**
     * 
     * @throws Exception
     */
    public void test() throws Exception {
        userTransaction.begin();
        long start = System.nanoTime();
        List<Employee> employees =
            jdbcManager.from(Employee.class).getResultList();
        long end = System.nanoTime();
        userTransaction.commit();
        assertEquals(10000, employees.size());
        DecimalFormat df = new DecimalFormat("#,##0");
        System.out.printf(
            "%14s (nanoTime) : %s\n",
            df.format(end - start),
            getClass().getSimpleName());
    }

    @Override
    protected void tearDown() throws Exception {
        jdbcManager = null;
        super.tearDown();
    }
}
