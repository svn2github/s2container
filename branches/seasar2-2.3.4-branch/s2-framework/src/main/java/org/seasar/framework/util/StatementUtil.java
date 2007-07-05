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
package org.seasar.framework.util;

import java.sql.SQLException;
import java.sql.Statement;

import org.seasar.framework.exception.SQLRuntimeException;

/**
 * @author higa
 * 
 */
public final class StatementUtil {

    private StatementUtil() {
    }

    /**
     * フェッチサイズを設定します。
     * 
     * @param statement
     * @param fetchSize
     * @throws SQLRuntimeException
     *             {@link SQLException}が発生した場合
     * @see Statement#setFetchSize(int)
     */
    public static void setFetchSize(Statement statement, int fetchSize)
            throws SQLRuntimeException {
        try {
            statement.setFetchSize(fetchSize);
        } catch (SQLException ex) {
            throw new SQLRuntimeException(ex);
        }
    }

    /**
     * 最大行数を設定します。
     * 
     * @param statement
     * @param maxRows
     * @throws SQLRuntimeException
     *             {@link SQLException}が発生した場合
     * @see Statement#setMaxRows(int)
     */
    public static void setMaxRows(Statement statement, int maxRows)
            throws SQLRuntimeException {
        try {
            statement.setMaxRows(maxRows);
        } catch (SQLException ex) {
            throw new SQLRuntimeException(ex);
        }
    }

    /**
     * クエリタイムアウトを設定します。
     * 
     * @param statement
     * @param queryTimeout
     * @throws SQLRuntimeException
     *             {@link SQLException}が発生した場合
     * @see Statement#setQueryTimeout(int)
     */
    public static void setQueryTimeout(Statement statement, int queryTimeout)
            throws SQLRuntimeException {
        try {
            statement.setQueryTimeout(queryTimeout);
        } catch (SQLException ex) {
            throw new SQLRuntimeException(ex);
        }
    }

    /**
     * {@link Statement}を閉じます。
     * 
     * @param statement
     * @throws SQLRuntimeException
     *             {@link SQLException}が発生した場合
     * @see Statement#close()
     */
    public static void close(Statement statement) throws SQLRuntimeException {
        if (statement == null) {
            return;
        }
        try {
            statement.close();
        } catch (SQLException ex) {
            throw new SQLRuntimeException(ex);
        }
    }
}
