package test.org.seasar.framework.container.factory;

import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.container.AspectDef;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.PropertyDef;
import org.seasar.framework.container.factory.Backport175AnnotationHandler;

import test.org.seasar.framework.container.factory.ConstantAnnotationHandlerTest.Hoge;

/**
 * @author higa
 */
public class Backport175AnnotationHandlerTest extends S2TestCase {

    private Backport175AnnotationHandler handler = new Backport175AnnotationHandler();
    
    public void testCreateComponentDef() throws Exception {
        assertNotNull("1", handler.createComponentDef(Hoge.class));
        ComponentDef cd = handler.createComponentDef(Hoge2.class);
        assertEquals("2", "aaa", cd.getComponentName());
        assertEquals("3", "prototype", cd.getInstanceMode());
        assertEquals("4", "property", cd.getAutoBindingMode());
    }
    
    public void testCreatePropertyDef() throws Exception {
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(Hoge.class);
        PropertyDesc propDesc = beanDesc.getPropertyDesc("aaa");
        assertNull("1", handler.createPropertyDef(getContainer(), beanDesc, propDesc));

        beanDesc = BeanDescFactory.getBeanDesc(Hoge2.class);
        propDesc = beanDesc.getPropertyDesc("aaa");
        PropertyDef propDef = handler.createPropertyDef(getContainer(), beanDesc, propDesc);
        assertEquals("2", "aaa", propDef.getPropertyName());
        assertEquals("3", "aaa2", propDef.getExpression());
        
        propDesc = beanDesc.getPropertyDesc("bbb");
        propDef = handler.createPropertyDef(getContainer(), beanDesc, propDesc);
        assertEquals("4", true, propDef.isNoInject());
        
        register(String.class, "ccc");
        propDesc = beanDesc.getPropertyDesc("ccc");
        propDef = handler.createPropertyDef(getContainer(), beanDesc, propDesc);
        assertEquals("5", "ccc", propDef.getExpression());
    }
    
    public void testCreatePropertyDefForConstantAnnotation() throws Exception {
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(Hoge.class);
        PropertyDesc propDesc = beanDesc.getPropertyDesc("aaa");
        assertNull("1", handler.createPropertyDef(getContainer(), beanDesc,
                propDesc));

        beanDesc = BeanDescFactory.getBeanDesc(Hoge3.class);
        propDesc = beanDesc.getPropertyDesc("aaa");
        PropertyDef propDef = handler.createPropertyDef(getContainer(),
                beanDesc, propDesc);
        assertEquals("2", "aaa", propDef.getPropertyName());
        assertEquals("3", "aaa2", propDef.getExpression());

        propDesc = beanDesc.getPropertyDesc("bbb");
        propDef = handler.createPropertyDef(getContainer(), beanDesc, propDesc);
        assertEquals("4", true, propDef.isNoInject());
    }

    public void testAppendAspect() throws Exception {
        ComponentDef cd = handler.createComponentDefWithDI(Hoge.class);
        assertEquals("1", 1, cd.getAspectDefSize());
        AspectDef aspectDef = cd.getAspectDef(0);
        assertEquals("2", "aop.traceInterceptor", aspectDef.getExpression());
    }
    
    public void testAppendAspectForConstantAnnotation() throws Exception {
        ComponentDef cd = handler.createComponentDefWithDI(Hoge3.class);
        assertEquals("1", 1, cd.getAspectDefSize());
        AspectDef aspectDef = cd.getAspectDef(0);
        assertEquals("2", "aop.traceInterceptor", aspectDef.getExpression());
    }
}