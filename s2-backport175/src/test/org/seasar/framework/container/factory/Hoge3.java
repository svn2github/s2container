package test.org.seasar.framework.container.factory;

public class Hoge3 {

    public static final String COMPONENT = "name = aaa, instance = prototype, autoBinding = property";
    
    public static final String ASPECT = "interceptor=aop.traceInterceptor, pointcut=setAaa";

    public static final String aaa_INJECT = "aaa2";

    public static final boolean bbb_No_INJECT = true;

    public void setAaa(String aaa) {
    }

    public void setBbb(String bbb) {
    }
}
