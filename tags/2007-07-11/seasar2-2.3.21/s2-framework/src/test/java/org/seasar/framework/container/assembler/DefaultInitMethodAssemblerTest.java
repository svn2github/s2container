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
package org.seasar.framework.container.assembler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import junit.framework.TestCase;

import org.seasar.framework.beans.MethodNotFoundRuntimeException;
import org.seasar.framework.container.ArgDef;
import org.seasar.framework.container.IllegalMethodRuntimeException;
import org.seasar.framework.container.InitMethodDef;
import org.seasar.framework.container.MethodAssembler;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.impl.ArgDefImpl;
import org.seasar.framework.container.impl.ComponentDefImpl;
import org.seasar.framework.container.impl.InitMethodDefImpl;
import org.seasar.framework.container.impl.S2ContainerImpl;

/**
 * @author higa
 * 
 */
public class DefaultInitMethodAssemblerTest extends TestCase {

    public void testAssemble() throws Exception {
        S2Container container = new S2ContainerImpl();
        ComponentDefImpl cd = new ComponentDefImpl(HashMap.class);
        InitMethodDef md = new InitMethodDefImpl("put");
        ArgDef argDef = new ArgDefImpl("aaa");
        md.addArgDef(argDef);
        ArgDef argDef2 = new ArgDefImpl("111");
        md.addArgDef(argDef2);
        cd.addInitMethodDef(md);
        container.register(cd);
        MethodAssembler assembler = new DefaultInitMethodAssembler(cd);
        HashMap map = new HashMap();
        assembler.assemble(map);
        assertEquals("1", "111", map.get("aaa"));
    }

    public void testAssembleForExpression() throws Exception {
        S2Container container = new S2ContainerImpl();
        ComponentDefImpl cd = new ComponentDefImpl(HashMap.class);
        InitMethodDef md = new InitMethodDefImpl();
        md.setExpression("#self.put('aaa', '111')");
        cd.addInitMethodDef(md);
        container.register(cd);
        MethodAssembler assembler = new DefaultInitMethodAssembler(cd);
        HashMap map = new HashMap();
        assembler.assemble(map);
        assertEquals("1", "111", map.get("aaa"));
    }

    public void testAssembleForAuto() throws Exception {
        S2Container container = new S2ContainerImpl();
        ComponentDefImpl cd = new ComponentDefImpl(B.class);
        InitMethodDef md = new InitMethodDefImpl("bbb");
        cd.addInitMethodDef(md);
        container.register(cd);
        container.register(ArrayList.class);
        MethodAssembler assembler = new DefaultInitMethodAssembler(cd);
        B b = new B();
        assembler.assemble(b);
        assertEquals("1", 0, b.getValueSize());
    }

    public void testAssembleIllegalArgument() throws Exception {
        S2Container container = new S2ContainerImpl();
        ComponentDefImpl cd = new ComponentDefImpl(HashMap.class);
        InitMethodDef md = new InitMethodDefImpl("put");
        cd.addInitMethodDef(md);
        container.register(cd);
        MethodAssembler assembler = new DefaultInitMethodAssembler(cd);
        HashMap map = new HashMap();
        try {
            assembler.assemble(map);
            fail("1");
        } catch (MethodNotFoundRuntimeException ex) {
            System.out.println(ex);
        }
    }

    public void testAssembleIllegalArgument2() throws Exception {
        S2Container container = new S2ContainerImpl();
        ComponentDefImpl cd = new ComponentDefImpl(B.class);
        InitMethodDef md = new InitMethodDefImpl("setAaa");
        ArgDef argDef = new ArgDefImpl("aaa");
        md.addArgDef(argDef);
        cd.addInitMethodDef(md);
        container.register(cd);
        MethodAssembler assembler = new DefaultInitMethodAssembler(cd);
        try {
            assembler.assemble(new B());
            fail("1");
        } catch (IllegalMethodRuntimeException ex) {
            System.out.println(ex);
        }
    }

    public void testAssembleField() throws Exception {
        S2Container container = new S2ContainerImpl();
        ComponentDefImpl cd = new ComponentDefImpl(Integer.class);
        InitMethodDef md = new InitMethodDefImpl();
        md.setExpression("#out.println(@Integer@MIN_VALUE)");
        cd.addInitMethodDef(md);
        container.register(cd);
        MethodAssembler assembler = new DefaultInitMethodAssembler(cd);
        assembler.assemble(new Integer(1));
    }

    public void testAssembleWhenComponentNull() throws Exception {
        S2Container container = new S2ContainerImpl();
        ComponentDefImpl cd = new ComponentDefImpl(Integer.class);
        InitMethodDef md = new InitMethodDefImpl();
        md.setExpression("#out.println(@Integer@MIN_VALUE)");
        cd.addInitMethodDef(md);
        container.register(cd);
        MethodAssembler assembler = new DefaultInitMethodAssembler(cd);
        assembler.assemble(null);
    }

    public interface Foo {
        public String getHogeName();
    }

    public static class A implements Foo {

        private Hoge hoge_;

        public A() {
        }

        public Hoge getHoge() {
            return hoge_;
        }

        public void setHoge(Hoge hoge) {
            hoge_ = hoge;
        }

        public String getHogeName() {
            return hoge_.getName();
        }
    }

    public interface Hoge {

        public String getName();
    }

    public static class B implements Hoge {

        private List values_;

        public String getName() {
            return "B";
        }

        public void setAaa(int aaa) {
        }

        public void bbb(List values) {
            values_ = values;
        }

        public int getValueSize() {
            return values_.size();
        }
    }
}