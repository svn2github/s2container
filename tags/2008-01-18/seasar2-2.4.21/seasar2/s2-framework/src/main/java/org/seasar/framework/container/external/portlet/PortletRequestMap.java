/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
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
package org.seasar.framework.container.external.portlet;

import java.util.Iterator;

import javax.portlet.PortletRequest;

import org.seasar.framework.container.external.AbstractExternalContextMap;
import org.seasar.framework.util.EnumerationIterator;

/**
 * Portlet用のRequestMapです。
 * 
 * @author <a href="mailto:shinsuke@yahoo.co.jp">Shinsuke Sugaya</a>
 */
public class PortletRequestMap extends AbstractExternalContextMap {

    private PortletRequest request;

    /**
     * {@link PortletRequestMap}を作成します。
     * 
     * @param request
     */
    public PortletRequestMap(PortletRequest request) {
        this.request = request;
    }

    protected Object getAttribute(String key) {
        return request.getAttribute(key);
    }

    protected void setAttribute(String key, Object value) {
        request.setAttribute(key, value);
    }

    protected Iterator getAttributeNames() {
        return new EnumerationIterator(request.getAttributeNames());
    }

    protected void removeAttribute(String key) {
        request.removeAttribute(key);
    }
}