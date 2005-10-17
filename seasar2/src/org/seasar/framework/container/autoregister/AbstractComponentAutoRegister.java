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
package org.seasar.framework.container.autoregister;

import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.InstanceDef;
import org.seasar.framework.container.factory.AnnotationHandler;
import org.seasar.framework.container.factory.AnnotationHandlerFactory;
import org.seasar.framework.util.ClassUtil;


/**
 * @author higa
 *
 */
public abstract class AbstractComponentAutoRegister extends AbstractAutoRegister {

    protected static final String CLASS_SUFFIX = ".class";
    
    private AutoNaming autoNaming = new DefaultAutoNaming();
    
    public static final String instanceDef_BINDING = "bindingType=may";
    
    private InstanceDef instanceDef;
    
    public AutoNaming getAutoNaming() {
        return autoNaming;
    }

    public void setAutoNaming(AutoNaming autoNaming) {
        this.autoNaming = autoNaming;
    }
    
    public InstanceDef getInstanceDef() {
        return instanceDef;
    }

    public void setInstanceDef(InstanceDef instanceDef) {
        this.instanceDef = instanceDef;
    }

    protected void regist(final String packageName, final String shortClassName) {
        final AnnotationHandler annoHandler = AnnotationHandlerFactory
                .getAnnotationHandler();
        final String className = ClassUtil.concatName(packageName, shortClassName);
        final ComponentDef cd = annoHandler.createComponentDef(className, instanceDef);
        if (cd.getComponentName() == null && autoNaming != null) {
            cd.setComponentName(autoNaming.defineName(packageName, shortClassName));
        }
        annoHandler.appendDI(cd);
        getContainer().register(cd);
    }
}