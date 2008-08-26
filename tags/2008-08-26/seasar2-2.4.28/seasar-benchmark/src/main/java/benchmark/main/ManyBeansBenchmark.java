package benchmark.main;

public class ManyBeansBenchmark extends AbstractManyBeansBenchmark {

    public void seasarSingleton01000() throws Exception {
        seasarGetComponents("benchmark/many/simple_singleton_01000.dicon", 1000);
    }

    public void seasarSingleton02000() throws Exception {
        seasarGetComponents("benchmark/many/simple_singleton_02000.dicon", 2000);
    }

    public void seasarSingleton05000() throws Exception {
        seasarGetComponents("benchmark/many/simple_singleton_05000.dicon", 5000);
    }

    public void seasarSingleton10000() throws Exception {
        seasarGetComponents("benchmark/many/simple_singleton_10000.dicon",
                10000);
    }

    public void seasarSingleton01000From02000() throws Exception {
        seasarGetComponents("benchmark/many/simple_singleton_02000.dicon", 1000);
    }

    public void seasarSingleton01000From05000() throws Exception {
        seasarGetComponents("benchmark/many/simple_singleton_05000.dicon", 1000);
    }

    public void seasarSingleton01000From10000() throws Exception {
        seasarGetComponents("benchmark/many/simple_singleton_10000.dicon", 1000);
    }

    public void seasarPrototype01000() throws Exception {
        seasarGetComponents("benchmark/many/simple_prototype_01000.dicon", 1000);
    }

    public void seasarPrototype02000() throws Exception {
        seasarGetComponents("benchmark/many/simple_prototype_02000.dicon", 2000);
    }

    public void seasarPrototype05000() throws Exception {
        seasarGetComponents("benchmark/many/simple_prototype_05000.dicon", 5000);
    }

    public void seasarPrototype10000() throws Exception {
        seasarGetComponents("benchmark/many/simple_prototype_10000.dicon",
                10000);
    }

    public void seasarSameClass01000() throws Exception {
        seasarGetComponents("benchmark/many/simple_sameClass_01000.dicon", 1000);
    }

    public void seasarSameClass02000() throws Exception {
        seasarGetComponents("benchmark/many/simple_sameClass_02000.dicon", 2000);
    }

    public void seasarSameClass05000() throws Exception {
        seasarGetComponents("benchmark/many/simple_sameClass_05000.dicon", 5000);
    }

    public void seasarSameClass10000() throws Exception {
        seasarGetComponents("benchmark/many/simple_sameClass_10000.dicon",
                10000);
    }

    public void springSingleton01000() throws Exception {
        springGetComponents("benchmark/many/simple_singleton_01000.xml", 1000);
    }

    public void springSingleton02000() throws Exception {
        springGetComponents("benchmark/many/simple_singleton_02000.xml", 2000);
    }

    public void springSingleton05000() throws Exception {
        springGetComponents("benchmark/many/simple_singleton_05000.xml", 5000);
    }

    public void springSingleton10000() throws Exception {
        springGetComponents("benchmark/many/simple_singleton_10000.xml", 10000);
    }

    public void springSingleton01000From02000() throws Exception {
        springGetComponents("benchmark/many/simple_singleton_02000.xml", 1000);
    }

    public void springSingleton01000From05000() throws Exception {
        springGetComponents("benchmark/many/simple_singleton_05000.xml", 1000);
    }

    public void springSingleton01000From10000() throws Exception {
        springGetComponents("benchmark/many/simple_singleton_10000.xml", 1000);
    }

    public void springPrototype01000() throws Exception {
        springGetComponents("benchmark/many/simple_prototype_01000.xml", 1000);
    }

    public void springPrototype02000() throws Exception {
        springGetComponents("benchmark/many/simple_prototype_02000.xml", 2000);
    }

    public void springPrototype05000() throws Exception {
        springGetComponents("benchmark/many/simple_prototype_05000.xml", 5000);
    }

    public void springPrototype10000() throws Exception {
        springGetComponents("benchmark/many/simple_prototype_10000.xml", 10000);
    }

    public void springSameClass01000() throws Exception {
        springGetComponents("benchmark/many/simple_sameClass_01000.xml", 1000);
    }

    public void springSameClass02000() throws Exception {
        springGetComponents("benchmark/many/simple_sameClass_02000.xml", 2000);
    }

    public void springSameClass05000() throws Exception {
        springGetComponents("benchmark/many/simple_sameClass_05000.xml", 5000);
    }

    public void springSameClass10000() throws Exception {
        springGetComponents("benchmark/many/simple_sameClass_10000.xml", 10000);
    }

}
