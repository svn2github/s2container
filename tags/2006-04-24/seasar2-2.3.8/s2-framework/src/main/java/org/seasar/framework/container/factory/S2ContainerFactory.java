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
package org.seasar.framework.container.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import org.seasar.framework.container.ExtensionNotFoundRuntimeException;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.assembler.AssemblerFactory;
import org.seasar.framework.container.deployer.ComponentDeployerFactory;
import org.seasar.framework.container.impl.S2ContainerBehavior;
import org.seasar.framework.util.ResourceUtil;

/**
 * @author higa
 * 
 */
public final class S2ContainerFactory {

    public static final String FACTORY_CONFIG_KEY = "org.seasar.framework.container.factory.config";
    public static final String FACTORY_CONFIG_PATH = "s2container.dicon";
    public static final String DEFAULT_BUILDER_NAME = "defaultBuilder";

    protected static S2Container configurationContainer;
    protected static Provider provider = new DefaultProvider();
    protected static S2ContainerBuilder defaultBuilder = new XmlS2ContainerBuilder();
    protected static ThreadLocal processingPaths = new ThreadLocal() {

        protected Object initialValue() {
            return new LinkedHashSet();
        }
    };

    static {
        configure();
    }

    public static synchronized S2Container create(final String path) {
        return getProvider().create(path);
    }

    public static synchronized S2Container create(final String path, final ClassLoader classLoader) {
        return getProvider().create(path, classLoader);
    }

    public static S2Container include(final S2Container parent, final String path) {
        return getProvider().include(parent, path);
    }

    public static void configure() {
        final String configFile = System.getProperty(FACTORY_CONFIG_KEY, FACTORY_CONFIG_PATH);
        configure(configFile);
    }

    public static synchronized void configure(final String configFile) {
        if (ResourceUtil.isExist(configFile)) {
            final S2ContainerBuilder builder = new XmlS2ContainerBuilder();
            configurationContainer = builder.build(configFile);
            Configurator configurator;
            if (configurationContainer.hasComponentDef(Configurator.class)) {
                configurator = (Configurator) configurationContainer
                        .getComponent(Configurator.class);
            }
            else {
                configurator = new DefaultConfigurator();
            }
            configurator.configure(configurationContainer);
        }
    }

    public static synchronized void destroy() {
        defaultBuilder = null;
        provider = null;
        configurationContainer.destroy();
        configurationContainer = null;
    }

    protected static Provider getProvider() {
        return provider;
    }

    protected static void setProvider(final Provider p) {
        provider = p;
    }

    protected static S2ContainerBuilder getDefaultBuilder() {
        return defaultBuilder;
    }

    protected static void setDefaultBuilder(final S2ContainerBuilder builder) {
        defaultBuilder = builder;
    }

    protected static void enter(final String path) {
        final Set paths = (Set) processingPaths.get();
        if (paths.contains(path)) {
            throw new CircularIncludeRuntimeException(path, paths);
        }
        paths.add(path);
    }

    protected static void leave(final String path) {
        final Set paths = (Set) processingPaths.get();
        paths.remove(path);
    }

    public interface Provider {

        S2Container create(String path);

        S2Container create(String path, ClassLoader classLoader);

        S2Container include(S2Container parent, String path);
    }

    public static class DefaultProvider implements Provider {

        public static final String pathResolver_BINDING = "bindingType=may";
        
        protected PathResolver pathResolver_ = new SimplePathResolver();
        protected boolean hotswapMode;

        public PathResolver getPathResolver() {
            return pathResolver_;
        }

        public void setPathResolver(final PathResolver pathResolver) {
            pathResolver_ = pathResolver;
        }

        public boolean getHotswapMode() {
            return hotswapMode;
        }

        public void setHotswapMode(final boolean hotswapMode) {
            this.hotswapMode = hotswapMode;
        }

        public S2Container create(final String path) {
            final String realPath = pathResolver_.resolvePath(null, path);
            enter(realPath);
            try {
                final String ext = getExtension(realPath);
                final S2Container container = getBuilder(ext).build(realPath);
                container.setHotswapMode(hotswapMode);
                return container;
            }
            finally {
                leave(realPath);
            }
        }

        public S2Container create(final String path, final ClassLoader classLoader) {
            final String realPath = pathResolver_.resolvePath(null, path);
            enter(realPath);
            try {
                final String ext = getExtension(realPath);
                final S2Container container = getBuilder(ext).build(realPath, classLoader);
                return container;
            }
            finally {
                leave(realPath);
            }
        }

        public S2Container include(final S2Container parent, final String path) {
            final String realPath = pathResolver_.resolvePath(parent.getPath(), path);
            enter(realPath);
            try {
                final S2Container root = parent.getRoot();
                S2Container child = null;
                synchronized (root) {
                    if (root.hasDescendant(realPath)) {
                        child = root.getDescendant(realPath);
                        parent.include(child);
                    }
                    else {
                        final String ext = getExtension(realPath);
                        final S2ContainerBuilder builder = getBuilder(ext);
                        child = builder.include(parent, realPath);
                        root.registerDescendant(child);
                    }
                }
                return child;
            }
            finally {
                leave(realPath);
            }
        }

        protected String getExtension(final String path) {
            final String ext = ResourceUtil.getExtension(path);
            if (ext == null) {
                throw new ExtensionNotFoundRuntimeException(path);
            }
            return ext;
        }

        protected S2ContainerBuilder getBuilder(final String ext) {
            if (configurationContainer != null && configurationContainer.hasComponentDef(ext)) {
                return (S2ContainerBuilder) configurationContainer.getComponent(ext);
            }
            return defaultBuilder;
        }
    }

    public interface Configurator {

        void configure(S2Container bootstrapContainer);
    }

    public static class DefaultConfigurator implements Configurator {

        public void configure(final S2Container bootstrapContainer) {
            if (configurationContainer.hasComponentDef(Provider.class)) {
                provider = (Provider) configurationContainer.getComponent(Provider.class);
            }
            else if (configurationContainer.hasComponentDef(PathResolver.class)
                    && provider instanceof DefaultProvider) {
                ((DefaultProvider) provider)
                        .setPathResolver((PathResolver) configurationContainer
                                .getComponent(PathResolver.class));
            }

            if (configurationContainer.hasComponentDef(DEFAULT_BUILDER_NAME)) {
                defaultBuilder = (S2ContainerBuilder) configurationContainer
                        .getComponent(DEFAULT_BUILDER_NAME);
            }
            else if (configurationContainer.hasComponentDef(ResourceResolver.class)
                    && defaultBuilder instanceof AbstractS2ContainerBuilder) {
                ((AbstractS2ContainerBuilder) defaultBuilder)
                        .setResourceResolver((ResourceResolver) configurationContainer
                                .getComponent(ResourceResolver.class));
            }

            if (configurationContainer.hasComponentDef(S2ContainerBehavior.Provider.class)) {
                S2ContainerBehavior
                        .setProvider((S2ContainerBehavior.Provider) configurationContainer
                                .getComponent(S2ContainerBehavior.Provider.class));
            }

            if (configurationContainer.hasComponentDef(ComponentDeployerFactory.Provider.class)) {
                ComponentDeployerFactory
                        .setProvider((ComponentDeployerFactory.Provider) configurationContainer
                                .getComponent(ComponentDeployerFactory.Provider.class));
            }

            if (configurationContainer.hasComponentDef(AssemblerFactory.Provider.class)) {
                AssemblerFactory.setProvider((AssemblerFactory.Provider) configurationContainer
                        .getComponent(AssemblerFactory.Provider.class));
            }
        }
    }
}
