package benchmark.main;

import java.net.URL;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.util.ResourceUtil;
import org.springframework.beans.factory.BeanFactory;

public class BenchmarkVersion {

    public static void main(String[] args) {
        String seasarClass = S2Container.class.getName().replace('.', '/')
                + ".class";
        //System.out.println(seasarClass);
        URL seasarResource = ResourceUtil.getResource(seasarClass);
        System.out.println(seasarResource);

        String springClass = BeanFactory.class.getName().replace('.', '/')
                + ".class";
        //System.out.println(springClass);
        URL springResource = ResourceUtil.getResource(springClass);
        System.out.println(springResource);

        /*
         Runtime runtime = Runtime.getRuntime();
         long freeMemory = runtime.freeMemory();
         long totalMemory = runtime.totalMemory();
         long maxMemory = runtime.maxMemory();
         System.out.println("freeMemory=" + freeMemory + ", totalMemory="
         + totalMemory + ", maxMemory=" + maxMemory);
         RuntimeMemory runtimeMemory = new RuntimeMemory();
         System.out.println(runtimeMemory.toStringKilobyte());
         System.out.println(runtimeMemory.toStringMegabyte());
         */
    }

}
