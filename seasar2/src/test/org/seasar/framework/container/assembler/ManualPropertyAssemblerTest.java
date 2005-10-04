package test.org.seasar.framework.container.assembler;

import junit.framework.TestCase;

import org.seasar.framework.beans.IllegalPropertyRuntimeException;
import org.seasar.framework.beans.PropertyNotFoundRuntimeException;
import org.seasar.framework.container.PropertyAssembler;
import org.seasar.framework.container.PropertyDef;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.binding.ManualPropertyAssembler;
import org.seasar.framework.container.impl.ComponentDefImpl;
import org.seasar.framework.container.impl.PropertyDefImpl;
import org.seasar.framework.container.impl.S2ContainerImpl;

/**
 * @author higa
 *
 */
public class ManualPropertyAssemblerTest extends TestCase {

	public ManualPropertyAssemblerTest(String arg0) {
		super(arg0);
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(ManualPropertyAssemblerTest.class);
	}

	public void testAssemble() throws Exception {
		S2Container container = new S2ContainerImpl();
		ComponentDefImpl cd = new ComponentDefImpl(A.class);
		PropertyDef pd = new PropertyDefImpl("hoge", new B());
		cd.addPropertyDef(pd);
		container.register(cd);
		PropertyAssembler assembler = new ManualPropertyAssembler(cd);
		A a = new A();
		assembler.assemble(a);
		assertEquals("1", "B", a.getHogeName());
	}
	
	public void testAssembleIllegalProperty() throws Exception {
		S2Container container = new S2ContainerImpl();
		ComponentDefImpl cd = new ComponentDefImpl(A.class);
		PropertyDef pd = new PropertyDefImpl("hoge");
		pd.setExpression("b");
		cd.addPropertyDef(pd);
		container.register(cd);
		PropertyAssembler assembler = new ManualPropertyAssembler(cd);
		A a = new A();
		try {
			assembler.assemble(a);
			fail("1");
		} catch (IllegalPropertyRuntimeException ex) {
			System.out.println(ex);
		}
	}
	
	public void testAssembleIllegalProperty2() throws Exception {
		S2Container container = new S2ContainerImpl();
		ComponentDefImpl cd = new ComponentDefImpl(A.class);
		PropertyDef pd = new PropertyDefImpl("abc", "111");
		cd.addPropertyDef(pd);
		container.register(cd);
		PropertyAssembler assembler = new ManualPropertyAssembler(cd);
		A a = new A();
		try {
			assembler.assemble(a);
			fail("1");
		} catch (PropertyNotFoundRuntimeException ex) {
			System.out.println(ex);
		}
	}
	
	public void testAssembleIllegalProperty3() throws Exception {
		S2Container container = new S2ContainerImpl();
		ComponentDefImpl cd = new ComponentDefImpl(B.class);
		PropertyDef pd = new PropertyDefImpl("aaa", "abc");
		cd.addPropertyDef(pd);
		container.register(cd);
		PropertyAssembler assembler = new ManualPropertyAssembler(cd);
		B b = new B();
		try {
			assembler.assemble(b);
			fail("1");
		} catch (IllegalPropertyRuntimeException ex) {
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

		public A() {
		}

		public Hoge getHoge() {
			return hoge_;
		}

		public void setHoge(Hoge hoge) {
			hoge_ = hoge;
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
		
		public void setAaa(int aaa) {
		}
	}
}