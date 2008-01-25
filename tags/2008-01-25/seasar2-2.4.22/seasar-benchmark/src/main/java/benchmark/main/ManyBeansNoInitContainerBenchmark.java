package benchmark.main;

public class ManyBeansNoInitContainerBenchmark extends
        AbstractManyBeansBenchmark {

    public void springSingleton01000() throws Exception {
        setBeanFactoryCreator(new XmlBeanFactoryCreator());
        springGetComponents("benchmark/many/simple_singleton_01000.xml", 1000);
    }

    public void springSingleton02000() throws Exception {
        setBeanFactoryCreator(new XmlBeanFactoryCreator());
        springGetComponents("benchmark/many/simple_singleton_02000.xml", 2000);
    }

    public void springSingleton05000() throws Exception {
        setBeanFactoryCreator(new XmlBeanFactoryCreator());
        springGetComponents("benchmark/many/simple_singleton_05000.xml", 5000);
    }

    public void springSingleton10000() throws Exception {
        setBeanFactoryCreator(new XmlBeanFactoryCreator());
        springGetComponents("benchmark/many/simple_singleton_10000.xml", 10000);
    }

    public void seasarSingleton01000() throws Exception {
        setS2ContainerInitializeStrategy(new S2ContainerNoInitStrategy());
        seasarGetComponents("benchmark/many/simple_singleton_01000.dicon", 1000);
    }

    public void seasarSingleton02000() throws Exception {
        setS2ContainerInitializeStrategy(new S2ContainerNoInitStrategy());
        seasarGetComponents("benchmark/many/simple_singleton_02000.dicon", 2000);
    }

    public void seasarSingleton05000() throws Exception {
        setS2ContainerInitializeStrategy(new S2ContainerNoInitStrategy());
        seasarGetComponents("benchmark/many/simple_singleton_05000.dicon", 5000);
    }

    public void seasarSingleton10000() throws Exception {
        setS2ContainerInitializeStrategy(new S2ContainerNoInitStrategy());
        seasarGetComponents("benchmark/many/simple_singleton_10000.dicon",
                10000);
    }

}
