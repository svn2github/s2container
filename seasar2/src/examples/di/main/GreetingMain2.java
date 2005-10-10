package examples.di.main;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory;

import examples.di.impl.GreetingClientImpl;

public class GreetingMain2 {

    private static final String PATH =
        "examples/di/dicon/GreetingMain2.dicon";
 
    public static void main(String[] args) {
        S2Container container =
            S2ContainerFactory.create(PATH);
        GreetingClientImpl greetingClient = (GreetingClientImpl)
            container.getComponent("greetingClient");
        greetingClient.execute();
    }
}