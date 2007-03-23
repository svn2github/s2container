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
package org.seasar3.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import junit.framework.TestCase;

/**
 * @author higa
 * 
 */
public class ArrayMapTest extends TestCase {

    private ArrayMap<String, String> map;

    /**
     * Test method for {@link ArrayMap#size()}.
     * 
     * @throws Exception
     */
    public void testSize() throws Exception {
        assertEquals("1", 3, map.size());
        map.put("3", "test3");
        assertEquals("2", 4, map.size());
    }

    /**
     * Test method for {@link ArrayMap#isEmpty()}.
     * 
     * @throws Exception
     */
    public void testIsEmpty() throws Exception {
        assertTrue("1", !map.isEmpty());
        map.clear();
        assertTrue("2", map.isEmpty());
    }

    /**
     * Test method for {@link ArrayMap#containsValue(Object)}.
     * 
     * @throws Exception
     */
    public void testContainsValue() throws Exception {
        assertTrue("1", map.containsValue("test2"));
        assertTrue("2", !map.containsValue("test3"));
    }

    /**
     * Test method for {@link ArrayMap#containsKey(Object)}.
     * 
     * @throws Exception
     */
    public void testContainsKey() throws Exception {
        assertTrue("1", map.containsKey("2"));
        assertTrue("2", !map.containsKey("3"));
        map.put("3", null);
        assertTrue("3", map.containsKey("3"));
    }

    /**
     * Test method for {@link ArrayMap#indexOf(Object)}.
     * 
     * @throws Exception
     */
    public void testIndexOf() throws Exception {
        assertEquals("1", 1, map.indexOf("test"));
        assertEquals("1", 0, map.indexOf(null));
        assertEquals("2", -1, map.indexOf("test3"));
    }

    /**
     * Test method for {@link ArrayMap#get(Object)} and
     * {@link ArrayMap#get(int)}.
     * 
     * @throws Exception
     */
    public void testGet() throws Exception {
        assertEquals("1", "test", map.get("1"));
        assertEquals("2", null, map.get(null));
        assertEquals("3", null, map.get("test3"));
        assertEquals("4", null, map.get(0));
    }

    /**
     * Test method for {@link ArrayMap#put(Object, Object)}.
     * 
     * @throws Exception
     */
    public void testPut() throws Exception {
        assertEquals("1", "test", map.put("1", "test3"));
        assertEquals("2", "test3", map.get("1"));
        assertEquals("3", "test3", map.get(1));
        map.put(null, "test4");
        map.put(null, "test5");
    }

    /**
     * Test method for {@link ArrayMap#remove(Object)} and
     * {@link ArrayMap#remove(int)}.
     * 
     * @throws Exception
     */
    public void testRemove() throws Exception {
        assertEquals("1", "test", map.remove("1"));
        assertEquals("2", 2, map.size());
        assertEquals("3", null, map.remove("dummy"));
        assertEquals("4", null, map.remove(0));
    }

    /**
     * Test method for {@link ArrayMap#remove(Object)}.
     * 
     * @throws Exception
     */
    public void testRemove2() throws Exception {
        Map<String, String> m = new ArrayMap<String, String>();
        m.put("1", "d");
        m.remove("1");
        assertEquals("1", false, m.containsKey("1"));
        m.put("1", "d");
        m.remove("1");
        assertEquals("2", false, m.containsKey("1"));
    }

    /**
     * Test method for {@link ArrayMap#remove(Object)}.
     * 
     * @throws Exception
     */
    public void testRemove3() throws Exception {
        Map<MyKey, String> m = new ArrayMap<MyKey, String>();
        m.put(new MyKey("1"), "d");
        m.put(new MyKey("2"), "d");
        m.remove(new MyKey("1"));
        assertEquals("1", false, m.containsKey(new MyKey("1")));
    }

    /**
     * Test method for {@link ArrayMap#remove(Object)}.
     * 
     * @throws Exception
     */
    public void testRemove4() throws Exception {
        ArrayMap<String, String> m = new ArrayMap<String, String>();
        m.put("1", "d");
        m.put("2", "d");
        System.out.println("remove before:" + m);
        m.remove("2");
        System.out.println("remove after:" + m);
        assertEquals("1", false, m.containsKey("2"));
        assertEquals("2", true, m.containsKey("1"));
        assertEquals("3", "d", m.get("1"));
        assertEquals("4", null, m.get("2"));
        assertEquals("5", "d", m.get(0));
    }

    /**
     * Test method for {@link ArrayMap#putAll(Map)}.
     * 
     * @throws Exception
     */
    public void testPutAll() throws Exception {
        Map<String, String> m = new HashMap<String, String>();
        m.put("3", "test3");
        m.put("4", "test4");
        map.putAll(m);
        assertEquals("1", "test3", map.get("3"));
        assertEquals("2", "test4", map.get("4"));
        assertEquals("3", 5, map.size());
    }

    /**
     * Test method for {@link ArrayMap#equals(Object)}.
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void testEqaulas() throws Exception {
        Map<String, String> copy = (ArrayMap<String, String>) map.clone();
        assertTrue("1", map.equals(copy));
        assertTrue("2", !map.equals(null));
        map.put("3", "test3");
        assertTrue("3", !map.equals(copy));
    }

    /**
     * Test method for {@link ArrayMap#toString()}.
     * 
     * @throws Exception
     */
    public void testToString() throws Exception {
        assertNotNull("1", map.toString());
    }

    /**
     * Test method for {@link ArrayMap#clear()}.
     * 
     * @throws Exception
     */
    public void testClear() throws Exception {
        map.clear();
        assertEquals("1", 0, map.size());
    }

    /**
     * Test method for {@link ArrayMap#entrySet()}.
     * 
     * @throws Exception
     */
    public void testEntrySet() throws Exception {
        Iterator i = map.entrySet().iterator();
        assertEquals("1", null, ((Map.Entry) i.next()).getKey());
        assertEquals("2", "1", ((Map.Entry) i.next()).getKey());
        assertEquals("1", "2", ((Map.Entry) i.next()).getKey());
    }

    protected void setUp() throws Exception {
        map = new ArrayMap<String, String>();
        map.put(null, null);
        map.put("1", "test");
        map.put("2", "test2");
    }

    protected void tearDown() throws Exception {
        map = null;
    }

    private static class MyKey {
        Object _key;

        MyKey(Object key) {
            _key = key;
        }

        public int hashCode() {
            return 0;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (o == null || !(o instanceof MyKey)) {
                return false;
            }
            return _key.equals(((MyKey) o)._key);
        }
    }
}