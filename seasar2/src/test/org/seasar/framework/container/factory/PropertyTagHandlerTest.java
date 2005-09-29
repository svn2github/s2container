package test.org.seasar.framework.container.factory;

import java.util.Date;

import junit.framework.TestCase;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory;

/**
 * @author higa
 *
 */
public class PropertyTagHandlerTest extends TestCase {

	private static final String PATH =
		"test/org/seasar/framework/container/factory/PropertyTagHandlerTest.dicon";

	public PropertyTagHandlerTest(String arg0) {
		super(arg0);
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(PropertyTagHandlerTest.class);
	}

	public void testProperty() throws Exception {
		S2Container container = S2ContainerFactory.create(PATH);
		assertEquals(
			"1",
			new Date(0),
			container.getComponent(Date.class));
	}
}
