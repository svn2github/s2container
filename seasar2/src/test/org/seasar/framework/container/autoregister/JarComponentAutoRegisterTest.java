package test.org.seasar.framework.container.autoregister;

import java.io.File;

import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.autoregister.JarComponentAutoRegister;

/**
 * @author higa
 */
public class JarComponentAutoRegisterTest extends S2TestCase {

    private S2Container child;
    
    protected void setUp() {
        include("JarComponentAutoRegisterTest.dicon");
    }
    public void testGetBaseDir() throws Exception {
        File file = JarComponentAutoRegister.getBaseDir();
        System.out.println(file);
        assertEquals("1", "lib", file.getName());
        assertEquals("2", "seasar2", file.getParentFile().getName());
    }
    
    public void testRegisterAll() throws Exception {
        assertTrue("1", child.hasComponentDef("testSuite"));
    }
}