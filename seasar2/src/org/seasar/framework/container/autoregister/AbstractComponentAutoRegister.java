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
import org.seasar.framework.container.factory.AnnotationHandler;
import org.seasar.framework.container.factory.AnnotationHandlerFactory;


/**
 * @author higa
 *
 */
public abstract class AbstractComponentAutoRegister extends AbstractAutoRegister {

    protected static final String CLASS_SUFFIX = ".class";
    
    private AutoNaming autoNaming;
    
    public AutoNaming getAutoNaming() {
        return autoNaming;
    }

    public void setAutoNaming(AutoNaming autoNaming) {
        this.autoNaming = autoNaming;
    }

    protected void regist(final String packageName, final String shortClassName) {
        final AnnotationHandler annoHandler = AnnotationHandlerFactory
                .getAnnotationHandler();
        final String className = packageName == null ? shortClassName : packageName + "."
                + shortClassName;
        final ComponentDef cd = annoHandler.createComponentDef(className);
        if (cd.getComponentName() == null && autoNaming != null) {
            cd.setComponentName(autoNaming.defineName(packageName, shortClassName));
        }
        if (!hasComponentDef(cd.getComponentName())) {
            annoHandler.appendDI(cd);
            getContainer().register(cd);
        }
    }
}