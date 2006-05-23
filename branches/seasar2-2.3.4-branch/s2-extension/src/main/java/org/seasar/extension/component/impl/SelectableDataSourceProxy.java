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
package org.seasar.extension.component.impl;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.exception.EmptyRuntimeException;

public class SelectableDataSourceProxy implements DataSource {
    private final S2Container container;

    private final ThreadLocal context = new ThreadLocal();

    public SelectableDataSourceProxy(final S2Container container) {
        this.container = container;
    }

    public String getDataSourceName() {
        return (String) context.get();
    }

    public void setDataSourceName(final String dataSourceName) {
        context.set(dataSourceName);
    }

    public DataSource getDataSource() {
        final String dataSourceName = getDataSourceName();
        if (dataSourceName == null) {
            throw new EmptyRuntimeException("dataSourceName");
        }
        return (DataSource) container.getComponent(dataSourceName);
    }

    public Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    public Connection getConnection(final String username, final String password)
            throws SQLException {
        return getDataSource().getConnection(username, password);
    }

    public PrintWriter getLogWriter() throws SQLException {
        return getDataSource().getLogWriter();
    }

    public void setLogWriter(final PrintWriter out) throws SQLException {
        getDataSource().setLogWriter(out);
    }

    public int getLoginTimeout() throws SQLException {
        return getDataSource().getLoginTimeout();
    }

    public void setLoginTimeout(int seconds) throws SQLException {
        getDataSource().setLoginTimeout(seconds);
    }
}
