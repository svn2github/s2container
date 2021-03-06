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
package org.seasar.framework.container.deployer;

import junit.framework.TestCase;

import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.ComponentDeployer;
import org.seasar.framework.container.impl.ComponentDefImpl;

/**
 * @author koichik
 */
public class ComponentDeployerFactoryTest extends TestCase {

    public void setUp() throws Exception {
        ComponentDeployerFactory
                .setProvider(new ExternalComponentDeployerProvider());
    }

    public void tearDown() throws Exception {
        ComponentDeployerFactory
                .setProvider(new ComponentDeployerFactory.DefaultProvider());
    }

    public void testSingleton() throws Exception {
        ComponentDef cd = new ComponentDefImpl();
        cd.setInstanceDef(InstanceDefFactory.SINGLETON);
        ComponentDeployer deployer = ComponentDeployerFactory
                .createSingletonComponentDeployer(cd);
        assertTrue("1", deployer instanceof SingletonComponentDeployer);
    }

    public void testPrototype() throws Exception {
        ComponentDef cd = new ComponentDefImpl();
        cd.setInstanceDef(InstanceDefFactory.PROTOTYPE);
        ComponentDeployer deployer = ComponentDeployerFactory
                .createPrototypeComponentDeployer(cd);
        assertTrue("1", deployer instanceof PrototypeComponentDeployer);
    }

    public void testRequest() throws Exception {
        ComponentDef cd = new ComponentDefImpl();
        cd.setInstanceDef(InstanceDefFactory.REQUEST);

        ComponentDeployer deployer = ComponentDeployerFactory
                .createRequestComponentDeployer(cd);
        assertTrue("1", deployer instanceof RequestComponentDeployer);
    }

    public void testApplication() throws Exception {
        ComponentDef cd = new ComponentDefImpl();
        cd.setInstanceDef(InstanceDefFactory.APPLICATION);
        ComponentDeployer deployer = ComponentDeployerFactory
                .createServletContextComponentDeployer(cd);
        assertTrue("1", deployer instanceof ApplicationComponentDeployer);
    }

    public void testSession() throws Exception {
        ComponentDef cd = new ComponentDefImpl();
        cd.setInstanceDef(InstanceDefFactory.SESSION);
        ComponentDeployer deployer = ComponentDeployerFactory
                .createSessionComponentDeployer(cd);
        assertTrue("1", deployer instanceof SessionComponentDeployer);
    }

    public void testOuter() throws Exception {
        ComponentDef cd = new ComponentDefImpl();
        cd.setInstanceDef(InstanceDefFactory.OUTER);
        ComponentDeployer deployer = ComponentDeployerFactory
                .createOuterComponentDeployer(cd);
        assertTrue("1", deployer instanceof OuterComponentDeployer);
    }
}
