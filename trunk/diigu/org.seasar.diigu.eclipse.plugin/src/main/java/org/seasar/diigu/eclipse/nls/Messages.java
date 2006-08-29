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
package org.seasar.diigu.eclipse.nls;

import org.eclipse.osgi.util.NLS;

/**
 * @author taichi
 * 
 */
public class Messages extends NLS {

    public static String ENHANCE_BEGIN;

    public static String ENHANCE_PROCEED;

    public static String ENHANCE_END;

    public static String USE_BUILDER;

    public static String SELECT_EXPRESSION;

    public static String TEST_EXPRESSION;

    public static String ENHANCE_FULLBUILD;

    public static String ENHANCE_INCREMENTALBUILD;

    public static String SELECT_EXPRESSION_CHANGED;

    public static String ENHANCE_ERROR;

    public static String ANALYZE_DEPENDENCY;

    public static String CLASS_FILE_NOT_FOUND;

    static {
        Class clazz = Messages.class;
        NLS.initializeMessages(clazz.getName(), clazz);
    }
}
