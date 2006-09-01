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
package org.seasar.extension.datasource.impl;

import javax.sql.DataSource;

import org.seasar.extension.datasource.DataSourceFactory;
import org.seasar.framework.container.S2Container;

/**
 * @author higa
 * 
 */
public class DataSourceFactoryImpl implements DataSourceFactory {

    protected ThreadLocal selectableDataSourceName = new ThreadLocal();

    protected S2Container container;

    public String getSelectableDataSourceName() {
        return (String) selectableDataSourceName.get();
    }

    public void setSelectableDataSourceName(String name) {
        selectableDataSourceName.set(name);
    }

    public String getDataSourceName(String name) {
        if (name != null) {
            return name;
        }
        return getSelectableDataSourceName();
    }

    /**
     * @return Returns the container.
     */
    public S2Container getContainer() {
        return container;
    }

    /**
     * @param container
     *            The container to set.
     */
    public void setContainer(S2Container container) {
        this.container = container;
    }

    public DataSource getDataSource(String name) {
        return (DataSource) container.getRoot().getComponent(
                getDataSourceComponentName(name));
    }

    protected String getDataSourceComponentName(String name) {
        String dsName = getDataSourceName(name);
        if (dsName == null) {
            return "dataSource";
        }
        return dsName + "DataSource";
    }
}
