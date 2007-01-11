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
package org.seasar.diigu;

import java.util.HashSet;
import java.util.Set;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.ConstructorDoc;
import com.sun.javadoc.Doclet;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.Parameter;
import com.sun.javadoc.RootDoc;
import com.sun.javadoc.Type;

public class ParameterNameDoclet extends Doclet {
    protected static final Set PRIMITIVE_TYPES = new HashSet();
    static {
        PRIMITIVE_TYPES.add("boolean");
        PRIMITIVE_TYPES.add("byte");
        PRIMITIVE_TYPES.add("short");
        PRIMITIVE_TYPES.add("int");
        PRIMITIVE_TYPES.add("long");
        PRIMITIVE_TYPES.add("float");
        PRIMITIVE_TYPES.add("double");
        PRIMITIVE_TYPES.add("char");
        PRIMITIVE_TYPES.add("void");
    }

    public ParameterNameDoclet() {
    }

    public static boolean start(final RootDoc rootdoc) {
        final ClassDoc classDocs[] = rootdoc.classes();
        for (int i = 0; i < classDocs.length; i++) {
            final ClassDoc classDoc = classDocs[i];
            enhanceClassFile(classDoc);
        }
        return true;
    }

    protected static void enhanceClassFile(final ClassDoc classDoc) {
        final ParameterNameEnhancer enhancer = new ParameterNameEnhancer(
                toBinaryName(classDoc));
        boolean dirty = false;
        dirty |= enhanceConstructor(enhancer, classDoc);
        dirty |= enhanceMethod(enhancer, classDoc);
        if (dirty) {
            enhancer.save();
        }
    }

    protected static boolean enhanceConstructor(
            final ParameterNameEnhancer enhancer, final ClassDoc classDoc) {
        final ClassDoc outerClass = classDoc.containingClass();
        final boolean nonStaticInncerClass = outerClass != null
                && outerClass.isClass() && classDoc.isClass()
                && !classDoc.isStatic();
        boolean dirty = false;
        final ConstructorDoc[] constructorDocs = classDoc.constructors();
        for (int i = 0; i < constructorDocs.length; ++i) {
            if (nonStaticInncerClass) {
                dirty |= enhanceConstructor(enhancer, constructorDocs[i],
                        outerClass);
            } else {
                dirty |= enhanceConstructor(enhancer, constructorDocs[i]);
            }
        }
        return dirty;
    }

    protected static boolean enhanceConstructor(
            final ParameterNameEnhancer enhancer,
            final ConstructorDoc constructorDoc) {
        final Parameter[] parameters = constructorDoc.parameters();
        final int numParameters = parameters.length;
        if (numParameters == 0) {
            return false;
        }

        final String[] parameterTypes = new String[numParameters];
        final String[] parameterNames = new String[numParameters];
        for (int i = 0; i < numParameters; ++i) {
            final Parameter parameter = parameters[i];
            parameterTypes[i] = toBinaryName(parameter.type());
            parameterNames[i] = parameter.name();
        }
        enhancer.setConstructorParameterNames(parameterTypes, parameterNames);
        return true;
    }

    protected static boolean enhanceConstructor(
            final ParameterNameEnhancer enhancer,
            final ConstructorDoc constructorDoc, final ClassDoc outerClass) {
        final Parameter[] parameters = constructorDoc.parameters();
        final int numParameters = parameters.length;

        final String[] parameterTypes = new String[numParameters + 1];
        final String[] parameterNames = new String[numParameters + 1];
        parameterTypes[0] = toBinaryName(outerClass);
        parameterNames[0] = "this";
        for (int i = 0; i < numParameters; ++i) {
            final Parameter parameter = parameters[i];
            parameterTypes[i + 1] = toBinaryName(parameter.type());
            parameterNames[i + 1] = parameter.name();
        }
        enhancer.setConstructorParameterNames(parameterTypes, parameterNames);
        return true;
    }

    protected static boolean enhanceMethod(
            final ParameterNameEnhancer enhancer, final ClassDoc classDoc) {
        boolean dirty = false;
        final MethodDoc[] methodDocs = classDoc.methods();
        for (int i = 0; i < methodDocs.length; ++i) {
            dirty |= enhanceMethod(enhancer, methodDocs[i]);
        }
        return dirty;
    }

    protected static boolean enhanceMethod(
            final ParameterNameEnhancer enhancer, final MethodDoc methodDoc) {
        final Parameter[] parameters = methodDoc.parameters();
        final int numParameters = parameters.length;
        if (numParameters == 0) {
            return false;
        }

        final String[] parameterTypes = new String[numParameters];
        final String[] parameterNames = new String[numParameters];
        for (int i = 0; i < numParameters; ++i) {
            final Parameter parameter = parameters[i];
            final Type type = parameter.type();
            parameterTypes[i] = toBinaryName(type);
            parameterNames[i] = parameter.name();
        }
        final String methodName = methodDoc.name();
        enhancer.setMethodParameterNames(methodName, parameterTypes,
                parameterNames);
        return true;
    }

    protected static String toBinaryName(final Type type) {
        final String dimension = type.dimension();
        final String qualifiedTypeName = type.qualifiedTypeName();
        if (PRIMITIVE_TYPES.contains(qualifiedTypeName)) {
            return qualifiedTypeName + dimension;
        }

        final String typeName = type.typeName();
        assert qualifiedTypeName.endsWith(typeName);
        if (typeName.indexOf('.') == -1) {
            return qualifiedTypeName + dimension;
        }

        final String packageName = qualifiedTypeName.substring(0,
                qualifiedTypeName.length() - typeName.length());
        return packageName + typeName.replace('.', '$');
    }
}
