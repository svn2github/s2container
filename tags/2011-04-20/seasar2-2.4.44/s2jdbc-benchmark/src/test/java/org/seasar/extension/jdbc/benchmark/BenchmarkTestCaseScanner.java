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
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.seasar.framework.util.ClassTraversal;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.ResourceUtil;

/**
 * @author taedium
 * 
 */
public class BenchmarkTestCaseScanner {

    @SuppressWarnings("unchecked")
    private static final List<Class<?>> benckmarkMarkers =
        Arrays.asList(
            SelectOwnerBenchmark.class,
            SelectInverseBenchmark.class,
            SelectManyToOneFetchBenchmark.class,
            SelectOneToManyFetchBenchmark.class,
            SelectOneToOneFetchFromOwnerBenchmark.class,
            SelectOneToOneFetchFromInverseBenchmark.class,
            PagingBenchmark.class,
            InsertSequenceBenchmark.class,
            InsertAssignBenchmark.class,
            UpdateBenchmark.class,
            UpdateChangedOnlyBenchmark.class,
            DeleteBenchmark.class,
            BatchInsertSequenceBenchmark.class,
            BatchInsertAssignBenchmark.class,
            BatchUpdateBenchmark.class,
            BatchDeleteBenchmark.class,
            SqlSelectDtoBenchmark.class,
            SqlFileSelectDtoBenchmark.class);

    private static final Map<Class<?>, Set<String>> scannedClassNameMap =
        new LinkedHashMap<Class<?>, Set<String>>();
    static {
        for (Class<?> marker : benckmarkMarkers) {
            scannedClassNameMap.put(marker, new TreeSet<String>());
        }
    }

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        scan();
        write();
    }

    private static void scan() {
        File dir = ResourceUtil.getBuildDir(BenchmarkTestCaseScanner.class);
        ClassTraversal.forEach(dir, new ClassTraversal.ClassHandler() {

            public void processClass(String packageName, String shortClassName) {
                String className =
                    ClassUtil.concatName(packageName, shortClassName);
                Class<?> clazz = ClassUtil.forName(className);
                for (Class<?> interfaceClass : clazz.getInterfaces()) {
                    if (scannedClassNameMap.containsKey(interfaceClass)) {
                        scannedClassNameMap.get(interfaceClass).add(className);
                    }
                }
            }
        });
    }

    /**
     * 
     * @param fileName
     */
    private static void write() {
        for (Set<String> classNames : scannedClassNameMap.values()) {
            for (String className : classNames) {
                System.out.println(className);
            }
            System.out.println("#");
        }
    }
}
