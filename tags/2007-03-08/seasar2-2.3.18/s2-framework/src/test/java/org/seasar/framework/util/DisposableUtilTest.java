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
package org.seasar.framework.util;

import junit.framework.TestCase;

/**
 * @author koichik
 * 
 */
public class DisposableUtilTest extends TestCase {

    private int count;

    protected void setUp() throws Exception {
        DisposableUtil.dispose();
    }

    public void test1() throws Exception {
        DisposableUtil.add(new TestDisposable());
        assertEquals(1, DisposableUtil.disposables.size());
        DisposableUtil.dispose();
        assertEquals(1, count);
        assertEquals(0, DisposableUtil.disposables.size());
    }

    public void test2() throws Exception {
        DisposableUtil.add(new TestDisposable());
        DisposableUtil.add(new TestDisposable());
        assertEquals(2, DisposableUtil.disposables.size());
        DisposableUtil.dispose();
        assertEquals(2, count);
        assertEquals(0, DisposableUtil.disposables.size());
    }

    public void test3() throws Exception {
        DisposableUtil.add(new TestDisposable());
        DisposableUtil.add(new TestDisposable2());
        DisposableUtil.add(new TestDisposable());
        assertEquals(3, DisposableUtil.disposables.size());
        DisposableUtil.dispose();
        assertEquals(3, count);
        assertEquals(0, DisposableUtil.disposables.size());
    }

    public class TestDisposable implements Disposable {
        public void dispose() {
            ++count;
        }
    }

    public class TestDisposable2 implements Disposable {
        public void dispose() {
            ++count;
            throw new RuntimeException();
        }
    }
}
