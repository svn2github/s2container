package test.org.seasar.framework.container.factory;

import org.seasar.framework.container.annotation.Aspect;

public class Hoge4 {

    @Aspect(interceptor="aop.traceInterceptor")
    public String getAaa() {
        return null;
    }
}
