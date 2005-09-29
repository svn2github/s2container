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
import org.seasar.framework.container.util.AutoBindingUtil;

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

    public static ConstructorAssembler createConstructorAssembler(final ComponentDef cd) {
        return getProvider().createConstructorAssembler(cd);
    }

    public static PropertyAssembler createPropertyAssembler(final ComponentDef cd) {
        return getProvider().createPropertyAssembler(cd);
    }

    public static MethodAssembler createInitMethodAssembler(final ComponentDef cd) {
        return getProvider().createInitMethodAssembler(cd);
    }

    public static MethodAssembler createDestroyMethodAssembler(final ComponentDef cd) {
        return getProvider().createDestroyMethodAssembler(cd);
    }

    public interface Provider {
        ConstructorAssembler createConstructorAssembler(ComponentDef cd);

        PropertyAssembler createPropertyAssembler(ComponentDef cd);

        MethodAssembler createInitMethodAssembler(ComponentDef cd);

        MethodAssembler createDestroyMethodAssembler(ComponentDef cd);
    }

    public static class DefaultProvider implements Provider {
        public ConstructorAssembler createConstructorAssembler(final ComponentDef cd) {
            if (cd.getArgDefSize() > 0) {
                return createManualConstructorAssembler(cd);
            }
            else if (cd.getExpression() != null) {
                return createExpressionConstructorAssembler(cd);
            }

            final String autoBindingMode = cd.getAutoBindingMode();
            if (AutoBindingUtil.isAuto(autoBindingMode)
                    || AutoBindingUtil.isConstructor(autoBindingMode)) {
                return createAutoConstructorAssembler(cd);
            }
            return createDefaultConstructorAssembler(cd);
        }

        public PropertyAssembler createPropertyAssembler(final ComponentDef cd) {
            final String autoBindingMode = cd.getAutoBindingMode();
            if (AutoBindingUtil.isAuto(autoBindingMode)
                    || AutoBindingUtil.isProperty(autoBindingMode)) {
                return createAutoPropertyAssembler(cd);
            }
            if (cd.getPropertyDefSize() > 0) {
                return createManualPropertyAssembler(cd);
            }
            return createOtherPropertyAssembler(cd);
        }

        public MethodAssembler createInitMethodAssembler(final ComponentDef cd) {
            return new DefaultInitMethodAssembler(cd);
        }

        public MethodAssembler createDestroyMethodAssembler(final ComponentDef cd) {
            return new DefaultDestroyMethodAssembler(cd);
        }

        protected ConstructorAssembler createAutoConstructorAssembler(final ComponentDef cd) {
            return new AutoConstructorAssembler(cd);
        }

        protected ConstructorAssembler createManualConstructorAssembler(final ComponentDef cd) {
            return new ManualConstructorAssembler(cd);
        }

        protected ConstructorAssembler createExpressionConstructorAssembler(
                final ComponentDef cd) {
            return new ExpressionConstructorAssembler(cd);
        }

        protected ConstructorAssembler createDefaultConstructorAssembler(final ComponentDef cd) {
            return new DefaultConstructorAssembler(cd);
        }

        protected PropertyAssembler createAutoPropertyAssembler(final ComponentDef cd) {
            return new AutoPropertyAssembler(cd);
        }

        protected PropertyAssembler createManualPropertyAssembler(final ComponentDef cd) {
            return new ManualPropertyAssembler(cd);
        }

        protected PropertyAssembler createOtherPropertyAssembler(final ComponentDef cd) {
            return new DefaultPropertyAssembler(cd);
        }
    }
}
