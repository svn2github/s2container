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

import java.io.File;
import java.net.URL;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Default implementation for {@link MessageResourceBundleFactoryProvider}.
 * 
 * @author higa
 * 
 */
public class DefaultMessageResourceBundleFactoryProvider implements
        MessageResourceBundleFactoryProvider {

    private Map<String, DefaultMessageResourceBundleCache> bundleCaches = new ConcurrentHashMap<String, DefaultMessageResourceBundleCache>();

    public MessageResourceBundle getBundle(Locale locale,
            String messageBundleName) {
        if (locale == null) {
            throw new NullPointerException("locale");
        }
        if (messageBundleName == null) {
            throw new NullPointerException("messageBundleName");
        }
        DefaultMessageResourceBundle parentBundle = getBundle(messageBundleName);
        DefaultMessageResourceBundle bundle = getBundle(messageBundleName + "_"
                + locale.getLanguage());
        if (bundle != null) {
            bundle.setParent(parentBundle);
            return bundle;
        }
        return parentBundle;
    }

    protected DefaultMessageResourceBundle getBundle(String messageBundleName) {
        DefaultMessageResourceBundleCache cache = bundleCaches
                .get(messageBundleName);
        if (cache != null) {
            if (cache.isModified()) {
                cache.setBundle(createBundle(messageBundleName));
            }
            return cache.getBundle();
        }
        cache = new DefaultMessageResourceBundleCache(
                createBundle(messageBundleName), getFile(messageBundleName));
        bundleCaches.put(messageBundleName, cache);
        return cache.getBundle();
    }

    protected File getFile(String messageBundleName) {
        if (!Env.isHotDeployment()) {
            return null;
        }
        String path = fromMessageBundleNameToPath(messageBundleName);
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource(path);
        if (url == null) {
            return null;
        }
        return new File(URLUtil.getFile(url));
    }

    protected DefaultMessageResourceBundle createBundle(String messageBundleName) {
        String path = fromMessageBundleNameToPath(messageBundleName);
        Properties props = PropertiesUtil.create(path);
        if (props == null) {
            return null;
        }
        return new DefaultMessageResourceBundle(props);
    }

    protected String fromMessageBundleNameToPath(String messageBundleName) {
        return messageBundleName.replace('.', '/')
                + PropertiesUtil.PROPERTIES_SUFFIX;
    }

}
