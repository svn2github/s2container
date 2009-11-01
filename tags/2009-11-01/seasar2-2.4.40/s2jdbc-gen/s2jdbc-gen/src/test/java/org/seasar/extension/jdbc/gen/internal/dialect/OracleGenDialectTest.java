/*
 * Copyright 2004-2009 the Seasar Foundation and the Others.
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
package org.seasar.extension.jdbc.gen.internal.dialect;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Types;

import org.junit.Test;
import org.seasar.extension.jdbc.gen.dialect.GenDialect.ColumnType;
import org.seasar.extension.jdbc.gen.dialect.GenDialect.SqlBlockContext;
import org.seasar.extension.jdbc.gen.sqltype.SqlType;

import static org.junit.Assert.*;

/**
 * @author taedium
 * 
 */
public class OracleGenDialectTest {

    private OracleGenDialect dialect = new OracleGenDialect();

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testGetColumnType_number() throws Exception {
        ColumnType type = dialect.getColumnType("NUMBER", Types.OTHER);
        assertEquals("number(10,5)", type.getColumnDefinition(0, 10, 5, null));
        assertEquals(Integer.class, type.getAttributeClass(0, 9, 0));
        assertEquals(BigDecimal.class, type.getAttributeClass(0, 9, 2));
        assertEquals(Long.class, type.getAttributeClass(0, 10, 0));
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testGetColumnType_varchar2() throws Exception {
        ColumnType type = dialect.getColumnType("varchar2", Types.OTHER);
        assertEquals("varchar2(10)", type.getColumnDefinition(10, 0, 0, null));
        assertEquals(String.class, type.getAttributeClass(10, 0, 0));
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testGetColumnType_timestamp() throws Exception {
        ColumnType type = dialect.getColumnType("timestamp(", Types.OTHER);
        assertEquals("timestamp(5)", type.getColumnDefinition(0, 0, 5, null));
        assertEquals(Timestamp.class, type.getAttributeClass(0, 0, 5));
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testGetSqlType_binary() throws Exception {
        SqlType type = dialect.getSqlType(Types.BINARY);
        assertEquals("raw(2000)", type.getDataType(2000, 0, 0, false));
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testGetSqlType_varchar() throws Exception {
        SqlType type = dialect.getSqlType(Types.VARCHAR);
        assertEquals("varchar2(4000)", type.getDataType(4000, 0, 0, false));
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testGetSqlType_bigint() throws Exception {
        SqlType type = dialect.getSqlType(Types.BIGINT);
        assertEquals("number(10,0)", type.getDataType(0, 10, 0, false));
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testCreateSqlBlockContext() throws Exception {
        SqlBlockContext context = dialect.createSqlBlockContext();
        context.addKeyword("create");
        context.addKeyword("or");
        context.addKeyword("replace");
        context.addKeyword("procedure");
        assertTrue(context.isInSqlBlock());

        context = dialect.createSqlBlockContext();
        context.addKeyword("drop");
        assertFalse(context.isInSqlBlock());

        context = dialect.createSqlBlockContext();
        context.addKeyword("begin");
        assertTrue(context.isInSqlBlock());
    }
}
