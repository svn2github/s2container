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

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * @author manhole
 */
public abstract class AbstractDiiguEnhanceMojo extends AbstractMojo implements
        EnhanceParameter {

    /**
     * packagenames
     * 
     * @parameter property="packagenames" default-value="*"
     */
    private String packagenames;

    /**
     * flag marking the task verbosity.
     * 
     * @parameter property="verbose"
     */
    private boolean verbose;

    /**
     * the name of the encoding of the source files.
     * 
     * @parameter property="encoding" default-value="UTF-8"
     */
    private String encoding;

    public boolean isVerbose() {
        return verbose;
    }

    public String getPackagenames() {
        return packagenames;
    }

    public String getEncoding() {
        return encoding;
    }

    public void execute() throws MojoExecutionException, MojoFailureException {
        final DiiguEnhancer enhancer = new DiiguEnhancer();
        enhancer.setLog(getLog());
        enhancer.enhance(this);
    }

}
