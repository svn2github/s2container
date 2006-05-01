package benchmark.main;

import java.text.DecimalFormat;

import junit.framework.Assert;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class AbstractManyBeansBenchmark extends AbstractBenchmark {

    private DecimalFormat format_ = new DecimalFormat("00000");

    protected void seasarAutoRegister(final String dicon, final int times) {
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
                    + format_.format(i));
            Assert.assertNotNull(component);
        }
        end = System.currentTimeMillis();
        System.out.println("[Seasar] get " + times + " component(1):"
                + (end - start));
        reportGetComponents((end - start), 1);

        start = end;
        for (int i = 0; i < times; i++) {
            Object component = container.getComponent("nullBean"
                    + format_.format(i));
            Assert.assertNotNull(component);
        }
        end = System.currentTimeMillis();
        System.out.println("[Seasar] get " + times + " component(2):"
                + (end - start));
        reportGetComponents((end - start), 2);

        reportMemory();
    }

    protected void springGetComponents(String beansXml, int times) {
        System.out.println(beansXml);
        long start = System.currentTimeMillis();
        BeanFactory factory = new XmlBeanFactory(
                new ClassPathResource(beansXml));
        springGetComponents0(times, start, factory);
    }

    protected void springGetComponents0(final int times, long start,
            BeanFactory factory) {
        long end = System.currentTimeMillis();
        System.out.println("[Spring] container creation:" + (end - start));
        reportContainerCreation(end - start);
        reportContainerInit(0);

        start = end;
        for (int i = 0; i < times; i++) {
            Object component = factory.getBean("nullBean" + format_.format(i));
            Assert.assertNotNull(component);
        }
        end = System.currentTimeMillis();
        System.out.println("[Spring] get " + times + " component(1):"
                + (end - start));
        reportGetComponents((end - start), 1);

        start = end;
        for (int i = 0; i < times; i++) {
            Object component = factory.getBean("nullBean" + format_.format(i));
            Assert.assertNotNull(component);
        }
        end = System.currentTimeMillis();
        System.out.println("[Spring] get " + times + " component(2):"
                + (end - start));
        reportGetComponents((end - start), 2);

        reportMemory();
    }

    protected void seasarGetComponents(String dicon, int times) {
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
        for (int i = 0; i < times; i++) {
            Object component = container.getComponent("nullBean"
                    + format_.format(i));
            Assert.assertNotNull(component);
        }
        end = System.currentTimeMillis();
        System.out.println("[Seasar] get " + times + " component(1):"
                + (end - start));
        reportGetComponents((end - start), 1);

        start = end;
        for (int i = 0; i < times; i++) {
            Object component = container.getComponent("nullBean"
                    + format_.format(i));
            Assert.assertNotNull(component);
        }
        end = System.currentTimeMillis();
        System.out.println("[Seasar] get " + times + " component(2):"
                + (end - start));
        reportGetComponents((end - start), 2);

        reportMemory();
    }

}
