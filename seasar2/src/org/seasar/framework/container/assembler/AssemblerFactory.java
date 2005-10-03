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

import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.ConstructorAssembler;
import org.seasar.framework.container.PropertyAssembler;

/**
 * @author higa
 * @author koichik
 */
public class AssemblerFactory {
    private static Provider provider_ = new DefaultProvider();

    public static Provider getProvider() {
        return provider_;
    }

    public static void setProvider(final Provider provider) {
        provider_ = provider;
    }

    public static MethodAssembler createInitMethodAssembler(final ComponentDef cd) {
        return getProvider().createInitMethodAssembler(cd);
    }

    public static MethodAssembler createDestroyMethodAssembler(final ComponentDef cd) {
        return getProvider().createDestroyMethodAssembler(cd);
    }

    public static ConstructorAssembler createAutoConstructorAssembler(final ComponentDef cd) {
        return getProvider().createAutoConstructorAssembler(cd);
    }

    public static ConstructorAssembler createManualConstructorAssembler(final ComponentDef cd) {
        return getProvider().createManualConstructorAssembler(cd);
    }

    public static ConstructorAssembler createExpressionConstructorAssembler(
            final ComponentDef cd) {
        return getProvider().createExpressionConstructorAssembler(cd);
    }

    public static ConstructorAssembler createDefaultConstructorAssembler(final ComponentDef cd) {
        return getProvider().createDefaultConstructorAssembler(cd);
    }

    public static PropertyAssembler createAutoPropertyAssembler(final ComponentDef cd) {
        return getProvider().createAutoPropertyAssembler(cd);
    }

    public static PropertyAssembler createManualPropertyAssembler(final ComponentDef cd) {
        return getProvider().createManualPropertyAssembler(cd);
    }

    public static PropertyAssembler createDefaultPropertyAssembler(final ComponentDef cd) {
        return getProvider().createDefaultPropertyAssembler(cd);
    }

    public interface Provider {
        
        MethodAssembler createInitMethodAssembler(ComponentDef cd);

        MethodAssembler createDestroyMethodAssembler(ComponentDef cd);

        ConstructorAssembler createAutoConstructorAssembler(ComponentDef cd);

        ConstructorAssembler createManualConstructorAssembler(ComponentDef cd);

        ConstructorAssembler createExpressionConstructorAssembler(ComponentDef cd);

        ConstructorAssembler createDefaultConstructorAssembler(ComponentDef cd);
        
        PropertyAssembler createAutoPropertyAssembler(ComponentDef cd);

        PropertyAssembler createManualPropertyAssembler(ComponentDef cd);

        PropertyAssembler createDefaultPropertyAssembler(ComponentDef cd);
    }

    public static class DefaultProvider implements Provider {
        
        public MethodAssembler createInitMethodAssembler(final ComponentDef cd) {
            return new DefaultInitMethodAssembler(cd);
        }

        public MethodAssembler createDestroyMethodAssembler(final ComponentDef cd) {
            return new DefaultDestroyMethodAssembler(cd);
        }

        public ConstructorAssembler createAutoConstructorAssembler(final ComponentDef cd) {
            return new AutoConstructorAssembler(cd);
        }

        public ConstructorAssembler createManualConstructorAssembler(final ComponentDef cd) {
            return new ManualConstructorAssembler(cd);
        }

        public ConstructorAssembler createExpressionConstructorAssembler(
                final ComponentDef cd) {
            return new ExpressionConstructorAssembler(cd);
        }

        public ConstructorAssembler createDefaultConstructorAssembler(final ComponentDef cd) {
            return new DefaultConstructorAssembler(cd);
        }

        public PropertyAssembler createAutoPropertyAssembler(final ComponentDef cd) {
            return new AutoPropertyAssembler(cd);
        }

        public PropertyAssembler createManualPropertyAssembler(final ComponentDef cd) {
            return new ManualPropertyAssembler(cd);
        }

        public PropertyAssembler createDefaultPropertyAssembler(final ComponentDef cd) {
            return new DefaultPropertyAssembler(cd);
        }
    }
}
