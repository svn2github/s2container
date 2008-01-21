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
package org.seasar3.beanutil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Map;

import org.seasar3.exception.PropertyCouldNotReadRuntimeException;
import org.seasar3.exception.PropertyCouldNotWriteRuntimeException;
import org.seasar3.exception.PropertyNotReadableRuntimeException;
import org.seasar3.exception.PropertyNotWritableRuntimeException;

import sun.reflect.misc.MethodUtil;

/**
 * This class describes one property that a Java Bean has.
 * 
 * @author higa
 * @since 3.0
 */
public final class PropertyDesc {

    private String propertyName;

    private Class<?> propertyClass;

    private Class<?> beanClass;

    private Method readMethod;

    private Method writeMethod;

    private Field field;

    private Constructor<?> stringConstructor;

    private Method valueOfMethod;

    private boolean readable = false;

    private boolean writable = false;

    private ParameterizedClassDesc parameterizedClassDesc;

    /**
     * Constructor.
     * 
     * @param propertyName
     *            the property name.
     * @param propertyClass
     *            the property class.
     * @param beanClass
     *            the bean class.
     */
    PropertyDesc(String propertyName, Class<?> propertyClass, Class<?> beanClass) {
        if (propertyName == null) {
            throw new NullPointerException("propertyName");
        }
        if (propertyClass == null) {
            throw new NullPointerException("propertyClass");
        }
        if (beanClass == null) {
            throw new NullPointerException("beanClass");
        }
        this.propertyName = propertyName;
        this.propertyClass = propertyClass;
        this.beanClass = beanClass;
    }

    /**
     * {@link PropertyDesc}を作成します。
     * 
     * @param propertyName
     * @param propertyType
     * @param readMethod
     * @param writeMethod
     * @param field
     * @param beanDesc
     */
    public PropertyDesc(String propertyName, Class propertyType,
            Method readMethod, Method writeMethod, Field field,
            BeanDesc beanDesc) {

        if (propertyName == null) {
            throw new EmptyRuntimeException("propertyName");
        }
        if (propertyType == null) {
            throw new EmptyRuntimeException("propertyType");
        }
        this.propertyName = propertyName;
        this.propertyType = propertyType;
        setReadMethod(readMethod);
        setWriteMethod(writeMethod);
        setField(field);
        this.beanDesc = beanDesc;
        setupStringConstructor();
        setupValueOfMethod();
        setUpParameterizedClassDesc();
    }

    private void setupStringConstructor() {
        Constructor[] cons = propertyType.getConstructors();
        for (int i = 0; i < cons.length; ++i) {
            Constructor con = cons[i];
            if (con.getParameterTypes().length == 1
                    && con.getParameterTypes()[0].equals(String.class)) {
                stringConstructor = con;
                break;
            }
        }
    }

    private void setupValueOfMethod() {
        Method[] methods = propertyType.getMethods();
        for (int i = 0; i < methods.length; ++i) {
            Method method = methods[i];
            if (MethodUtil.isBridgeMethod(method)
                    || MethodUtil.isSyntheticMethod(method)) {
                continue;
            }
            if (ModifierUtil.isStatic(method.getModifiers())
                    && method.getName().equals("valueOf")
                    && method.getParameterTypes().length == 1
                    && method.getParameterTypes()[0].equals(String.class)) {
                valueOfMethod = method;
                break;
            }
        }
    }

    /**
     * Returns the property name.
     * 
     * @return the property name.
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * Returns the property class.
     * 
     * @return the property class.
     */
    public Class<?> getPropertyClass() {
        return propertyClass;
    }

    /**
     * Returns the bean class.
     * 
     * @return the bean class.
     */
    public Class<?> getBeanClass() {
        return beanClass;
    }

    /**
     * Returns the read method.
     * 
     * @return the read method.
     */
    public Method getReadMethod() {
        return readMethod;
    }

    /**
     * Determines if this property is readable.
     * 
     * @return whether this property is readable.
     */
    public boolean isReadable() {
        return readable;
    }

    void setReadMethod(Method readMethod) {
        this.readMethod = readMethod;
        if (readMethod != null) {
            readable = true;
        }
    }

    /**
     * Returns the write method.
     * 
     * @return the write method.
     */
    public Method getWriteMethod() {
        return writeMethod;
    }

    /**
     * Determines if this property is writable.
     * 
     * @return whether this property is writable.
     */
    public boolean isWritable() {
        return writable;
    }

    void setWriteMethod(Method writeMethod) {
        this.writeMethod = writeMethod;
        if (writeMethod != null) {
            writable = true;
        }
    }

    /**
     * Returns the field.
     * 
     * @return the field.
     */
    public Field getField() {
        return field;
    }

    void setField(Field field) {
        this.field = field;
        if (field != null) {
            field.setAccessible(true);
            if (Modifier.isPublic(field.getModifiers())) {
                readable = true;
                writable = true;
            }
        }
    }

    /**
     * Returns the value.
     * 
     * @param target
     *            the bean instance.
     * @return the value.
     */
    public Object getValue(Object target) {
        if (!readable) {
            throw new PropertyNotReadableRuntimeException(beanClass,
                    propertyName);
        }
        try {
            if (readMethod != null) {
                return readMethod.invoke(target);
            }
            return field.get(target);
        } catch (Throwable t) {
            throw new PropertyCouldNotReadRuntimeException(beanClass,
                    propertyName, t);
        }
    }

    /**
     * Sets the value.
     * 
     * @param target
     *            the bean instance.
     * @param value
     *            the value.
     */
    public void setValue(Object target, Object value) {
        if (!writable) {
            throw new PropertyNotWritableRuntimeException(beanClass,
                    propertyName);
        }
        try {
            value = ConversionUtil.convert(value, propertyClass);
            if (writeMethod != null) {
                writeMethod.invoke(target, value);
            } else {
                field.set(target, value);
            }
        } catch (Throwable t) {
            if (t.getClass() == RuntimeException.class) {
                t = t.getCause();
            }
            throw new PropertyCouldNotWriteRuntimeException(beanClass,
                    propertyName, value, t);
        }
    }

    public boolean isParameterized() {
        return parameterizedClassDesc != null
                && parameterizedClassDesc.isParameterizedClass();
    }

    public ParameterizedClassDesc getParameterizedClassDesc() {
        return parameterizedClassDesc;
    }

    public Class getElementClassOfCollection() {
        if (!Collection.class.isAssignableFrom(propertyType)
                || !isParameterized()) {
            return null;
        }
        return parameterizedClassDesc.getArguments()[0].getRawClass();
    }

    public Class getKeyClassOfMap() {
        if (!Map.class.isAssignableFrom(propertyType) || !isParameterized()) {
            return null;
        }
        return parameterizedClassDesc.getArguments()[0].getRawClass();
    }

    public Class getValueClassOfMap() {
        if (!Map.class.isAssignableFrom(propertyType) || !isParameterized()) {
            return null;
        }
        return parameterizedClassDesc.getArguments()[1].getRawClass();
    }

}
