package benchmark.main;

import java.util.TreeMap;

import junit.framework.TestCase;
import benchmark.main.ManyBeansBenchmark;

public class ManyBeansBenchmarkTest extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(ManyBeansBenchmarkTest.class);
    }

    private ManyBeansBenchmark benchmark_;
    {
        benchmark_ = new ManyBeansBenchmark();
        benchmark_.setReport(new TreeMap());
    }

    public void testSeasarSingleton() throws Exception {
        benchmark_.seasarSingleton10000();
    }

    public void testSeasarPrototype() throws Exception {
        benchmark_.seasarPrototype10000();
    }

    public void testSeasarSameClass() throws Exception {
        benchmark_.seasarSameClass10000();
    }

    public void testSpringSingleton() throws Exception {
        benchmark_.springSingleton10000();
    }

    public void testSpringPrototype() throws Exception {
        benchmark_.springPrototype10000();
    }

    public void testSpringSameClass() throws Exception {
        benchmark_.springSameClass10000();
    }

}
