package test.examples.autonumber;

import org.seasar.framework.aop.interceptors.MockInterceptor;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.impl.S2ContainerImpl;

import examples.autonumber.AutoNumber;
import examples.autonumber.AutoNumberDao;
import examples.autonumber.AutoNumberImpl;
import examples.autonumber.NumberKeyNotFoundRuntimeException;
import junit.framework.TestCase;

public class AutoNumberImplTest extends TestCase {

	public AutoNumberImplTest(String arg0) {
		super(arg0);
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(AutoNumberImplTest.class);
	}

	public void testNext() {
		S2Container container = new S2ContainerImpl();
		container.register(AutoNumberImpl.class);
		MockInterceptor mi = new MockInterceptor();
		mi.setReturnValue("increment", new Integer(1));
		mi.setReturnValue("getCurrentNumber", new Integer(1));
		AutoNumberDao dao = (AutoNumberDao) mi.createProxy(AutoNumberDao.class);
		container.register(dao);
		AutoNumber autoNumber = (AutoNumber) container.getComponent(AutoNumber.class);
		assertEquals("1", 1, autoNumber.next(1));
	}

	public void testNextForNumberKeyNotFound() {
		S2Container container = new S2ContainerImpl();
		container.register(AutoNumberImpl.class);
		MockInterceptor mi = new MockInterceptor();
		mi.setReturnValue(new Integer(0));
		AutoNumberDao dao = (AutoNumberDao) mi.createProxy(AutoNumberDao.class);
		container.register(dao);
		AutoNumber autoNumber = (AutoNumber) container.getComponent(AutoNumber.class);
		try {
			autoNumber.next(-1);
			fail("1");
		} catch (NumberKeyNotFoundRuntimeException ex) {
			assertEquals("2", -1, ex.getNumberKey());
		}
	}
}
