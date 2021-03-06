/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
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
package org.seasar.extension.jdbc.manager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.seasar.extension.jdbc.JdbcContext;
import org.seasar.extension.jdbc.util.ConnectionUtil;
import org.seasar.extension.jdbc.util.StatementCache;
import org.seasar.framework.log.Logger;

/**
 * {@link JdbcContext}の実装クラスです。
 * 
 * @author higa
 * 
 */
public class JdbcContextImpl implements JdbcContext {

    private static final Logger logger = Logger
            .getLogger(JdbcContextImpl.class);

    private Connection connection;

    private Statement statement;

    private boolean transactional;

    private int preparedStatementCacheSize = 10;

    private int cursorPreparedStatementCacheSize = 5;

    private int callableStatementCacheSize = 5;

    private StatementCache preparedStatementCache = new StatementCache(
            preparedStatementCacheSize);

    private StatementCache cursorPreparedStatementCache = new StatementCache(
            cursorPreparedStatementCacheSize);

    private StatementCache callableStatementCache = new StatementCache(
            callableStatementCacheSize);

    /**
     * {@link JdbcContextImpl}を作成します。
     * 
     * @param connection
     *            コネクション
     * @param transactional
     *            トランザクション中に作成されたかどうか
     */
    public JdbcContextImpl(Connection connection, boolean transactional) {
        this.connection = connection;
        this.transactional = transactional;
    }

    public void destroy() {
        if (connection == null) {
            return;
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.log(e);
            }
            statement = null;
        }
        try {
            preparedStatementCache.destroy();
        } catch (SQLException e) {
            logger.log(e);
        }
        try {
            cursorPreparedStatementCache.destroy();
        } catch (SQLException e) {
            logger.log(e);
        }
        try {
            callableStatementCache.destroy();
        } catch (SQLException e) {
            logger.log(e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            logger.log(e);
        }
        connection = null;
    }

    public boolean isTransactional() {
        return transactional;
    }

    public Statement getStatement() {
        if (statement != null) {
            return statement;
        }
        statement = ConnectionUtil.createStatement(connection);
        return statement;
    }

    @SuppressWarnings("unchecked")
    public PreparedStatement getPreparedStatement(String sql) {
        PreparedStatement ps = (PreparedStatement) preparedStatementCache
                .get(sql);
        if (ps != null) {
            return ps;
        }
        ps = ConnectionUtil.prepareStatement(connection, sql);
        preparedStatementCache.put(sql, ps);
        return ps;
    }

    @SuppressWarnings("unchecked")
    public PreparedStatement getPreparedStatement(String sql,
            int autoGeneratedKeys) {
        return ConnectionUtil.prepareStatement(connection, sql,
                autoGeneratedKeys);
    }

    @SuppressWarnings("unchecked")
    public PreparedStatement getCursorPreparedStatement(String sql) {
        PreparedStatement ps = (PreparedStatement) cursorPreparedStatementCache
                .get(sql);
        if (ps != null) {
            return ps;
        }
        ps = ConnectionUtil.prepareStatement(connection, sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        cursorPreparedStatementCache.put(sql, ps);
        return ps;
    }

    @SuppressWarnings("unchecked")
    public CallableStatement getCallableStatement(String sql) {
        CallableStatement cs = (CallableStatement) callableStatementCache
                .get(sql);
        if (cs != null) {
            return cs;
        }
        cs = ConnectionUtil.prepareCall(connection, sql);
        callableStatementCache.put(sql, cs);
        return cs;
    }

    /**
     * JDBCコンテキストが破棄されたかどうかを返します。
     * 
     * @return JDBCコンテキストが破棄されたかどうか
     */
    public boolean idDestroyed() {
        return isConnectionNull();
    }

    /**
     * コネクションが<code>null</code>かどうかを返します。
     * 
     * @return コネクションが<code>null</code>かどうか
     */
    public boolean isConnectionNull() {
        return connection == null;
    }

    /**
     * ステートメントが<code>null</code>かどうかを返します。
     * 
     * @return ステートメントが<code>null</code>かどうか
     */
    public boolean isStatementNull() {
        return statement == null;
    }

    /**
     * 準備されたステートメントのキャッシュが空かどうかを返します。
     * 
     * @return 準備されたステートメントのキャッシュが空かどうか
     */
    public boolean isPreparedStatementCacheEmpty() {
        return preparedStatementCache.isEmpty();
    }

    /**
     * カーソルつきの準備されたステートメントのキャッシュが空かどうかを返します。
     * 
     * @return カーソルつきの準備されたステートメントのキャッシュが空かどうか
     */
    public boolean isCursorPreparedStatementCacheEmpty() {
        return cursorPreparedStatementCache.isEmpty();
    }

    /**
     * 呼び出し可能なステートメントのキャッシュが空かどうかを返します。
     * 
     * @return 呼び出し可能なステートメントのキャッシュが空かどうか
     */
    public boolean isCallableStatementCacheEmpty() {
        return callableStatementCache.isEmpty();
    }

    /**
     * 準備されたステートメントをキャッシュする数を返します。
     * 
     * @return 準備されたステートメントをキャッシュする数
     */
    public int getPreparedStatementCacheSize() {
        return preparedStatementCacheSize;
    }

    /**
     * 準備されたステートメントをキャッシュする数を設定します。
     * 
     * @param preparedStatementCacheSize
     *            準備されたステートメントをキャッシュする数
     */
    public void setPreparedStatementCacheSize(int preparedStatementCacheSize) {
        this.preparedStatementCacheSize = preparedStatementCacheSize;
        preparedStatementCache = new StatementCache(preparedStatementCacheSize);
    }

    /**
     * カーソルつきの準備されたステートメントをキャッシュする数を返します。
     * 
     * @return カーソルつきの準備されたステートメントをキャッシュする数
     */
    public int getCursorPreparedStatementCacheSize() {
        return cursorPreparedStatementCacheSize;
    }

    /**
     * カーソルつきの準備されたステートメントをキャッシュする数を設定します。
     * 
     * @param cursorPreparedStatementCacheSize
     *            カーソルつきの準備されたステートメントをキャッシュする数
     */
    public void setCursorPreparedStatementCacheSize(
            int cursorPreparedStatementCacheSize) {
        this.cursorPreparedStatementCacheSize = cursorPreparedStatementCacheSize;
        cursorPreparedStatementCache = new StatementCache(
                cursorPreparedStatementCacheSize);
    }

    /**
     * 呼び出し可能なステートメントをキャッシュする数を返します。
     * 
     * @return 呼び出し可能なステートメントをキャッシュする数
     */
    public int getCallableStatementCacheSize() {
        return callableStatementCacheSize;
    }

    /**
     * 呼び出し可能なステートメントをキャッシュする数を設定します。
     * 
     * @param callableStatementCacheSize
     *            呼び出し可能なステートメントをキャッシュする数
     */
    public void setCallableStatementCacheSize(int callableStatementCacheSize) {
        this.callableStatementCacheSize = callableStatementCacheSize;
        callableStatementCache = new StatementCache(callableStatementCacheSize);
    }

}
