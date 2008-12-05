package benchmark.main;

import java.util.SortedMap;
import java.util.TreeMap;

import junit.framework.TestCase;

public class NoDIBenchmarkTest extends TestCase {

    private NoDIBenchmark benchmark_;

    private SortedMap report_;

    {
        benchmark_ = new NoDIBenchmark();
        report_ = new TreeMap();
        benchmark_.setReport(report_);
    }

    protected void tearDown() throws Exception {
        System.out.println(report_);
        super.tearDown();
    }

    public void test01000() throws Exception {
        benchmark_.noDI01000();
    }

    public void test02000() throws Exception {
        benchmark_.noDI02000();
    }

    public void test05000() throws Exception {
        benchmark_.noDI05000();
    }

    public void test10000() throws Exception {
        benchmark_.noDI10000();
    }

}
