package benchmark.main;

public class NoDIBenchmark extends AbstractBenchmark {

    public void noDI01000() throws Exception {
        noDI(1000);
    }

    public void noDI02000() throws Exception {
        noDI(2000);
    }

    public void noDI05000() throws Exception {
        noDI(5000);
    }

    public void noDI10000() throws Exception {
        noDI(10000);
    }

    private void noDI(int times) throws Exception {
        long start = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            Class clazz = getBeanClass(i);
            clazz.newInstance();
        }
        long end = System.currentTimeMillis();
        reportTime(end - start);
        reportMemory();
    }

    public void noDISameClass01000() throws Exception {
        noDISameClass(1000);
    }

    public void noDISameClass02000() throws Exception {
        noDISameClass(2000);
    }

    public void noDISameClass05000() throws Exception {
        noDISameClass(5000);
    }

    public void noDISameClass10000() throws Exception {
        noDISameClass(10000);
    }

    private void noDISameClass(int times) throws Exception {
        final String className = "benchmark.many.b00.NullBean00000";
        long start = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            Class clazz = Class.forName(className);
            clazz.newInstance();
        }
        long end = System.currentTimeMillis();
        reportTime(end - start);
        reportMemory();
    }

}
