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
package org.seasar.framework.container.assembler;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.PropertyDef;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.impl.ComponentDefImpl;
import org.seasar.framework.container.impl.PropertyDefImpl;
import org.seasar.framework.container.impl.S2ContainerImpl;

public class AbstBindingTypeDefTest extends TestCase {

    public void testBindingComponentDef() throws Exception {
        S2Container container = new S2ContainerImpl();
        container.register(ComponentDefAware.class);
        ComponentDef cd = container.getComponentDef(ComponentDefAware.class);
        ComponentDefAware cdAware = (ComponentDefAware) cd.getComponent();
        assertSame("1", cd, cdAware.getComponentDef());
    }

    public void testBindAutoForField() throws Exception {
        S2Container container = new S2ContainerImpl();
        ComponentDefImpl cd = new ComponentDefImpl(Hoge.class);
        container.register(cd);
        container.register(new ArrayList());
        container.register(new ArrayList());
        Hoge hoge = (Hoge) container.getComponent(Hoge.class);
        assertNull("1", hoge.aaa);
    }

    public void testBindAutoForField2() throws Exception {
        S2Container container = new S2ContainerImpl();
        ComponentDefImpl cd = new ComponentDefImpl(Hoge2.class);
        cd.setAutoBindingDef(AutoBindingDefFactory.SEMIAUTO);
        PropertyDef propDef = new PropertyDefImpl("aaa");
        propDef.setAccessTypeDef(AccessTypeDefFactory.FIELD);
        cd.addPropertyDef(propDef);
        container.register(cd);
        container.register(new Hoge3());
        Hoge2 hoge2 = (Hoge2) container.getComponent(Hoge2.class);
        assertNotNull("1", hoge2.aaa);
    }

    public void testBindAutoForPackagePrefix() throws Exception {
        S2Container container = new S2ContainerImpl();
        ComponentDefImpl cd = new ComponentDefImpl(Hoge.class, "aaa_hoge");
        ComponentDefImpl cd2 = new ComponentDefImpl(Foo.class);
        container.register(cd);
        container.register(cd2);
        Foo foo = (Foo) container.getComponent(Foo.class);
        assertNotNull("1", foo.getHoge());
    }

    public void testBindAutoForArray() throws Exception {
        S2Container container = new S2ContainerImpl();
        container.register(Foo2.class);
        container.register(Hoge3.class);
        container.register(Hoge4.class);
        Foo2 foo2 = (Foo2) container.getComponent(Foo2.class);
        assertNotNull(foo2.getHoges());
    }

    public static class ComponentDefAware {
        private ComponentDef componentDef;

        public ComponentDef getComponentDef() {
            return componentDef;
        }

        public void setComponentDef(ComponentDef componentDef) {
            this.componentDef = componentDef;
        }
    }

    public static class Hoge {
        private List aaa;

        public List getAaa() {
            return aaa;
        }
    }

    public static class Hoge2 {
        private IHoge aaa;
    }

    public static interface IHoge {
    }

    public static class Hoge3 implements IHoge {
    }

    public static class Hoge4 extends Hoge3 {
    }

    public static class Foo {

        private Hoge hoge;

        public Hoge getHoge() {
            return hoge;
        }

        public void setHoge(Hoge hoge) {
            this.hoge = hoge;
        }
    }

    public static class Foo2 {

        private IHoge[] hoges;

        public IHoge[] getHoges() {
            return hoges;
        }

        public void setHoges(IHoge[] hoges) {
            this.hoges = hoges;
        }
    }
}
