package test.org.seasar.framework.container.factory;

import org.seasar.framework.container.annotation.AutoBindingType;
import org.seasar.framework.container.annotation.Binding;
import org.seasar.framework.container.annotation.BindingType;
import org.seasar.framework.container.annotation.Component;
import org.seasar.framework.container.annotation.InstanceType;

@Component(name="aaa", instance=InstanceType.PROTOTYPE,
        autoBinding=AutoBindingType.PROPERTY)
public class Hoge2 {    
    
    @Binding("aaa2")
    public void setAaa(String aaa) {
    }
    
    @Binding(bindingType=BindingType.NONE)
    public void setBbb(String bbb) {
    }
    
    /**
     * @param ccc
     * @org.seasar.framework.container.annotations.Inject
     */
    @Binding
    public void setCcc(String ccc) {
    }
}
