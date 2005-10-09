package test.org.seasar.framework.container.autoregister;

import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.container.S2Container;

/**
 * @author higa
 */
public class FileSystemComponentAutoRegisterTest extends S2TestCase {

    private S2Container child;
    
    public void setUpRegistAll() throws Exception {
        include("autoRegister.dicon");
    }
    
    public void testRegistAll() throws Exception {
        Foo foo = (Foo) child.getComponent(Foo.class);
        assertNotNull("1", foo);
        Foo2 foo2 = (Foo2) child.getComponent(Foo2.class);
        assertNotNull("2", foo2);
        assertNotNull("3", child.getComponent(Foo3.class));
        assertSame("4", foo2, foo.getFoo2());
        assertNotNull("5", child.getComponent("foo3"));
        assertFalse("6", child.hasComponentDef(Foo4Impl.class));
    }
}