package org.seasar.diigu;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.ConstructorDoc;
import com.sun.javadoc.Doclet;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.Parameter;
import com.sun.javadoc.RootDoc;
import com.sun.javadoc.Type;

public class ParameterNameDoclet extends Doclet {

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
        final String className = classDoc.qualifiedTypeName();
        final ParameterNameEnhancer enhancer = new ParameterNameEnhancer(
                className);
        boolean dirty = false;
        dirty |= enhanceConstructor(enhancer, classDoc);
        dirty |= enhanceMethod(enhancer, classDoc);
        if (dirty) {
            enhancer.save();
        }
    }

    protected static boolean enhanceConstructor(
            final ParameterNameEnhancer enhancer, final ClassDoc classDoc) {
        boolean dirty = false;
        final ConstructorDoc[] constructorDocs = classDoc.constructors();
        for (int i = 0; i < constructorDocs.length; ++i) {
            dirty |= enhanceConstructor(enhancer, constructorDocs[i]);
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
            parameterTypes[i] = parameter.type().qualifiedTypeName();
            parameterNames[i] = parameter.name();
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
            parameterTypes[i] = type.qualifiedTypeName() + type.dimension();
            parameterNames[i] = parameter.name();
        }
        final String methodName = methodDoc.name();
        enhancer.setMethodParameterNames(methodName, parameterTypes,
                parameterNames);
        return true;
    }
}
