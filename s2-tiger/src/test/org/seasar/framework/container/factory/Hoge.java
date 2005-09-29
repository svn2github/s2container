package test.org.seasar.framework.container.factory;

import org.seasar.framework.container.annotation.Aspect;

@Aspect(interceptor="aop.traceInterceptor", pointcut="getAaa")
public class Hoge {

    public String getAaa() {
        return null;
    }
}
