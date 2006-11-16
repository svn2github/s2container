/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
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
package org.seasar.framework.aop.interceptors;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashSet;

import junit.framework.TestCase;

import org.seasar.framework.aop.Aspect;
import org.seasar.framework.aop.Pointcut;
import org.seasar.framework.aop.impl.AspectImpl;
import org.seasar.framework.aop.impl.PointcutImpl;
import org.seasar.framework.aop.proxy.AopProxy;

/**
 * @author higa
 * 
 */
public class TraceInterceptorTest extends TestCase {

    public void testIntercept() throws Exception {
        TraceInterceptor interceptor = new TraceInterceptor();
        Pointcut pointcut = new PointcutImpl(new String[] { "getTime" });
        Aspect aspect = new AspectImpl(interceptor, pointcut);
        AopProxy aopProxy = new AopProxy(Date.class, new Aspect[] { aspect });
        Date proxy = (Date) aopProxy.create();
        proxy.getTime();
    }

    public void testIntercept2() throws Exception {
        TraceInterceptor interceptor = new TraceInterceptor();
        Pointcut pointcut = new PointcutImpl(new String[] { "hoge" });
        Aspect aspect = new AspectImpl(interceptor, pointcut);
        AopProxy aopProxy = new AopProxy(ThrowError.class,
                new Aspect[] { aspect });
        ThrowError proxy = (ThrowError) aopProxy.create();
        try {
            proxy.hoge();
        } catch (Throwable ignore) {
        }
    }

    public void testIntercept3() throws Exception {
        TraceInterceptor interceptor = new TraceInterceptor();
        Pointcut pointcut = new PointcutImpl(new String[] { "geho" });
        Aspect aspect = new AspectImpl(interceptor, pointcut);
        AopProxy aopProxy = new AopProxy(ThrowError.class,
                new Aspect[] { aspect });
        ThrowError proxy = (ThrowError) aopProxy.create();
        proxy.geho(new String[0]);
    }

    public void testIntercept_array() throws Exception {
        TraceInterceptor interceptor = new TraceInterceptor();
        Pointcut pointcut = new PointcutImpl(new String[] { "hoge" });
        Aspect aspect = new AspectImpl(interceptor, pointcut);
        AopProxy aopProxy = new AopProxy(ArrayHoge.class,
                new Aspect[] { aspect });
        ArrayHoge proxy = (ArrayHoge) aopProxy.create();
        proxy.hoge(new String[] { "111" });
    }

    public void testAppendObject() throws Exception {
        TraceInterceptor interceptor = new TraceInterceptor();
        assertEquals("null", interceptor.appendObject(new StringBuffer(), null)
                .toString());
        assertEquals("[abc]", interceptor.appendObject(new StringBuffer(),
                new Object[] { "abc" }).toString());
        assertEquals("[abc, [1], [a, b], [A, B, C]]", interceptor.appendObject(
                new StringBuffer(),
                new Object[] {
                        "abc",
                        new Object[] { "1" },
                        Arrays.asList(new Object[] { "a", "b" }),
                        new LinkedHashSet(Arrays.asList(new Object[] { "A",
                                "B", "C" })) }).toString());
    }

    public void testAppendArray() throws Exception {
        TraceInterceptor interceptor = new TraceInterceptor();
        assertEquals("[[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]]", interceptor
                .appendObject(
                        new StringBuffer(),
                        new Object[] { new Object[] { "1", "2", "3", "4", "5",
                                "6", "7", "8", "9", "10", "11", "12" } })
                .toString());
        interceptor.setMaxLengthOfCollection(11);
        assertEquals("[[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]]", interceptor
                .appendObject(
                        new StringBuffer(),
                        new Object[] { new Object[] { "1", "2", "3", "4", "5",
                                "6", "7", "8", "9", "10", "11", "12" } })
                .toString());
        interceptor.setMaxLengthOfCollection(20);
        assertEquals("[[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]]", interceptor
                .appendObject(
                        new StringBuffer(),
                        new Object[] { new Object[] { "1", "2", "3", "4", "5",
                                "6", "7", "8", "9", "10", "11", "12" } })
                .toString());
    }

    public static class ThrowError {
        public void hoge() {
            throw new RuntimeException("hoge");
        }

        public void geho(String[] array) {
        }
    }

    public static class ArrayHoge {
        public String[] hoge(String[] arg) {
            return new String[] { "aaa", "bbb" };
        }
    }
}