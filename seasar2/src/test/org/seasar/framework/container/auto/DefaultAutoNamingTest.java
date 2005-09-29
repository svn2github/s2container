package test.org.seasar.framework.container.auto;

import junit.framework.TestCase;

import org.seasar.framework.container.auto.DefaultAutoNaming;

/**
 * @author higa
 */
public class DefaultAutoNamingTest extends TestCase {

    public void testDefineName() throws Exception {
        DefaultAutoNaming naming = new DefaultAutoNaming();
        assertEquals("1", "foo", naming.defineName("Foo"));
        assertEquals("2", "foo4", naming.defineName("Foo4Impl"));
    }
}