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
package org.seasar.extension.dao.helper.impl;

import java.lang.reflect.Method;

import junit.framework.TestCase;

import org.seasar.extension.dao.helper.impl.dao.HogeDao;
import org.seasar.extension.dao.helper.impl.dao.aaa.Hoge2Dao;
import org.seasar.extension.dao.helper.impl.dao.impl.HogeDaoImpl;
import org.seasar.framework.convention.impl.NamingConventionImpl;
import org.seasar.framework.util.ClassUtil;

/**
 * 
 * @author higa
 */
public class DaoHelperImplTest extends TestCase {

    /**
     * @throws Exception
     */
    public void testGetDaoInterface() throws Exception {
        DaoHelperImpl helper = new DaoHelperImpl();
        helper.setNamingConvention(new NamingConventionImpl());
        assertEquals(HogeDao.class, helper.getDaoInterface(HogeDao.class));
        assertEquals(HogeDao.class, helper.getDaoInterface(HogeDaoImpl.class));
    }

    /**
     * @throws Exception
     */
    public void testGetDataSourceName() throws Exception {
        DaoHelperImpl helper = new DaoHelperImpl();
        helper.setNamingConvention(new NamingConventionImpl());
        assertNull(helper.getDataSourceName(HogeDao.class));
        assertEquals("aaa", helper.getDataSourceName(Hoge2Dao.class));
    }

    /**
     * @throws Exception
     */
    public void testGetSqlBySqlFile() throws Exception {
        DaoHelperImpl helper = new DaoHelperImpl();
        helper.setNamingConvention(new NamingConventionImpl());
        Method m = ClassUtil.getMethod(HogeDao.class, "find", null);
        assertEquals("standard", helper.getSqlBySqlFile(HogeDao.class, m, null));
        assertEquals("oracle", helper.getSqlBySqlFile(HogeDao.class, m,
                "oracle"));
    }
}