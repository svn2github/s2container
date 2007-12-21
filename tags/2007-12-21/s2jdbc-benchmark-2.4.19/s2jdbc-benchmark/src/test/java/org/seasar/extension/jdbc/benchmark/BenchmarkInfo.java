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
package org.seasar.extension.jdbc.benchmark;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Formatter;

import org.seasar.framework.env.Env;

/**
 * @author taedium
 * 
 */
public class BenchmarkInfo {

    /**
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String fileName = null;
        if (args.length > 0) {
            fileName = args[0];
        }
        Formatter f = null;
        try {
            OutputStream os =
                fileName != null ? new FileOutputStream(fileName, true)
                    : System.out;
            f = new Formatter(os);
            f.format("%s=%s\n", "ENV value(RDBMS)", Env.getValue());
            f.format("%s=%s\n", getPropertyKeyValue("java.runtime.name"));
            f.format("%s=%s\n", getPropertyKeyValue("java.vm.version"));
            f.format("%s=%s\n", getPropertyKeyValue("java.vm.vendor"));
            f.format("%s=%s\n", getPropertyKeyValue("java.version"));
            f.format("%s=%s\n", getPropertyKeyValue("os.name"));
            f.format("%s=%s\n", getPropertyKeyValue("os.arch"));
            f.format("%s=%s\n", getPropertyKeyValue("java.class.path"));
            f.format("\n");
            f.flush();
        } finally {
            if (fileName != null) {
                f.close();
            }
        }
    }

    private static Object[] getPropertyKeyValue(String key) {
        return new Object[] { key, System.getProperty(key) };
    }
}
