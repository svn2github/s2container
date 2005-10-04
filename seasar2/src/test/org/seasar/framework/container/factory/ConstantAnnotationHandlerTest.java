package test.org.seasar.framework.container.factory;

import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.container.AspectDef;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.PropertyDef;
import org.seasar.framework.container.assembler.AutoBindingDefFactory;
import org.seasar.framework.container.deployer.InstanceDefFactory;
import org.seasar.framework.container.factory.ConstantAnnotationHandler;

/**
 * @author higa
 */
public class ConstantAnnotationHandlerTest extends S2TestCase {

    private ConstantAnnotationHandler handler = new ConstantAnnotationHandler();

    public void testCreateComponentDef() throws Exception {
        assertNotNull("1", handler.createComponentDef(Hoge.class));
        ComponentDef cd = handler.createComponentDef(Hoge2.class);
        assertEquals("2", "aaa", cd.getComponentName());
        assertEquals("3", InstanceDefFactory.PROTOTYPE, cd.getInstanceDef());
        assertEquals("4", AutoBindingDefFactory.PROPERTY, cd.getAutoBindingDef());
        try {
            handler.createComponentDef(Hoge3.class);
            fail("5");
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void testCreatePropertyDef() throws Exception {
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(Hoge.class);
        PropertyDesc propDesc = beanDesc.getPropertyDesc("aaa");
        assertNull("1", handler.createPropertyDef(getContainer(), beanDesc,
                propDesc));

        beanDesc = BeanDescFactory.getBeanDesc(Hoge2.class);
        propDesc = beanDesc.getPropertyDesc("aaa");
        PropertyDef propDef = handler.createPropertyDef(getContainer(),
                beanDesc, propDesc);
        assertEquals("2", "aaa", propDef.getPropertyName());
        assertEquals("3", "aaa2", propDef.getExpression());

        propDesc = beanDesc.getPropertyDesc("bbb");
        propDef = handler.createPropertyDef(getContainer(), beanDesc, propDesc);
        assertEquals("4", true, propDef.isNoInject());
    }
    
    public void setUpAppendAspect() {
        include("aop.dicon");
    }

    public void testAppendAspect() throws Exception {
        ComponentDef cd = handler.createComponentDefWithDI(Hoge.class);
        assertEquals("1", 1, cd.getAspectDefSize());
        AspectDef aspectDef = cd.getAspectDef(0);
        assertEquals("2", "aop.traceInterceptor", aspectDef.getExpression());
    }

    public static class Hoge {
        
        public static final String ASPECT = "interceptor=aop.traceInterceptor, pointcut=getAaa";

        public String getAaa() {
            return null;
        }
    }

    public static class Hoge2 {
        public static final String COMPONENT = "name = aaa, instance = prototype, autoBinding = property";

        public static final String aaa_INJECT = "aaa2";

        public static final boolean bbb_No_INJECT = true;

        public void setAaa(String aaa) {
        }

        public void setBbb(String bbb) {
        }
    }

    public static class Hoge3 {
        public static final String COMPONENT = "dummy = aaa";
    }
}