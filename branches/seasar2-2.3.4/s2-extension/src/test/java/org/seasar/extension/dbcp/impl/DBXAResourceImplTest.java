package org.seasar.extension.dbcp.impl;

import java.sql.Connection;

import javax.sql.XAConnection;
import javax.sql.XADataSource;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.seasar.extension.dbcp.impl.DBXAResourceImpl;
import org.seasar.extension.jta.xa.XidImpl;
import org.seasar.extension.unit.S2TestCase;

public class DBXAResourceImplTest extends S2TestCase {

	private static final String PATH = "connection2.dicon";
	private XADataSource xads2_;
	private XAConnection xacon_;
	private Connection con_;
	private XAResource xares_;
	
    public DBXAResourceImplTest(String name) {
        super(name);
    }

    public void testDoBegen() throws Exception {
    	xares_.start(new XidImpl(), XAResource.TMNOFLAGS);
    	assertEquals("1", false, con_.getAutoCommit());
    }
    
	public void testDoCommit() throws Exception {
		Xid xid = new XidImpl();
		xares_.start(xid, XAResource.TMNOFLAGS);
		xares_.end(xid, XAResource.TMSUCCESS);
		xares_.commit(xid, true);
		assertEquals("1", true, con_.getAutoCommit());
	}
	
	public void testDoRollback() throws Exception {
		Xid xid = new XidImpl();
		xares_.start(xid, XAResource.TMNOFLAGS);
		xares_.end(xid, XAResource.TMFAIL);
		xares_.rollback(xid);
		assertEquals("1", true, con_.getAutoCommit());
	}

    protected void setUp() throws Exception {
    	include(PATH);
    	xads2_ = (XADataSource) getComponent("xads");
    	xacon_ = xads2_.getXAConnection();
    	con_ = xacon_.getConnection();
    	xares_ = new DBXAResourceImpl(con_);
    }

    protected void tearDown() throws Exception {
    	xacon_.close();
    }

    public static Test suite ( ) {
        return new TestSuite(DBXAResourceImplTest.class);
    }

    public static void main (String[] args) {
        junit.textui.TestRunner.main(new String[]{DBXAResourceImplTest.class.getName()});
    }
}