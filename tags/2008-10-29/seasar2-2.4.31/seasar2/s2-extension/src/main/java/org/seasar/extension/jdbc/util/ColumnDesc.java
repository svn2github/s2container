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
package org.seasar.extension.jdbc.util;

import java.sql.Types;

/**
 * データベースメタデータから取得したカラム情報を格納するクラスです。
 * 
 * @author higa
 * 
 */
public class ColumnDesc {

    private String name;

    private int sqlType;

    /**
     * 名前を返します。
     * 
     * @return 名前
     */
    public String getName() {
        return name;
    }

    /**
     * 名前を設定します。
     * 
     * @param name
     *            名前
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * JDBCで定義されている型を返します。
     * 
     * @return JDBCで定義されている型
     * @see Types
     */
    public int getSqlType() {
        return sqlType;
    }

    /**
     * JDBCで定義されている型を設定します。
     * 
     * @param sqlType
     *            JDBCで定義されている型
     * @see Types
     */
    public void setSqlType(int sqlType) {
        this.sqlType = sqlType;
    }

    public int hashCode() {
        return name.hashCode();
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ColumnDesc)) {
            return false;
        }
        ColumnDesc other = (ColumnDesc) o;
        return name.equals(other.name);
    }

    public String toString() {
        return "{name:" + name + ", type:" + sqlType + "}";
    }
}
