/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
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
package org.seasar3.lookup;

import java.lang.annotation.Target;
import java.lang.reflect.Method;

import junit.framework.TestCase;

import org.seasar3.exception.MultipleConfigurationAnnotationsException;
import org.seasar3.exception.NotAnnotatedException;

/**
 * @author higa
 * 
 */
public class S3Test extends TestCase {

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        S3.dispose();
    }

    /**
     * Test method for {@link S3#lookup(Class)}.
     */
    public void testLookupForConfigClassIsNull() {
        try {
            S3.lookup(null);
            fail();
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }

    /**
     * Test method for {@link S3#lookup(Class)}.
     */
    public void testLookup() {
        assertNotNull(S3.lookup(MyConfig.class));
    }

    /**
     * Test method for {@link S3#lookup(Class)}.
     */
    public void testLookupForCache() {
        MyConfig config = S3.lookup(MyConfig.class);
        assertSame(config, S3.lookup(MyConfig.class));
    }

    /**
     * Test method for {@link S3#lookup(Class)}.
     */
    public void testLookupForProduction() {
        Client client = S3.lookup(MyConfig.class).client();
        assertEquals("production", client.go());
    }

    /**
     * Test method for {@link S3#lookup(Class)}.
     */
    public void testLookupForMockConfig() {
        Client client = S3.lookup(MyMockConfig.class).client();
        assertEquals("mock", client.go());
    }

    /**
     * Test method for {@link S3#lookup(Class)}.
     */
    public void testLookupForNestedConfig() {
        Client client = S3.lookup(ClientConfig.class).client();
        assertEquals("production", client.go());
    }

    /**
     * Test method for {@link S3#initialize()}.
     */
    public void testInitializeForSingleton() {
        MyConfig myConfig = S3.lookup(MyConfig.class);
        assertSame(myConfig.service(), myConfig.service());
    }

    /**
     * Test method for {@link S3#initialize()}.
     */
    public void testInitializeForSingletonWithClassAnnotation() {
        MyAllSingletonConfig myConfig = S3.lookup(MyAllSingletonConfig.class);
        assertSame(myConfig.service(), myConfig.service());
    }

    /**
     * Test method for {@link S3#initialize()}.
     */
    public void testInitializeForPrototype() {
        MyConfig myConfig = S3.lookup(MyConfig.class);
        assertNotSame(myConfig.client(), myConfig.client());
    }

    /**
     * Test method for {@link S3#override(Class, Class)}.
     */
    public void testOverride() {
        S3.override(ServiceConfig.class, ServiceMockConfig.class);
        Client client = S3.lookup(ClientConfig.class).client();
        assertEquals("mock", client.go());
    }

    /**
     * Test method for
     * {@link S3#setConfigurationCustomizer(Class, ConfigurationCustomizer)} and
     * {@link S3#getConfigurationCustomizer(Class)}.
     */
    public void testConfigurationCustomizer() {
        SingletonCustomizer customizer = new SingletonCustomizer();
        S3.setConfigurationCustomizer(Singleton.class, customizer);
        assertSame(customizer, S3.getConfigurationCustomizer(Singleton.class));
    }

    /**
     * Test method for
     * {@link S3#setConfigurationCustomizer(Class, ConfigurationCustomizer)}.
     */
    public void testSetConfigurationCustomizerForAnnotationClassIsNull() {
        SingletonCustomizer customizer = new SingletonCustomizer();
        try {
            S3.setConfigurationCustomizer(null, customizer);
            fail();
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }

    /**
     * Test method for
     * {@link S3#setConfigurationCustomizer(Class, ConfigurationCustomizer)}.
     */
    public void testSetConfigurationCustomizerForIllegalAnnotationClass() {
        SingletonCustomizer customizer = new SingletonCustomizer();
        try {
            S3.setConfigurationCustomizer(Target.class, customizer);
            fail();
        } catch (NotAnnotatedException e) {
            System.out.println(e);
        }
    }

    /**
     * Test method for {@link S3#findConfiguratoinAnnotationClass(Class)}.
     */
    public void testFindConfigurationAnnotationClass() {
        assertEquals(Singleton.class, S3
                .findConfiguratoinAnnotationClass(MyAllSingletonConfig.class));
    }

    /**
     * Test method for {@link S3#findConfiguratoinAnnotationClass(Class)}.
     */
    public void testFindConfigurationAnnotationClassForNotFound() {
        assertNull(S3.findConfiguratoinAnnotationClass(MyConfig.class));
    }

    /**
     * Test method for {@link S3#findConfiguratoinAnnotationClass(Class)}.
     */
    public void testFindConfigurationAnnotationClassForMultiple() {
        try {
            S3.findConfiguratoinAnnotationClass(MyErrorConfig.class);
            fail();
        } catch (MultipleConfigurationAnnotationsException e) {
            System.out.println(e);
        }
    }

    /**
     * Test method for
     * {@link S3#findConfiguratoinAnnotationClass(java.lang.reflect.Method))}.
     * 
     * @throws Exception
     */
    public void testFindConfigurationAnnotationClassForMultipleByMethod()
            throws Exception {
        Method m = MyErrorConfig.class.getDeclaredMethod("service",
                (Class[]) null);
        try {
            S3.findConfiguratoinAnnotationClass(m);
            fail();
        } catch (MultipleConfigurationAnnotationsException e) {
            System.out.println(e);
        }
    }

    /**
     * Test method for {@link S3#createNewConfigurationClassName(String)}.
     */
    public void testCreateNewConfigurationClassName() {
        String newName = S3.createNewConfigurationClassName("aaa.Hoge");
        System.out.println(newName);
        assertNotNull(newName);
    }
}