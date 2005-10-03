package test.org.seasar.framework.beans.impl;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.impl.BeanDescImpl;
import org.seasar.framework.util.MathUtil;

import junit.framework.TestCase;

/**
 * @author higa
 *
 */
public class BeanDescImplTest extends TestCase {

	/**
	 * Constructor for BeanDescFactory.
	 * @param arg0
	 */
	public BeanDescImplTest(String arg0) {
		super(arg0);
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(BeanDescImplTest.class);
	}

	public void testPropertyDesc() throws Exception {
		BeanDesc beanDesc = new BeanDescImpl(MyBean.class);
		assertEquals("1", 3, beanDesc.getPropertyDescSize());
		PropertyDesc propDesc = beanDesc.getPropertyDesc("aaa");
		assertEquals("2", "aaa", propDesc.getPropertyName());
		assertEquals("3", String.class, propDesc.getPropertyType());
		assertNotNull("4", propDesc.getReadMethod());
		assertNull("5", propDesc.getWriteMethod());

		propDesc = beanDesc.getPropertyDesc("CCC");
		assertEquals("6", "CCC", propDesc.getPropertyName());
		assertEquals("7", boolean.class, propDesc.getPropertyType());
		assertNotNull("8", propDesc.getReadMethod());
		assertNull("9", propDesc.getWriteMethod());

		propDesc = beanDesc.getPropertyDesc("eee");
		assertEquals("10", "eee", propDesc.getPropertyName());
		assertEquals("11", String.class, propDesc.getPropertyType());
		assertNotNull("12", propDesc.getReadMethod());
		assertNotNull("13", propDesc.getWriteMethod());
	}

	public void testInvoke() throws Exception {
		BeanDesc beanDesc = new BeanDescImpl(MyBean.class);
		assertEquals(
			"1",
			new Integer(3),
			beanDesc.invoke(
				new MyBean(),
				"add",
				new Object[] { new Integer(1), new Integer(2)}));
	}

	public void testInvoke2() throws Exception {
		BeanDesc beanDesc = new BeanDescImpl(MyBean.class);
		assertEquals(
			"1",
			new Integer(3),
			beanDesc.invoke(
				new MyBean(),
				"add2",
				new Object[] { new BigDecimal(1), new BigDecimal(2)}));
	}

	public void testInvoke3() throws Exception {
		BeanDesc beanDesc = new BeanDescImpl(Math.class);
		assertEquals(
			"1",
			new Integer(3),
			beanDesc.invoke(
				null,
				"max",
				new Object[] { new Integer(1), new Integer(3)}));
		assertEquals(
			"2",
			new Long(3),
			beanDesc.invoke(
				null,
				"max",
				new Object[] { new Long(1), new Long(3)}));
	}
	
	public void testInvoke4() throws Exception {
		BeanDesc beanDesc = new BeanDescImpl(Math.class);
		assertEquals(
			"1",
			new Double(3),
			beanDesc.invoke(
				null,
				"ceil",
				new Object[] { new BigDecimal(2.1)}));
	}
	
	public void testInvoke5() throws Exception {
		BeanDesc beanDesc = new BeanDescImpl(MyBean.class);
		assertEquals(
			"1",
			new Integer("3"),
			beanDesc.invoke(
				new MyBean(),
				"echo",
				new Object[] { new Double("3")}));
	}
	
	public void testInvokeForException() throws Exception {
		BeanDesc beanDesc = new BeanDescImpl(MyBean.class);
		try {
			beanDesc.invoke(new MyBean(), "throwException", null);
			fail("1");
		} catch (IllegalStateException ex) {
			System.out.println(ex);
		}
	}

	public void testNewInstance() throws Exception {
		BeanDesc beanDesc = new BeanDescImpl(Integer.class);
		Integer i = new Integer(10);
		Object[] args = new Object[] { i };
		assertEquals("1", i, beanDesc.newInstance(args));
		Object[] args2 = new Object[] { "10" };
		assertEquals("2", i, beanDesc.newInstance(args2));
	}

	public void testNewInstance2() throws Exception {
		BeanDesc beanDesc = new BeanDescImpl(Integer.class);
		BigDecimal d = new BigDecimal(10);
		Object[] args = new Object[] { d };
		assertEquals("1", new Integer(10), beanDesc.newInstance(args));
	}
	
	public void testGetFields() throws Exception {
		BeanDesc beanDesc = new BeanDescImpl(MyBean.class);
		assertTrue("1", beanDesc.hasField("HOGE"));
        Field field = beanDesc.getField("HOGE");
        assertEquals("2", "hoge2", field.get(null));
        assertTrue("3", beanDesc.hasField("aaa"));
	}
	
	public void testGetMethodNames() throws Exception {
		BeanDesc beanDesc = new BeanDescImpl(getClass());
		String[] names = beanDesc.getMethodNames();
		for (int i = 0; i < names.length; ++i) {
			System.out.println(names[i]);
		}
		assertTrue("1", names.length > 0);
	}
	
	public void testInvalidProperty() throws Exception {
		BeanDesc beanDesc = new BeanDescImpl(MyBean2.class);
		assertEquals("1", false, beanDesc.hasPropertyDesc("aaa"));
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
    
    public static interface MyInterface {
        String HOGE = "hoge";
    }
    
    public static interface MyInterface2 extends MyInterface {
        String HOGE = "hoge2";
    }

	public static class MyBean implements MyInterface2 {
        
        private String aaa;

		public String getAaa() {
			return aaa;
		}

		public String getBbb(Object a) {
			return null;
		}

		public boolean isCCC() {
			return true;
		}

		public Object isDdd() {
			return null;
		}

		public String getEee() {
			return null;
		}

		public void setEee(String eee) {
		}

		public Number add(Number arg1, Number arg2) {
			return MathUtil.add(arg1, arg2);
		}

		public int add2(int arg1, int arg2) {
			return arg1 + arg2;
		}
		
		public Integer echo(Integer arg) {
			return arg;
		}
		
		public void throwException() {
			throw new IllegalStateException("hoge");
		}
	}
	
	public static class MyBean2 {
		public void setAaa(int i) {
		}
		public void setAaa(String s) {
		}
	}

}
