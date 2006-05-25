package benchmark.main;

import junit.framework.Assert;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import benchmark.aop.Greeting;
import benchmark.aop.GreetingImpl2;

public class AopBenchmark extends AbstractBenchmark {

    private int times_ = 10000000;

    public void springProxyAopCallSingleton() {
        springProxyAopCall("benchmark/aop/greeting.xml");
    }

    public void springProxyAopCallPrototype() throws Exception {
        springProxyAopCall("benchmark/aop/greeting_prototype.xml");
    }

    private void springProxyAopCall(final String xml) {
        BeanFactory factory = createBeanFactory(xml); //new XmlBeanFactory(new ClassPathResource(xml));
        Greeting greeting = (Greeting) factory.getBean("greeting");
        Assert.assertEquals(true, greeting.getClass().getName().indexOf(
                "$Proxy") > -1);
        Assert.assertNotNull(greeting.greet());
        long start = System.currentTimeMillis();
        for (int i = 0; i < times_; i++) {
            greeting.greet();
        }
        long end = System.currentTimeMillis();
        System.out
                .println("[Spring] " + times_ + " times AOP:" + (end - start));
        reportTime(end - start);
        reportMemory();
    }

    public void springCglibAopCallSingleton() throws Exception {
        springCglibAopCall("benchmark/aop/greeting.xml");
    }

    public void springCglibAopCallPrototype() throws Exception {
        springCglibAopCall("benchmark/aop/greeting_prototype.xml");
    }

    private void springCglibAopCall(final String xml) {
        BeanFactory factory = createBeanFactory(xml);
        GreetingImpl2 greeting = (GreetingImpl2) factory.getBean("greeting2");
        Assert.assertEquals(true, greeting.getClass().getName()
                .indexOf("CGLIB") > -1);
        Assert.assertNotNull(greeting.greet());
        long start = System.currentTimeMillis();
        for (int i = 0; i < times_; i++) {
            greeting.greet();
        }
        long end = System.currentTimeMillis();
        System.out
                .println("[Spring] " + times_ + " times AOP:" + (end - start));
        reportTime(end - start);
        reportMemory();
    }

    public void seasarAopCallSingleton() throws Exception {
        seasarAopCall("benchmark/aop/greeting.dicon");
    }

    public void seasarAopCallPrototype() throws Exception {
        seasarAopCall("benchmark/aop/greeting_prototype.dicon");
    }

    private void seasarAopCall(final String dicon) {
        S2Container container = S2ContainerFactory.create(dicon);
        container.init();
        Greeting greeting = (Greeting) container.getComponent("greeting");
        Assert.assertNotNull(greeting.greet());
        long start = System.currentTimeMillis();
        for (int i = 0; i < times_; i++) {
            greeting.greet();
        }
        long end = System.currentTimeMillis();
        System.out
                .println("[Seasar] " + times_ + " times AOP:" + (end - start));
        reportTime(end - start);
        reportMemory();
    }

    public void seasarNoAopCallSingleton() throws Exception {
        seasarNoAopCall("benchmark/aop/greeting_noAop.dicon");
    }

    public void seasarNoAopCallPrototype() throws Exception {
        seasarNoAopCall("benchmark/aop/greeting_prototype_noAop.dicon");
    }

    private void seasarNoAopCall(final String dicon) {
        S2Container container = S2ContainerFactory.create(dicon);
        container.init();
        Greeting greeting = (Greeting) container.getComponent("greeting");
        Assert.assertNull(greeting.greet());
        long start = System.currentTimeMillis();
        for (int i = 0; i < times_; i++) {
            greeting.greet();
        }
        long end = System.currentTimeMillis();
        System.out
                .println("[Seasar] " + times_ + " times AOP:" + (end - start));
        reportTime(end - start);
        reportMemory();
    }

    public void seasarAopCallSingletonNoInterface() throws Exception {
        seasarAopCallNoInterface("benchmark/aop/greeting.dicon");
    }

    public void seasarAopCallPrototypeNoInterface() throws Exception {
        seasarAopCallNoInterface("benchmark/aop/greeting_prototype.dicon");
    }

    private void seasarAopCallNoInterface(final String dicon) {
        S2Container container = S2ContainerFactory.create(dicon);
        container.init();
        GreetingImpl2 greeting = (GreetingImpl2) container
                .getComponent("greeting2");
        Assert.assertNotNull(greeting.greet());
        long start = System.currentTimeMillis();
        for (int i = 0; i < times_; i++) {
            greeting.greet();
        }
        long end = System.currentTimeMillis();
        System.out
                .println("[Seasar] " + times_ + " times AOP:" + (end - start));
        reportTime(end - start);
        reportMemory();
    }

    public void seasarGetSingletonAop() throws Exception {
        seasarGetComponent("benchmark/aop/greeting.dicon");
    }

    public void seasarGetPrototypeAop() throws Exception {
        seasarGetComponent("benchmark/aop/greeting_prototype.dicon");
    }

    public void seasarGetSingletonNoAop() throws Exception {
        seasarGetComponent("benchmark/aop/greeting_noAop.dicon");
    }

    public void seasarGetPrototypeNoAop() throws Exception {
        seasarGetComponent("benchmark/aop/greeting_prototype_noAop.dicon");
    }

    private void seasarGetComponent(final String dicon) {
        S2Container container = S2ContainerFactory.create(dicon);
        container.init();
        {
            Greeting greeting = (Greeting) container.getComponent("greeting");
            Assert.assertNotNull(greeting);
        }
        long start = System.currentTimeMillis();
        for (int i = 0; i < times_; i++) {
            Greeting greeting = (Greeting) container.getComponent("greeting");
        }
        long end = System.currentTimeMillis();
        System.out
                .println("[Seasar] " + times_ + " times AOP:" + (end - start));
        reportTime(end - start);
        reportMemory();
    }

    public void springGetProxySingletonAop() throws Exception {
        springGetProxyComponent("benchmark/aop/greeting.xml");
    }

    public void springGetProxyPrototypeAop() throws Exception {
        springGetProxyComponent("benchmark/aop/greeting_prototype.xml");
    }

    private void springGetProxyComponent(final String xml) {
        BeanFactory factory = createBeanFactory(xml);
        {
            Greeting greeting = (Greeting) factory.getBean("greeting");
            Assert.assertNotNull(greeting);
        }
        long start = System.currentTimeMillis();
        for (int i = 0; i < times_; i++) {
            Greeting greeting = (Greeting) factory.getBean("greeting");
        }
        long end = System.currentTimeMillis();
        System.out
                .println("[Spring] " + times_ + " times AOP:" + (end - start));
        reportTime(end - start);
        reportMemory();
    }

    public void springGetCglibSingletonAop() throws Exception {
        springGetCglibComponent("benchmark/aop/greeting.xml");
    }

    public void springGetCglibPrototypeAop() throws Exception {
        springGetCglibComponent("benchmark/aop/greeting_prototype.xml");
    }

    private void springGetCglibComponent(final String xml) {
        BeanFactory factory = createBeanFactory(xml);
        {
            GreetingImpl2 greeting = (GreetingImpl2) factory
                    .getBean("greeting2");
            Assert.assertNotNull(greeting);
        }
        long start = System.currentTimeMillis();
        for (int i = 0; i < times_; i++) {
            GreetingImpl2 greeting = (GreetingImpl2) factory
                    .getBean("greeting2");
        }
        long end = System.currentTimeMillis();
        System.out
                .println("[Spring] " + times_ + " times AOP:" + (end - start));
        reportTime(end - start);
        reportMemory();
    }

    protected BeanFactory createBeanFactory(String beansXml) {
        return new ClassPathXmlApplicationContext(beansXml);
    }

}
