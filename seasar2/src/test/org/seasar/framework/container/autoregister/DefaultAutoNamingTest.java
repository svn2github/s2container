package test.org.seasar.framework.container.autoregister;

import junit.framework.TestCase;

import org.seasar.framework.container.autoregister.DefaultAutoNaming;

/**
 * @author higa
 */
public class DefaultAutoNamingTest extends TestCase {

    public void testDefineName() throws Exception {
        DefaultAutoNaming naming = new DefaultAutoNaming();
        assertEquals("1", "foo", naming.defineName(null, "Foo"));
        assertEquals("2", "foo4", naming.defineName(null, "Foo4Impl"));
    }
}