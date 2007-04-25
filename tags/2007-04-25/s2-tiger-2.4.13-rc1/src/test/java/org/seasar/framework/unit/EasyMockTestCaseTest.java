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
package org.seasar.framework.unit;

import static org.easymock.EasyMock.expect;

import java.util.Map;

import org.seasar.framework.unit.annotation.EasyMock;
import org.seasar.framework.unit.annotation.EasyMockType;

/**
 * @author koichik
 * 
 */
public class EasyMockTestCaseTest extends EasyMockTestCase {
    @EasyMock
    private Runnable runnable;

    @EasyMock(EasyMockType.STRICT)
    private Map<String, String> map;

    public void testRunnable() {
        runnable.run();
    }

    public void recordRunnable() {
        runnable.run();
    }

    public void testMap() throws Exception {
        map.put("a", "A");
        map.put("b", "B");
        assertEquals(2, map.size());
    }

    public void recordMap() throws Exception {
        expect(map.put("a", "A")).andReturn(null);
        expect(map.put("b", "B")).andReturn(null);
        expect(map.size()).andReturn(2);
    }

    public void testOldStyle() throws Exception {
        new Subsequence() {
            @Override
            protected void replay() throws Exception {
                map.put("a", "A");
                assertEquals(1, map.size());
            }

            @Override
            protected void record() throws Exception {
                expect(map.put("a", "A")).andReturn(null);
                expect(map.size()).andReturn(1);
            }
        }.doTest();
    }

}
