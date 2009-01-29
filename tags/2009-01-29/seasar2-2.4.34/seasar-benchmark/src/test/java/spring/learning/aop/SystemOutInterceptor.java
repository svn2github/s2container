package spring.learning.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class SystemOutInterceptor implements MethodInterceptor {

    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("BEFORE: SystemOutInterceptor.invoke()");
        Object result = invocation.proceed();
        System.out.println("AFTER : SystemOutInterceptor.invoke()");
        return result;
    }

}
