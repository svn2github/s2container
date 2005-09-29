package test.org.seasar.framework.container.factory;

/**
 * @author higa
 * @org.seasar.framework.container.annotation.Component(
 *  name = "aaa", instance = "prototype", autoBinding = "property")
 *
 */
public class Hoge2 {    
    
    /**
     * @param aaa
     * @org.seasar.framework.container.annotation.Inject("aaa2")
     */
    public void setAaa(String aaa) {
    }
    
    /**
     * @param bbb
     * @org.seasar.framework.container.annotation.NoInject
     */
    public void setBbb(String bbb) {
    }
    
    /**
     * @param ccc
     * @org.seasar.framework.container.annotation.Inject
     */
    public void setCcc(String ccc) {
    }
}
