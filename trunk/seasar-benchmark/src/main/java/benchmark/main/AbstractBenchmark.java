package benchmark.main;

import java.text.DecimalFormat;
import java.util.Map;

import benchmark.util.RuntimeMemory;

public abstract class AbstractBenchmark {

    private DecimalFormat format_ = new DecimalFormat("00000");

    private DecimalFormat format2_ = new DecimalFormat("00");

    private Map report_;

    protected void reportTime(long time) {
        report_.put("time", Long.toString(time));
    }

    public void setReport(Map report) {
        report_ = report;
    }

    protected void reportContainerCreation(long duration) {
        report_.put("container creation", Long.toString(duration));
    }

    protected void reportContainerInit(long duration) {
        report_.put("container init", Long.toString(duration));
    }

    protected void reportGetComponents(long duration, int count) {
        report_.put("get components(" + count + ")", Long.toString(duration));
    }

    protected void reportMemory() {
        for (int i = 0; i < 5; i++) {
            System.runFinalization();
            System.gc();
        }
        RuntimeMemory mem = new RuntimeMemory();
        System.out.println(mem.toStringMegabyte());
        report_.put("useMemory (Total - Free)", mem.mega(mem.getUse()));
    }

    public Map getReport() {
        return report_;
    }

    protected Class getBeanClass(int i) throws ClassNotFoundException {
        final String className = "benchmark.many.b" + format2_.format(i / 1000)
                + ".NullBean" + format_.format(i);
        Class clazz = Class.forName(className);
        return clazz;
    }

}
