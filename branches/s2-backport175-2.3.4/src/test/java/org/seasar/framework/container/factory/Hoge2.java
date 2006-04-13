package org.seasar.framework.container.factory;

/**
 * @author higa
 * @org.seasar.framework.container.annotation.backport175.Component(
 *      name = "aaa",
 *      instance = "prototype",
 *      autoBinding = "property")
 *
 */
public class Hoge2 {    
    
    /**
     * @param aaa
     * @org.seasar.framework.container.annotation.backport175.Binding("aaa2")
     */
    public void setAaa(String aaa) {
    }
    
    /**
     * @param bbb
     * @org.seasar.framework.container.annotation.backport175.Binding(
     *      bindingType="none")
     */
    public void setBbb(String bbb) {
    }
    
    /**
     * @param ccc
     * @org.seasar.framework.container.annotation.Binding
     */
    public void setCcc(String ccc) {
    }
}
