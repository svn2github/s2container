package benchmark.main;

import junit.framework.Assert;

import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.impl.BeanDescImpl;
import org.springframework.beans.BeanWrapperImpl;

public class BeanDescBenchmark extends AbstractBenchmark {

    public void seasarBeanDescCreation01000() throws Exception {
        seasarBeanDescCreation(1000);
    }

    public void seasarBeanDescCreation02000() throws Exception {
        seasarBeanDescCreation(2000);
    }

    public void seasarBeanDescCreation05000() throws Exception {
        seasarBeanDescCreation(5000);
    }

    public void seasarBeanDescCreation10000() throws Exception {
        seasarBeanDescCreation(10000);
    }

    public void springBeanWrapperCreation01000() throws Exception {
        springBeanWrapperCreation(1000);
    }

    public void springBeanWrapperCreation02000() throws Exception {
        springBeanWrapperCreation(2000);
    }

    public void springBeanWrapperCreation05000() throws Exception {
        springBeanWrapperCreation(5000);
    }

    public void springBeanWrapperCreation10000() throws Exception {
        springBeanWrapperCreation(10000);
    }

    private void seasarBeanDescCreation(int times)
            throws ClassNotFoundException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            Class clazz = getBeanClass(i);
            BeanDescImpl beanDesc = new BeanDescImpl(clazz);
        }
        long end = System.currentTimeMillis();
        reportTime(end - start);
        reportMemory();
    }

    private void springBeanWrapperCreation(int times)
            throws ClassNotFoundException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            Class clazz = getBeanClass(i);
            BeanWrapperImpl beanWrapper = new BeanWrapperImpl(clazz);
        }
        long end = System.currentTimeMillis();
        reportTime(end - start);
        reportMemory();
    }

    public void seasarBeanDescAccess01000() throws Exception {
        seasarBeanDescAccess(1000);
    }

    public void seasarBeanDescAccess02000() throws Exception {
        seasarBeanDescAccess(2000);
    }

    public void seasarBeanDescAccess05000() throws Exception {
        seasarBeanDescAccess(5000);
    }

    public void seasarBeanDescAccess10000() throws Exception {
        seasarBeanDescAccess(10000);
    }

    private void seasarBeanDescAccess(int times) {
        long start = System.currentTimeMillis();
        Foo foo = new Foo();
        BeanDescImpl beanDesc = new BeanDescImpl(foo.getClass());
        for (int i = 0; i < times; i++) {
            PropertyDesc propertyDesc = beanDesc.getPropertyDesc("aaa");
            propertyDesc.setValue(foo, "abc" + i);
            Assert.assertEquals("abc" + i, propertyDesc.getValue(foo));
        }
        long end = System.currentTimeMillis();
        reportTime(end - start);
        reportMemory();
    }

    public void springBeanWrapperAccess01000() throws Exception {
        springBeanWrapperAccess(1000);
    }

    public void springBeanWrapperAccess02000() throws Exception {
        springBeanWrapperAccess(2000);
    }

    public void springBeanWrapperAccess05000() throws Exception {
        springBeanWrapperAccess(5000);
    }

    public void springBeanWrapperAccess10000() throws Exception {
        springBeanWrapperAccess(10000);
    }

    private void springBeanWrapperAccess(int times) {
        Foo foo = new Foo();
        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(foo);
        long start = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            beanWrapper.setPropertyValue("aaa", "abc" + i);
            Assert.assertEquals("abc" + i, beanWrapper.getPropertyValue("aaa"));
        }
        long end = System.currentTimeMillis();
        reportTime(end - start);
        reportMemory();
    }

    public void loadClassOnly01000() throws Exception {
        loadClassOnly(1000);
    }

    public void loadClassOnly02000() throws Exception {
        loadClassOnly(2000);
    }

    public void loadClassOnly05000() throws Exception {
        loadClassOnly(5000);
    }

    public void loadClassOnly10000() throws Exception {
        loadClassOnly(10000);
    }

    private void loadClassOnly(int times) throws ClassNotFoundException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            Class clazz = getBeanClass(i);
        }
        long end = System.currentTimeMillis();
        reportTime(end - start);
        reportMemory();
    }

    public static class Foo {
        private String aaa_;

        public String getAaa() {
            return aaa_;
        }

        public void setAaa(String aaa) {
            aaa_ = aaa;
        }
    }

}
