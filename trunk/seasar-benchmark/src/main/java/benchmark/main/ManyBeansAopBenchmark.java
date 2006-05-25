package benchmark.main;

public class ManyBeansAopBenchmark extends AbstractManyBeansBenchmark {

    public void seasarAopAutoRegisterSingleton01000() {
        seasarAutoRegister(
                "benchmark/many/aop_autoRegister_singleton_01000.dicon", 1000);
    }

    public void seasarAopAutoRegisterSingleton02000() {
        seasarAutoRegister(
                "benchmark/many/aop_autoRegister_singleton_02000.dicon", 2000);
    }

    public void seasarAopAutoRegisterSingleton05000() {
        seasarAutoRegister(
                "benchmark/many/aop_autoRegister_singleton_05000.dicon", 5000);
    }

    public void seasarAopAutoRegisterSingleton10000() {
        seasarAutoRegister(
                "benchmark/many/aop_autoRegister_singleton_10000.dicon", 10000);
    }

    public void springAopSingleton01000() {
        springGetComponents("benchmark/many/aop_singleton_01000.xml", 1000);
    }

    public void springAopSingleton02000() {
        springGetComponents("benchmark/many/aop_singleton_02000.xml", 2000);
    }

    public void springAopSingleton05000() {
        springGetComponents("benchmark/many/aop_singleton_05000.xml", 5000);
    }

    public void springAopSingleton10000() {
        springGetComponents("benchmark/many/aop_singleton_10000.xml", 10000);
    }

    public void springAopAutoRegisterSingleton01000() {
        springGetComponents(
                "benchmark/many/aop_autoRegister_singleton_01000.xml", 1000);
    }

    public void springAopAutoRegisterSingleton02000() {
        springGetComponents(
                "benchmark/many/aop_autoRegister_singleton_02000.xml", 2000);
    }

    public void springAopAutoRegisterSingleton05000() {
        springGetComponents(
                "benchmark/many/aop_autoRegister_singleton_05000.xml", 5000);
    }

    public void springAopAutoRegisterSingleton10000() {
        springGetComponents(
                "benchmark/many/aop_autoRegister_singleton_10000.xml", 10000);
    }

    public void seasarAopPrototype01000() {
        seasarGetComponents("benchmark/many/aop_prototype_01000.dicon", 1000);
    }

    public void seasarAopPrototype02000() {
        seasarGetComponents("benchmark/many/aop_prototype_02000.dicon", 2000);
    }

    public void seasarAopPrototype05000() {
        seasarGetComponents("benchmark/many/aop_prototype_05000.dicon", 5000);
    }

    public void seasarAopPrototype10000() {
        seasarGetComponents("benchmark/many/aop_prototype_10000.dicon", 10000);
    }

    public void springAopPrototype01000() {
        springGetComponents("benchmark/many/aop_prototype_01000.xml", 1000);
    }

    public void springAopPrototype02000() {
        springGetComponents("benchmark/many/aop_prototype_02000.xml", 2000);
    }

    public void springAopPrototype05000() {
        springGetComponents("benchmark/many/aop_prototype_05000.xml", 5000);
    }

    public void springAopPrototype10000() {
        springGetComponents("benchmark/many/aop_prototype_10000.xml", 10000);
    }

    public void seasarAopSingleton01000() {
        seasarGetComponents("benchmark/many/aop_singleton_01000.dicon", 1000);
    }

    public void seasarAopSingleton02000() {
        seasarGetComponents("benchmark/many/aop_singleton_02000.dicon", 2000);
    }

    public void seasarAopSingleton05000() {
        seasarGetComponents("benchmark/many/aop_singleton_05000.dicon", 5000);
    }

    public void seasarAopSingleton10000() {
        seasarGetComponents("benchmark/many/aop_singleton_10000.dicon", 10000);
    }

}
