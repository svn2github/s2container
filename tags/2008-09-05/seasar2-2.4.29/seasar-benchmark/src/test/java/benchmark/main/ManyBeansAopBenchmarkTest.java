package benchmark.main;

import java.util.TreeMap;

import junit.framework.TestCase;

public class ManyBeansAopBenchmarkTest extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(ManyBeansAopBenchmarkTest.class);
    }

    private ManyBeansAopBenchmark benchmark_;
    {
        benchmark_ = new ManyBeansAopBenchmark();
        benchmark_.setReport(new TreeMap());
    }

    public void testSeasarAopPrototype10000() throws Exception {
        benchmark_.seasarAopPrototype10000();
    }

    public void testSpringAopPrototype10000() throws Exception {
        benchmark_.springAopPrototype10000();
    }

    public void testSeasarAopAutoRegisterSingleton10000() throws Exception {
        benchmark_.seasarAopAutoRegisterSingleton10000();
    }

    public void testSpringAopAutoRegisterSingleton01000() throws Exception {
        benchmark_.springAopAutoRegisterSingleton01000();
    }

}
