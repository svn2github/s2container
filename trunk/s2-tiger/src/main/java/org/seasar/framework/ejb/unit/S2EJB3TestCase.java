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
package org.seasar.framework.ejb.unit;

import java.lang.reflect.Method;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.TransactionManager;

import org.seasar.extension.dataset.DataSet;
import org.seasar.extension.dataset.DataTable;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.TigerAnnotationHandler;
import org.seasar.framework.exception.EmptyRuntimeException;
import org.seasar.framework.unit.annotation.Rollback;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.ResourceUtil;
import org.seasar.framework.util.TransactionManagerUtil;

/**
 * @author taedium
 * 
 */
public abstract class S2EJB3TestCase extends S2TestCase {

    private static final String EJB3TX_DICON = "ejb3tx.dicon";

    private static final String S2HIBERNATE_JPA_DICON = "s2hibernate-jpa.dicon";

    private TigerAnnotationHandler handler = new TigerAnnotationHandler();

    private ProxiedObjectResolver resolver;

    private EntityManager entityManager;

    public S2EJB3TestCase() {
    }

    public S2EJB3TestCase(String name) {
        super(name);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        includeDicons();
    }

    public final void includeDicons() {
        if (ResourceUtil.isExist(EJB3TX_DICON)) {
            include(EJB3TX_DICON);
        }
        if (ResourceUtil.isExist(S2HIBERNATE_JPA_DICON)) {
            include(S2HIBERNATE_JPA_DICON);
        }
    }

    @Override
    public void register(Class componentClass) {
        ComponentDef cd = handler.createComponentDef(componentClass, null);
        handler.appendDI(cd);
        handler.appendAspect(cd);
        handler.appendInterType(cd);
        handler.appendInitMethod(cd);
        register(cd);
    }

    @Override
    public DataSet reload(DataSet dataSet) {
        flush();
        return super.reload(dataSet);
    }

    @Override
    public DataTable reload(DataTable table) {
        flush();
        return super.reload(table);
    }

    @Override
    protected boolean needTransaction() {
        Method method = ClassUtil.getMethod(getClass(), getName(), null);
        return super.needTransaction() || method.isAnnotationPresent(Rollback.class);
    }
    
    protected void flush() {
        if (entityManager != null && isTransactionActive()) {
            entityManager.flush();
        }
    }

    protected boolean isTransactionActive() {
        TransactionManager tm = (TransactionManager) getComponent(TransactionManager.class);
        return tm != null && TransactionManagerUtil.isActive(tm);
    }

    @Override
    protected void setUpAfterContainerInit() throws Throwable {
        super.setUpAfterContainerInit();
        setupEntityManager();
    }

    @Override
    protected void tearDownBeforeContainerDestroy() throws Throwable {
        tearDownEntityManager();
        super.tearDownBeforeContainerDestroy();
    }

    protected void setupEntityManager() {
        S2Container container = getContainer();
        try {
            if (container.hasComponentDef(EntityManager.class)) {
                entityManager = (EntityManager) container
                        .getComponent(EntityManager.class);
            }
        } catch (Throwable t) {
            System.err.println(t);
        }
    }

    protected void tearDownEntityManager() {
        entityManager = null;
    }

    public EntityManager getEntityManager() {
        if (entityManager == null) {
            throw new EmptyRuntimeException("entityManager");
        }
        return entityManager;
    }

    protected void assertEntityEquals(String message, DataSet expected,
            Object entity) {
        assertEntityEquals(message, expected, entity, false);
    }
    
    protected void assertEntityEquals(String message, DataSet expected,
            Object entity, boolean includesRelationships) {

        assertNotNull("entity is null.", entity);
        EntityReader reader;
        if (resolver == null) {
            reader = new EntityReader(entity, includesRelationships);
        } else {
            reader = new EntityReader(entity, includesRelationships, resolver);
        }
        assertEqualsIgnoreOrder(message, expected, reader.read());
    }

    protected void assertEntityListEquals(String message, DataSet expected,
            List<?> list) {
        assertEntityListEquals(message, expected, list, false);
    }

    protected void assertEntityListEquals(String message, DataSet expected,
            List<?> list, boolean includesRelationships) {
        
        assertNotNull("entity list is null.", list);
        EntityListReader reader;
        if (resolver == null) {
            reader = new EntityListReader(list, includesRelationships);
        } else {
            reader = new EntityListReader(list, includesRelationships, resolver);
        }
        assertEqualsIgnoreOrder(message, expected, reader.read());
    }
    
    protected void assertEqualsIgnoreOrder(String message, DataSet expected,
            DataSet actual) {

        message = message == null ? "" : message;
        for (int i = 0; i < expected.getTableSize(); ++i) {
            String tableName = expected.getTable(i).getTableName();
            String notFound = message + ":Table " + tableName
                    + " was not found.";
            assertTrue(notFound, actual.hasTable(tableName));
            assertEquals(message, expected.getTable(i), actual
                    .getTable(tableName));
        }
    }

}
