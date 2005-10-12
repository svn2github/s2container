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
package org.seasar.framework.container.factory;

import org.aopalliance.intercept.MethodInterceptor;
import org.seasar.framework.aop.Pointcut;
import org.seasar.framework.aop.impl.PointcutImpl;
import org.seasar.framework.container.AspectDef;
import org.seasar.framework.container.impl.AspectDefImpl;
import org.seasar.framework.util.StringUtil;

public class AspectDefFactory {

    protected AspectDefFactory() {
    }
    
    public static AspectDef createAspectDef(String interceptorName, String pointcutStr) {
        Pointcut pointcut = createPointcut(pointcutStr);
        AspectDef aspectDef = new AspectDefImpl(pointcut);
        aspectDef.setExpression(interceptorName);
        return aspectDef;
    }
    
    public static AspectDef createAspectDef(MethodInterceptor interceptor, String pointcutStr) {
        Pointcut pointcut = createPointcut(pointcutStr);
        AspectDef aspectDef = new AspectDefImpl(pointcut);
        aspectDef.setValue(interceptor);
        return aspectDef;
    }
    
    public static Pointcut createPointcut(String pointcutStr) {
        if (pointcutStr != null) {
            String[] methodNames = StringUtil.split(pointcutStr, ", \n");
            return new PointcutImpl(methodNames);
        }
        return null;
    }
}
