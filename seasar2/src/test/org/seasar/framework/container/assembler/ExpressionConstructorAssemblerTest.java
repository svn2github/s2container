package test.org.seasar.framework.container.assembler;

import junit.framework.TestCase;

import org.seasar.framework.container.ClassUnmatchRuntimeException;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.assembler.ExpressionConstructorAssembler;
import org.seasar.framework.container.impl.ComponentDefImpl;
import org.seasar.framework.container.impl.S2ContainerImpl;

/**
 * @author higa
 *
 */
public class ExpressionConstructorAssemblerTest extends TestCase {

	public ExpressionConstructorAssemblerTest(String arg0) {
		super(arg0);
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(ExpressionConstructorAssemblerTest.class);
	}

	public void testAssemble() throws Exception {
		S2Container container = new S2ContainerImpl();
		ComponentDefImpl cd = new ComponentDefImpl(Object.class, "obj");
		container.register(cd);
		ComponentDefImpl cd2 = new ComponentDefImpl();
		cd2.setExpression("obj.hashCode()");
		container.register(cd2);
		ExpressionConstructorAssembler assembler =
			new ExpressionConstructorAssembler(cd2);
		Integer myInt = (Integer) assembler.assemble();
		assertNotNull("1", myInt);
	}
	
	public void testAssembleForNull() throws Exception {
		S2Container container = new S2ContainerImpl();
		ComponentDefImpl cd = new ComponentDefImpl(Object.class, "obj");
		cd.setExpression("null");
		container.register(cd);
		ExpressionConstructorAssembler assembler =
			new ExpressionConstructorAssembler(cd);
		try {
			assembler.assemble();
			fail("1");
		} catch (ClassUnmatchRuntimeException ex) {
			System.out.println(ex);
		}
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