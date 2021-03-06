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

import org.seasar.framework.util.ClassTraversal;
import org.seasar.framework.util.ResourceUtil;

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
        File packageDir = getRootDir();
        ClassTraversal.forEach(packageDir, packageName, this);
    }
    
    protected File getRootDir() {
        return ResourceUtil.getResourceAsFile(fileNameOfRoot).getParentFile();
    }
}