/*
 * Copyright 2004-2013 the Seasar Foundation and the Others.
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
package org.seasar.extension.jdbc.it.util;

import java.text.MessageFormat;

/**
 * @author taedium
 *
 */
/**
 * SQLファイル内に記述されたSQLの実行に失敗した場合にスローされる例外です。
 * 
 * @author taedium
 */
public class SqlFailedRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /** ファイルのパス */
    protected String sqlFilePath;

    /** 行番号 */
    protected int lineNumber;

    /** SQL */
    protected String sql;

    /** メッセージ */
    protected String message;

    /**
     * インスタンスを構築します。
     * 
     * @param cause
     *            原因
     * @param sqlFilePath
     *            ファイルのパス
     * @param lineNumber
     *            行番号
     * @param sql
     *            SQL
     */
    public SqlFailedRuntimeException(Exception cause, String sqlFilePath,
            int lineNumber, String sql) {
        super(cause);
        this.sqlFilePath = sqlFilePath;
        this.lineNumber = lineNumber;
        this.sql = sql;
        message =
            MessageFormat
                .format(
                    "SQL failed(path=[{0}], lineNumber=[{1}], sql=[{2}]). because {3}",
                    new Object[] { sqlFilePath, lineNumber, sql, cause });
    }

    /**
     * ファイルのパスを返します。
     * 
     * @return ファイルのパス
     */
    public String getSqlFilePath() {
        return sqlFilePath;
    }

    /**
     * SQLを返します。
     * 
     * @return SQL
     */
    public String getSql() {
        return sql;
    }

    /**
     * 行番号を返します。
     * 
     * @return 行番号
     */
    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    public String getMessage() {
        return message;
    }

}