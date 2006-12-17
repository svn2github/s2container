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
package org.seasar.framework.unit;

import java.lang.reflect.Method;
import java.util.Map;

import org.seasar.framework.container.ComponentDef;

/**
 * @author taedium
 * 
 */
public interface InternalTestContext extends TestContext {

    void setTest(Object test);

    void setTestClass(Class<?> testClass);

    void setTestMethod(Method testMethod);

    void setExpressionContext(Map<String, Object> expressionContext);

    void initContainer();

    void destroyContainer();

    void prepareTestData();

    <T> T getComponent(Class<T> componentKey);

    Object getComponent(Object componentKey);

    boolean hasComponentDef(Object componentKey);

    ComponentDef getComponentDef(final int index);

    ComponentDef getComponentDef(final Object componentKey);

}
