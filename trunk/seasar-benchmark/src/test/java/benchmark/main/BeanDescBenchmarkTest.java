package benchmark.main;

import java.util.LinkedHashMap;
import java.util.Map;

import junit.framework.TestCase;

public class BeanDescBenchmarkTest extends TestCase {

    private BeanDescBenchmark benchmark_;

    private Map report_;

    {
        benchmark_ = new BeanDescBenchmark();
        report_ = new LinkedHashMap();
        benchmark_.setReport(report_);
    }

    protected void tearDown() throws Exception {
        System.out.println(report_);
        super.tearDown();
    }

    public void test1() throws Exception {
        benchmark_.seasarBeanDescCreation01000();
    }

    public void testSpringBeanWrapper01000() throws Exception {
        benchmark_.springBeanWrapperCreation01000();
    }

    public void testSeasarBeanDescAccess() throws Exception {
        benchmark_.seasarBeanDescAccess01000();
    }

    public void testSpringBeanWrapperAccess() throws Exception {
        benchmark_.springBeanWrapperAccess01000();
    }

}
