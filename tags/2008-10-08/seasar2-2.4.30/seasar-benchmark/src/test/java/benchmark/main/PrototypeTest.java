package benchmark.main;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory;

import benchmark.aop.Greeting;
import junit.framework.Assert;
import junit.framework.TestCase;

public class PrototypeTest extends TestCase {

    private long times_ = 1000000;

    public void test1() throws Exception {
        S2Container factory = S2ContainerFactory
                .create("benchmark/aop/greeting_prototype_noAop.dicon");
        long start = System.currentTimeMillis();
//        long before = start;
        for (int i = 0; i < times_; i++) {
            Greeting greeting = (Greeting) factory.getComponent("greeting");
            Assert.assertNotNull(greeting);
//            if (i % 10000 == 0) {
//                long now = System.currentTimeMillis();
//                System.out.println(i + "," + (now - before));
//                before = now;
//            }
        }
        long end = System.currentTimeMillis();
        System.out.println(times_ + " total:" + (end - start));
    }
}
