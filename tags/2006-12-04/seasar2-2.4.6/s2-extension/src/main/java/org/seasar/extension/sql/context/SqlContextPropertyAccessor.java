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
package org.seasar.extension.sql.context;

import java.util.Map;

import ognl.ObjectPropertyAccessor;
import ognl.OgnlException;

import org.seasar.extension.sql.SqlContext;

/**
 * @author higa
 * 
 */
public class SqlContextPropertyAccessor extends ObjectPropertyAccessor {

    private static final String HAS_PREFIX = "has_";

    public Object getProperty(Map cx, Object target, Object name)
            throws OgnlException {

        SqlContext ctx = (SqlContext) target;
        String argName = name.toString();
        if (argName.startsWith(HAS_PREFIX)) {
            return Boolean.valueOf(ctx.hasArg(argName.substring(HAS_PREFIX
                    .length())));
        }
        return ctx.getArg(argName);
    }

}