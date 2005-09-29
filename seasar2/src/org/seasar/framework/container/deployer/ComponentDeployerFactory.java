/*
 * Copyright 2004-2005 the Seasar Foundation and the Others.
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

import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.util.InstanceModeUtil;

/**
 * @author higa
 */
public class ComponentDeployerFactory {
    private static Provider provider_ = new DefaultProvider();

    public static void main(String[] args) {
        junit.textui.TestRunner.run(ComponentDeployerFactory.class);
    }

    public static Provider getProvider() {
        return provider_;
    }

    public static void setProvider(final Provider provider) {
        provider_ = provider;
    }

    public static ComponentDeployer create(final ComponentDef cd) {
        return getProvider().createComponentDeployer(cd);
    }

    public interface Provider {
        ComponentDeployer createComponentDeployer(ComponentDef cd);
    }

    public static class DefaultProvider implements Provider {
        public ComponentDeployer createComponentDeployer(final ComponentDef cd) {
            final String instanceMode = cd.getInstanceMode();
            if (InstanceModeUtil.isSingleton(instanceMode)) {
                return createSingletonComponentDeployer(cd);
            }
            else if (InstanceModeUtil.isPrototype(instanceMode)) {
                return createPrototypeComponentDeployer(cd);
            }
            else if (InstanceModeUtil.isRequest(instanceMode)) {
                return createRequestComponentDeployer(cd);
            }
            else if (InstanceModeUtil.isSession(instanceMode)) {
                return createSessionComponentDeployer(cd);
            }
            else if (InstanceModeUtil.isOuter(instanceMode)) {
                return createOuterComponentDeployer(cd);
            }
            else {
                return createDefaultComponentDeployer(cd);
            }
        }

        protected ComponentDeployer createSingletonComponentDeployer(final ComponentDef cd) {
            return new SingletonComponentDeployer(cd);
        }

        protected ComponentDeployer createPrototypeComponentDeployer(final ComponentDef cd) {
            return new PrototypeComponentDeployer(cd);
        }

        protected ComponentDeployer createRequestComponentDeployer(final ComponentDef cd) {
            return new RequestComponentDeployer(cd);
        }

        protected ComponentDeployer createSessionComponentDeployer(final ComponentDef cd) {
            return new SessionComponentDeployer(cd);
        }

        protected ComponentDeployer createOuterComponentDeployer(final ComponentDef cd) {
            return new OuterComponentDeployer(cd);
        }

        protected ComponentDeployer createDefaultComponentDeployer(final ComponentDef cd) {
            return createOuterComponentDeployer(cd);
        }
    }
}
