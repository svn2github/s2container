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
package org.seasar3.core;

import java.util.Properties;

/**
 * Default implementation for {@link MessageResourceBundleFactoryProvider}
 * 
 * @author shot
 * @author higa
 * @since 3.0
 */
public class DefaultMessageResourceBundle implements MessageResourceBundle {

    private Properties props;

    private MessageResourceBundle parent;

    /**
     * Creates new <code>DefaultMessageResourceBundle</code>.
     * 
     * @param props
     */
    public DefaultMessageResourceBundle(Properties props) {
        this(props, null);
    }

    /**
     * Creates new <code>DefaultMessageResourceBundle</code>.
     * 
     * @param props
     * @param parent
     */
    public DefaultMessageResourceBundle(Properties props,
            MessageResourceBundle parent) {
        this.props = props;
        setParent(parent);
    }

    public String get(String key) {
        if (key == null) {
            return null;
        }
        if (props.containsKey(key)) {
            return props.getProperty(key);
        }
        return (parent != null) ? parent.get(key) : null;
    }

    public MessageResourceBundle getParent() {
        return parent;
    }

    public void setParent(MessageResourceBundle parent) {
        this.parent = parent;
    }
}