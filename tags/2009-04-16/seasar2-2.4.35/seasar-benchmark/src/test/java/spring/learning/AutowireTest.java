package spring.learning;

import junit.framework.TestCase;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class AutowireTest extends TestCase {

    public void testByType() throws Exception {
        // ## Arrange ##
        XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource(
                "spring/learning/AutowireTest_byType.xml"));

        // ## Act ##
        Object component = factory.getBean("beanA");
        BeanA a = (BeanA) component;

        // ## Assert ##
        assertNotNull(a);
        assertNotNull(a.getBeanB());
    }

    public void testByType_Seasar() throws Exception {
        // ## Arrange ##
        S2Container container = S2ContainerFactory
                .create("spring/learning/AutowireTest_byType.dicon");

        // ## Act ##
        Object component = container.getComponent("beanA");
        BeanA a = (BeanA) component;

        // ## Assert ##
        assertNotNull(a);
        assertNotNull(a.getBeanB());
    }

    public void testNo() throws Exception {
        // ## Arrange ##
        XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource(
                "spring/learning/AutowireTest_no.xml"));

        // ## Act ##
        Object component = factory.getBean("beanA");
        BeanA a = (BeanA) component;

        // ## Assert ##
        assertNotNull(a);
        assertNull(a.getBeanB());
    }

    public void testNo_Seasar() throws Exception {
        // ## Arrange ##
        S2Container container = S2ContainerFactory
                .create("spring/learning/AutowireTest_no.dicon");

        // ## Act ##
        Object component = container.getComponent("beanA");
        BeanA a = (BeanA) component;

        // ## Assert ##
        assertNotNull(a);
        assertNull(a.getBeanB());
    }

    public void testManual() throws Exception {
        // ## Arrange ##
        XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource(
                "spring/learning/AutowireTest_manual.xml"));

        // ## Act ##
        Object component = factory.getBean("beanA");
        BeanA a = (BeanA) component;

        // ## Assert ##
        assertNotNull(a);
        assertNotNull(a.getBeanB());
    }

    public void testManual_Seasar() throws Exception {
        // ## Arrange ##
        S2Container container = S2ContainerFactory
                .create("spring/learning/AutowireTest_manual.dicon");

        // ## Act ##
        Object component = container.getComponent("beanA");
        BeanA a = (BeanA) component;

        // ## Assert ##
        assertNotNull(a);
        assertNotNull(a.getBeanB());
    }

    public static interface BeanA {
        BeanB getBeanB();
    }

    public static interface BeanB {
    }

    public static class BeanAImpl implements BeanA {
        private BeanB beanB_;

        public BeanB getBeanB() {
            return beanB_;
        }

        public void setBeanB(BeanB beanB) {
            beanB_ = beanB;
        }
    }

    public static class BeanBImpl implements BeanB {
    }

}
