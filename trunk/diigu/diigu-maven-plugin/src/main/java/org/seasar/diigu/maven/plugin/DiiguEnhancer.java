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

import java.io.File;
import java.util.Iterator;

import org.apache.maven.plugin.logging.Log;
import org.apache.tools.ant.ComponentHelper;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Javadoc;
import org.apache.tools.ant.taskdefs.Javadoc.DocletInfo;
import org.apache.tools.ant.types.Path;

/**
 * @author manhole
 */
public class DiiguEnhancer {

    private static final String DOCLET_NAME = "org.seasar.diigu.ParameterNameDoclet";

    public void enhance(final EnhanceParameter parameter) {
        if (getLog().isDebugEnabled()) {
            getLog().debug("encoding=" + parameter.getEncoding());
            getLog().debug("packagenames=" + parameter.getPackagenames());
            getLog().debug("verbose=" + parameter.isVerbose());
            for (final Iterator it = parameter.getSourcepath().iterator(); it
                    .hasNext();) {
                final String path = (String) it.next();
                getLog().debug("sourcepath=" + path);
            }
            for (final Iterator it = parameter.getClasspath().iterator(); it
                    .hasNext();) {
                final String path = (String) it.next();
                getLog().debug("classpath=" + path);
            }
        }
        final Javadoc task = new Javadoc();
        task.setProject(createProject());
        task.setVerbose(parameter.isVerbose());

        final DocletInfo doclet = task.createDoclet();
        doclet.setName(DOCLET_NAME);

        {
            final Path docletPath = doclet.createPath();
            final Path classpath = task.createClasspath();
            for (final Iterator it = parameter.getClasspath().iterator(); it
                    .hasNext();) {
                final String path = (String) it.next();
                if (!new File(path).exists()) {
                    getLog().info("classpath does not exist: " + path);
                    continue;
                }
                classpath.setPath(path);
                docletPath.setPath(path);
            }
        }

        {
            final Path sourcepath = task.createSourcepath();
            for (Iterator it = parameter.getSourcepath().iterator(); it
                    .hasNext();) {
                String path = (String) it.next();
                if (!new File(path).exists()) {
                    getLog().info("source directory does not exist: " + path);
                    return;
                }
                sourcepath.setPath(path);
            }
        }
        task.setPackagenames(parameter.getPackagenames());
        task.setEncoding(parameter.getEncoding());

        task.execute();
    }

    private Project createProject() {
        Project project = new Project();
        project.setName("diigu-maven-plugin");
        ComponentHelper componentHelper = ComponentHelper
                .getComponentHelper(project);
        componentHelper.initDefaultDefinitions();
        return project;
    }

    private Log log;

    public Log getLog() {
        return log;
    }

    public void setLog(Log log) {
        this.log = log;
    }

}
