package test.org.seasar.framework.container.factory;

import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.container.AspectDef;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.InstanceDef;
import org.seasar.framework.container.PropertyDef;
import org.seasar.framework.container.deployer.InstanceDefFactory;
import org.seasar.framework.container.factory.TigerAnnotationHandler;

import test.org.seasar.framework.container.factory.ConstantAnnotationHandlerTest.Hoge;

/**
 * @author higa
 */
public class TigerAnnotationHandlerTest extends S2TestCase {

    private TigerAnnotationHandler handler = new TigerAnnotationHandler();

    public void testCreateComponentDef() throws Exception {
        assertNotNull("1", handler.createComponentDef(Hoge.class, null));
        ComponentDef cd = handler.createComponentDef(Hoge2.class, null);
        assertEquals("2", "aaa", cd.getComponentName());
        assertEquals("3", "prototype", cd.getInstanceDef().getName());
        assertEquals("4", "property", cd.getAutoBindingDef().getName());
        ComponentDef cd2 = handler.createComponentDef(Hoge.class, InstanceDefFactory.REQUEST);
        assertEquals("5", InstanceDef.REQUEST_NAME, cd2.getInstanceDef().getName());
    }

    public void testCreatePropertyDef() throws Exception {
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(Hoge.class);
        PropertyDesc propDesc = beanDesc.getPropertyDesc("aaa");
        assertNull("1", handler.createPropertyDef(beanDesc, propDesc));

        beanDesc = BeanDescFactory.getBeanDesc(Hoge2.class);
        propDesc = beanDesc.getPropertyDesc("aaa");
        PropertyDef propDef = handler.createPropertyDef(beanDesc, propDesc);
        assertEquals("2", "aaa", propDef.getPropertyName());
        assertEquals("3", "aaa2", propDef.getExpression());

        propDesc = beanDesc.getPropertyDesc("bbb");
        propDef = handler.createPropertyDef(beanDesc, propDesc);
        assertEquals("4", "none", propDef.getBindingTypeDef().getName());
    }

    public void testCreatePropertyDefForConstantAnnotation() throws Exception {
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(Hoge.class);
        PropertyDesc propDesc = beanDesc.getPropertyDesc("aaa");
        assertNull("1", handler.createPropertyDef(beanDesc, propDesc));

        beanDesc = BeanDescFactory.getBeanDesc(Hoge3.class);
        propDesc = beanDesc.getPropertyDesc("aaa");
        PropertyDef propDef = handler.createPropertyDef(beanDesc, propDesc);
        assertEquals("2", "aaa", propDef.getPropertyName());
        assertEquals("3", "aaa2", propDef.getExpression());

        propDesc = beanDesc.getPropertyDesc("bbb");
        propDef = handler.createPropertyDef(beanDesc, propDesc);
        assertEquals("4", "none", propDef.getBindingTypeDef().getName());
    }

    public void testAppendAspect() throws Exception {
        ComponentDef cd = handler.createComponentDefWithDI(Hoge.class, null);
        assertEquals("1", 1, cd.getAspectDefSize());
        AspectDef aspectDef = cd.getAspectDef(0);
        assertEquals("2", "aop.traceInterceptor", aspectDef.getExpression());
    }
    
    public void testAppendAspectForMethod() throws Exception {
        ComponentDef cd = handler.createComponentDefWithDI(Hoge4.class, null);
        assertEquals("1", 1, cd.getAspectDefSize());
        AspectDef aspectDef = cd.getAspectDef(0);
        assertEquals("2", "aop.traceInterceptor", aspectDef.getExpression());
        assertTrue("3", aspectDef.getPointcut().isApplied(Hoge4.class.getMethod("getAaa")));
        assertFalse("4", aspectDef.getPointcut().isApplied(Hoge4.class.getMethod("getAaa", new Class[] {String.class})));
    }

    public void testAppendAspectForConstantAnnotation() throws Exception {
        ComponentDef cd = handler.createComponentDefWithDI(Hoge3.class, null);
        assertEquals("1", 1, cd.getAspectDefSize());
        AspectDef aspectDef = cd.getAspectDef(0);
        assertEquals("2", "aop.traceInterceptor", aspectDef.getExpression());
    }
}