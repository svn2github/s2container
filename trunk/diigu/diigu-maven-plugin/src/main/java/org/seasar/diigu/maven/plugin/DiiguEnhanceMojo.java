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
package org.seasar.diigu.maven.plugin;

import java.util.List;

/**
 * Parameter Name Enhancer.
 * 
 * @author manhole
 * 
 * @goal enhance
 * @phase compile
 * @requiresDependencyResolution compile
 */
public class DiiguEnhanceMojo extends AbstractDiiguEnhanceMojo {

    /**
     * The source directories containing the sources to be compiled.
     * 
     * @parameter expression="${project.compileSourceRoots}"
     * @required
     * @readonly
     */
    private List compileSourceRoots;

    /**
     * Project classpath.
     * 
     * @parameter expression="${project.compileClasspathElements}"
     * @required
     * @readonly
     */
    private List compileClasspathElements;

    public List getClasspath() {
        return compileClasspathElements;
    }

    public List getDocletpath() {
        return compileClasspathElements;
    }

    public List getSourcepath() {
        return compileSourceRoots;
    }

}
