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
package org.seasar.extension.persistence;

/**
 * @author higa
 * 
 */
public class PropertyMeta {

    private String name;

    private boolean aTransient = false;

    private ColumnMeta columnMeta;

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    public boolean isTransient() {
        return aTransient;
    }

    public void setTransient(boolean aTransient) {
        this.aTransient = aTransient;
    }

    /**
     * @return Returns the columnMeta.
     */
    public ColumnMeta getColumnMeta() {
        return columnMeta;
    }

    /**
     * @param columnMeta
     *            The columnMeta to set.
     */
    public void setColumnMeta(ColumnMeta columnMeta) {
        this.columnMeta = columnMeta;
    }
}
