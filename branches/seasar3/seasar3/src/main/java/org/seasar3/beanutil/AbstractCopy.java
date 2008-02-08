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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.seasar3.exception.ConverterRuntimeException;

/**
 * An abstract class to copy Java Bean and map.
 * 
 * @author higa
 * @since 3.0
 * @param <S>
 *            the sub type.
 * 
 */
public abstract class AbstractCopy<S extends AbstractCopy<S>> {

    /**
     * The array of empty string.
     */
    protected static final String[] EMPTY_STRING_ARRAY = new String[0];

    /**
     * The delimiter of Java Bean.
     */
    protected static final char BEAN_DELIMITER = '$';

    /**
     * The delimiter of Map.
     */
    protected static final char MAP_DELIMITER = '.';

    /**
     * The array of property name that are included as target.
     */
    protected String[] includePropertyNames = EMPTY_STRING_ARRAY;

    /**
     * The array of property name that are excluded as target.
     */
    protected String[] excludePropertyNames = EMPTY_STRING_ARRAY;

    /**
     * Whether the property that has null value is excluded.
     */
    protected boolean excludesNull = false;

    /**
     * Whether the property that has white-space value is excluded.
     */
    protected boolean excludesWhitespace = false;

    /**
     * The prefix of property name.
     */
    protected String prefix;

    /**
     * Some converters that are related to the specific property.
     */
    protected Map<String, Converter> converterMap = new HashMap<String, Converter>();

    /**
     * Some converters that are not related to the specific property.
     */
    protected List<Converter> converters = new ArrayList<Converter>();

    /**
     * Includes some properties as target.
     * 
     * @param propertyNames
     *            the array of property name.
     * @return this instance.
     */
    @SuppressWarnings("unchecked")
    public S includes(String... propertyNames) {
        this.includePropertyNames = propertyNames;
        return (S) this;
    }

    /**
     * Excludes some properties.
     * 
     * @param propertyNames
     *            the array of property name.
     * @return this instance.
     */
    @SuppressWarnings("unchecked")
    public S excludes(String... propertyNames) {
        this.excludePropertyNames = propertyNames;
        return (S) this;
    }

    /**
     * Excludes some properties that have null value.
     * 
     * @return this instance.
     */
    @SuppressWarnings("unchecked")
    public S excludesNull() {
        this.excludesNull = true;
        return (S) this;
    }

    /**
     * Excludes some properties that have white-space value.
     * 
     * @return this instance.
     */
    @SuppressWarnings("unchecked")
    public S excludesWhitespace() {
        this.excludesWhitespace = true;
        return (S) this;
    }

    /**
     * Specifies the prefix of property name that is included as target.
     * 
     * @param prefix
     *            the prefix.
     * @return this instance.
     * 
     */
    @SuppressWarnings("unchecked")
    public S prefix(String prefix) {
        this.prefix = prefix;
        return (S) this;
    }

    /**
     * Specifies the converter.
     * 
     * @param converter
     *            the converter.
     * @param propertyNames
     *            the array of property name.
     * @return this instance.
     */
    @SuppressWarnings("unchecked")
    public S converter(Converter converter, String... propertyNames) {
        if (propertyNames.length == 0) {
            converters.add(converter);
        } else {
            for (String name : propertyNames) {
                converterMap.put(name, converter);
            }
        }
        return (S) this;
    }

    /**
     * Specifies the date converter.
     * 
     * @param pattern
     *            the pattern.
     * @param propertyNames
     *            the array of property name.
     * @return this instance.
     */
    public S dateConverter(String pattern, String... propertyNames) {
        return converter(new DateConverter(pattern), propertyNames);
    }

    /**
     * Specifies the number converter.
     * 
     * @param pattern
     *            the pattern.
     * @param propertyNames
     *            the array of property name.
     * @return this instance.
     */
    public S numberConverter(String pattern, String... propertyNames) {
        return converter(new NumberConverter(pattern), propertyNames);
    }

    /**
     * Determines if the property is target.
     * 
     * @param name
     *            the property name.
     * @return Whether the property is target.
     */
    protected boolean isTargetProperty(String name) {
        if (includePropertyNames.length > 0) {
            for (String s : includePropertyNames) {
                if (s.equals(name)
                        && (prefix == null || name.startsWith(prefix))) {
                    for (String s2 : excludePropertyNames) {
                        if (s2.equals(name)) {
                            return false;
                        }
                    }
                    return true;
                }
            }
            return false;
        }
        if (excludePropertyNames.length > 0) {
            for (String s : excludePropertyNames) {
                if (s.equals(name)) {
                    return false;
                }
            }
            return true;
        }
        return prefix == null || name.startsWith(prefix);
    }

    /**
     * Copies Java Bean to Java Bean.
     * 
     * @param src
     *            source.
     * @param dest
     *            destination.
     */
    protected void copyBeanToBean(Object src, Object dest) {
        BeanDesc srcBeanDesc = BeanUtil.getBeanDesc(src.getClass());
        BeanDesc destBeanDesc = BeanUtil.getBeanDesc(dest.getClass());
        int size = srcBeanDesc.getPropertyDescSize();
        for (int i = 0; i < size; i++) {
            PropertyDesc srcPropertyDesc = srcBeanDesc.getPropertyDesc(i);
            String srcPropertyName = srcPropertyDesc.getPropertyName();
            if (!srcPropertyDesc.isReadable()
                    || !isTargetProperty(srcPropertyName)) {
                continue;
            }
            String destPropertyName = trimPrefix(srcPropertyName);
            if (!destBeanDesc.hasPropertyDesc(destPropertyName)) {
                continue;
            }
            PropertyDesc destPropertyDesc = destBeanDesc
                    .getPropertyDesc(destPropertyName);
            if (!destPropertyDesc.isWritable()) {
                continue;
            }
            Object value = srcPropertyDesc.getValue(src);
            if (value instanceof String && excludesWhitespace
                    && ((String) value).trim().length() == 0) {
                continue;
            }
            if (value == null && excludesNull) {
                continue;
            }
            value = convertValue(value, destPropertyName, destPropertyDesc
                    .getPropertyClass());
            destPropertyDesc.setValue(dest, value);
        }
    }

    /**
     * Copies Java Bean to map.
     * 
     * @param src
     *            source.
     * @param dest
     *            destination.
     */
    @SuppressWarnings("unchecked")
    protected void copyBeanToMap(Object src, Map dest) {
        BeanDesc srcBeanDesc = BeanUtil.getBeanDesc(src.getClass());
        int size = srcBeanDesc.getPropertyDescSize();
        for (int i = 0; i < size; i++) {
            PropertyDesc srcPropertyDesc = srcBeanDesc.getPropertyDesc(i);
            String srcPropertyName = srcPropertyDesc.getPropertyName();
            if (!srcPropertyDesc.isReadable()
                    || !isTargetProperty(srcPropertyName)) {
                continue;
            }
            Object value = srcPropertyDesc.getValue(src);
            if (value instanceof String && excludesWhitespace
                    && ((String) value).trim().length() == 0) {
                continue;
            }
            if (value == null && excludesNull) {
                continue;
            }
            String destPropertyName = trimPrefix(srcPropertyName.replace(
                    BEAN_DELIMITER, MAP_DELIMITER));
            value = convertValue(value, destPropertyName, null);
            dest.put(destPropertyName, value);
        }
    }

    /**
     * Copies map to Java Bean.
     * 
     * @param src
     *            source.
     * @param dest
     *            destination.
     */
    protected void copyMapToBean(Map<String, Object> src, Object dest) {
        BeanDesc destBeanDesc = BeanUtil.getBeanDesc(dest.getClass());
        for (Iterator<String> i = src.keySet().iterator(); i.hasNext();) {
            String srcPropertyName = i.next();
            if (!isTargetProperty(srcPropertyName)) {
                continue;
            }
            String destPropertyName = trimPrefix(srcPropertyName.replace(
                    MAP_DELIMITER, BEAN_DELIMITER));
            if (!destBeanDesc.hasPropertyDesc(destPropertyName)) {
                continue;
            }
            PropertyDesc destPropertyDesc = destBeanDesc
                    .getPropertyDesc(destPropertyName);
            if (!destPropertyDesc.isWritable()) {
                continue;
            }
            Object value = src.get(srcPropertyName);
            if (value instanceof String && excludesWhitespace
                    && ((String) value).trim().length() == 0) {
                continue;
            }
            if (value == null && excludesNull) {
                continue;
            }
            value = convertValue(value, destPropertyName, destPropertyDesc
                    .getPropertyClass());
            destPropertyDesc.setValue(dest, value);
        }
    }

    /**
     * Copies map to map.
     * 
     * @param src
     *            source.
     * @param dest
     *            destination.
     */
    protected void copyMapToMap(Map<String, Object> src,
            Map<String, Object> dest) {
        for (Iterator<String> i = src.keySet().iterator(); i.hasNext();) {
            String srcPropertyName = i.next();
            if (!isTargetProperty(srcPropertyName)) {
                continue;
            }
            String destPropertyName = trimPrefix(srcPropertyName);
            Object value = src.get(srcPropertyName);
            if (value instanceof String && excludesWhitespace
                    && ((String) value).trim().length() == 0) {
                continue;
            }
            if (value == null && excludesNull) {
                continue;
            }
            value = convertValue(value, destPropertyName, null);
            dest.put(destPropertyName, value);
        }
    }

    /**
     * Trims the prefix.
     * 
     * @param propertyName
     *            the property name
     * @return the result.
     */
    protected String trimPrefix(String propertyName) {
        if (prefix == null) {
            return propertyName;
        }
        return propertyName.substring(prefix.length());
    }

    /**
     * Converts the value.
     * 
     * @param value
     *            the value.
     * @param destPropertyName
     *            the property name of destination.
     * @param destPropertyClass
     *            the property class of destination.
     * @return the converted value.
     */
    protected Object convertValue(Object value, String destPropertyName,
            Class<?> destPropertyClass) {
        if (value == null) {
            return null;
        }
        Converter converter = converterMap.get(destPropertyName);
        if (converter == null) {
            Class<?> targetClass = value.getClass() != String.class ? value
                    .getClass() : destPropertyClass;
            if (targetClass == null) {
                return value;
            }
            for (Converter c : converters) {
                if (c.isTarget(targetClass)) {
                    converter = c;
                    break;
                }
            }
            if (converter == null) {
                return value;
            }
        }
        try {
            if (value.getClass() == String.class) {
                return converter.getAsObject((String) value);
            }
            return converter.getAsString(value);
        } catch (Throwable cause) {
            throw new ConverterRuntimeException(destPropertyName, value, cause);
        }
    }
}