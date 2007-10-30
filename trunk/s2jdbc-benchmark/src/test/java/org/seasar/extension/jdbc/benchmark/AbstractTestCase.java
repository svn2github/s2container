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

import java.text.DecimalFormat;

import javax.transaction.UserTransaction;

import junit.framework.TestCase;

import org.seasar.framework.container.SingletonS2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * @author taedium
 * 
 */
public abstract class AbstractTestCase extends TestCase {

    private UserTransaction userTransaction;

    private long startTime;

    private long endTime;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        SingletonS2ContainerFactory.init();
        userTransaction =
            SingletonS2Container.getComponent(UserTransaction.class);
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
