package test.org.seasar.framework.beans.factory;

import junit.framework.TestCase;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;

/**
 * @author higa
 *
 * ���̐������ꂽ�R�����g�̑}�������e���v���[�g��ύX���邽��
 * �E�B���h�E > �ݒ� > Java > �R�[�h���� > �R�[�h�ƃR�����g
 */
public class BeanDescFactoryTest extends TestCase {

	/**
	 * Constructor for BeanDescFactory.
	 * @param arg0
	 */
	public BeanDescFactoryTest(String arg0) {
		super(arg0);
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(BeanDescFactoryTest.class);
	}

	public void testGetBeanDesc() throws Exception {
		BeanDesc beanDesc = BeanDescFactory.getBeanDesc(MyBean.class);
		assertSame("1", beanDesc, BeanDescFactory.getBeanDesc(MyBean.class));
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

	public static class MyBean {

		public String getAaa() {
			return null;
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
	}

}
