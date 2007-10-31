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
package org.seasar.extension.jdbc.benchmark.s2dao;

import java.util.List;

import org.seasar.extension.jdbc.benchmark.BenchmarkTestCase;
import org.seasar.extension.jdbc.benchmark.SelectInverseSideBenchmark;
import org.seasar.framework.container.SingletonS2Container;

/**
 * @author taedium
 * 
 */
public class S2DaoSelectInverseSideTest extends BenchmarkTestCase implements
        SelectInverseSideBenchmark {

    private AddressDao addressDao;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        addressDao = SingletonS2Container.getComponent(AddressDao.class);
        addressDao.initialize();
    }

    /**
     * 
     * @throws Exception
     */
    public void test() throws Exception {
        begin();
        List<Address> addresses = addressDao.select();
        end();
        assertEquals(10000, addresses.size());
        assertNotNull(addresses.get(0).addressId);
        assertNotNull(addresses.get(0).street);
        assertNotNull(addresses.get(0).versionNo);
    }

    @Override
    protected void tearDown() throws Exception {
        addressDao = null;
        super.tearDown();
    }
}