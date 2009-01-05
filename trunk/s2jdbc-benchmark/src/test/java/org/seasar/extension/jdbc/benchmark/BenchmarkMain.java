/*
 * Copyright 2004-2009 the Seasar Foundation and the Others.
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
package org.seasar.extension.jdbc.benchmark;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import org.seasar.framework.env.Env;

/**
 * @author taedium
 * 
 */
public class BenchmarkMain {

    private static String ENV_VALUE = "CLASSPATH";

    /**
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        new BenchmarkMain().run(args);
    }

    /**
     * 
     * @param args
     * @throws Exception
     */
    protected void run(final String[] args) throws Exception {
        final String command = getCommand(args);
        if (command.equals("append")) {
            final String appendClasspath = getAppendClasspath(args);
            String classpath =
                createClasspath(appendClasspath.split(File.pathSeparator));
            writeClasspath(classpath);
        } else if (command.equals("info")) {
            writeInfo();
        }
    }

    /**
     * 
     * @param args
     * @return
     */
    protected String getCommand(String[] args) {
        String command = getArg("--command", args);
        if (command.equals("")) {
            throw new IllegalArgumentException(args.toString());
        }
        return command;
    }

    /**
     * 
     * @param args
     * @return
     * @throws IllegalArgumentException
     */
    protected String getAppendClasspath(final String[] args)
            throws IllegalArgumentException {
        final String classpath = getArg("--appendclasspath", args);
        return classpath.equals("") ? "." : classpath;
    }

    /**
     * 
     * @param name
     * @param args
     * @return
     */
    protected String getArg(final String name, final String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(name)) {
                if (i + 1 < args.length) {
                    return args[i + 1];
                }
                throw new IllegalArgumentException(args.toString());
            }
        }
        return "";
    }

    /**
     * 
     * @param pathStrings
     * @return
     * @throws IOException
     */
    protected String createClasspath(final String[] pathStrings)
            throws IOException {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < pathStrings.length; ++i) {
            addPath(buf, new File(pathStrings[i]));
        }
        if (buf.length() > File.pathSeparator.length()) {
            buf.setLength(buf.length() - File.pathSeparator.length());
        }
        return buf.toString();
    }

    /**
     * 
     * @param buf
     * @param path
     * @throws IOException
     */
    protected void addPath(final StringBuilder buf, final File path)
            throws IOException {
        if (path.isDirectory()) {
            for (File file : getJarFiles(path)) {
                buf.append(file.getCanonicalPath());
                buf.append(File.pathSeparator);
            }
        } else {
            if (isJar(path)) {
                buf.append(path.getCanonicalPath());
                buf.append(File.pathSeparator);
            }
        }
    }

    /**
     * 
     * @param dir
     * @return
     */
    protected File[] getJarFiles(final File dir) {
        return dir.listFiles(new FileFilter() {

            public boolean accept(final File pathname) {
                return isJar(pathname);
            }
        });
    }

    /**
     * 
     * @param pathname
     * @return
     */
    protected boolean isJar(final File pathname) {
        final int dot = pathname.getName().lastIndexOf('.');
        if (0 <= dot) {
            final String extention = pathname.getName().substring(dot + 1);
            return "jar".equalsIgnoreCase(extention);
        }
        return false;
    }

    /**
     * 
     * @param classpathString
     */
    protected void writeClasspath(String classpathString) {
        if (System.getProperty("os.name").startsWith("Windows")) {
            System.out.print("SET " + ENV_VALUE + "=%" + ENV_VALUE + "%;"
                + classpathString);
        } else {
            throw new UnsupportedOperationException("outClasspathURLs");
        }
    }

    /**
     * 
     */
    protected void writeInfo() {
        System.out.format("%s=%s\n", "ENV value(RDBMS)", Env.getValue());
        System.out.format("%s=%s\n", getPropertyKeyValue("java.runtime.name"));
        System.out.format("%s=%s\n", getPropertyKeyValue("java.vm.version"));
        System.out.format("%s=%s\n", getPropertyKeyValue("java.vm.vendor"));
        System.out.format("%s=%s\n", getPropertyKeyValue("java.version"));
        System.out.format("%s=%s\n", getPropertyKeyValue("os.name"));
        System.out.format("%s=%s\n", getPropertyKeyValue("os.arch"));
        System.out.format("%s=%s\n", getPropertyKeyValue("java.class.path"));
        System.out.format("\n");
    }

    private static Object[] getPropertyKeyValue(String key) {
        return new Object[] { key, System.getProperty(key) };
    }
}
