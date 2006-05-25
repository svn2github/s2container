package benchmark.main;

import java.text.DecimalFormat;

import junit.framework.Assert;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public abstract class AbstractManyBeansBenchmark extends AbstractBenchmark {

    private DecimalFormat format_ = new DecimalFormat("00000");

    protected void springGetComponents(String beansXml, int times) {
        System.out.println(beansXml);
        long start = System.currentTimeMillis();
        BeanFactory factory = createBeanFactory(beansXml);
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
        initializeS2Container(container);
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

    protected void seasarAutoRegister(final String dicon, final int times) {
        seasarGetComponents(dicon, times);
    }

    private BeanFactoryCreator beanFactoryCreator_;

    private S2ContainerInitializeStrategy containerInitializeStrategy_;

    protected BeanFactory createBeanFactory(String beansXml) {
        if (beanFactoryCreator_ == null) {
            beanFactoryCreator_ = new ClassPathXmlApplicationContextCreator();
        }
        return beanFactoryCreator_.createBeanFactory(beansXml);
    }

    private void initializeS2Container(S2Container container) {
        if (containerInitializeStrategy_ == null) {
            containerInitializeStrategy_ = new S2ContainerInitStrategy();
        }
        containerInitializeStrategy_.init(container);
    }

    protected static interface BeanFactoryCreator {
        BeanFactory createBeanFactory(String beansXml);
    }

    protected static class XmlBeanFactoryCreator implements BeanFactoryCreator {
        public BeanFactory createBeanFactory(String beansXml) {
            return new XmlBeanFactory(new ClassPathResource(beansXml));
        }
    }

    protected static class ClassPathXmlApplicationContextCreator implements
            BeanFactoryCreator {
        public BeanFactory createBeanFactory(String beansXml) {
            return new ClassPathXmlApplicationContext(beansXml);
        }
    }

    protected void setBeanFactoryCreator(BeanFactoryCreator beanFactoryCreator) {
        beanFactoryCreator_ = beanFactoryCreator;
    }

    protected static interface S2ContainerInitializeStrategy {
        void init(S2Container container);
    }

    protected static class S2ContainerInitStrategy implements
            S2ContainerInitializeStrategy {
        public void init(S2Container container) {
            container.init();
        }
    }

    protected static class S2ContainerNoInitStrategy implements
            S2ContainerInitializeStrategy {
        public void init(S2Container container) {
        }
    }

    protected void setS2ContainerInitializeStrategy(
            S2ContainerInitializeStrategy containerInitializeStrategy) {
        containerInitializeStrategy_ = containerInitializeStrategy;
    }

}
