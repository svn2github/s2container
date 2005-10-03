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
package org.seasar.framework.container.assembler;

import org.seasar.framework.container.AutoBindingDef;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.ConstructorAssembler;

public abstract class AbstractAutoBindingDef implements AutoBindingDef {

    private String name;
    
    protected AbstractAutoBindingDef(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public ConstructorAssembler createConstructorAssembler(ComponentDef componentDef) {
        if (componentDef.getArgDefSize() > 0) {
            return new ManualConstructorAssembler(componentDef);
        } else if (componentDef.getExpression() != null) {
            return new ExpressionConstructorAssembler(componentDef);
        }
        return doCreateConstructorAssembler(componentDef);
    }
    
    protected abstract ConstructorAssembler doCreateConstructorAssembler(ComponentDef componentDef);
}
