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
package org.seasar.framework.beans.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.ConstructorNotFoundRuntimeException;
import org.seasar.framework.beans.FieldNotFoundRuntimeException;
import org.seasar.framework.beans.MethodNotFoundRuntimeException;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.PropertyNotFoundRuntimeException;
import org.seasar.framework.exception.EmptyRuntimeException;
import org.seasar.framework.util.ArrayMap;
import org.seasar.framework.util.CaseInsensitiveMap;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.ConstructorUtil;
import org.seasar.framework.util.DoubleConversionUtil;
import org.seasar.framework.util.FieldUtil;
import org.seasar.framework.util.FloatConversionUtil;
import org.seasar.framework.util.IntegerConversionUtil;
import org.seasar.framework.util.LongConversionUtil;
import org.seasar.framework.util.MethodUtil;
import org.seasar.framework.util.ShortConversionUtil;
import org.seasar.framework.util.StringUtil;

/**
 * @author higa
 * 
 */
public final class BeanDescImpl implements BeanDesc {

    private static final Object[] EMPTY_ARGS = new Object[0];

    private Class beanClass;

    private Constructor[] constructors;

    private CaseInsensitiveMap propertyDescCache = new CaseInsensitiveMap();

    private Map methodsCache = new HashMap();

    private Map fieldCache = new HashMap();

    private transient Set invalidPropertyNames = new HashSet();

    /**
     * 
     */
    public BeanDescImpl(Class beanClass) throws EmptyRuntimeException {
        if (beanClass == null) {
            throw new EmptyRuntimeException("beanClass");
        }
        this.beanClass = beanClass;
        constructors = beanClass.getConstructors();
        setupPropertyDescs();
        setupMethods();
        setupFields();
    }

    /**
     * @see org.seasar.framework.beans.BeanDesc#getBeanClass()
     */
    public Class getBeanClass() {
        return beanClass;
    }

    public boolean hasPropertyDesc(String propertyName) {
        return propertyDescCache.get(propertyName) != null;
    }

    /**
     * @see org.seasar.framework.beans.BeanDesc#getPropertyDesc(java.lang.String)
     */
    public PropertyDesc getPropertyDesc(String propertyName)
            throws PropertyNotFoundRuntimeException {

        PropertyDesc pd = (PropertyDesc) propertyDescCache.get(propertyName);
        if (pd == null) {
            throw new PropertyNotFoundRuntimeException(beanClass, propertyName);
        }
        return pd;
    }

    private PropertyDesc getPropertyDesc0(String propertyName) {
        return (PropertyDesc) propertyDescCache.get(propertyName);
    }

    /**
     * @see org.seasar.framework.beans.BeanDesc#getPropertyDesc(int)
     */
    public PropertyDesc getPropertyDesc(int index) {
        return (PropertyDesc) propertyDescCache.get(index);
    }

    /**
     * @see org.seasar.framework.beans.BeanDesc#getPropertyDescSize()
     */
    public int getPropertyDescSize() {
        return propertyDescCache.size();
    }

    /**
     * @see org.seasar.framework.beans.BeanDesc#hasField(java.lang.String)
     */
    public boolean hasField(String fieldName) {
        return fieldCache.get(fieldName) != null;
    }

    /**
     * @see org.seasar.framework.beans.BeanDesc#getField(java.lang.String)
     */
    public Field getField(String fieldName) {
        Field field = (Field) fieldCache.get(fieldName);
        if (field == null) {
            throw new FieldNotFoundRuntimeException(beanClass, fieldName);
        }
        return field;
    }

    /**
     * @see org.seasar.framework.beans.BeanDesc#getFieldValue(java.lang.String,
     *      java.lang.Object)
     */
    public Object getFieldValue(String fieldName, Object target)
            throws FieldNotFoundRuntimeException {
        Field field = getField(fieldName);
        return FieldUtil.get(field, target);
    }

    /**
     * @see org.seasar.framework.beans.BeanDesc#newInstance(java.lang.Object[])
     */
    public Object newInstance(Object[] args)
            throws ConstructorNotFoundRuntimeException {

        Constructor constructor = getSuitableConstructor(args);
        return ConstructorUtil.newInstance(constructor, args);
    }

    /**
     * @see org.seasar.framework.beans.BeanDesc#invoke(java.lang.Object,
     *      java.lang.String, java.lang.Object[])
     */
    public Object invoke(Object target, String methodName, Object[] args) {
        Method method = getSuitableMethod(methodName, args);
        return MethodUtil.invoke(method, target, args);
    }

    /**
     * @see org.seasar.framework.beans.BeanDesc#getSuitableConstructor(java.lang.Object[])
     */
    public Constructor getSuitableConstructor(Object[] args)
            throws ConstructorNotFoundRuntimeException {

        if (args == null) {
            args = EMPTY_ARGS;
        }
        Constructor constructor = findSuitableConstructor(args);
        if (constructor != null) {
            return constructor;
        }
        constructor = findSuitableConstructorAdjustNumber(args);
        if (constructor != null) {
            return constructor;
        }
        throw new ConstructorNotFoundRuntimeException(beanClass, args);
    }

    /**
     * @see org.seasar.framework.beans.BeanDesc#getMethods(java.lang.String)
     */
    public Method[] getMethods(String methodName)
            throws MethodNotFoundRuntimeException {

        Method[] methods = (Method[]) methodsCache.get(methodName);
        if (methods == null) {
            throw new MethodNotFoundRuntimeException(beanClass, methodName,
                    null);
        }
        return methods;
    }

    /**
     * @see org.seasar.framework.beans.BeanDesc#hasMethod(java.lang.String)
     */
    public boolean hasMethod(String methodName) {
        return methodsCache.get(methodName) != null;
    }

    public String[] getMethodNames() {
        return (String[]) methodsCache.keySet().toArray(
                new String[methodsCache.size()]);
    }

    private Constructor findSuitableConstructor(Object[] args) {
        outerLoop: for (int i = 0; i < constructors.length; ++i) {
            Class[] paramTypes = constructors[i].getParameterTypes();
            if (paramTypes.length != args.length) {
                continue;
            }
            for (int j = 0; j < args.length; ++j) {
                if (args[j] == null
                        || ClassUtil.isAssignableFrom(paramTypes[j], args[j]
                                .getClass())) {
                    continue;
                }
                continue outerLoop;
            }
            return constructors[i];
        }
        return null;
    }

    private Constructor findSuitableConstructorAdjustNumber(Object[] args) {
        outerLoop: for (int i = 0; i < constructors.length; ++i) {
            Class[] paramTypes = constructors[i].getParameterTypes();
            if (paramTypes.length != args.length) {
                continue;
            }
            for (int j = 0; j < args.length; ++j) {
                if (args[j] == null
                        || ClassUtil.isAssignableFrom(paramTypes[j], args[j]
                                .getClass())
                        || adjustNumber(paramTypes, args, j)) {
                    continue;
                }
                continue outerLoop;
            }
            return constructors[i];
        }
        return null;
    }

    private static boolean adjustNumber(Class[] paramTypes, Object[] args,
            int index) {

        if (paramTypes[index].isPrimitive()) {
            if (paramTypes[index] == int.class) {
                args[index] = IntegerConversionUtil.toInteger(args[index]);
                return true;
            } else if (paramTypes[index] == double.class) {
                args[index] = DoubleConversionUtil.toDouble(args[index]);
                return true;
            } else if (paramTypes[index] == long.class) {
                args[index] = LongConversionUtil.toLong(args[index]);
                return true;
            } else if (paramTypes[index] == short.class) {
                args[index] = ShortConversionUtil.toShort(args[index]);
                return true;
            } else if (paramTypes[index] == float.class) {
                args[index] = FloatConversionUtil.toFloat(args[index]);
                return true;
            }
        } else {
            if (paramTypes[index] == Integer.class) {
                args[index] = IntegerConversionUtil.toInteger(args[index]);
                return true;
            } else if (paramTypes[index] == Double.class) {
                args[index] = DoubleConversionUtil.toDouble(args[index]);
                return true;
            } else if (paramTypes[index] == Long.class) {
                args[index] = LongConversionUtil.toLong(args[index]);
                return true;
            } else if (paramTypes[index] == Short.class) {
                args[index] = ShortConversionUtil.toShort(args[index]);
                return true;
            } else if (paramTypes[index] == Float.class) {
                args[index] = FloatConversionUtil.toFloat(args[index]);
                return true;
            }
        }
        return false;
    }

    private void setupPropertyDescs() {
        Method[] methods = beanClass.getMethods();
        for (int i = 0; i < methods.length; i++) {
            Method m = methods[i];
            String methodName = m.getName();
            if (methodName.startsWith("get")) {
                if (m.getParameterTypes().length != 0
                        || methodName.equals("getClass")) {
                    continue;
                }
                String propertyName = decapitalizePropertyName(methodName
                        .substring(3));
                setupReadMethod(m, propertyName);
            } else if (methodName.startsWith("is")) {
                if (m.getParameterTypes().length != 0
                        || !m.getReturnType().equals(Boolean.TYPE)) {
                    continue;
                }
                String propertyName = decapitalizePropertyName(methodName
                        .substring(2));
                setupReadMethod(m, propertyName);
            } else if (methodName.startsWith("set")) {
                if (m.getParameterTypes().length != 1
                        || methodName.equals("setClass")) {
                    continue;
                }
                String propertyName = decapitalizePropertyName(methodName
                        .substring(3));
                setupWriteMethod(m, propertyName);
            }
        }
        for (Iterator i = invalidPropertyNames.iterator(); i.hasNext();) {
            propertyDescCache.remove(i.next());
        }
        invalidPropertyNames.clear();
    }

    private static String decapitalizePropertyName(String name) {
        if (StringUtil.isEmpty(name)) {
            return name;
        }
        if (name.length() > 1 && Character.isUpperCase(name.charAt(1))
                && Character.isUpperCase(name.charAt(0))) {

            return name;
        }
        char chars[] = name.toCharArray();
        chars[0] = Character.toLowerCase(chars[0]);
        return new String(chars);
    }

    private void addPropertyDesc(PropertyDesc propertyDesc)
            throws EmptyRuntimeException {

        if (propertyDesc == null) {
            throw new EmptyRuntimeException("propertyDesc");
        }
        propertyDescCache.put(propertyDesc.getPropertyName(), propertyDesc);
    }

    private void setupReadMethod(Method readMethod, String propertyName) {
        Class propertyType = readMethod.getReturnType();
        PropertyDesc propDesc = getPropertyDesc0(propertyName);
        if (propDesc != null) {
            if (!propDesc.getPropertyType().equals(propertyType)) {
                invalidPropertyNames.add(propertyName);
            } else {
                propDesc.setReadMethod(readMethod);
            }
        } else {
            propDesc = new PropertyDescImpl(propertyName, propertyType,
                    readMethod, null, this);
            addPropertyDesc(propDesc);
        }
    }

    private void setupWriteMethod(Method writeMethod, String propertyName) {
        Class propertyType = writeMethod.getParameterTypes()[0];
        PropertyDesc propDesc = getPropertyDesc0(propertyName);
        if (propDesc != null) {
            if (!propDesc.getPropertyType().equals(propertyType)) {
                invalidPropertyNames.add(propertyName);
            } else {
                propDesc.setWriteMethod(writeMethod);
            }
        } else {
            propDesc = new PropertyDescImpl(propertyName, propertyType, null,
                    writeMethod, this);
            addPropertyDesc(propDesc);
        }
    }

    private Method getSuitableMethod(String methodName, Object[] args)
            throws MethodNotFoundRuntimeException {

        if (args == null) {
            args = EMPTY_ARGS;
        }
        Method[] methods = getMethods(methodName);
        Method method = findSuitableMethod(methods, args);
        if (method != null) {
            return method;
        }
        method = findSuitableMethodAdjustNumber(methods, args);
        if (method != null) {
            return method;
        }
        throw new MethodNotFoundRuntimeException(beanClass, methodName, args);
    }

    private Method findSuitableMethod(Method[] methods, Object[] args) {
        outerLoop: for (int i = 0; i < methods.length; ++i) {
            Class[] paramTypes = methods[i].getParameterTypes();
            if (paramTypes.length != args.length) {
                continue;
            }
            for (int j = 0; j < args.length; ++j) {
                if (args[j] == null
                        || ClassUtil.isAssignableFrom(paramTypes[j], args[j]
                                .getClass())) {
                    continue;
                }
                continue outerLoop;
            }
            return methods[i];
        }
        return null;
    }

    private Method findSuitableMethodAdjustNumber(Method[] methods,
            Object[] args) {

        outerLoop: for (int i = 0; i < methods.length; ++i) {
            Class[] paramTypes = methods[i].getParameterTypes();
            if (paramTypes.length != args.length) {
                continue;
            }
            for (int j = 0; j < args.length; ++j) {
                if (args[j] == null
                        || ClassUtil.isAssignableFrom(paramTypes[j], args[j]
                                .getClass())
                        || adjustNumber(paramTypes, args, j)) {
                    continue;
                }
                continue outerLoop;
            }
            return methods[i];
        }
        return null;
    }

    private void setupMethods() {
        ArrayMap methodListMap = new ArrayMap();
        Method[] methods = beanClass.getMethods();
        for (int i = 0; i < methods.length; i++) {
            List list = (List) methodListMap.get(methods[i].getName());
            if (list == null) {
                list = new ArrayList();
                methodListMap.put(methods[i].getName(), list);
            }
            list.add(methods[i]);
        }
        for (int i = 0; i < methodListMap.size(); ++i) {
            List methodList = (List) methodListMap.get(i);
            methodsCache.put(methodListMap.getKey(i), methodList
                    .toArray(new Method[methodList.size()]));
        }
    }

    /*
     * private void setupField() { for (Class clazz = beanClass_; clazz !=
     * Object.class && clazz != null; clazz = clazz.getSuperclass()) {
     * 
     * Field[] fields = clazz.getDeclaredFields(); for (int i = 0; i <
     * fields.length; ++i) { Field field = fields[i]; String fname =
     * field.getName(); if (!fieldCache_.containsKey(fname)) {
     * fieldCache_.put(fname, field); } } } }
     */
    private void setupFields() {
        setupFields(beanClass);
    }

    private void setupFields(Class targetClass) {
        if (targetClass.isInterface()) {
            setupFieldsByInterface(targetClass);
        } else {
            setupFieldsByClass(targetClass);
        }
    }

    private void setupFieldsByInterface(Class interfaceClass) {
        addFields(interfaceClass);
        Class[] interfaces = interfaceClass.getInterfaces();
        for (int i = 0; i < interfaces.length; ++i) {
            setupFieldsByInterface(interfaces[i]);
        }
    }

    private void addFields(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; ++i) {
            Field field = fields[i];
            String fname = field.getName();
            if (!fieldCache.containsKey(fname)) {
                fieldCache.put(fname, field);
            }
        }
    }

    private void setupFieldsByClass(Class targetClass) {
        addFields(targetClass);
        Class[] interfaces = targetClass.getInterfaces();
        for (int i = 0; i < interfaces.length; ++i) {
            setupFieldsByInterface(interfaces[i]);
        }
        Class superClass = targetClass.getSuperclass();
        if (superClass != Object.class && superClass != null) {
            setupFieldsByClass(superClass);
        }
    }
    /*
     * private void setupField() { Field[] fields = beanClass_.getFields(); for
     * (int i = 0; i < fields.length; i++) { if
     * (Modifier.isStatic(fields[i].getModifiers())) {
     * fieldCache_.put(fields[i].getName(), fields[i]); } } }
     */
}
