/*
 * Copyright 2004-2009 the Seasar Foundation and the Others.
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
package org.seasar.framework.mock.portlet;

import javax.portlet.PortletSession;

/**
 * モック用の<code>PortletSession</code>のインターフェースです。
 * 
 * @author shinsuke
 * 
 */
public interface MockPortletSession extends PortletSession {

    /**
     * 有効かどうかを返します。。
     * 
     * @return 有効かどうか
     */
    boolean isValid();

    /**
     * 有効かどうかを設定します。
     * 
     * @param valid
     */
    void setValid(boolean valid);

    /**
     * セッションにアクセスします。
     */
    void access();
}
