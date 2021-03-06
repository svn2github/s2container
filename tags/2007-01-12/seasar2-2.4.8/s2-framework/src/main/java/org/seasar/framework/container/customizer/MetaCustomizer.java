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
package org.seasar.framework.container.customizer;

import org.seasar.framework.container.ComponentCustomizer;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.MetaDef;
import org.seasar.framework.container.impl.MetaDefImpl;

/**
 * @author koichik
 * 
 */
public class MetaCustomizer implements ComponentCustomizer {

    protected ComponentDef componentDef;

    public void setComponentDef(final ComponentDef componentDef) {
        this.componentDef = componentDef;
    }

    public void customize(final ComponentDef cd) {
        final MetaDef metaDef = componentDef.getMetaDef("autoRegister");
        if (metaDef == null) {
            return;
        }
        for (int i = 0; i < metaDef.getMetaDefSize(); ++i) {
            final MetaDef meta = metaDef.getMetaDef(i);
            cd.addMetaDef(new MetaDefImpl(meta.getName(), meta.getValue()));
        }
    }

}
