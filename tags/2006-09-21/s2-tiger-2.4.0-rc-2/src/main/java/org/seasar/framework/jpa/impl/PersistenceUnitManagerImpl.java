package org.seasar.framework.jpa.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transaction;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.DestroyMethod;
import org.seasar.framework.jpa.PersistenceUnitManager;

@Component
public class PersistenceUnitManagerImpl implements PersistenceUnitManager {

    protected final Map<String, EntityManagerFactory> persistenceUnits = new HashMap<String, EntityManagerFactory>();

    protected final Map<EntityManagerFactory, ConcurrentMap<Transaction, EntityManager>> emfContexts = new HashMap<EntityManagerFactory, ConcurrentMap<Transaction, EntityManager>>();

    public PersistenceUnitManagerImpl() {
    }

    @DestroyMethod
    public synchronized void close() {
        for (final EntityManagerFactory emf : persistenceUnits.values()) {
            emf.close();
        }
        persistenceUnits.clear();
        emfContexts.clear();
    }

    public synchronized EntityManagerFactory getEntityManagerFactory(
            final String unitName) {
        final EntityManagerFactory emf = persistenceUnits.get(unitName);
        if (emf != null) {
            return emf;
        }
        return createEntityManagerFactory(unitName);
    }

    protected EntityManagerFactory createEntityManagerFactory(
            final String unitName) {
        final EntityManagerFactory emf = Persistence
                .createEntityManagerFactory(unitName);
        persistenceUnits.put(unitName, emf);
        emfContexts.put(emf,
                new ConcurrentHashMap<Transaction, EntityManager>());
        return emf;
    }

    public ConcurrentMap<Transaction, EntityManager> getEmfContext(
            final EntityManagerFactory emf) {
        return emfContexts.get(emf);
    }

}
