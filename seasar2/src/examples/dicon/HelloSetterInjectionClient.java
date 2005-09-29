package examples.dicon;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory;

public class HelloSetterInjectionClient {

	private static final String PATH =
		"examples/dicon/HelloSetterInjection.dicon";
		
	public static void main(String[] args) {
		S2Container container = S2ContainerFactory.create(PATH);
		Hello hello = (Hello) container.getComponent(Hello.class);
		hello.showMessage();
	}
}