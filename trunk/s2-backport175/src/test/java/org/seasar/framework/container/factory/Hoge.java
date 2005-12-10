package org.seasar.framework.container.factory;

/**
 * @author higa
 * @org.seasar.framework.container.annotation.backport175.Aspect(
 *  value="aop.traceInterceptor",
 *  pointcut="getAaa")
 * @org.seasar.framework.container.annotation.backport175.InterType("fieldInterType")
 *
 */
public class Hoge {

    public String getAaa() {
        return null;
    }

    /**
     * @org.seasar.framework.container.annotation.backport175.InitMethod
     */
    public void init() {
    }
}
