package test.org.seasar.framework.container.assembler;

import junit.framework.TestCase;

import org.seasar.framework.aop.interceptors.TraceInterceptor;
import org.seasar.framework.container.ArgDef;
import org.seasar.framework.container.ConstructorAssembler;
import org.seasar.framework.container.IllegalConstructorRuntimeException;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.binding.ManualConstructorAssembler;
import org.seasar.framework.container.impl.ArgDefImpl;
import org.seasar.framework.container.impl.AspectDefImpl;
import org.seasar.framework.container.impl.ComponentDefImpl;
import org.seasar.framework.container.impl.S2ContainerImpl;

/**
 * @author higa
 *
 */
public class ManualConstructorAssemblerTest extends TestCase {

	public ManualConstructorAssemblerTest(String arg0) {
		super(arg0);
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(ManualConstructorAssemblerTest.class);
	}

	public void testAssemble() throws Exception {
		S2Container container = new S2ContainerImpl();
		ComponentDefImpl cd = new ComponentDefImpl(A.class);
		ArgDef argDef = new ArgDefImpl(new B());
		cd.addArgDef(argDef);
		container.register(cd);
		ConstructorAssembler assembler = new ManualConstructorAssembler(cd);
		A a = (A) assembler.assemble();
		assertEquals("1", "B", a.getHogeName());
	}

	public void testAssembleAspect() throws Exception {
		S2Container container = new S2ContainerImpl();
		ComponentDefImpl cd = new ComponentDefImpl(A.class);
		cd.addAspectDef(new AspectDefImpl(new TraceInterceptor()));
		ArgDef argDef = new ArgDefImpl(new B());
		cd.addArgDef(argDef);
		container.register(cd);
		ConstructorAssembler assembler = new ManualConstructorAssembler(cd);
		A a = (A) assembler.assemble();
		assertEquals("1", "B", a.getHogeName());
	}

	public void testAssembleIllegalConstructorArgument() throws Exception {
		S2Container container = new S2ContainerImpl();
		ComponentDefImpl cd = new ComponentDefImpl(A.class);
		ArgDef argDef = new ArgDefImpl();
		argDef.setExpression("hoge");
		cd.addArgDef(argDef);
		container.register(cd);
		ConstructorAssembler assembler = new ManualConstructorAssembler(cd);
		try {
			assembler.assemble();
			fail("1");
		} catch (IllegalConstructorRuntimeException ex) {
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

	public interface Foo {
		public String getHogeName();
	}

	public static class A implements Foo {

		private Hoge hoge_;

		public A(Hoge hoge) {
			hoge_ = hoge;
		}

		public Hoge getHoge() {
			return hoge_;
		}

		public String getHogeName() {
			return hoge_.getName();
		}
	}

	public interface Hoge {

		public String getName();
	}

	public static class B implements Hoge {

		public String getName() {
			return "B";
		}
	}
}