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
package org.seasar3.message;

import java.util.Properties;

/**
 * This class represents the message bundle.
 * 
 * @author higa
 * @since 3.0
 */
public class MessageResourceBundle {

    /**
     * The persistent set of properties.
     */
    protected Properties properties;

    /**
     * The parent.
     */
    protected MessageResourceBundle parent;

    /**
     * Constructor.
     * 
     * @param properties
     */
    public MessageResourceBundle(Properties properties) {
        this(properties, null);
    }

    /**
     * Constructor.
     * 
     * @param properties
     *            the persistent set of properties.
     * @param parent
     *            the parent.
     */
    public MessageResourceBundle(Properties properties,
            MessageResourceBundle parent) {
        this.properties = properties;
        setParent(parent);
    }

    /**
     * Returns the message. If the key does not exist, returns null. If the
     * parent has same key, returns self value. If the parent has key which self
     * does not have, returns parent value.
     * 
     * @param key
     *            the key.
     * @return the message.
     */
    public String get(String key) {
        if (key == null) {
            return null;
        }
        if (properties.containsKey(key)) {
            return properties.getProperty(key);
        }
        return (parent != null) ? parent.get(key) : null;
    }

    /**
     * Returns the parent.
     * 
     * @return the parent.
     */
    public MessageResourceBundle getParent() {
        return parent;
    }

    /**
     * Sets the parent.
     * 
     * @param parent
     *            the parent.
     */
    public void setParent(MessageResourceBundle parent) {
        this.parent = parent;
    }
}