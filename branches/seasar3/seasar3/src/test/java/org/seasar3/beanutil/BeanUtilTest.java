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
package org.seasar3.beanutil;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

/**
 * @author higa
 * 
 */
public class BeanUtilTest extends TestCase {

    List<String> list;

    @SuppressWarnings("unchecked")
    List list2;

    Map<String, String> map;

    Map<String, String>[] maps;

    /**
     * 
     * @throws Exception
     */
    public void testGetRawClass() throws Exception {
        assertEquals(List.class, BeanUtil.getRawClass(getClass()
                .getDeclaredField("list").getGenericType()));
    }

    /**
     * 
     * @throws Exception
     */
    public void testGetRawClassForArray() throws Exception {
        assertEquals(new Map[0].getClass(), BeanUtil.getRawClass(getClass()
                .getDeclaredField("maps").getGenericType()));
    }

    /**
     * 
     * @throws Exception
     */
    public void testGetRawClassForNotParameterizedClass() throws Exception {
        assertEquals(List.class, BeanUtil.getRawClass(getClass()
                .getDeclaredField("list2").getGenericType()));
    }

    /**
     * 
     * @throws Exception
     */
    public void testTypeArguments() throws Exception {
        Type[] arguments = BeanUtil.getTypeArguments(getClass()
                .getDeclaredField("list").getGenericType());
        assertEquals(1, arguments.length);
        assertEquals(String.class, BeanUtil.getRawClass(arguments[0]));
    }

    /**
     * 
     * @throws Exception
     */
    public void testTypeArguments2() throws Exception {
        Type[] arguments = BeanUtil.getTypeArguments(getClass()
                .getDeclaredField("map").getGenericType());
        assertEquals(2, arguments.length);
        assertEquals(String.class, BeanUtil.getRawClass(arguments[0]));
        assertEquals(String.class, BeanUtil.getRawClass(arguments[1]));
    }

    /**
     * 
     * @throws Exception
     */
    public void testTypeArgumentsForArray() throws Exception {
        Type[] arguments = BeanUtil.getTypeArguments(getClass()
                .getDeclaredField("maps").getGenericType());
        assertEquals(2, arguments.length);
        assertEquals(String.class, BeanUtil.getRawClass(arguments[0]));
        assertEquals(String.class, BeanUtil.getRawClass(arguments[1]));
    }

    /**
     * 
     * @throws Exception
     */
    public void testTypeArgumentsForNotParameterizedClass() throws Exception {
        Type[] arguments = BeanUtil.getTypeArguments(getClass()
                .getDeclaredField("list2").getGenericType());
        assertNull(arguments);
    }

    /**
     * 
     * @throws Exception
     */
    public void testCreateParameterizedClassDesc() throws Exception {
        ParameterizedClassDesc pcd = BeanUtil
                .createParameterizedClassDesc(getClass().getDeclaredField(
                        "list").getGenericType());
        assertNotNull(pcd);
        assertEquals(List.class, pcd.getRawClass());
        assertEquals(1, pcd.getArguments().length);
        assertEquals(String.class, pcd.getArguments()[0].getRawClass());
    }
}
