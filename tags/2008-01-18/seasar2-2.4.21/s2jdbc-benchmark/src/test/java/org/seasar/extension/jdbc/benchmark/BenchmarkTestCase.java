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
package org.seasar.extension.jdbc.benchmark;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DecimalFormat;
import java.util.Formatter;

import javax.sql.DataSource;
import javax.transaction.UserTransaction;

import junit.framework.TestCase;
import junit.textui.TestRunner;

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

    private static String fileName;

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
        warmConnection();
    }

    /**
     * 
     * @throws Exception
     */
    protected void warmConnection() throws Exception {
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
        userTransaction = null;
        SingletonS2ContainerFactory.destroy();
        super.tearDown();
        printBenchmark();
    }

    /**
     * 
     * @throws Exception
     */
    protected void printBenchmark() throws Exception {
        DecimalFormat df = new DecimalFormat("#,##0");
        Formatter f = null;
        try {
            OutputStream os =
                fileName != null ? new FileOutputStream(fileName, true)
                    : System.out;
            f = new Formatter(os);
            f.format(
                "%14s (nanoTime) : %s\n",
                df.format(endTime - startTime),
                getClass().getSimpleName());
            f.flush();
        } finally {
            if (fileName != null) {
                f.close();
            }
        }
        System.out.println("\n" + getClass().getName());
    }

    /**
     * 
     * @param clazz
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            throw new IllegalArgumentException("no className");
        }
        String className = args[0];
        if (args.length > 1) {
            fileName = args[1];
        }
        TestRunner.main(new String[] { className });
    }
}
