package org.seasar.framework.container.factory;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.unit.S2FrameworkTestCase;

/**
 * @author higa
 */
public class ComponentTagHandler2Test extends S2FrameworkTestCase {

    private S2Container child;
    
    public void setUp() throws Exception {
        include("ComponentTagHandler2Test.dicon");
    }
    
    public void testNormal() throws Exception {
        Foo foo = (Foo) child.getComponent("foo");
        assertNotNull("1", foo);
        assertEquals("2", "111", foo.getAaa());
        Foo foo2 = (Foo) child.getComponent("foo2");
        assertNotNull("3", foo);
        assertEquals("4", "222", foo2.getAaa());
    }
    
    public void testInterfaceWithAspect() throws Exception {
        Greeting greeting = (Greeting) child.getComponent(Greeting.class);
        assertEquals("1", "Hello", greeting.greet());
    }
}