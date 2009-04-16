package spring.learning.aop;

import junit.framework.TestCase;

import org.aopalliance.aop.Advice;
import org.seasar.framework.aop.Aspect;
import org.seasar.framework.aop.Pointcut;
import org.seasar.framework.aop.impl.AspectImpl;
import org.seasar.framework.aop.impl.PointcutImpl;
import org.seasar.framework.aop.proxy.AopProxy;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;

public class ProxyFactoryLearngingTest extends TestCase {

    public void test1() throws Exception {
        // -----
        ProxyFactory factory = new ProxyFactory(new Object());
        factory.setProxyTargetClass(true);

        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
        pointcut.setPattern(".*toString");
        Advice advice = new SystemOutInterceptor();
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut,
                advice);

        factory.addAdvisor(advisor);
        final Object proxy = factory.getProxy();
        // -----

        System.out.println(proxy.toString());
    }

    public void test2() throws Exception {
        SystemOutInterceptor invocation = new SystemOutInterceptor();
        Pointcut pointcut = new PointcutImpl(new String[] { "toString" });
        Aspect aspect = new AspectImpl(invocation, pointcut);
        AopProxy aopProxy = new AopProxy(Object.class, new Aspect[] { aspect });
        Object proxy = aopProxy.create();
        System.out.println(proxy.toString());
    }

}
