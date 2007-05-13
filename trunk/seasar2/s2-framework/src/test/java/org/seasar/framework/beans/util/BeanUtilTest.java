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
package org.seasar.framework.beans.util;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

/**
 * @author higa
 * 
 */
public class BeanUtilTest extends TestCase {

    /**
     * @throws Exception
     */
    public void testCopyProperties() throws Exception {
        MyClass src = new MyClass();
        src.setAaa("111");
        src.setCcc("333");

        MyClass2 dest = new MyClass2();
        dest.setAaa("aaa");
        dest.setBbb("bbb");
        dest.setDdd("ddd");

        BeanUtil.copyProperties(src, dest);
        assertEquals("111", dest.getAaa());
        assertNull(dest.getBbb());
        assertEquals("ddd", dest.getDdd());
    }

    /**
     * @throws Exception
     */
    public void testCopyPropertiesWithoutNull() throws Exception {
        MyClass src = new MyClass();
        src.setAaa("111");
        src.setCcc("333");

        MyClass2 dest = new MyClass2();
        dest.setAaa("aaa");
        dest.setBbb("bbb");
        dest.setDdd("ddd");

        BeanUtil.copyProperties(src, dest, false);
        assertEquals("111", dest.getAaa());
        assertEquals("bbb", dest.getBbb());
        assertEquals("ddd", dest.getDdd());
    }

    /**
     * @throws Exception
     */
    public void testCopyToMap() throws Exception {
        HogeDto hoge = new HogeDto();
        hoge.setA("A");
        hoge.setB(true);
        hoge.setC(3);
        Map map = new HashMap();
        BeanUtil.copyProperties(hoge, map);
        assertNotNull(map);
        assertEquals("A", map.get("a"));
        assertEquals(new Boolean(true), map.get("b"));
        assertEquals(new Integer(3), map.get("c"));
    }

    /**
     * @throws Exception
     */
    public void testCopyToBean() throws Exception {
        Map map = new HashMap();
        map.put("a", "A");
        map.put("b", new Boolean(true));
        map.put("c", new Integer(3));
        map.put("d", new Double(1.4));
        HogeDto hoge = new HogeDto();
        BeanUtil.copyProperties(map, hoge);
        assertEquals("A", hoge.getA());
        assertEquals(new Boolean(true), new Boolean(hoge.isB()));
        assertEquals(new Integer(3), new Integer(hoge.getC()));
    }

    /**
     * 
     */
    public static class HogeDto {
        private String a;

        private boolean b;

        private int c;

        /**
         * @return
         */
        public String getA() {
            return a;
        }

        /**
         * @param a
         */
        public void setA(String a) {
            this.a = a;
        }

        /**
         * @return
         */
        public boolean isB() {
            return b;
        }

        /**
         * @param b
         */
        public void setB(boolean b) {
            this.b = b;
        }

        /**
         * @return
         */
        public int getC() {
            return c;
        }

        /**
         * @param c
         */
        public void setC(int c) {
            this.c = c;
        }
    }

    /**
     * 
     */
    public static class MyClass {
        private String aaa;

        private String bbb;

        private String ccc;

        /**
         * @return Returns the aaa.
         */
        public String getAaa() {
            return aaa;
        }

        /**
         * @param aaa
         *            The aaa to set.
         */
        public void setAaa(String aaa) {
            this.aaa = aaa;
        }

        /**
         * @return Returns the bbb.
         */
        public String getBbb() {
            return bbb;
        }

        /**
         * @param bbb
         *            The bbb to set.
         */
        public void setBbb(String bbb) {
            this.bbb = bbb;
        }

        /**
         * @return Returns the ccc.
         */
        public String getCcc() {
            return ccc;
        }

        /**
         * @param ccc
         *            The ccc to set.
         */
        public void setCcc(String ccc) {
            this.ccc = ccc;
        }
    }

    /**
     * 
     */
    public static class MyClass2 {
        private String aaa;

        private String bbb;

        private String ddd;

        /**
         * @return Returns the aaa.
         */
        public String getAaa() {
            return aaa;
        }

        /**
         * @param aaa
         *            The aaa to set.
         */
        public void setAaa(String aaa) {
            this.aaa = aaa;
        }

        /**
         * @return Returns the bbb.
         */
        public String getBbb() {
            return bbb;
        }

        /**
         * @param bbb
         *            The bbb to set.
         */
        public void setBbb(String bbb) {
            this.bbb = bbb;
        }

        /**
         * @return Returns the ddd.
         */
        public String getDdd() {
            return ddd;
        }

        /**
         * @param ddd
         *            The ddd to set.
         */
        public void setDdd(String ddd) {
            this.ddd = ddd;
        }
    }
}
