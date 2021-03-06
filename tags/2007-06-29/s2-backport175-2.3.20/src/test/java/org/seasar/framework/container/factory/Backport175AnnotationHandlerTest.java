package org.seasar.framework.container.factory;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.container.AspectDef;
import org.seasar.framework.container.AutoBindingDef;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.IllegalInitMethodAnnotationRuntimeException;
import org.seasar.framework.container.InitMethodDef;
import org.seasar.framework.container.InstanceDef;
import org.seasar.framework.container.PropertyDef;
import org.seasar.framework.container.assembler.AutoBindingDefFactory;
import org.seasar.framework.container.assembler.BindingTypeDefFactory;
import org.seasar.framework.container.deployer.InstanceDefFactory;
import org.seasar.framework.unit.S2FrameworkTestCase;
import org.seasar.framework.util.ResourceUtil;

/**
 * @author higa
 */
public class Backport175AnnotationHandlerTest extends S2FrameworkTestCase {

    private Backport175AnnotationHandler handler = new Backport175AnnotationHandler();

    public void testCreateComponentDef() throws Exception {
        assertNotNull("1", handler.createComponentDef(Hoge.class, null));
        ComponentDef cd = handler.createComponentDef(Hoge2.class, null);
        assertEquals("2", "aaa", cd.getComponentName());
        assertEquals("3", "prototype", cd.getInstanceDef().getName());
        assertEquals("4", "property", cd.getAutoBindingDef().getName());
        ComponentDef cd2 = handler.createComponentDef(Hoge.class, InstanceDefFactory.REQUEST);
        assertEquals("5", InstanceDef.REQUEST_NAME, cd2.getInstanceDef().getName());

        ComponentDef cd3 = handler.createComponentDef(Hoge.class, InstanceDefFactory.REQUEST,
                AutoBindingDefFactory.NONE);
        assertEquals("6", AutoBindingDef.NONE_NAME, cd3.getAutoBindingDef().getName());

        ComponentDef cd4 = handler.createComponentDef(Hoge2.class, InstanceDefFactory.REQUEST,
                AutoBindingDefFactory.NONE);
        assertEquals("7", AutoBindingDef.PROPERTY_NAME, cd4.getAutoBindingDef().getName());

        ComponentDef cd5 = handler.createComponentDef(Hoge3.class, InstanceDefFactory.REQUEST,
                AutoBindingDefFactory.NONE);
        assertEquals("8", AutoBindingDef.PROPERTY_NAME, cd5.getAutoBindingDef().getName());
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
        assertEquals("4", BindingTypeDefFactory.NONE.getName(), propDef.getBindingTypeDef()
                .getName());
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
        assertEquals("4", BindingTypeDefFactory.NONE.getName(), propDef.getBindingTypeDef()
                .getName());
    }

    public void testAppendAspect() throws Exception {
        ComponentDef cd = handler.createComponentDef(Hoge.class, null);
        handler.appendAspect(cd);
        assertEquals("1", 1, cd.getAspectDefSize());
        AspectDef aspectDef = cd.getAspectDef(0);
        assertEquals("2", "aop.traceInterceptor", aspectDef.getExpression());
    }

    public void testAppendAspectForMethod() throws Exception {
        ComponentDef cd = handler.createComponentDef(Hoge4.class, null);
        handler.appendAspect(cd);
        assertEquals("1", 1, cd.getAspectDefSize());
        AspectDef aspectDef = cd.getAspectDef(0);
        assertEquals("2", "aop.traceInterceptor", aspectDef.getExpression());
        assertTrue("3", aspectDef.getPointcut().isApplied(Hoge4.class.getMethod("getAaa", null)));
        assertFalse("4", aspectDef.getPointcut().isApplied(
                Hoge4.class.getMethod("getAaa", new Class[] { String.class })));
    }

    public void testAppendAspectForConstantAnnotation() throws Exception {
        ComponentDef cd = handler.createComponentDef(Hoge3.class, null);
        handler.appendAspect(cd);
        assertEquals("1", 1, cd.getAspectDefSize());
        AspectDef aspectDef = cd.getAspectDef(0);
        assertEquals("2", "aop.traceInterceptor", aspectDef.getExpression());
    }

    public void testAppendInitMethod() throws Exception {
        ComponentDef cd = handler.createComponentDef(Hoge.class, null);
        handler.appendInitMethod(cd);
        assertEquals("1", 1, cd.getInitMethodDefSize());
        InitMethodDef initMethodDef = cd.getInitMethodDef(0);
        assertEquals("2", "init", initMethodDef.getMethodName());
    }

    public void testAppendInitMethodForConstantAnnotation() throws Exception {
        ComponentDef cd = handler.createComponentDef(Hoge3.class, null);
        handler.appendInitMethod(cd);
        assertEquals("1", 1, cd.getInitMethodDefSize());
        InitMethodDef initMethodDef = cd.getInitMethodDef(0);
        assertEquals("2", "init", initMethodDef.getMethodName());
    }

    public void setUpAppendInitMethodForDicon() throws Exception {
        System.out.println(ResourceUtil.getResourceAsFile(getClass().getName().replace('.', '/')
                + ".dicon"));
        include("Backport175AnnotationHandlerTest.dicon");
    }

    public void testAppendInitMethodForDicon() throws Exception {
        ComponentDef cd = getComponentDef(Hoge5.class);
        assertEquals("1", 1, cd.getInitMethodDefSize());
    }

    public void testAppendInitMethodForException() throws Exception {
        ComponentDef cd = handler.createComponentDef(Hoge6.class, null);
        try {
            handler.appendInitMethod(cd);
            fail("1");
        }
        catch (IllegalInitMethodAnnotationRuntimeException ex) {
            System.out.println(ex);
        }
    }
}