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
package org.seasar.framework.util;

import java.io.File;
import java.net.URL;

import junit.framework.TestCase;

public class ResourceUtilTest extends TestCase {

    public void testGetResourcePath() throws Exception {
        assertEquals("1", "aaa/bbb.xml", ResourceUtil.getResourcePath(
                "aaa/bbb.xml", "xml"));
        assertEquals("2", "aaa/bbb.xml", ResourceUtil.getResourcePath(
                "aaa.bbb", "xml"));
        assertEquals("3", "org/seasar/framework/util/ResourceUtilTest.class",
                ResourceUtil.getResourcePath(getClass()));
    }

    public void testGetResource() throws Exception {
        assertNotNull("1", ResourceUtil.getResource("java/lang/String.class",
                "class"));
        try {
            ResourceUtil.getResource("hoge", "xml");
            fail("2");
        } catch (ResourceNotFoundRuntimeException e) {
            System.out.println(e);
            assertEquals("3", "hoge.xml", e.getPath());
        }
        System.out.println(ResourceUtil.getResource("."));
    }

    public void testGetBuildDir() throws Exception {
        File file = ResourceUtil.getBuildDir(getClass());
        System.out.println(file);
    }

    public void testIsExist() throws Exception {
        assertEquals("1", true, ResourceUtil.isExist("SSRMessages.properties"));
        assertEquals("2", false, ResourceUtil.isExist("hoge"));
    }

    public void testGetExtension() throws Exception {
        assertEquals("1", "xml", ResourceUtil.getExtension("aaa/bbb.xml"));
        assertEquals("2", null, ResourceUtil.getExtension("aaa"));
    }

    public void testRemoteExtension() throws Exception {
        assertEquals("1", "aaa/bbb", ResourceUtil
                .removeExtension("aaa/bbb.xml"));
        assertEquals("2", "aaa/bbb", ResourceUtil.removeExtension("aaa/bbb"));
    }

    public void testToExternalForm() throws Exception {
        URL url = new File("C:\\Program Files").toURL();
        assertEquals("file:/C:/Program Files/", ResourceUtil
                .toExternalForm(url));
    }

    public void testGetFileName() throws Exception {
        URL url = new File("C:\\Program Files").toURL();
        assertEquals("/C:/Program Files/", ResourceUtil.getFileName(url));
    }
}