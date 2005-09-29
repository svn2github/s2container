package test.org.seasar.extension.dbcp.impl;

import java.sql.SQLException;

import javax.transaction.Transaction;
import javax.transaction.TransactionManager;
import javax.transaction.xa.XAResource;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.seasar.extension.dbcp.ConnectionWrapper;
import org.seasar.extension.jta.TransactionManagerImpl;
import org.seasar.extension.unit.S2TestCase;

public class ConnectionWrapperImplTest extends S2TestCase {

	private static final String PATH = "connection.dicon";
	private ConnectionWrapper con_;
	private DummyConnectionPool dummyPool_;

	public ConnectionWrapperImplTest(String name) {
		super(name);
	}

	public void testCloseReally() throws Exception {
		con_.closeReally();
		assertEquals("1", true, con_.isClosed());
	}

	public void testClose() throws Exception {
		try {
			con_.close();
			assertEquals("1", true, con_.isClosed());
			assertEquals("2", true, dummyPool_.isCheckIned());
		} finally {
			con_.closeReally();
		}
	}

	public void testRelease() throws Exception {
		try {
			con_.setTransactionIsolation(100);
			fail("1");
		} catch (SQLException ex) {
			System.out.println(ex);
			assertEquals("2", true, dummyPool_.isReleased());
		} finally {
			con_.closeReally();
		}
	}

	public void testInit() throws Exception {
		TransactionManager tm = new TransactionManagerImpl();
		try {
			tm.begin();
			Transaction tx = tm.getTransaction();
			XAResource xares = con_.getXAConnection().getXAResource();
			tx.enlistResource(xares);
			tx.commit();
			con_.close();
			con_.init(true);
		} finally {
			con_.closeReally();
		}
	}

	public void testCleanup() throws Exception {
		TransactionManager tm = new TransactionManagerImpl();
		try {
			tm.begin();
			Transaction tx = tm.getTransaction();
			XAResource xares = con_.getXAConnection().getXAResource();
			tx.enlistResource(xares);
			tx.commit();
			con_.cleanup();
			assertTrue("1", con_.isClosed());
			con_.init(true);
		} finally {
			con_.closeReally();
		}
	}

	public void testRestrictedOperations() throws Exception {
		TransactionManager tm = new TransactionManagerImpl();
		try {
			tm.begin();
			Transaction tx = tm.getTransaction();
			con_.init(false);
			XAResource xares = con_.getXAConnection().getXAResource();
			tx.enlistResource(xares);
			try {
			    con_.setAutoCommit(true);
			    fail("1");
			} catch (SQLException expected) {
			}
			try {
			    con_.commit();
			    fail("2");
			} catch (SQLException expected) {
			}
			try {
			    con_.rollback();
			    fail("3");
			} catch (SQLException expected) {
			}
			try {
			    con_.setSavepoint();
			    fail("4");
			} catch (SQLException expected) {
			}
			try {
			    con_.setSavepoint(null);
			    fail("5");
			} catch (SQLException expected) {
			}
			assertFalse("6", con_.isClosed());
			tx.commit();
			con_.cleanup();
			assertTrue("7", con_.isClosed());
			con_.init(true);
		} finally {
			con_.closeReally();
		}
	}

	protected void setUp() throws Exception {
		include(PATH);
	}

	protected void tearDown() throws Exception {
	}

	public static Test suite() {
		return new TestSuite(ConnectionWrapperImplTest.class);
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.main(
			new String[] { ConnectionWrapperImplTest.class.getName()});
	}
}