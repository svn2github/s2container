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
package org.seasar.framework.container.autoregister;

import java.io.File;
import java.net.URL;

import org.aopalliance.intercept.MethodInterceptor;
import org.seasar.framework.util.FileUtil;
import org.seasar.framework.util.JarFileUtil;
import org.seasar.framework.util.ResourceUtil;

/**
 * 
 * @author koichik, higa
 */
public class JarComponentAutoRegister extends AbstractJarComponentAutoRegister {

    private Class referenceClass = MethodInterceptor.class;

    public JarComponentAutoRegister() {
    }

    public Class getReferenceClass() {
        return referenceClass;
    }

    public void setReferenceClass(Class referenceClass) {
        this.referenceClass = referenceClass;
    }

    protected void setupBaseDir() {
        String path = ResourceUtil.getResourcePath(referenceClass);
        URL url = ResourceUtil.getResource(path);
        File jarFile = new File(JarFileUtil.toJarFilePath(url));
        File dir = jarFile.getParentFile();
        setBaseDir(FileUtil.getCanonicalPath(dir));
    }
}