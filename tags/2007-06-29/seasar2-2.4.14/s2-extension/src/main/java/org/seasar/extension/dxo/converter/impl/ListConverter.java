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
package org.seasar.extension.dxo.converter.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.seasar.extension.dxo.converter.ConversionContext;

/**
 * @author Satoshi Kimura
 * @author koichik
 */
public class ListConverter extends AbstractConverter {

    public Class[] getSourceClasses() {
        return new Class[] { Object.class };
    }

    public Class getDestClass() {
        return List.class;
    }

    public Object convert(final Object source, final Class destClass,
            final ConversionContext context) {
        if (source == null) {
            return null;
        }
        if (source instanceof List) {
            return source;
        }
        if (source.getClass().isArray()) {
            return Arrays.asList((Object[]) source);
        }
        final List list = new ArrayList();
        if (source instanceof Collection) {
            list.addAll((Collection) source);
        } else {
            list.add(source);
        }
        return list;
    }

}
