package test.org.seasar.framework.container.assembler;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.seasar.framework.aop.interceptors.TraceInterceptor;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.assembler.ConstructorAssembler;
import org.seasar.framework.container.assembler.DefaultConstructorAssembler;
import org.seasar.framework.container.impl.AspectDefImpl;
import org.seasar.framework.container.impl.ComponentDefImpl;
import org.seasar.framework.container.impl.S2ContainerImpl;

/**
 * @author higa
 *
 */
public class DefaultConstructorAssemblerTest extends TestCase {

	public DefaultConstructorAssemblerTest(String arg0) {
		super(arg0);
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(
			DefaultConstructorAssemblerTest.class);
	}

	public void testAssemble() throws Exception {
		S2Container container = new S2ContainerImpl();
		ComponentDefImpl cd = new ComponentDefImpl(ArrayList.class);
		container.register(cd);
		ConstructorAssembler assempbler = new DefaultConstructorAssembler(cd);
		assertNotNull("1", assempbler.assemble());
	}

	public void testAssembleAspect() throws Exception {
		S2Container container = new S2ContainerImpl();
		ComponentDefImpl cd = new ComponentDefImpl(ArrayList.class);
		cd.addAspectDef(new AspectDefImpl(new TraceInterceptor()));
		container.register(cd);
		ConstructorAssembler assempbler = new DefaultConstructorAssembler(cd);
		List list = (List) assempbler.assemble();
		list.size();
	}

	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/*
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}
}