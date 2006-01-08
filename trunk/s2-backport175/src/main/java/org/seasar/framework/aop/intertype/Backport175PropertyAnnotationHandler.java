/*
 * Copyright 2004-2005 the Seasar Foundation and the Others.
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

import org.codehaus.backport175.reader.Annotation;
import org.codehaus.backport175.reader.Annotations;
import org.seasar.framework.aop.intertype.PropertyInterType.PropertyAnnotationHandler;
import org.seasar.framework.container.annotation.backport175.Property;

/**
 * @author y-komori
 * 
 */
public class Backport175PropertyAnnotationHandler implements PropertyAnnotationHandler {
    public int getPropertyType(Class clazz, int defaultValue) {
        return getPropertyType(Annotations.getAnnotation(Property.class, clazz), defaultValue);
    }

    public int getPropertyType(Field field, int defaultValue) {
        return getPropertyType(Annotations.getAnnotation(Property.class, field), defaultValue);
    }

    public int getPropertyType(Annotation property, int defaultValue) {
        int propertyType = defaultValue;
        if (property != null) {
            String type = ((Property) property).value();
            propertyType = PropertyInterType.valueOf(type);
        }
        return propertyType;
    }
}
