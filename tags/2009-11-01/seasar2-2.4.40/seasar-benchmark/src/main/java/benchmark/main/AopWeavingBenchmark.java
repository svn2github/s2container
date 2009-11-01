package benchmark.main;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.seasar.framework.aop.Aspect;
import org.seasar.framework.aop.Pointcut;
import org.seasar.framework.aop.impl.AspectImpl;
import org.seasar.framework.aop.impl.PointcutImpl;
import org.seasar.framework.aop.proxy.AopProxy;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;

import benchmark.aop.GreetInterceptor;

public class AopWeavingBenchmark extends AbstractBenchmark {

    public void seasarWeaving01000() throws Exception {
        seasar(1000);
    }

    public void seasarWeaving02000() throws Exception {
        seasar(2000);
    }

    public void seasarWeaving05000() throws Exception {
        seasar(5000);
    }

    public void seasarWeaving10000() throws Exception {
        seasar(10000);
    }

    public void springWeaving01000() throws Exception {
        spring(1000);
    }

    public void springWeaving02000() throws Exception {
        spring(2000);
    }

    public void springWeaving05000() throws Exception {
        spring(5000);
    }

    public void springWeaving10000() throws Exception {
        spring(10000);
    }

    private void seasar(final int times) throws Exception {
        long start = System.currentTimeMillis();
        MethodInterceptor interceptor = new GreetInterceptor();
        for (int i = 0; i < times; i++) {
            final Class clazz = getBeanClass(i);
            Pointcut pointcut = new PointcutImpl(new String[] { "toString" });
            Aspect aspect = new AspectImpl(interceptor, pointcut);
            AopProxy aopProxy = new AopProxy(clazz, new Aspect[] { aspect });
            Object proxy = aopProxy.create();
        }
        long end = System.currentTimeMillis();
        reportTime(end - start);
        reportMemory();
    }

    private void spring(final int times) throws Exception {
        long start = System.currentTimeMillis();
        Advice advice = new GreetInterceptor();
        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
        pointcut.setPattern(".*toString");
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut,
                advice);
        for (int i = 0; i < times; i++) {
            final Class clazz = getBeanClass(i);
            Object instance = clazz.newInstance();
            ProxyFactory factory = new ProxyFactory(instance);
            factory.setProxyTargetClass(true);
            factory.addAdvisor(advisor);
            Object proxy = factory.getProxy();
        }
        long end = System.currentTimeMillis();
        reportTime(end - start);
        reportMemory();
    }

}
