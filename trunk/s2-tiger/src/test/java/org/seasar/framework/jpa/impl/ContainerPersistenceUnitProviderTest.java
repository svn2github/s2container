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
package org.seasar.framework.jpa.impl;

import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;
import javax.persistence.spi.PersistenceUnitInfo;

import org.seasar.framework.jpa.PersistenceUnitConfiguration;
import org.seasar.framework.jpa.PersistenceUnitInfoFactory;
import org.seasar.framework.unit.S2TigerTestCase;
import org.seasar.framework.unit.annotation.EasyMock;
import org.seasar.framework.util.ClassTraversal;
import org.seasar.framework.util.ResourceTraversal;

import static org.easymock.EasyMock.*;

/**
 * {@link ContainerPersistenceUnitProvider}のテストクラスです。
 * 
 * @author taedium
 */
public class ContainerPersistenceUnitProviderTest extends S2TigerTestCase {

    @EasyMock
    private PersistenceUnitInfoFactory unitInfoFactory;

    @EasyMock
    private PersistenceUnitConfiguration unitConfiguration;

    @EasyMock
    private PersistenceUnitInfo unitInfo;

    @Override
    protected void setUp() throws Exception {
        MyPersistenceProvider.invoked = false;
    }

    /**
     * {@link ContainerPersistenceUnitProvider#createEntityManagerFactory(String, String)}をテストします。
     * 
     * @throws Exception
     */
    public void testCreateEntityManagerFactory() throws Exception {
        ContainerPersistenceUnitProvider provider = new ContainerPersistenceUnitProvider();
        provider.setPersistenceUnitInfoFactory(unitInfoFactory);
        provider.setPersistenceUnitConfiguration(unitConfiguration);
        provider.unitInfoMap.put("foo", unitInfo);

        provider.createEntityManagerFactory("hoge", "foo");
        assertTrue(MyPersistenceProvider.invoked);
    }

    /**
     * {@ link #testCreateEntityManagerFactory()}で利用するモックの振る舞いを記録します。
     * 
     * @throws Exception
     */
    public void recordCreateEntityManagerFactory() throws Exception {
        unitConfiguration.detectMappingFiles(eq("hoge"),
                isA(ResourceTraversal.ResourceHandler.class));
        unitConfiguration.detectPersistenceClasses(eq("hoge"),
                isA(ClassTraversal.ClassHandler.class));
        expect(unitInfo.getNewTempClassLoader()).andReturn(
                Thread.currentThread().getContextClassLoader());
        expect(unitInfo.getPersistenceProviderClassName()).andReturn(
                MyPersistenceProvider.class.getName());
    }

    /**
     * テスト内でインスタンス化されるクラスです。
     * 
     * @author taedium
     */
    public static class MyPersistenceProvider implements PersistenceProvider {

        private static boolean invoked;

        @SuppressWarnings("unchecked")
        public EntityManagerFactory createContainerEntityManagerFactory(
                PersistenceUnitInfo unitInfo, Map map) {
            invoked = true;
            return null;
        }

        @SuppressWarnings("unchecked")
        public EntityManagerFactory createEntityManagerFactory(String unitName,
                Map map) {
            return null;
        }

    }
}
