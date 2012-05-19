/*
 * Copyright 2004-2012 the Seasar Foundation and the Others.
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
package org.seasar.extension.jdbc.it.util.dialect;

import java.util.Arrays;

/**
 * MS SQL Server 2005用の方言です。
 * 
 * @author taedium
 */
public class Mssql2005Dialect extends StandardDialect {

    @Override
    public String getSqlBlockDelimiter() {
        return "go";
    }

    @Override
    public SqlBlockContext createSqlBlockContext() {
        return new MssqlSqlBlockContext();
    }

    /**
     * MS SQL Server 2005用の{@link SqlBlockContext}の実装クラスです。
     * 
     * @author taedium
     * 
     */
    public static class MssqlSqlBlockContext extends StandardSqlBlockContext {

        /**
         * インスタンスを構築します。
         */
        protected MssqlSqlBlockContext() {
            sqlBlockStartKeywordsList.add(Arrays.asList("create", "procedure"));
            sqlBlockStartKeywordsList.add(Arrays.asList("create", "function"));
            sqlBlockStartKeywordsList.add(Arrays.asList("create", "trigger"));
            sqlBlockStartKeywordsList.add(Arrays.asList("alter", "procedure"));
            sqlBlockStartKeywordsList.add(Arrays.asList("alter", "function"));
            sqlBlockStartKeywordsList.add(Arrays.asList("alter", "trigger"));
            sqlBlockStartKeywordsList.add(Arrays.asList("declare"));
            sqlBlockStartKeywordsList.add(Arrays.asList("begin"));
        }
    }
}
