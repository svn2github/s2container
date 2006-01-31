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
package org.seasar.framework.util;

import java.util.Map;

import ognl.ClassResolver;
import ognl.Ognl;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.exception.OgnlRuntimeException;

/**
 * @author higa
 * 
 */
public final class OgnlUtil {

    private OgnlUtil() {
    }

    public static Object getValue(Object exp, Object root) {
        return getValue(exp, root, null, 0);
    }

    public static Object getValue(Object exp, Object root, String path,
            int lineNumber) {
        return getValue(exp, null, root, path, lineNumber);
    }

    public static Object getValue(Object exp, Map ctx, Object root) {
        return getValue(exp, ctx, root, null, 0);
    }

    public static Object getValue(Object exp, Map ctx, Object root,
            String path, int lineNumber) {
        try {
            Map newCtx = addClassResolverIfNecessary(ctx, root);
            if (newCtx != null) {
                return Ognl.getValue(exp, newCtx, root);
            } else {
                return Ognl.getValue(exp, root);
            }
        } catch (Exception ex) {
            throw new OgnlRuntimeException(ex, path, lineNumber);
        }
    }

    public static Object parseExpression(String expression) {
        return parseExpression(expression, null, 0);
    }

    public static Object parseExpression(String expression, String path,
            int lineNumber) {
        try {
            return Ognl.parseExpression(expression);
        } catch (Exception ex) {
            throw new OgnlRuntimeException(ex, path, lineNumber);
        }
    }

    static Map addClassResolverIfNecessary(Map ctx, Object root) {
        if (root instanceof S2Container) {
            S2Container container = (S2Container) root;
            ClassLoader classLoader = container.getClassLoader();
            if (classLoader != null) {
                ClassResolverImpl classResolver = new ClassResolverImpl(
                        classLoader);
                if (ctx == null) {
                    ctx = Ognl.createDefaultContext(root, classResolver);
                } else {
                    ctx = Ognl.addDefaultContext(root, classResolver, ctx);
                }
            }
        }
        return ctx;
    }

    public static class ClassResolverImpl implements ClassResolver {
        private ClassLoader classLoader_;

        public ClassResolverImpl(ClassLoader classLoader) {
            classLoader_ = classLoader;
        }

        public Class classForName(String className, Map ctx)
                throws ClassNotFoundException {
            try {
                return Class.forName(className, true, classLoader_);
            } catch (ClassNotFoundException ex) {
                int dot = className.indexOf('.');
                if (dot < 0) {
                    return Class.forName("java.lang." + className, true,
                            classLoader_);
                } else {
                    throw ex;
                }
            }
        }
    }
}
