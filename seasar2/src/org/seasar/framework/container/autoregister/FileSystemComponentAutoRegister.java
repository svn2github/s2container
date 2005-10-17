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

import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.ResourceUtil;
import org.seasar.framework.util.StringUtil;

/**
 * @author higa
 *
 */
public class FileSystemComponentAutoRegister extends AbstractComponentAutoRegister {

    private String fileNameOfRoot = "j2ee.dicon";
        
    public void setFileNameOfRoot(String fileNameOfRoot) {
        this.fileNameOfRoot = fileNameOfRoot;
    }

    public void registAll() {
        for (int i = 0; i < getClassPatternSize(); ++i) {
            ClassPattern cp = getClassPattern(i);
            regist(cp);
        }
    }
    
    protected void regist(ClassPattern classPattern) {
        String packageName = classPattern.getPackageName();
        File packageDir = getPackageDir(packageName);
        regist(classPattern, packageDir, packageName);
    }
    
    protected void regist(ClassPattern classPattern, File packageDir, String packageName) {
        File[] files = packageDir.listFiles();
        for (int i = 0; i < files.length; ++i) {
            File file = files[i];
            String fileName = file.getName();
            if (file.isDirectory()) {
                regist(classPattern, file, ClassUtil.concatName(packageName, fileName));
                continue;
            }
            if (!fileName.endsWith(CLASS_SUFFIX)) {
                continue;
            }
            String shortClassName = fileName.substring(0, fileName.length() - CLASS_SUFFIX.length());
            if (isIgnore(packageName, shortClassName)) {
                continue;
            }
            if (classPattern.isAppliedShortClassName(shortClassName)) {
                regist(packageName, shortClassName);
            }
        }
    }
    
    protected File getPackageDir(String packageName) {
        File packageDir = getRootDir();
        if (packageName != null) {
            String[] names = StringUtil.split(packageName, ".");
            for (int i = 0; i < names.length; i++) {
                packageDir = new File(packageDir, names[i]);
            }
        }
        return packageDir;
    }
    
    protected File getRootDir() {
        return ResourceUtil.getResourceAsFile(fileNameOfRoot).getParentFile();
    }
}