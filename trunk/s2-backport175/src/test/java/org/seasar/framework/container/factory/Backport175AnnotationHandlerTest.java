/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.framework.container.factory;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.container.AspectDef;
import org.seasar.framework.container.AutoBindingDef;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.DestroyMethodDef;
import org.seasar.framework.container.IllegalDestroyMethodAnnotationRuntimeException;
import org.seasar.framework.container.IllegalInitMethodAnnotationRuntimeException;
import org.seasar.framework.container.InitMethodDef;
import org.seasar.framework.container.InstanceDef;
import org.seasar.framework.container.InterTypeDef;
import org.seasar.framework.container.PropertyDef;
import org.seasar.framework.container.assembler.AutoBindingDefFactory;
import org.seasar.framework.container.assembler.BindingTypeDefFactory;
import org.seasar.framework.container.deployer.InstanceDefFactory;
import org.seasar.framework.container.ognl.OgnlExpression;
import org.seasar.framework.unit.S2FrameworkTestCase;

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

        ComponentDef cd3 = handler.createComponentDef(Hoge.class,
                InstanceDefFactory.REQUEST, AutoBindingDefFactory.NONE);
        assertEquals("6", AutoBindingDef.NONE_NAME, cd3.getAutoBindingDef()
                .getName());

        ComponentDef cd4 = handler.createComponentDef(Hoge2.class,
                InstanceDefFactory.REQUEST, AutoBindingDefFactory.NONE);
        assertEquals("7", AutoBindingDef.PROPERTY_NAME, cd4
                .getAutoBindingDef().getName());

        ComponentDef cd5 = handler.createComponentDef(Hoge3.class,
                InstanceDefFactory.REQUEST, AutoBindingDefFactory.NONE);
        assertEquals("8", AutoBindingDef.PROPERTY_NAME, cd5
                .getAutoBindingDef().getName());
    }

    public void testCreatePropertyDef() throws Exception {
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(Hoge.class);
        PropertyDesc propDesc = beanDesc.getPropertyDesc("aaa");
        assertNull("1", handler.createPropertyDef(beanDesc, propDesc));

        ComponentDef cd = handler.createComponentDef(Hoge.class, InstanceDefFactory.SINGLETON);
        handler.appendDI(cd);
        PropertyDef propDef = cd.getPropertyDef("bbb");
        assertEquals("field", propDef.getAccessTypeDef().getName());
        assertEquals("hoge", ((OgnlExpression) propDef.getExpression()).getSource());

        propDef = cd.getPropertyDef("ccc");
        assertEquals("property", propDef.getAccessTypeDef().getName());
        assertEquals("bar", ((OgnlExpression) propDef.getExpression()).getSource());

        beanDesc = BeanDescFactory.getBeanDesc(Hoge2.class);
        propDesc = beanDesc.getPropertyDesc("aaa");
        propDef = handler.createPropertyDef(beanDesc, propDesc);
        assertEquals("2", "aaa", propDef.getPropertyName());
        assertEquals("3", "aaa2", ((OgnlExpression) propDef.getExpression()).getSource());

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
        assertEquals("3", "aaa2", ((OgnlExpression) propDef.getExpression()).getSource());

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
        assertEquals("2", "aop.traceInterceptor", ((OgnlExpression) aspectDef.getExpression())
                .getSource());
    }

    public void testAppendAspectForMethod() throws Exception {
        ComponentDef cd = handler.createComponentDef(Hoge4.class, null);
        handler.appendAspect(cd);
        assertEquals("1", 1, cd.getAspectDefSize());
        AspectDef aspectDef = cd.getAspectDef(0);
        assertEquals("2", "aop.traceInterceptor", ((OgnlExpression) aspectDef.getExpression())
                .getSource());
        assertTrue("3", aspectDef.getPointcut().isApplied(Hoge4.class.getMethod("getAaa", null)));
        assertFalse("4", aspectDef.getPointcut().isApplied(
                Hoge4.class.getMethod("getAaa", new Class[] { String.class })));
    }

    public void testAppendAspectForConstantAnnotation() throws Exception {
        ComponentDef cd = handler.createComponentDef(Hoge3.class, null);
        handler.appendAspect(cd);
        assertEquals("1", 1, cd.getAspectDefSize());
        AspectDef aspectDef = cd.getAspectDef(0);
        assertEquals("2", "aop.traceInterceptor", ((OgnlExpression) aspectDef.getExpression())
                .getSource());
    }

    public void testInterType() throws Exception {
        ComponentDef cd = handler.createComponentDef(Hoge.class, null);
        handler.appendInterType(cd);
        assertEquals("1", 1, cd.getInterTypeDefSize());
        InterTypeDef interTypeDef = cd.getInterTypeDef(0);
        assertEquals("2", "fieldInterType", ((OgnlExpression) interTypeDef.getExpression())
                .getSource());
    }

    public void testAppendInterTypeForConstantAnnotation() throws Exception {
        ComponentDef cd = handler.createComponentDef(Hoge3.class, null);
        handler.appendInterType(cd);
        assertEquals("1", 1, cd.getInterTypeDefSize());
        InterTypeDef interTypeDef = cd.getInterTypeDef(0);
        assertEquals("2", "fieldInterType", ((OgnlExpression) interTypeDef.getExpression())
                .getSource());
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

    public void testAppendDestroyMethod() throws Exception {
        ComponentDef cd = handler.createComponentDef(Hoge.class, null);
        handler.appendDestroyMethod(cd);
        assertEquals("1", 1, cd.getDestroyMethodDefSize());
        DestroyMethodDef destroyMethodDef = cd.getDestroyMethodDef(0);
        assertEquals("2", "destroy", destroyMethodDef.getMethodName());
    }

    public void testAppendDestroyMethodForConstantAnnotation() throws Exception {
        ComponentDef cd = handler.createComponentDef(Hoge3.class, null);
        handler.appendDestroyMethod(cd);
        assertEquals("1", 1, cd.getDestroyMethodDefSize());
        DestroyMethodDef destroyMethodDef = cd.getDestroyMethodDef(0);
        assertEquals("2", "destroy", destroyMethodDef.getMethodName());
    }

    public void setUpAppendDestroyMethodForDicon() throws Exception {
        include("Backport175AnnotationHandlerTest.dicon");
    }

    public void testAppendDestroyMethodForDicon() throws Exception {
        ComponentDef cd = getComponentDef(Hoge5.class);
        assertEquals("1", 1, cd.getDestroyMethodDefSize());
    }

    public void testAppendDestroyMethodForException() throws Exception {
        ComponentDef cd = handler.createComponentDef(Hoge6.class, null);
        try {
            handler.appendDestroyMethod(cd);
            fail("1");
        }
        catch (IllegalDestroyMethodAnnotationRuntimeException ex) {
            System.out.println(ex);
        }
    }
}
