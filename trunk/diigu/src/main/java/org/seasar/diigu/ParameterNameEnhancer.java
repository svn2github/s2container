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
package org.seasar.diigu;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtBehavior;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.LoaderClassPath;
import javassist.NotFoundException;
import javassist.bytecode.ConstPool;
import javassist.bytecode.ParameterAnnotationsAttribute;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.StringMemberValue;

/**
 * 
 * @author koichik
 */
public class ParameterNameEnhancer {

    protected static final String PARAMETER_NAME_ANNOTATION_FQCN = "org.seasar.framework.beans.annotation.ParameterName";

    protected static final Map classPoolMap = Collections
            .synchronizedMap(new WeakHashMap());

    protected final ClassPool pool;

    protected final String className;

    protected final CtClass clazz;

    public ParameterNameEnhancer(final String className) {
        this(className, Thread.currentThread().getContextClassLoader());
    }

    public ParameterNameEnhancer(final String className,
            final ClassLoader loader) {
        this(className, getClassPool(loader));
    }

    public ParameterNameEnhancer(final String className, final ClassPool pool) {
        this.className = className;
        this.pool = pool;
        try {
            clazz = pool.get(className);
        } catch (final NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void setConstructorParameterNames(final String[] parameterTypeNames,
            final String[] parameterNames) {
        assert parameterTypeNames.length == parameterNames.length;
        if (parameterTypeNames.length == 0) {
            return;
        }
        try {
            setParameterNames(clazz.getDeclaredConstructor(toCtClassArray(pool,
                    parameterTypeNames)), parameterNames);
        } catch (final NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void setMethodParameterNames(final String methodName,
            final String[] parameterTypeNames, final String[] parameterNames) {
        assert parameterTypeNames.length == parameterNames.length;
        if (parameterTypeNames.length == 0) {
            return;
        }

        try {
            final CtMethod method = clazz.getDeclaredMethod(methodName,
                    toCtClassArray(pool, parameterTypeNames));
            setParameterNames(method, parameterNames);
        } catch (final NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void save() {
        try {
            final String[] names = className.split("\\.");
            final URL url = pool.find(className);
            File file = new File(new URI(url.toString()));
            for (int i = 0; i < names.length; ++i) {
                file = file.getParentFile();
            }
            save(file.getAbsolutePath());
        } catch (final URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(final String baseDirectoryName) {
        try {
            clazz.writeFile(baseDirectoryName);
            clazz.detach();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        } catch (final NotFoundException e) {
            throw new RuntimeException(e);
        } catch (final CannotCompileException e) {
            throw new RuntimeException(e);
        }
    }

    protected void setParameterNames(final CtBehavior behavior,
            final String[] parameterNames) throws NotFoundException {
        final ParameterAnnotationsAttribute annotationsAttribute = getParameterAnnotationsAttribute(behavior);
        final Annotation[][] annotationsArray = annotationsAttribute
                .getAnnotations();
        assert annotationsArray.length == parameterNames.length;

        for (int i = 0; i < annotationsArray.length; ++i) {
            final Annotation[] annotations = createParameterAnnotations(
                    behavior, annotationsArray[i], parameterNames[i]);
            annotationsArray[i] = annotations;
        }

        annotationsAttribute.setAnnotations(annotationsArray);
        behavior.getMethodInfo().addAttribute(annotationsAttribute);
    }

    protected ParameterAnnotationsAttribute getParameterAnnotationsAttribute(
            final CtBehavior behavior) throws NotFoundException {
        final ParameterAnnotationsAttribute annotationsAttribute = (ParameterAnnotationsAttribute) behavior
                .getMethodInfo().getAttribute(
                        ParameterAnnotationsAttribute.visibleTag);
        if (annotationsAttribute != null) {
            return annotationsAttribute;
        }
        return createParameterAnnotationsAttribute(behavior);
    }

    protected ParameterAnnotationsAttribute createParameterAnnotationsAttribute(
            final CtBehavior behavior) throws NotFoundException {
        final ParameterAnnotationsAttribute annotationsAttribute = new ParameterAnnotationsAttribute(
                behavior.getMethodInfo().getConstPool(),
                ParameterAnnotationsAttribute.visibleTag);
        final int length = behavior.getParameterTypes().length;
        final Annotation[][] annotations = new Annotation[length][];
        for (int i = 0; i < length; ++i) {
            annotations[i] = new Annotation[0];
        }
        annotationsAttribute.setAnnotations(annotations);
        return annotationsAttribute;
    }

    protected Annotation[] createParameterAnnotations(
            final CtBehavior behavior, final Annotation[] annotations,
            final String parameterName) {
        for (int i = 0; i < annotations.length; ++i) {
            final Annotation annotation = annotations[i];
            if (PARAMETER_NAME_ANNOTATION_FQCN.equals(annotation.getTypeName())) {
                return annotations;
            }
        }

        final Annotation[] newAnnotations = new Annotation[annotations.length + 1];
        System.arraycopy(annotations, 0, newAnnotations, 0, annotations.length);
        newAnnotations[annotations.length] = createAnnotation(behavior,
                parameterName);
        return newAnnotations;
    }

    protected Annotation createAnnotation(final CtBehavior behavior,
            final String parameterName) {
        final ConstPool constPool = behavior.getMethodInfo().getConstPool();
        final Annotation annotation = new Annotation(
                PARAMETER_NAME_ANNOTATION_FQCN, constPool);
        annotation.addMemberValue("value", new StringMemberValue(parameterName,
                constPool));
        return annotation;
    }

    protected static String getSimpleClassName(final Class clazz) {
        if (clazz.isArray()) {
            return getSimpleClassName(clazz.getComponentType()) + "[]";
        }
        return clazz.getName();
    }

    public static ClassPool getClassPool(final ClassLoader classLoader) {
        ClassPool classPool = (ClassPool) classPoolMap.get(classLoader);
        if (classPool == null) {
            if (classLoader == null) {
                return ClassPool.getDefault();
            }
            classPool = new ClassPool();
            classPool.appendClassPath(new LoaderClassPath(classLoader));
            classPoolMap.put(classLoader, classPool);
        }
        return classPool;
    }

    public static CtClass toCtClass(final ClassPool classPool, final Class clazz) {
        return toCtClass(classPool, getSimpleClassName(clazz));
    }

    public static CtClass toCtClass(final ClassPool classPool,
            final String className) {
        try {
            return classPool.get(className);
        } catch (final NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static CtClass[] toCtClassArray(final ClassPool classPool,
            final String[] classNames) {
        if (classNames == null) {
            return null;
        }
        final CtClass[] result = new CtClass[classNames.length];
        for (int i = 0; i < result.length; ++i) {
            result[i] = toCtClass(classPool, classNames[i]);
        }
        return result;
    }

    public static CtClass[] toCtClassArray(final ClassPool classPool,
            final Class[] classes) {
        if (classes == null) {
            return null;
        }
        final CtClass[] result = new CtClass[classes.length];
        for (int i = 0; i < result.length; ++i) {
            result[i] = toCtClass(classPool, classes[i]);
        }
        return result;
    }

    public static CtClass createCtClass(final ClassPool classPool,
            final String name) {
        return createCtClass(classPool, name, Object.class);
    }

    public static CtClass createCtClass(final ClassPool classPool,
            final String name, final Class superClass) {
        return createCtClass(classPool, name, toCtClass(classPool, superClass));
    }

    public static CtClass createCtClass(final ClassPool classPool,
            final String name, final CtClass superClass) {
        return classPool.makeClass(name, superClass);
    }

}
