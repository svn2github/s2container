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

    private static final String STR_READ = "read";

    private static final String STR_WRITE = "write";

    private static final String STR_READWRITE = "readwrite";

    public boolean isPropertyAnnotatted(Field field) {
        Annotation property = Annotations.getAnnotation(Property.class, field);
        return (property != null) ? true : false;
    }

    public int getPropertyType(Field field) {
        Annotation property = Annotations.getAnnotation(Property.class, field);
        int propertyType = PropertyInterType.NONE;
        if (property != null) {
            String type = ((Property) property).value();
            if (STR_READ.equals(type)) {
                propertyType = PropertyInterType.READ;
            } else if (STR_WRITE.equals(type)) {
                propertyType = PropertyInterType.WRITE;
            } else if (STR_READWRITE.equals(type)) {
                propertyType = PropertyInterType.READWRITE;
            }
        }

        return propertyType;
    }
}
