package test.org.seasar.framework.container.assembler;

import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.container.AutoBindingNotFoundRuntimeException;
import org.seasar.framework.container.assembler.AutoBindingDefFactory;

/**
 * @author higa
 */
public class AutoBindingDefFactoryTest extends S2TestCase {
    
    public void testGetAutoBindingDef() throws Exception {
        assertEquals("1", AutoBindingDefFactory.AUTO,
                AutoBindingDefFactory.getAutoBindingDef("auto"));
        try {
            AutoBindingDefFactory.getAutoBindingDef("hoge");
            fail("2");
        } catch (AutoBindingNotFoundRuntimeException ex) {
            System.out.println(ex);
        }
    }
}