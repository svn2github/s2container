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
package org.seasar.extension.jdbc.benchmark;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DecimalFormat;

import javax.sql.DataSource;
import javax.transaction.UserTransaction;

import junit.framework.TestCase;

import org.seasar.extension.jdbc.SqlLogRegistryLocator;
import org.seasar.extension.jdbc.util.ConnectionUtil;
import org.seasar.framework.container.SingletonS2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.util.PreparedStatementUtil;
import org.seasar.framework.util.StatementUtil;

/**
 * @author taedium
 * 
 */
public abstract class BenchmarkTestCase extends TestCase {

    private static final String SQL = "select * from Employee";

    /** */
    protected UserTransaction userTransaction;

    /** */
    protected long startTime;

    /** */
    protected long endTime;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        SingletonS2ContainerFactory.init();
        SqlLogRegistryLocator.setInstance(null);
        userTransaction =
            SingletonS2Container.getComponent(UserTransaction.class);

        DataSource dataSource =
            SingletonS2Container.getComponent(DataSource.class);
        Connection con = dataSource.getConnection();
        try {
            PreparedStatement ps = ConnectionUtil.prepareCall(con, SQL);
            try {
                PreparedStatementUtil.execute(ps);
            } finally {
                StatementUtil.close(ps);
            }
        } finally {
            ConnectionUtil.close(con);
        }
    }

    /**
     * 
     * @throws Exception
     */
    protected void begin() throws Exception {
        userTransaction.begin();
        startTime = System.nanoTime();
    }

    /**
     * 
     * @throws Exception
     */
    protected void end() throws Exception {
        endTime = System.nanoTime();
        userTransaction.rollback();
    }

    @Override
    protected void tearDown() throws Exception {
        printBenchmark();
        userTransaction = null;
        SingletonS2ContainerFactory.destroy();
        super.tearDown();
    }

    /**
     * 
     */
    protected void printBenchmark() {
        DecimalFormat df = new DecimalFormat("#,##0");
        System.out.printf("%14s (nanoTime) : %s\n", df.format(endTime
            - startTime), getClass().getSimpleName());
    }
}
