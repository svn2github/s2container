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

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.seasar.extension.jdbc.ValueType;

/**
 * Binary用の {@link ValueType}です。
 * 
 * @author higa
 * 
 */
public class BinaryType implements ValueType {

    public Object getValue(ResultSet resultSet, int index) throws SQLException {
        try {
            return toByteArray(resultSet.getBlob(index));
        } catch (SQLException e) {
            return resultSet.getBytes(index);
        }
    }

    public Object getValue(ResultSet resultSet, String columnName)
            throws SQLException {
        try {
            return toByteArray(resultSet.getBlob(columnName));
        } catch (SQLException e) {
            return resultSet.getBytes(columnName);
        }
    }

    private byte[] toByteArray(Blob blob) throws SQLException {
        if (blob == null) {
            return null;
        }
        long l = blob.length();
        if (Integer.MAX_VALUE < l) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return blob.getBytes(1, (int) l);
    }

    public void bindValue(PreparedStatement ps, int index, Object value)
            throws SQLException {

        if (value == null) {
            ps.setNull(index, Types.BINARY);
        } else if (value instanceof byte[]) {
            byte[] ba = (byte[]) value;
            InputStream in = new ByteArrayInputStream(ba);
            ps.setBinaryStream(index, in, ba.length);
        } else {
            ps.setObject(index, value);
        }
    }
}