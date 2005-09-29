package test.org.seasar.framework.container.factory;

import org.seasar.framework.container.annotation.AutoBindingType;
import org.seasar.framework.container.annotation.Component;
import org.seasar.framework.container.annotation.Inject;
import org.seasar.framework.container.annotation.InstanceType;
import org.seasar.framework.container.annotation.NoInject;

@Component(name="aaa", instance=InstanceType.PROTOTYPE,
        autoBinding=AutoBindingType.PROPERTY)
public class Hoge2 {    
    
    @Inject("aaa2")
    public void setAaa(String aaa) {
    }
    
    @NoInject
    public void setBbb(String bbb) {
    }
    
    /**
     * @param ccc
     * @org.seasar.framework.container.annotations.Inject
     */
    @Inject
    public void setCcc(String ccc) {
    }
}
