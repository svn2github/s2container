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
package org.seasar.framework.container.autoregister;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.jar.JarFile;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.seasar.framework.container.AutoBindingDef;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.unit.S2FrameworkTestCase;
import org.seasar.framework.util.ResourceUtil;

/**
 * 
 * @author koichik
 */
public class ComponentAutoRegisterTest extends S2FrameworkTestCase {

    private S2Container child;

    /**
     * @throws Exception
     */
    public void setUpRegisterAll() throws Exception {
        include("ComponentAutoRegisterTest.dicon");
    }

    /**
     * @throws Exception
     */
    public void testRegisterAll() throws Exception {
        Foo foo = (Foo) child.getComponent(Foo.class);
        assertNotNull(foo);
        Foo2 foo2 = (Foo2) child.getComponent(Foo2.class);
        assertNotNull(foo2);
        assertSame(foo2, foo.getFoo2());
        assertNotNull(child.getComponent(Foo3.class));
        assertNotNull(child.getComponent("foo3"));
        assertFalse(child.hasComponentDef(Foo4Impl.class));
        assertNotNull(child
                .getComponent(org.seasar.framework.container.autoregister.sub.Foo5.class));
        assertNotNull(child.getComponentDef(TestSuite.class));
        assertNotNull(child.getComponentDef("testSuite"));
    }

    /**
     * @throws Exception
     */
    public void setUpBindingMode() throws Exception {
        include("ComponentAutoRegisterTest2.dicon");
    }

    /**
     * @throws Exception
     */
    public void testBindingMode() throws Exception {
        ComponentDef cd = child.getComponentDef(Foo.class);
        assertEquals("1", AutoBindingDef.PROPERTY_NAME, cd.getAutoBindingDef()
                .getName());
        cd = child.getComponentDef(Foo2.class);
        assertEquals("2", AutoBindingDef.NONE_NAME, cd.getAutoBindingDef()
                .getName());
    }

    /**
     * @throws Exception
     */
    public void setUpExternalBinding() throws Exception {
        include("ComponentAutoRegisterTest2.dicon");
    }

    /**
     * @throws Exception
     */
    public void testExternalBinding() throws Exception {
        ComponentDef cd = child.getComponentDef(Foo.class);
        assertTrue(cd.isExternalBinding());
    }

    /**
     * @throws Exception
     */
    public void setUpZipFileStrategy() throws Exception {
        include("ComponentAutoRegisterTest.dicon");
    }

    /**
     * @throws Exception
     */
    public void testZipFileStrategy() throws Exception {
        ComponentAutoRegister register = (ComponentAutoRegister) getComponent(ComponentAutoRegister.class);
        ComponentAutoRegister.ZipFileStrategy strategy = (ComponentAutoRegister.ZipFileStrategy) register.strategies
                .get("zip");
        String classFilePath = TestCase.class.getName().replace('.', '/')
                + ".class";
        URL classURL = ResourceUtil.getResource(classFilePath);
        JarURLConnection con = (JarURLConnection) classURL.openConnection();
        JarFile expected = con.getJarFile();
        URL jarURL = con.getJarFileURL();
        String zipURL = "zip:" + jarURL.getPath() + "!" + classFilePath;
        JarFile actual = strategy.createJarFile(new URL(null, zipURL,
                new URLStreamHandler() {
                    protected URLConnection openConnection(URL u)
                            throws IOException {
                        return null;
                    }
                }));
        assertEquals(expected.getName(), actual.getName());
    }
}
