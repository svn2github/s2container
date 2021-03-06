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
package org.seasar.extension.jdbc.types;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.seasar.extension.jdbc.ValueType;
import org.seasar.framework.util.InputStreamUtil;

/**
 * BinaryStream用の {@link ValueType}です。
 * 
 * @author higa
 * 
 */
public class BinaryStreamType implements ValueType {

    public Object getValue(ResultSet resultSet, int index) throws SQLException {
        return resultSet.getBinaryStream(index);
    }

    public Object getValue(ResultSet resultSet, String columnName)
            throws SQLException {

        return resultSet.getBinaryStream(columnName);
    }

    public void bindValue(PreparedStatement ps, int index, Object value)
            throws SQLException {

        if (value == null) {
            ps.setNull(index, Types.BINARY);
        } else if (value instanceof InputStream) {
            InputStream is = (InputStream) value;
            ps.setBinaryStream(index, is, InputStreamUtil.available(is));
        } else {
            ps.setObject(index, value);
        }
    }
}