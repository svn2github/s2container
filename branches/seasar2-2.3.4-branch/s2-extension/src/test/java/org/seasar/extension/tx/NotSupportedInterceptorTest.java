/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.extension.tx;

import javax.transaction.Status;
import javax.transaction.TransactionManager;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.seasar.extension.unit.S2TestCase;

/**
 * 
 * @author taichi S. 
 */
public class NotSupportedInterceptorTest extends S2TestCase {
    
    private static final String PATH = "NotSupportedInterceptorTest.dicon";

    private TxBean txBean;
	private ExceptionBean exBean;
	private TransactionManager tm;
    
    public static void main(String[] args) {
        junit.textui.TestRunner.run(NotSupportedInterceptorTest.class);
    }

    public static Test suite() {
		return new TestSuite(NotSupportedInterceptorTest.class);
	}

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        include(PATH);
    }

    public NotSupportedInterceptorTest(String arg0) {
        super(arg0);
    }
    
    public void testInvoke() throws Exception {
        assertEquals("�g�����U�N�V�������L���Ŗ�����",false ,txBean.hasTransaction());
    }
    
    public void testInvokeTx() throws Exception {
        assertEquals("�g�����U�N�V�������L���Ŗ�����",false ,txBean.hasTransaction());
        assertEquals("���ɊJ�n����Ă���g�����U�N�V�������L���ł��鎖", Status.STATUS_ACTIVE, tm.getStatus());
    }
    
    public void testInvokeExceptionTx() throws Exception {
        try {
            exBean.invoke();
            fail("��O��Aspect�ň���ׂ���Ă���\��������B");
        } catch(Exception e) {
            System.out.println(e);
        }
        assertEquals("���ɊJ�n����Ă���g�����U�N�V�������L���ł��鎖", Status.STATUS_ACTIVE, tm.getStatus());
    }

}
