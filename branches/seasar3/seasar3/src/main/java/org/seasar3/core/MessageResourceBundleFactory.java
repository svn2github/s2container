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

import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A factory class for {@link MessageResourceBundle}.
 * 
 * @author higa
 * @since 3.0
 */
public abstract class MessageResourceBundleFactory {

    private static MessageResourceBundleFactoryProvider defaultProvider = new DefaultMessageResourceBundleFactoryProvider();

    private static Map<String, MessageResourceBundleFactoryProvider> providers = new ConcurrentHashMap<String, MessageResourceBundleFactoryProvider>();

    protected MessageResourceBundleFactory() {
    }

    /**
     * Returns message bundle. If message bundle does not exist, returns null.
     * 
     * @param locale
     * @param messageBundleName
     *            such as aaa.foo(WEB-INF/class/aaa/foo.properties)
     * @return bundle message bundle
     * @throws NullPointerException
     *             if locale is null and messageBundleName is null.
     */
    public static MessageResourceBundle getBundle(Locale locale,
            String messageBundleName) {
        if (locale == null) {
            throw new NullPointerException("locale");
        }
        if (messageBundleName == null) {
            throw new NullPointerException("messageBundleName");
        }
        if (providers.size() == 0) {
            return defaultProvider.getBundle(locale, messageBundleName);
        }
        MessageResourceBundleFactoryProvider provider = providers
                .get(messageBundleName);
        if (provider == null) {
            provider = defaultProvider;
        }
        return provider.getBundle(locale, messageBundleName);
    }

    /**
     * Adds provider for messageBundleName.
     * 
     * @param messageBundleName
     * @param provider
     */
    public void addProvider(String messageBundleName,
            MessageResourceBundleFactoryProvider provider) {
        providers.put(messageBundleName, provider);
    }

    /**
     * Clears all providers.
     */
    public void clearProviders() {
        providers.clear();
    }
}
