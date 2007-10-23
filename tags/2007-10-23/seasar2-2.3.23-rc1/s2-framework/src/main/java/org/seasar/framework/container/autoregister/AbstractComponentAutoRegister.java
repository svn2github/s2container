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
package org.seasar.framework.container.autoregister;

import java.util.ArrayList;
import java.util.List;

import org.seasar.framework.container.AutoBindingDef;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.InstanceDef;
import org.seasar.framework.container.factory.AnnotationHandler;
import org.seasar.framework.container.factory.AnnotationHandlerFactory;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.ClassTraversal.ClassHandler;

/**
 * @author higa
 * 
 */
public abstract class AbstractComponentAutoRegister extends
        AbstractAutoRegister implements ClassHandler {

    protected static final String CLASS_SUFFIX = ".class";

    private AutoNaming autoNaming = new DefaultAutoNaming();

    private InstanceDef instanceDef;

    private AutoBindingDef autoBindingDef;

    public AutoNaming getAutoNaming() {
        return autoNaming;
    }

    public static final String autoNaming_BINDING = "bindingType=may";

    public void setAutoNaming(AutoNaming autoNaming) {
        this.autoNaming = autoNaming;
    }

    public InstanceDef getInstanceDef() {
        return instanceDef;
    }

    public static final String instanceDef_BINDING = "bindingType=may";

    public void setInstanceDef(InstanceDef instanceDef) {
        this.instanceDef = instanceDef;
    }

    public static final String autoBindingDef_BINDING = "bindingType=may";

    public void setAutoBindingDef(AutoBindingDef autoBindingDef) {
        this.autoBindingDef = autoBindingDef;
    }

    public void processClass(final String packageName,
            final String shortClassName) {
        if (isIgnore(packageName, shortClassName)) {
            return;
        }

        for (int i = 0; i < getClassPatternSize(); ++i) {
            final ClassPattern cp = getClassPattern(i);
            if (cp.isAppliedPackageName(packageName)
                    && cp.isAppliedShortClassName(shortClassName)) {
                register(packageName, shortClassName);
                return;
            }
        }
    }

    protected void register(final String packageName,
            final String shortClassName) {
        final AnnotationHandler annoHandler = AnnotationHandlerFactory
                .getAnnotationHandler();
        final String className = ClassUtil.concatName(packageName,
                shortClassName);
        final ComponentDef cd = annoHandler.createComponentDef(className,
                instanceDef, autoBindingDef);
        if (cd.getComponentName() == null && autoNaming != null) {
            cd.setComponentName(autoNaming.defineName(packageName,
                    shortClassName));
        }
        annoHandler.appendDI(cd);
        annoHandler.appendAspect(cd);
        annoHandler.appendInitMethod(cd);
        getContainer().register(cd);
    }

    /**
     * コンポーネントを検索する対象となるパッケージの配列を返します。
     * <p>
     * コンポーネントを検索する対象のパッケージは<code>ClassPattern</code>に設定されたパッケージ名から
     * 重複やサブパッケージを除いたものになります。 例えば<code>ClassPattern</code>に<code>aaa, aaa.bbb, bbb</code>が指定された場合、
     * <code>aaa.bbb</code>は<code>aaa</code>のサブパッケージなので取り除かれ、
     * <code>aaa, bbb</code>が検索対象のパッケージとなります。
     * </p>
     * 
     * @return コンポーネントを検索する対象となるパッケージの配列
     */
    protected String[] getTargetPackages() {
        final List result = new ArrayList();
        for (int i = 0; i < getClassPatternSize(); ++i) {
            final String packageName = getClassPattern(i).getPackageName();
            boolean append = true;
            for (int j = 0; j < result.size(); ++j) {
                final String root = (String) result.get(j);
                if (packageName.equals(root)) {
                    append = false;
                    break;
                } else if (packageName.startsWith(root)) {
                    append = false;
                    break;
                } else if (root.startsWith(packageName)) {
                    result.set(j, packageName);
                    append = false;
                    break;
                }
            }
            if (append) {
                result.add(packageName);
            }
        }
        return (String[]) result.toArray(new String[result.size()]);
    }

}
