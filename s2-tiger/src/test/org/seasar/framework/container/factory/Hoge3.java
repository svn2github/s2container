package test.org.seasar.framework.container.factory;

public class Hoge3 {

    public static final String COMPONENT = "name = aaa, instance = prototype, autoBinding = property";
    
    public static final String ASPECT = "interceptor=aop.traceInterceptor, pointcut=setAaa";

    public static final String aaa_BINDING = "value=aaa2";

    public static final String bbb_BINDING = "bindingType=none";

    public void setAaa(String aaa) {
    }

    public void setBbb(String bbb) {
    }
}
