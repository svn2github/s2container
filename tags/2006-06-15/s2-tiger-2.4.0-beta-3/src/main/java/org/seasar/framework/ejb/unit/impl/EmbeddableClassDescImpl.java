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
package org.seasar.framework.ejb.unit.impl;

import java.util.Map;

import org.seasar.framework.ejb.unit.EmbeddableClassDesc;
import org.seasar.framework.ejb.unit.PersistentColumn;
import org.seasar.framework.ejb.unit.PersistentStateDesc;

/**
 * @author taedium
 * 
 */
public class EmbeddableClassDescImpl extends AbstractPersistentClassDesc
        implements EmbeddableClassDesc {

    private final boolean isIdentifier;

    public EmbeddableClassDescImpl(Class persistentClass,
            String primaryTableName, boolean propertyAccessed,
            boolean isIdentifier) {

        super(persistentClass, primaryTableName, propertyAccessed);
        this.isIdentifier = isIdentifier;
        setupPersistentStateDescs();
    }

    public void overrideAttributes(Map<String, PersistentColumn> attribOverrides) {
        for (PersistentStateDesc stateDesc : getPersistentStateDescs()) {
            if (attribOverrides.containsKey(stateDesc.getName())) {
                PersistentColumn overriding = attribOverrides.get(stateDesc
                        .getName());
                PersistentColumn overridden = stateDesc.getColumn();
                if (overriding.getName() != null) {
                    overridden.setName(overriding.getName());
                }
                if (overriding.getTable() != null) {
                    overridden.setTable(overriding.getTable());
                }
            }
        }
    }

    public boolean isIdentifier() {
        return isIdentifier;
    }

    public boolean contains(PersistentStateDesc stateDesc) {
        for (PersistentStateDesc each : getPersistentStateDescs()) {
            if (each == stateDesc) {
                return true;
            }
        }
        return false;
    }
}