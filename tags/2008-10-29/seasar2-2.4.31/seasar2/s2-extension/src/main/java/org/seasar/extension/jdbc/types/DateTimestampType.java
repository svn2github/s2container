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
package org.seasar.extension.jdbc.types;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.seasar.extension.jdbc.ValueType;
import org.seasar.framework.util.DateConversionUtil;

/**
 * {@link Timestamp}と互換性をもつ{@link Date}用の{@link ValueType}です。
 * 
 * @author taedium
 * 
 */
public class DateTimestampType extends TimestampType {

    public Object getValue(ResultSet resultSet, int index) throws SQLException {
        return toDate(super.getValue(resultSet, index));
    }

    public Object getValue(ResultSet resultSet, String columnName)
            throws SQLException {
        return toDate(super.getValue(resultSet, columnName));
    }

    public Object getValue(CallableStatement cs, int index) throws SQLException {
        return toDate(super.getValue(cs, index));
    }

    public Object getValue(CallableStatement cs, String parameterName)
            throws SQLException {
        return toDate(super.getValue(cs, parameterName));
    }

    /**
     * {@link Date}に変換します。
     * 
     * @param value
     *            値
     * @return {@link Date}
     */
    protected Date toDate(Object value) {
        return DateConversionUtil.toDate(value);
    }
}
