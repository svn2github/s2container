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
package org.seasar.extension.dxo.annotation.impl;

import org.seasar.extension.dxo.annotation.AnnotationReader;
import org.seasar.extension.dxo.annotation.AnnotationReaderFactory;
import org.seasar.framework.exception.ClassNotFoundRuntimeException;
import org.seasar.framework.util.ClassUtil;

/**
 * @author Satoshi Kimura
 * @author koichik
 */
public class AnnotationReaderFactoryImpl implements AnnotationReaderFactory {

    private static final String TIGER_ANNOTATION_HANDLER_CLASS_NAME = "org.seasar.extension.dxo.annotation.impl.TigerAnnotationReader";

    private AnnotationReader annotationHandler;

    public AnnotationReaderFactoryImpl() {
        try {
            annotationHandler = (AnnotationReader) ClassUtil
                    .newInstance(TIGER_ANNOTATION_HANDLER_CLASS_NAME);
            return;
        } catch (final ClassNotFoundRuntimeException ignore) {
        }
        annotationHandler = new ConstantAnnotationReader();
    }

    public AnnotationReader getAnnotationReader() {
        return annotationHandler;
    }
}
