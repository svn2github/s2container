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
package org.seasar.framework.jpa.autodetector;

import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;

import org.seasar.framework.autodetector.impl.AbstractResourceAutoDetector;
import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.framework.container.annotation.tiger.BindingType;
import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InitMethod;
import org.seasar.framework.convention.NamingConvention;
import org.seasar.framework.util.ClassLoaderUtil;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.ResourceTraversal.ResourceHandler;

/**
 * @author taedium
 */
@Component
public class MappingFileAutoDetector extends AbstractResourceAutoDetector {

    protected NamingConvention namingConvention;

    public MappingFileAutoDetector() {
        addTargetDirPath("META-INF");
        addResourceNamePattern(".*Orm.xml");
    }

    @Binding(bindingType = BindingType.MAY)
    public void setNamingConvention(final NamingConvention namingConvention) {
        this.namingConvention = namingConvention;
    }

    @InitMethod
    public void init() {
        if (namingConvention != null) {
            final String entityPackageName = namingConvention
                    .getEntityPackageName();
            for (final String rootPackageName : namingConvention
                    .getRootPackageNames()) {
                final String packageName = ClassUtil.concatName(
                        rootPackageName, entityPackageName);
                final String path = packageName.replace(".", "/");
                addTargetDirPath(path);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void detect(final ResourceHandler handler) {
        for (int i = 0; i < getTargetDirPathSize(); i++) {
            final String targetDirPath = getTargetDirPath(i);
            for (final Iterator<URL> it = ClassLoaderUtil
                    .getResources(targetDirPath); it.hasNext();) {
                final URL targetDirUrl = it.next();
                detect(handler, targetDirPath, targetDirUrl);
            }
        }
    }

    protected void detect(final ResourceHandler handler,
            final String targetDirPath, final URL targetDirUrl) {
        final Strategy strategy = getStrategy(targetDirUrl.getProtocol());
        strategy.detect(targetDirPath, targetDirUrl, new ResourceHandler() {
            public void processResource(final String path, final InputStream is) {
                if (path.startsWith(targetDirPath) && isApplied(path)
                        && !isIgnored(path)) {
                    handler.processResource(path, is);
                }
            }
        });
    }

}
