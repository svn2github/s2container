package test.org.seasar.framework.container.assembler;

import junit.framework.TestCase;

import org.seasar.framework.container.ArgDef;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.ContainerConstants;
import org.seasar.framework.container.PropertyDef;
import org.seasar.framework.container.assembler.AssemblerFactory;
import org.seasar.framework.container.assembler.AutoConstructorAssembler;
import org.seasar.framework.container.assembler.AutoPropertyAssembler;
import org.seasar.framework.container.assembler.ConstructorAssembler;
import org.seasar.framework.container.assembler.DefaultConstructorAssembler;
import org.seasar.framework.container.assembler.DefaultDestroyMethodAssembler;
import org.seasar.framework.container.assembler.DefaultInitMethodAssembler;
import org.seasar.framework.container.assembler.DefaultPropertyAssembler;
import org.seasar.framework.container.assembler.ManualConstructorAssembler;
import org.seasar.framework.container.assembler.ManualPropertyAssembler;
import org.seasar.framework.container.assembler.MethodAssembler;
import org.seasar.framework.container.assembler.PropertyAssembler;
import org.seasar.framework.container.impl.ArgDefImpl;
import org.seasar.framework.container.impl.ComponentDefImpl;
import org.seasar.framework.container.impl.PropertyDefImpl;

/**
 * @author koichik
 */
public class AssemblerFactoryTest extends TestCase {
    public AssemblerFactoryTest(String name) {
        super(name);
    }

    public void testAutoBindingAuto() throws Exception {
        ComponentDef cd = new ComponentDefImpl();
        cd.setAutoBindingMode(ContainerConstants.AUTO_BINDING_AUTO);

        ConstructorAssembler ca = AssemblerFactory.createConstructorAssembler(cd);
        assertTrue("1", ca instanceof AutoConstructorAssembler);

        PropertyAssembler pa = AssemblerFactory.createPropertyAssembler(cd);
        assertTrue("2", pa instanceof AutoPropertyAssembler);

        MethodAssembler ia = AssemblerFactory.createInitMethodAssembler(cd);
        assertTrue("3", ia instanceof DefaultInitMethodAssembler);

        MethodAssembler da = AssemblerFactory.createDestroyMethodAssembler(cd);
        assertTrue("4", da instanceof DefaultDestroyMethodAssembler);
    }

    public void testAutoBindingProperty() throws Exception {
        ComponentDef cd = new ComponentDefImpl();
        cd.setAutoBindingMode(ContainerConstants.AUTO_BINDING_PROPERTY);

        ConstructorAssembler ca = AssemblerFactory.createConstructorAssembler(cd);
        assertTrue("1", ca instanceof DefaultConstructorAssembler);

        PropertyAssembler pa = AssemblerFactory.createPropertyAssembler(cd);
        assertTrue("2", pa instanceof AutoPropertyAssembler);

        MethodAssembler ia = AssemblerFactory.createInitMethodAssembler(cd);
        assertTrue("3", ia instanceof DefaultInitMethodAssembler);

        MethodAssembler da = AssemblerFactory.createDestroyMethodAssembler(cd);
        assertTrue("4", da instanceof DefaultDestroyMethodAssembler);
    }

    public void testAutoBindingConstructor() throws Exception {
        ComponentDef cd = new ComponentDefImpl();
        cd.setAutoBindingMode(ContainerConstants.AUTO_BINDING_CONSTRUCTOR);

        ConstructorAssembler ca = AssemblerFactory.createConstructorAssembler(cd);
        assertTrue("1", ca instanceof AutoConstructorAssembler);

        PropertyAssembler pa = AssemblerFactory.createPropertyAssembler(cd);
        assertTrue("2", pa instanceof DefaultPropertyAssembler);

        MethodAssembler ia = AssemblerFactory.createInitMethodAssembler(cd);
        assertTrue("3", ia instanceof DefaultInitMethodAssembler);

        MethodAssembler da = AssemblerFactory.createDestroyMethodAssembler(cd);
        assertTrue("4", da instanceof DefaultDestroyMethodAssembler);
    }

    public void testAutoBindingNone() throws Exception {
        ComponentDef cd = new ComponentDefImpl();
        cd.setAutoBindingMode(ContainerConstants.AUTO_BINDING_NONE);

        ConstructorAssembler ca = AssemblerFactory.createConstructorAssembler(cd);
        assertTrue("1", ca instanceof DefaultConstructorAssembler);

        PropertyAssembler pa = AssemblerFactory.createPropertyAssembler(cd);
        assertTrue("2", pa instanceof DefaultPropertyAssembler);

        MethodAssembler ia = AssemblerFactory.createInitMethodAssembler(cd);
        assertTrue("3", ia instanceof DefaultInitMethodAssembler);

        MethodAssembler da = AssemblerFactory.createDestroyMethodAssembler(cd);
        assertTrue("4", da instanceof DefaultDestroyMethodAssembler);
    }

    public void testConstructor() throws Exception {
        ComponentDef cd = new ComponentDefImpl();
        cd.setAutoBindingMode(ContainerConstants.AUTO_BINDING_CONSTRUCTOR);
        cd.addArgDef(new ArgDefImpl(""));
        ConstructorAssembler ca = AssemblerFactory.createConstructorAssembler(cd);
        assertTrue("1", ca instanceof ManualConstructorAssembler);

        cd = new ComponentDefImpl();
        cd.setAutoBindingMode(ContainerConstants.AUTO_BINDING_CONSTRUCTOR);
        ArgDef ad = new ArgDefImpl();
        ad.setExpression("''");
        cd.addArgDef(ad);
        ca = AssemblerFactory.createConstructorAssembler(cd);
        assertTrue("1", ca instanceof ManualConstructorAssembler);
    }

    public void testProperty() throws Exception {
        ComponentDef cd = new ComponentDefImpl();
        cd.setAutoBindingMode(ContainerConstants.AUTO_BINDING_PROPERTY);
        PropertyDef pd = new PropertyDefImpl("foo", "");
        cd.addPropertyDef(pd);
        PropertyAssembler pa = AssemblerFactory.createPropertyAssembler(cd);
        assertTrue("1", pa instanceof AutoPropertyAssembler);

        cd = new ComponentDefImpl();
        cd.setAutoBindingMode(ContainerConstants.AUTO_BINDING_NONE);
        pa = AssemblerFactory.createPropertyAssembler(cd);
        assertTrue("2", pa instanceof DefaultPropertyAssembler);

        pd = new PropertyDefImpl("foo", "");
        cd.addPropertyDef(pd);
        pa = AssemblerFactory.createPropertyAssembler(cd);
        assertTrue("1", pa instanceof ManualPropertyAssembler);
    }
}
