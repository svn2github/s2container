package examples.dicon;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory;

public class HelloConstructorInjectionClient {

	private static final String PATH =
		"examples/dicon/HelloConstructorInjection.dicon";
		
	public static void main(String[] args) {
		S2Container container = S2ContainerFactory.create(PATH);
		Hello hello = (Hello) container.getComponent(Hello.class);
		hello.showMessage();
		
		Hello hello2 = (Hello) container.getComponent("hello");
		hello2.showMessage();
	}
}