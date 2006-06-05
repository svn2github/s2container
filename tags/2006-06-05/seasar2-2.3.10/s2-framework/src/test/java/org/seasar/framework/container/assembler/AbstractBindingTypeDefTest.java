package org.seasar.framework.container.assembler;

import junit.framework.TestCase;

import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.impl.S2ContainerImpl;

public class AbstractBindingTypeDefTest extends TestCase {
    public AbstractBindingTypeDefTest() {
    }

    public AbstractBindingTypeDefTest(String name) {
        super(name);
    }

    public void testBindingComponentDef() throws Exception {
        S2Container container = new S2ContainerImpl();
        container.register(ComponentDefAware.class);
        ComponentDef cd = container.getComponentDef(ComponentDefAware.class);
        ComponentDefAware cdAware = (ComponentDefAware) cd.getComponent();
        assertSame("1", cd, cdAware.getComponentDef());
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
}
