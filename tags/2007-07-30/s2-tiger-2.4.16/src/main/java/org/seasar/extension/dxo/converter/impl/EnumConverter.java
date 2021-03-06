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

import org.seasar.extension.dxo.converter.ConversionContext;

/**
 * 数値または文字列から列挙定数へ変換するコンバータです。
 * 
 * @author koichik
 */
@SuppressWarnings("unchecked")
public class EnumConverter extends AbstractConverter {

    public Class getDestClass() {
        return Enum.class;
    }

    public Class[] getSourceClasses() {
        return new Class[] { Object.class };
    }

    public Object convert(Object source, Class destClass,
            ConversionContext context) {
        if (source == null) {
            return null;
        }
        if (source instanceof Number) {
            final int ordinal = Number.class.cast(source).intValue();
            return destClass.getEnumConstants()[ordinal];
        }
        final String name = source.toString();
        return Enum.valueOf(destClass, name);
    }

}
