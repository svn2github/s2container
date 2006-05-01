package benchmark.main;

import java.text.DecimalFormat;

import junit.framework.Assert;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class WireBenchmark extends AbstractBenchmark {

    private DecimalFormat format_ = new DecimalFormat("00000");

    private final int times_ = 1000;

    public void seasarAutoWireSingleton() throws Exception {
        seasar("benchmark/wire/autoWire.dicon");
    }

    public void springAutoWireSingleton() throws Exception {
        spring("benchmark/wire/autoWire.xml");
    }

    public void seasarAutoWirePrototype() throws Exception {
        seasar("benchmark/wire/autoWire_prototype.dicon");
    }

    public void springAutoWirePrototype() throws Exception {
        spring("benchmark/wire/autoWire_prototype.xml");
    }

    public void seasarManualWireSingleton() throws Exception {
        seasar("benchmark/wire/manualWire.dicon");
    }

    public void springManualWireSingleton() throws Exception {
        spring("benchmark/wire/manualWire.xml");
    }

    public void seasarManualWirePrototype() throws Exception {
        seasar("benchmark/wire/manualWire_prototype.dicon");
    }

    public void springManualWirePrototype() throws Exception {
        spring("benchmark/wire/manualWire_prototype.xml");
    }

    public void seasarNoWireSingleton() throws Exception {
        seasar("benchmark/wire/noWire.dicon");
    }

    public void springNoWireSingleton() throws Exception {
        spring("benchmark/wire/noWire.xml");
    }

    public void seasarAutoRegisterAutoWire() throws Exception {
        String dicon = "benchmark/wire/autoRegister_autoWire.dicon";
        System.out.println(dicon);
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
        for (int i = 0; i < times_; i++) {
            Object component = container.getComponent("bean"
                    + format_.format(i) + "A");
            Assert.assertNotNull(component);
        }
        end = System.currentTimeMillis();
        System.out.println("[Seasar] get " + times_ + " component(1):"
                + (end - start));
        reportGetComponents((end - start), 1);

        start = end;
        for (int i = 0; i < times_; i++) {
            Object component = container.getComponent("bean"
                    + format_.format(i) + "A");
            Assert.assertNotNull(component);
        }
        end = System.currentTimeMillis();
        System.out.println("[Seasar] get " + times_ + " component(2):"
                + (end - start));
        reportGetComponents((end - start), 2);

        reportMemory();
    }

    private void seasar(String dicon) {
        System.out.println(dicon);
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

        start = end;
        for (int i = 0; i < times_; i++) {
            Object component = container.getComponent("component"
                    + format_.format(i) + "A");
            Assert.assertNotNull(component);
        }
        end = System.currentTimeMillis();
        System.out.println("[Seasar] get " + times_ + " component(1):"
                + (end - start));
        reportGetComponents((end - start), 1);

        start = end;
        for (int i = 0; i < times_; i++) {
            Object component = container.getComponent("component"
                    + format_.format(i) + "A");
            Assert.assertNotNull(component);
        }
        end = System.currentTimeMillis();
        System.out.println("[Seasar] get " + times_ + " component(2):"
                + (end - start));
        reportGetComponents((end - start), 2);

        reportMemory();
    }

    private void spring(String beansXml) {
        System.out.println(beansXml);
        long start = System.currentTimeMillis();
        BeanFactory factory = new XmlBeanFactory(
                new ClassPathResource(beansXml));
        long end = System.currentTimeMillis();
        System.out.println("[Spring] container creation:" + (end - start));
        reportContainerCreation(end - start);
        reportContainerInit(0);

        start = end;
        for (int i = 0; i < times_; i++) {
            Object component = factory
                    .getBean("bean" + format_.format(i) + "A");
            Assert.assertNotNull(component);
        }
        end = System.currentTimeMillis();
        System.out.println("[Spring] get " + times_ + " component(1):"
                + (end - start));
        reportGetComponents((end - start), 1);

        start = end;
        for (int i = 0; i < times_; i++) {
            Object component = factory
                    .getBean("bean" + format_.format(i) + "A");
            Assert.assertNotNull(component);
        }
        end = System.currentTimeMillis();
        System.out.println("[Spring] get " + times_ + " component(2):"
                + (end - start));
        reportGetComponents((end - start), 2);

        reportMemory();
    }

}
