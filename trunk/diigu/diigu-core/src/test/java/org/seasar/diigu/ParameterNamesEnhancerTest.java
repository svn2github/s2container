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
package org.seasar.diigu;

import junit.framework.TestCase;

import org.seasar.diigu.test.Bar;
import org.seasar.diigu.test.Baz;
import org.seasar.diigu.test.Foo;
import org.seasar.diigu.test.Outer;
import org.seasar.diigu.test.OuterClass;
import org.seasar.diigu.test.Outer.Inner;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;

/**
 * @author koichik
 */
public class ParameterNamesEnhancerTest extends TestCase {
    protected static final String PKG = "org.seasar.diigu.test.";

    protected void setUp() throws Exception {
        super.setUp();

        ParameterNameEnhancer enhancer = new ParameterNameEnhancer(PKG + "Foo");
        enhancer.setMethodParameterNames("hoge", new String[] { "boolean",
                "byte[]", "java.lang.String" }, new String[] { "flag", "bytes",
                "str" });
        enhancer.save();

        enhancer = new ParameterNameEnhancer(PKG + "Bar");
        enhancer.setConstructorParameterNames(new String[] { PKG + "Foo",
                PKG + "Bar", PKG + "Baz" },
                new String[] { "foo", "bar", "baz" });
        enhancer.setMethodParameterNames("array", new String[] { "int[]",
                "int[][]", "int[][][]" }, new String[] { "a1", "a2", "a3" });
        enhancer.save();

        enhancer = new ParameterNameEnhancer("org.seasar.diigu.test.Baz");
        enhancer.setConstructorParameterNames(new String[] { PKG + "Foo",
                PKG + "Bar", PKG + "Baz" }, new String[] { "$foo", "$bar",
                "$baz" });
        enhancer.setMethodParameterNames("array", new String[] { "int[]",
                "int[][]", "int[][][]" }, new String[] { "array1", "array2",
                "array3" });
        enhancer.save();

        enhancer = new ParameterNameEnhancer(
                "org.seasar.diigu.test.Outer$Inner");
        enhancer.setMethodParameterNames("hoge", new String[] { PKG + "Outer",
                PKG + "Outer$Inner" }, new String[] { "outer", "inner" });
        enhancer.save();

        enhancer = new ParameterNameEnhancer(
                "org.seasar.diigu.test.Outer$Inner$InnerInner");
        enhancer.setMethodParameterNames("hoge", new String[] { PKG + "Outer",
                PKG + "Outer$Inner" }, new String[] { "outer", "inner" });
        enhancer.save();

        enhancer = new ParameterNameEnhancer(
                "org.seasar.diigu.test.Outer$Inner2");
        enhancer.setConstructorParameterNames(new String[] {
                "java.lang.String", "int" }, new String[] { "name", "hoge" });
        enhancer.save();

        enhancer = new ParameterNameEnhancer(
                "org.seasar.diigu.test.Outer$Inner2$InnerInner");
        enhancer.setMethodParameterNames("moge", new String[] { PKG + "Outer",
                PKG + "Outer$Inner" }, new String[] { "outer", "inner" });
        enhancer.save();

    }

    public void testInterface() throws Exception {
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(Foo.class);
        String[] names = beanDesc.getMethodParameterNames("hoge", new Class[] {
                boolean.class, byte[].class, String.class });
        assertNotNull(names);
        assertEquals(3, names.length);
        assertEquals("flag", names[0]);
        assertEquals("bytes", names[1]);
        assertEquals("str", names[2]);
    }

    public void testAbstractClass() throws Exception {
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(Bar.class);
        String[] names = beanDesc.getConstructorParameterNames(new Class[] {
                Foo.class, Bar.class, Baz.class });
        assertEquals(3, names.length);
        assertEquals("foo", names[0]);
        assertEquals("bar", names[1]);
        assertEquals("baz", names[2]);

        names = beanDesc.getMethodParameterNames("array", new Class[] {
                int[].class, int[][].class, int[][][].class });
        assertNotNull(names);
        assertEquals(3, names.length);
        assertEquals("a1", names[0]);
        assertEquals("a2", names[1]);
        assertEquals("a3", names[2]);
    }

    public void testConcreteClass() throws Exception {
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(Baz.class);
        String[] names = beanDesc.getConstructorParameterNames(new Class[] {
                Foo.class, Bar.class, Baz.class });
        assertEquals(3, names.length);
        assertEquals("$foo", names[0]);
        assertEquals("$bar", names[1]);
        assertEquals("$baz", names[2]);

        names = beanDesc.getMethodParameterNames("array", new Class[] {
                int[].class, int[][].class, int[][][].class });
        assertNotNull(names);
        assertEquals(3, names.length);
        assertEquals("array1", names[0]);
        assertEquals("array2", names[1]);
        assertEquals("array3", names[2]);
    }

    public void testInnerType() throws Exception {
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(Outer.Inner.class);
        String[] names = beanDesc.getMethodParameterNames("hoge", new Class[] {
                Outer.class, Inner.class });
        assertNotNull(names);
        assertEquals(2, names.length);
        assertEquals("outer", names[0]);
        assertEquals("inner", names[1]);

        beanDesc = BeanDescFactory.getBeanDesc(Outer.Inner.InnerInner.class);
        names = beanDesc.getMethodParameterNames("hoge", new Class[] {
                Outer.class, Inner.class });
        assertNotNull(names);
        assertEquals(2, names.length);
        assertEquals("outer", names[0]);
        assertEquals("inner", names[1]);

        beanDesc = BeanDescFactory.getBeanDesc(Outer.Inner2.class);
        names = beanDesc.getConstructorParameterNames(new Class[] {
                String.class, int.class });
        assertNotNull(names);
        assertEquals(2, names.length);
        assertEquals("name", names[0]);
        assertEquals("hoge", names[1]);

        beanDesc = BeanDescFactory.getBeanDesc(Outer.Inner2.InnerInner.class);
        names = beanDesc.getMethodParameterNames("moge", new Class[] {
                Outer.class, Inner.class });
        assertNotNull(names);
        assertEquals(2, names.length);
        assertEquals("outer", names[0]);
        assertEquals("inner", names[1]);
    }

    public void testInnerType2() throws Exception {
        setUpInnerType2();

        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(OuterClass.Inner.class);
        String[] names = beanDesc.getMethodParameterNames("hoge", new Class[] {
                Outer.class, OuterClass.Inner.class });
        assertNotNull(names);
        assertEquals(2, names.length);
        assertEquals("outer", names[0]);
        assertEquals("inner", names[1]);

        beanDesc = BeanDescFactory
                .getBeanDesc(OuterClass.Inner.InnerInner.class);
        names = beanDesc.getMethodParameterNames("hoge", new Class[] {
                Outer.class, OuterClass.Inner.class });
        assertNotNull(names);
        assertEquals(2, names.length);
        assertEquals("outer", names[0]);
        assertEquals("inner", names[1]);

        beanDesc = BeanDescFactory.getBeanDesc(OuterClass.Inner2.class);
        names = beanDesc.getConstructorParameterNames(new Class[] {
                OuterClass.class, String.class, int.class });
        assertNotNull(names);
        assertEquals(3, names.length);
        assertEquals("this", names[0]);
        assertEquals("name", names[1]);
        assertEquals("hoge", names[2]);

        beanDesc = BeanDescFactory
                .getBeanDesc(OuterClass.Inner2.InnerInner.class);
        names = beanDesc.getMethodParameterNames("moge", new Class[] {
                Outer.class, OuterClass.Inner.class });
        assertNotNull(names);
        assertEquals(2, names.length);
        assertEquals("outer", names[0]);
        assertEquals("inner", names[1]);
    }

    /**
     * 
     */
    private void setUpInnerType2() {
        ParameterNameEnhancer enhancer = null;
        enhancer = new ParameterNameEnhancer(
                "org.seasar.diigu.test.OuterClass$Inner");
        enhancer.setMethodParameterNames("hoge", new String[] { PKG + "Outer",
                PKG + "OuterClass$Inner" }, new String[] { "outer", "inner" });
        enhancer.save();

        enhancer = new ParameterNameEnhancer(
                "org.seasar.diigu.test.OuterClass$Inner$InnerInner");
        enhancer.setMethodParameterNames("hoge", new String[] { PKG + "Outer",
                PKG + "OuterClass$Inner" }, new String[] { "outer", "inner" });
        enhancer.save();

        enhancer = new ParameterNameEnhancer(
                "org.seasar.diigu.test.OuterClass$Inner2");
        enhancer.setConstructorParameterNames(
                new String[] { "org.seasar.diigu.test.OuterClass",
                        "java.lang.String", "int" }, new String[] { "this",
                        "name", "hoge" });
        enhancer.save();

        enhancer = new ParameterNameEnhancer(
                "org.seasar.diigu.test.OuterClass$Inner2$InnerInner");
        enhancer.setMethodParameterNames("moge", new String[] { PKG + "Outer",
                PKG + "OuterClass$Inner" }, new String[] { "outer", "inner" });
        enhancer.save();
    }

}
