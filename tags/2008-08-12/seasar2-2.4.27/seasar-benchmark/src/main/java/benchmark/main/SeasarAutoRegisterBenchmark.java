package benchmark.main;

import java.text.DecimalFormat;

import junit.framework.Assert;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory;

public class SeasarAutoRegisterBenchmark extends AbstractManyBeansBenchmark {

    private DecimalFormat format_ = new DecimalFormat("00000");

    public void seasarAutoRegisterPrototype01000() {
        seasarAutoRegister("benchmark/many/autoRegister_prototype_01000.dicon",
                1000);
    }

    public void seasarAutoRegisterPrototype02000() {
        seasarAutoRegister("benchmark/many/autoRegister_prototype_02000.dicon",
                2000);
    }

    public void seasarAutoRegisterPrototype05000() {
        seasarAutoRegister("benchmark/many/autoRegister_prototype_05000.dicon",
                5000);
    }

    public void seasarAutoRegisterPrototype10000() {
        seasarAutoRegister("benchmark/many/autoRegister_prototype_10000.dicon",
                10000);
    }

    public void seasarAutoRegisterSingleton01000() {
        seasarAutoRegister("benchmark/many/autoRegister_singleton_01000.dicon",
                1000);
    }

    public void seasarAutoRegisterSingleton02000() {
        seasarAutoRegister("benchmark/many/autoRegister_singleton_02000.dicon",
                2000);
    }

    public void seasarAutoRegisterSingleton05000() {
        seasarAutoRegister("benchmark/many/autoRegister_singleton_05000.dicon",
                5000);
    }

    public void seasarAutoRegisterSingleton10000() {
        seasarAutoRegister("benchmark/many/autoRegister_singleton_10000.dicon",
                10000);
    }

    public void seasarAutoRegisterFromJarSingleton01000() {
        seasarAutoRegisterFromJar(
                "benchmark/many/autoRegisterJar_singleton_01000.dicon", 1000);
    }

    public void seasarAutoRegisterFromJarSingleton02000() {
        seasarAutoRegisterFromJar(
                "benchmark/many/autoRegisterJar_singleton_02000.dicon", 2000);
    }

    public void seasarAutoRegisterFromJarSingleton05000() {
        seasarAutoRegisterFromJar(
                "benchmark/many/autoRegisterJar_singleton_05000.dicon", 5000);
    }

    public void seasarAutoRegisterFromJarSingleton10000() {
        seasarAutoRegisterFromJar(
                "benchmark/many/autoRegisterJar_singleton_10000.dicon", 10000);
    }

    private void seasarAutoRegisterFromJar(final String dicon, final int times) {
        long start = System.currentTimeMillis();
        S2Container container = S2ContainerFactory.create(dicon);
        long end = System.currentTimeMillis();
        System.out.println("[Seasar] container creation:" + (end - start));
        reportContainerCreation(end - start);

        start = end;
        container.init();
        end = System.currentTimeMillis();
        System.out.println("[Seasar] container init:" + (end - start));
        reportContainerInit(end - start);

        System.out.println("componentDefSize="
                + container.getComponentDefSize());

        start = end;
        for (int i = 0; i < times; i++) {
            Object component = container.getComponent("nullBean"
                    + format_.format(i + 10000));
            Assert.assertNotNull(component);
        }
        end = System.currentTimeMillis();
        System.out.println("[Seasar] get " + times + " component(1):"
                + (end - start));
        reportGetComponents((end - start), 1);

        start = end;
        for (int i = 0; i < times; i++) {
            Object component = container.getComponent("nullBean"
                    + format_.format(i + 10000));
            Assert.assertNotNull(component);
        }
        end = System.currentTimeMillis();
        System.out.println("[Seasar] get " + times + " component(2):"
                + (end - start));
        reportGetComponents((end - start), 2);

        reportMemory();
    }

}
