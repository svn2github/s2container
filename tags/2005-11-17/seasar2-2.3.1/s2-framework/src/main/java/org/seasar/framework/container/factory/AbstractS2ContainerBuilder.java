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
package org.seasar.framework.container.factory;

import java.io.InputStream;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.util.ResourceNotFoundRuntimeException;

/**
 * @author koichik
 */
public abstract class AbstractS2ContainerBuilder implements S2ContainerBuilder {
    protected ResourceResolver resourceResolver_ = new ClassPathResourceResolver();

    public ResourceResolver getResourceResolver() {
        return resourceResolver_;
    }

    public void setResourceResolver(final ResourceResolver resourceResolver) {
        resourceResolver_ = resourceResolver;
    }

    public S2Container build(final String path, final ClassLoader classLoader) {
        final ClassLoader oldLoader = Thread.currentThread().getContextClassLoader();
        try {
            if (classLoader != null) {
                Thread.currentThread().setContextClassLoader(classLoader);
            }
            return build(path);
        }
        finally {
            Thread.currentThread().setContextClassLoader(oldLoader);
        }
    }

    protected InputStream getInputStream(final String path) {
        final InputStream is = resourceResolver_.getInputStream(path);
        if (is == null) {
            throw new ResourceNotFoundRuntimeException(path);
        }
        return is;
    }
}
