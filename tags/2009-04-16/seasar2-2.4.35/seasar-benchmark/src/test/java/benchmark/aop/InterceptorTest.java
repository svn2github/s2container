package benchmark.aop;

import java.util.TreeMap;

import junit.framework.TestCase;
import benchmark.main.AopBenchmark;

public class InterceptorTest extends TestCase {

    private AopBenchmark benchmark_;
    {
        benchmark_ = new AopBenchmark();
        benchmark_.setReport(new TreeMap());
    }

    public void testSeasarAopCallPrototype() throws Exception {
        benchmark_.seasarAopCallPrototype();
    }

}
