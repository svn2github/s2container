package test.org.seasar.framework.container.assembler;

import junit.framework.TestCase;

import org.seasar.framework.container.ArgDef;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.ConstructorAssembler;
import org.seasar.framework.container.PropertyAssembler;
import org.seasar.framework.container.PropertyDef;
import org.seasar.framework.container.binding.AssemblerFacade;
import org.seasar.framework.container.binding.AutoBindingDefFactory;
import org.seasar.framework.container.binding.AutoConstructorAssembler;
import org.seasar.framework.container.binding.AutoPropertyAssembler;
import org.seasar.framework.container.binding.DefaultConstructorAssembler;
import org.seasar.framework.container.binding.DefaultDestroyMethodAssembler;
import org.seasar.framework.container.binding.DefaultInitMethodAssembler;
import org.seasar.framework.container.binding.DefaultPropertyAssembler;
import org.seasar.framework.container.binding.ManualConstructorAssembler;
import org.seasar.framework.container.binding.ManualPropertyAssembler;
import org.seasar.framework.container.binding.MethodAssembler;
import org.seasar.framework.container.impl.ArgDefImpl;
import org.seasar.framework.container.impl.ComponentDefImpl;
import org.seasar.framework.container.impl.PropertyDefImpl;

/**
 * @author koichik
 */
public class AssemblerFacadeTest extends TestCase {
    public AssemblerFacadeTest(String name) {
        super(name);
    }

    public void testAutoBindingAuto() throws Exception {
        ComponentDef cd = new ComponentDefImpl();
        cd.setAutoBindingDef(AutoBindingDefFactory.AUTO);

        ConstructorAssembler ca = AssemblerFacade.createConstructorAssembler(cd);
        assertTrue("1", ca instanceof AutoConstructorAssembler);

        PropertyAssembler pa = AssemblerFacade.createPropertyAssembler(cd);
        assertTrue("2", pa instanceof AutoPropertyAssembler);

        MethodAssembler ia = AssemblerFacade.createInitMethodAssembler(cd);
        assertTrue("3", ia instanceof DefaultInitMethodAssembler);

        MethodAssembler da = AssemblerFacade.createDestroyMethodAssembler(cd);
        assertTrue("4", da instanceof DefaultDestroyMethodAssembler);
    }

    public void testAutoBindingProperty() throws Exception {
        ComponentDef cd = new ComponentDefImpl();
        cd.setAutoBindingDef(AutoBindingDefFactory.PROPERTY);

        ConstructorAssembler ca = AssemblerFacade.createConstructorAssembler(cd);
        assertTrue("1", ca instanceof DefaultConstructorAssembler);

        PropertyAssembler pa = AssemblerFacade.createPropertyAssembler(cd);
        assertTrue("2", pa instanceof AutoPropertyAssembler);

        MethodAssembler ia = AssemblerFacade.createInitMethodAssembler(cd);
        assertTrue("3", ia instanceof DefaultInitMethodAssembler);

        MethodAssembler da = AssemblerFacade.createDestroyMethodAssembler(cd);
        assertTrue("4", da instanceof DefaultDestroyMethodAssembler);
    }

    public void testAutoBindingConstructor() throws Exception {
        ComponentDef cd = new ComponentDefImpl();
        cd.setAutoBindingDef(AutoBindingDefFactory.CONSTRUCTOR);

        ConstructorAssembler ca = AssemblerFacade.createConstructorAssembler(cd);
        assertTrue("1", ca instanceof AutoConstructorAssembler);

        PropertyAssembler pa = AssemblerFacade.createPropertyAssembler(cd);
        assertTrue("2", pa instanceof DefaultPropertyAssembler);

        MethodAssembler ia = AssemblerFacade.createInitMethodAssembler(cd);
        assertTrue("3", ia instanceof DefaultInitMethodAssembler);

        MethodAssembler da = AssemblerFacade.createDestroyMethodAssembler(cd);
        assertTrue("4", da instanceof DefaultDestroyMethodAssembler);
    }

    public void testAutoBindingNone() throws Exception {
        ComponentDef cd = new ComponentDefImpl();
        cd.setAutoBindingDef(AutoBindingDefFactory.NONE);

        ConstructorAssembler ca = AssemblerFacade.createConstructorAssembler(cd);
        assertTrue("1", ca instanceof DefaultConstructorAssembler);

        PropertyAssembler pa = AssemblerFacade.createPropertyAssembler(cd);
        assertTrue("2", pa instanceof DefaultPropertyAssembler);

        MethodAssembler ia = AssemblerFacade.createInitMethodAssembler(cd);
        assertTrue("3", ia instanceof DefaultInitMethodAssembler);

        MethodAssembler da = AssemblerFacade.createDestroyMethodAssembler(cd);
        assertTrue("4", da instanceof DefaultDestroyMethodAssembler);
    }

    public void testConstructor() throws Exception {
        ComponentDef cd = new ComponentDefImpl();
        cd.setAutoBindingDef(AutoBindingDefFactory.CONSTRUCTOR);
        cd.addArgDef(new ArgDefImpl(""));
        ConstructorAssembler ca = AssemblerFacade.createConstructorAssembler(cd);
        assertTrue("1", ca instanceof ManualConstructorAssembler);

        cd = new ComponentDefImpl();
        cd.setAutoBindingDef(AutoBindingDefFactory.CONSTRUCTOR);
        ArgDef ad = new ArgDefImpl();
        ad.setExpression("''");
        cd.addArgDef(ad);
        ca = AssemblerFacade.createConstructorAssembler(cd);
        assertTrue("1", ca instanceof ManualConstructorAssembler);
    }

    public void testProperty() throws Exception {
        ComponentDef cd = new ComponentDefImpl();
        cd.setAutoBindingDef(AutoBindingDefFactory.PROPERTY);
        PropertyDef pd = new PropertyDefImpl("foo", "");
        cd.addPropertyDef(pd);
        PropertyAssembler pa = AssemblerFacade.createPropertyAssembler(cd);
        assertTrue("1", pa instanceof AutoPropertyAssembler);

        cd = new ComponentDefImpl();
        cd.setAutoBindingDef(AutoBindingDefFactory.NONE);
        pa = AssemblerFacade.createPropertyAssembler(cd);
        assertTrue("2", pa instanceof DefaultPropertyAssembler);

        pd = new PropertyDefImpl("foo", "");
        cd.addPropertyDef(pd);
        pa = AssemblerFacade.createPropertyAssembler(cd);
        assertTrue("1", pa instanceof ManualPropertyAssembler);
    }
}
