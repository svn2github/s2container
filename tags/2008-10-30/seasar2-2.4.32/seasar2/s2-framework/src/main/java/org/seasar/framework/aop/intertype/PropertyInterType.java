/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
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
package org.seasar.framework.aop.intertype;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.seasar.framework.aop.InterType;
import org.seasar.framework.log.Logger;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.StringUtil;

/**
 * プロパティを追加するための{@link InterType}です。
 * 
 * @author y-komori
 * 
 */
public class PropertyInterType extends AbstractInterType {

    private static final String SETTER_PREFIX = "set";

    private static final String GETTER_PREFIX = "get";

    /**
     * プロパティを追加しません。
     */
    protected static final int NONE = 0;

    /**
     * getterメソッドだけを追加します。
     */
    protected static final int READ = 1;

    /**
     * setterメソッドだけを追加します。
     */
    protected static final int WRITE = 2;

    /**
     * getter、setterメソッドを追加します。
     */
    protected static final int READWRITE = 3;

    /**
     * noneの値
     */
    protected static final String STR_NONE = "none";

    /**
     * readの値
     */
    protected static final String STR_READ = "read";

    /**
     * writeの値
     */
    protected static final String STR_WRITE = "write";

    /**
     * readwriteの値
     */
    protected static final String STR_READWRITE = "readwrite";

    private static final String TIGER_ANNOTATION_HANDLER = "org.seasar.framework.aop.intertype.TigerPropertyAnnotationHandler";

    private static final String BACKPORT175_ANNOTATION_HANDLER = "org.seasar.framework.aop.intertype.Backport175PropertyAnnotationHandler";

    private static Logger logger = Logger.getLogger(PropertyInterType.class);

    private static PropertyAnnotationHandler annotationHandler = new DefaultPropertyAnnotationHandler();

    private boolean trace;

    private int defaultPropertyType = READWRITE;

    static {
        setupAnnotationHandler();
    }

    /**
     * 値を求めます。
     * 
     * @param type
     *            タイプ
     * @return 値
     */
    protected static int valueOf(String type) {
        int propertyType = NONE;
        if (STR_READ.equals(type)) {
            propertyType = READ;
        } else if (STR_WRITE.equals(type)) {
            propertyType = WRITE;
        } else if (STR_READWRITE.equals(type)) {
            propertyType = READWRITE;
        }
        return propertyType;
    }

    private static void setupAnnotationHandler() {
        Class clazz = null;
        try {
            clazz = Class.forName(TIGER_ANNOTATION_HANDLER, true,
                    PropertyInterType.class.getClassLoader());
        } catch (ClassNotFoundException e) {
            try {
                clazz = Class.forName(BACKPORT175_ANNOTATION_HANDLER, true,
                        PropertyInterType.class.getClassLoader());
            } catch (ClassNotFoundException e2) {
                return;
            }
        }
        annotationHandler = (PropertyAnnotationHandler) ClassUtil
                .newInstance(clazz);
    }

    /**
     * トレースを出力するかどうか設定します。
     * 
     * @param trace
     */
    public void setTrace(boolean trace) {
        this.trace = trace;
    }

    /**
     * デフォルトのpropertyType(NONE, READ, WRITE, READWRITE)を設定します。
     * 
     * @param defaultPropertyType
     */
    public void setDefaultPropertyType(String defaultPropertyType) {
        this.defaultPropertyType = valueOf(defaultPropertyType);
    }

    protected void introduce() {
        if (logger.isDebugEnabled()) {
            logger.debug("[PropertyInterType] Introducing... "
                    + targetClass.getName());
        }

        int defaultValue = annotationHandler.getPropertyType(getTargetClass(),
                defaultPropertyType);
        List targetFields = getTargetFields(targetClass);

        for (Iterator iter = targetFields.iterator(); iter.hasNext();) {
            Field field = (Field) iter.next();
            int property = annotationHandler.getPropertyType(field,
                    defaultValue);
            switch (property) {
            case READ:
                createGetter(targetClass, field);
                break;

            case WRITE:
                createSetter(targetClass, field);
                break;

            case READWRITE:
                createGetter(targetClass, field);
                createSetter(targetClass, field);
                break;

            default:
                break;
            }
        }
    }

    private void createGetter(Class targetClass, Field targetField) {
        String targetFieldName = targetField.getName();
        String methodName = GETTER_PREFIX + createMethodName(targetFieldName);
        if (hasMethod(methodName, null)) {
            return;
        }

        if (logger.isDebugEnabled()) {
            logger.debug("[PropertyInterType] Creating getter "
                    + targetClass.getName() + "#" + methodName);
        }

        StringBuffer src = new StringBuffer(512);
        src.append("{");
        if (trace) {
            src.append("org.seasar.framework.log.Logger logger =");
            src.append("org.seasar.framework.log.Logger.getLogger($class);");
            src.append("if(logger.isDebugEnabled()){");
            src
                    .append("logger.debug(\"CALL \" + $class.getSuperclass().getName() + \"#");
            src.append(methodName);
            src.append("() : \" + this.");
            src.append(targetFieldName);
            src.append(");}");
        }
        src.append("return this.");
        src.append(targetFieldName);
        src.append(";}");

        addMethod(targetField.getType(), methodName, src.toString());
    }

    private void createSetter(Class targetClass, Field targetField) {
        String targetFieldName = targetField.getName();
        String methodName = SETTER_PREFIX + createMethodName(targetFieldName);
        if (hasMethod(methodName, targetField.getType())) {
            return;
        }

        if (logger.isDebugEnabled()) {
            logger.debug("[PropertyInterType] Creating setter "
                    + targetClass.getName() + "#" + methodName);
        }

        StringBuffer src = new StringBuffer(512);
        src.append("{");
        if (trace) {
            src.append("org.seasar.framework.log.Logger logger =");
            src.append("org.seasar.framework.log.Logger.getLogger($class);");
            src.append("if(logger.isDebugEnabled()){");
            src
                    .append("logger.debug(\"CALL \" + $class.getSuperclass().getName() + \"#");
            src.append(methodName);
            src.append("(\" + $1 + \")\");}");
        }
        src.append("this.");
        src.append(targetFieldName);
        src.append(" = $1;}");

        addMethod(methodName, new Class[] { targetField.getType() }, src
                .toString());
    }

    private List getTargetFields(Class targetClass) {
        final Map nominationFields = new LinkedHashMap();
        gatherFields(targetClass, nominationFields);

        final List targetFields = new ArrayList(nominationFields.size());
        for (final Iterator it = nominationFields.values().iterator(); it
                .hasNext();) {
            final Field field = (Field) it.next();
            final int modifier = field.getModifiers();
            if (!Modifier.isPrivate(modifier)) {
                targetFields.add(field);
            }
        }
        return targetFields;
    }

    private void gatherFields(final Class targetClass, final Map fields) {
        final Field[] declaredFields = targetClass.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; ++i) {
            final Field field = declaredFields[i];
            final String name = field.getName();
            if (!fields.containsKey(name)) {
                fields.put(name, field);
            }
        }

        final Class superClass = targetClass.getSuperclass();
        if (superClass != null && superClass != Object.class) {
            gatherFields(superClass, fields);
        }
    }

    private String createMethodName(String fieldName) {
        String methodName = StringUtil.capitalize(fieldName);
        if (methodName.endsWith("_")) {
            methodName = methodName.substring(0, methodName.length() - 1);
        }

        return methodName;
    }

    private boolean hasMethod(String methodName, Class paramType) {
        Class[] param = null;
        if (paramType != null) {
            param = new Class[] { paramType };
        }
        try {
            getTargetClass().getMethod(methodName, param);
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    /**
     * 
     */
    public interface PropertyAnnotationHandler {
        /**
         * propertyTypeを返します。
         * 
         * @param clazz
         * @param defaultValue
         * @return propertyType
         */
        int getPropertyType(Class clazz, int defaultValue);

        /**
         * propertyTypeを返します。
         * 
         * @param field
         * @param defaultValue
         * @return
         */
        int getPropertyType(Field field, int defaultValue);
    }

    /**
     * {@link PropertyInterType.PropertyAnnotationHandler}のデフォルト実装です。
     * 
     */
    public static class DefaultPropertyAnnotationHandler implements
            PropertyAnnotationHandler {

        public int getPropertyType(Class clazz, int defaultValue) {
            return defaultValue;
        }

        public int getPropertyType(Field field, int defaultValue) {
            return defaultValue;
        }

    }
}
