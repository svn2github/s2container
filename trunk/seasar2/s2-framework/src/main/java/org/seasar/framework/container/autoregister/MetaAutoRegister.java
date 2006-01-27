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
package org.seasar.framework.container.autoregister;

import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.MetaDef;
import org.seasar.framework.container.impl.MetaDefImpl;

public class MetaAutoRegister extends AbstractComponentTargetAutoRegister {
    protected ComponentDef componentDef;

    public MetaAutoRegister() {
    }

    public ComponentDef getComponentDef() {
        return componentDef;
    }

    public void setComponentDef(final ComponentDef componentDef) {
        this.componentDef = componentDef;
    }

    protected void register(final ComponentDef cd) {
        for (int i = 0; i < componentDef.getMetaDefSize(); ++i) {
            final MetaDef meta = componentDef.getMetaDef(i);
            cd.addMetaDef(new MetaDefImpl(meta.getName(), meta.getValue()));
        }
    }
}
