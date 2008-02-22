package benchmark.main;

import java.lang.reflect.Method;

import junit.framework.TestCase;

public class BenchmarkMethodsTest extends TestCase {

    public void test1() throws Exception {
        final Method[] benchmarkMethods = BenchmarkMain
                .getBenchmarkMethods(AopBenchmark.class);
        print(benchmarkMethods);
    }

    public void test2() throws Exception {
        final Method[] benchmarkMethods = BenchmarkMain
                .getBenchmarkMethods(ManyBeansBenchmark.class);
        print(benchmarkMethods);
    }

    private void print(Method[] methods) {
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            System.out.println(method.getName());
        }
    }

}
