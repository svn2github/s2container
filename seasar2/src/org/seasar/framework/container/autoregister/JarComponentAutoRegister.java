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
package org.seasar.framework.container.autoregister;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import org.aopalliance.intercept.MethodInterceptor;
import org.seasar.framework.util.FileInputStreamUtil;
import org.seasar.framework.util.InputStreamUtil;
import org.seasar.framework.util.JarInputStreamUtil;
import org.seasar.framework.util.ResourceUtil;

/**
 * 
 * @author koichik, higa
 */
public class JarComponentAutoRegister extends AbstractComponentAutoRegister {
    
    private static final String METHOD_INTERCEPTOR_PATH = ResourceUtil.getResourcePath(MethodInterceptor.class);

    private static File baseDir = setupBaseDir();
    
    private String[] jarFileNames;

    public JarComponentAutoRegister() {
    }
    
    private static File setupBaseDir() {
        URL url = ResourceUtil.getResource(METHOD_INTERCEPTOR_PATH);
        String s = ResourceUtil.toExternalForm(url);
        int pos = s.lastIndexOf('!');
        String s2 = s.substring(9, pos);
        File f = new File(s2);
        return f.getParentFile();
    }
    
    public static File getBaseDir() {
        return baseDir;
    }

    public void registAll() {
        for (int i = 0; i < jarFileNames.length; ++i) {
            final File jarFile = findJar(jarFileNames[i].trim());
            final InputStream is = FileInputStreamUtil.create(jarFile);
            try {
                iterateJarEntry(is);
            }
            finally {
                InputStreamUtil.close(is);
            }
        }
    }

    protected File findJar(final String jarFileName) {
        return new File(baseDir, jarFileName);
    }

    protected void iterateJarEntry(final InputStream is) {
        final JarInputStream jarIs = JarInputStreamUtil.create(is);
        try {
            JarEntry entry;
            while ((entry = JarInputStreamUtil.getNextJarEntry(jarIs)) != null) {
                processEntry(entry.getName());
            }
        }
        finally {
            InputStreamUtil.close(jarIs);
        }
    }

    protected void processEntry(final String entryName) {
        if (!entryName.endsWith(CLASS_SUFFIX)) {
            return;
        }

        final String className = entryName.substring(0,
                entryName.length() - CLASS_SUFFIX.length()).replace('/', '.');
        final int index = className.lastIndexOf('.');
        final String packageName = (index == -1) ? null : className.substring(0, index);
        final String shortClassName = (index == -1) ? className : className
                .substring(index + 1);

        applyClassPattern(packageName, shortClassName);
    }

    protected void applyClassPattern(final String packageName, final String shortClassName) {
        if (isIgnore(packageName, shortClassName)) {
            return;
        }

        for (int i = 0; i < getClassPatternSize(); ++i) {
            final ClassPattern cp = getClassPattern(i);
            if (cp.isAppliedPackageName(packageName) && cp.isAppliedShortClassName(shortClassName)) {
                regist(packageName, shortClassName);
            }
        }
    }

    public void setJarFileNames(final String jarFileNames) {
        this.jarFileNames = jarFileNames.split(",");
    }
}
