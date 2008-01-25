package spring.learning.aop;

import java.util.Date;

import junit.framework.TestCase;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class AutoProxyTest extends TestCase {

    public void testAutoProxy() throws Exception {
        // ## Arrange ##
        //        XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource(
        //                "spring/learning/aop/AutoProxyTest.xml"));
        ApplicationContext factory = new ClassPathXmlApplicationContext(
                "spring/learning/aop/AutoProxyTest.xml");

        // ## Act ##
        Date foo = (Date) factory.getBean("dateBean");

        // ## Assert ##
        System.out.println(foo.getTime());
    }

    public void testManualProxy() throws Exception {
        // ## Arrange ##
        XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource(
                "spring/learning/aop/AutoProxyTest_ManualProxy.xml"));

        // ## Act ##
        Date foo = (Date) factory.getBean("dateBean");

        // ## Assert ##
        System.out.println(foo.getTime());
    }

    public void testAutoProxyWire() throws Exception {
        // ## Arrange ##
        System.out.println("1");
        ApplicationContext factory = new ClassPathXmlApplicationContext(
                "spring/learning/aop/AutoProxyTest_aopAutoRegister.xml");
        System.out.println("2");

        // ## Act ##
        BeanAaa aaa = (BeanAaa) factory.getBean("beanAaa");
        System.out.println("3");

        // ## Assert ##
        System.out.println(aaa.toString() + " " + aaa.hashCode());
        System.out.println("4");
        System.out.println(aaa.getBeanBbb());
        System.out.println("5");
    }

    public void testSeasarDI() throws Exception {
        S2Container container = S2ContainerFactory
                .create("spring/learning/aop/AutoProxyTest_singleton.dicon");
        System.out.println("1");
        container.init();
        System.out.println("2");
        BeanAaa aaa = (BeanAaa) container.getComponent("beanAaa");
        System.out.println("3");
        System.out.println(aaa.toString() + " " + aaa.hashCode());
        System.out.println("4");
        
    }

    public static class BeanAaa {
        public BeanAaa() {
            System.out.println("BeanAaa.BeanAaa() " + hashCode());
            new Throwable().printStackTrace();
        }

        private BeanBbb beanBbb_;

        public BeanBbb getBeanBbb() {
            System.out.println("BeanAaa.getBeanBbb() " + beanBbb_.hashCode());
            return beanBbb_;
        }

        public void setBeanBbb(BeanBbb beanBbb) {
            System.out.println("BeanAaa.setBeanBbb() " + beanBbb.hashCode());
            beanBbb_ = beanBbb;
        }
    }

    public static class BeanBbb {
        public BeanBbb() {
            System.out.println("BeanBbb.BeanBbb() " + hashCode());
            new Throwable().printStackTrace();
        }
    }

}
