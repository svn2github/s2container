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
package org.seasar3.lookup;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javassist.ClassPool;
import javassist.CtClass;

import org.seasar3.aop.ClassGenerator;
import org.seasar3.exception.MultipleConfigurationAnnotationsException;
import org.seasar3.exception.NotAnnotatedException;
import org.seasar3.util.ClassPoolUtil;
import org.seasar3.util.ClassUtil;
import org.seasar3.util.CtClassUtil;

/**
 * A S3 provides "lookup" function.
 * 
 * @author higa
 * 
 */
public class S3 {

    private static ConcurrentHashMap<String, Object> configs = new ConcurrentHashMap<String, Object>(
            17);

    private static ConcurrentHashMap<String, Class> overrides = new ConcurrentHashMap<String, Class>(
            17);

    private static Map<Class<? extends Annotation>, ConfigurationCustomizer> customizers = new HashMap<Class<? extends Annotation>, ConfigurationCustomizer>(
            17);

    private static Object sequenceLock = new Object();

    private static int sequence = 0;

    static {
        initialize();
    }

    private S3() {
    }

    protected static void initialize() {
        customizers.put(Singleton.class, new SingletonCustomizer());
        customizers.put(Prototype.class, new PrototypeCustomizer());
    }

    /**
     * Looks up configuration object. configuration object is managed as
     * singleton.
     * 
     * @param <T>
     * @param configClass
     * @return configuration object
     */
    @SuppressWarnings("unchecked")
    public static final <T> T lookup(Class<? extends T> configClass) {
        if (configClass == null) {
            throw new NullPointerException("configClass");
        }
        String name = configClass.getName();
        Class dest = overrides.get(name);
        if (dest != null) {
            configClass = dest;
        }
        T config = (T) configs.get(name);
        if (config != null) {
            return config;
        }
        config = createConfiguration(configClass);
        T config2 = (T) configs.putIfAbsent(name, config);
        return config2 != null ? config2 : config;
    }

    @SuppressWarnings("unchecked")
    protected static <T> T createConfiguration(Class<? extends T> clazz) {
        String originalClassName = clazz.getName();
        String newClassName = createNewConfigurationClassName(originalClassName);
        ClassPool classPool = ClassPoolUtil.getClassPool();
        CtClass ctClass = CtClassUtil.create(classPool, originalClassName,
                newClassName);
        ClassGenerator generator = new ClassGenerator(classPool, ctClass);
        Class<? extends Annotation> defaultAnnotationClass = findConfiguratoinAnnotationClass(clazz);
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.getDeclaringClass() == Object.class) {
                continue;
            }
            Class<? extends Annotation> annotationClass = findConfiguratoinAnnotationClass(method);
            if (annotationClass == null) {
                annotationClass = defaultAnnotationClass;
            }
            if (annotationClass != null) {
                ConfigurationCustomizer customizer = getConfigurationCustomizer(annotationClass);
                if (customizer != null) {
                    customizer.customize(generator, method);
                }
            }
        }
        Class<? extends T> configClass = generator.generate();
        return ClassUtil.newInstance(configClass);
    }

    protected static String createNewConfigurationClassName(String className) {
        synchronized (sequenceLock) {
            return "$$" + className + "$$" + ++sequence;
        }
    }

    protected static Class<? extends Annotation> findConfiguratoinAnnotationClass(
            Class<?> clazz) {
        Annotation[] annotations = clazz.getAnnotations();
        List<Class<? extends Annotation>> classes = findConfiguratoinAnnotationClasses(annotations);
        if (classes.size() > 1) {
            throw new MultipleConfigurationAnnotationsException(clazz
                    .toString(), classes);
        }
        if (classes.size() == 1) {
            return classes.get(0);
        }
        return null;
    }

    protected static Class<? extends Annotation> findConfiguratoinAnnotationClass(
            Method method) {
        Annotation[] annotations = method.getAnnotations();
        List<Class<? extends Annotation>> classes = findConfiguratoinAnnotationClasses(annotations);
        if (classes.size() > 1) {
            throw new MultipleConfigurationAnnotationsException(method
                    .toString(), classes);
        }
        if (classes.size() == 1) {
            return classes.get(0);
        }
        return null;
    }

    protected static List<Class<? extends Annotation>> findConfiguratoinAnnotationClasses(
            Annotation[] annotations) {
        List<Class<? extends Annotation>> classes = new ArrayList<Class<? extends Annotation>>();
        for (Annotation annotation : annotations) {
            Class<? extends Annotation> annotationType = annotation
                    .annotationType();
            if (annotationType.getAnnotation(ConfigurationCustomization.class) != null) {
                classes.add(annotationType);
            }
        }
        return classes;
    }

    /**
     * Overrides src configuration.
     * 
     * @param <T>
     * 
     * @param src
     * @param dest
     * @return
     */
    public static <T> void override(Class<T> src, Class<? extends T> dest) {
        if (src == null) {
            throw new NullPointerException("src");
        }
        if (dest == null) {
            throw new NullPointerException("dest");
        }
        overrides.put(src.getName(), dest);
    }

    /**
     * Returns {@link ConfigurationCustomizer}.
     * 
     * @param annotationClass
     * @return <code>ConfigurationCustomizer</code>
     */
    public static ConfigurationCustomizer getConfigurationCustomizer(
            Class<? extends Annotation> annotationClass) {
        if (annotationClass == null) {
            throw new NullPointerException("annotationClass");
        }
        return customizers.get(annotationClass);
    }

    /**
     * Sets {@link ConfigurationCustomizer}.
     * 
     * @param annotationClass
     * @param customizer
     * @throws NotAnnotatedException
     *             if annotationClass is null.
     * @throws NotAnnotatedException
     *             if annotationClass is not annotated by specific annotation.
     */
    public static void setConfigurationCustomizer(
            Class<? extends Annotation> annotationClass,
            ConfigurationCustomizer customizer) {
        if (annotationClass == null) {
            throw new NullPointerException("annotationClass");
        }
        if (annotationClass.getAnnotation(ConfigurationCustomization.class) == null) {
            throw new NotAnnotatedException(annotationClass,
                    ConfigurationCustomization.class);
        }
        customizers.put(annotationClass, customizer);
    }

    /**
     * Disposes all resources.
     */
    public static final void dispose() {
        configs.clear();
        overrides.clear();
        initialize();
    }
}