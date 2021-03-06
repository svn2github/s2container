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
package org.seasar.framework.container.factory;

/**
 * @author higa
 * @org.seasar.framework.container.annotation.backport175.Component( name =
 *                                                                   "aaa",
 *                                                                   instance =
 *                                                                   "prototype",
 *                                                                   autoBinding =
 *                                                                   "property",
 *                                                                   externalBinding = true)
 * 
 */
public class Hoge2 {

    /**
     * @param aaa
     * @org.seasar.framework.container.annotation.backport175.Binding("aaa2")
     */
    public void setAaa(String aaa) {
    }

    /**
     * @param bbb
     * @org.seasar.framework.container.annotation.backport175.Binding( bindingType="none")
     */
    public void setBbb(String bbb) {
    }

    /**
     * @param ccc
     * @org.seasar.framework.container.annotation.Binding
     */
    public void setCcc(String ccc) {
    }
}
