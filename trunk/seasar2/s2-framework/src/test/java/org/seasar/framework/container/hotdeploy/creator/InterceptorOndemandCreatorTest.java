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
package org.seasar.framework.container.hotdeploy.creator;

import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.hotdeploy.OndemandBehavior;
import org.seasar.framework.container.hotdeploy.OndemandCreator;
import org.seasar.framework.container.hotdeploy.impl.OndemandProjectImpl;
import org.seasar.framework.container.impl.S2ContainerBehavior;
import org.seasar.framework.convention.impl.NamingConventionImpl;
import org.seasar.framework.unit.S2FrameworkTestCase;
import org.seasar.framework.util.ClassUtil;

/**
 * @author higa
 * 
 */
public class InterceptorOndemandCreatorTest extends S2FrameworkTestCase {

    private ClassLoader originalLoader;

    private OndemandBehavior ondemand;

    protected void setUp() {
        originalLoader = Thread.currentThread().getContextClassLoader();
        NamingConventionImpl convention = new NamingConventionImpl();
        OndemandProjectImpl project = new OndemandProjectImpl();
        project.setRootPackageName(ClassUtil.getPackageName(getClass()));
        ondemand = new OndemandBehavior();
        project
                .setCreators(new OndemandCreator[] { new InterceptorOndemandCreator(
                        convention) });
        ondemand.addProject(project);
        S2ContainerBehavior.setProvider(ondemand);
        ondemand.start();
    }

    protected void tearDown() {
        ondemand.stop();
        S2ContainerBehavior
                .setProvider(new S2ContainerBehavior.DefaultProvider());
        Thread.currentThread().setContextClassLoader(originalLoader);
    }

    public void testAll() throws Exception {
        String name = "helloInterceptor";
        ComponentDef cd = getComponentDef(name);
        assertNotNull(cd);
        assertEquals(name, cd.getComponentName());

        String name2 = "nullInterceptor";
        ComponentDef cd2 = getComponentDef(name2);
        assertNotNull(cd2);
    }
}