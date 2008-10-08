//--------------------------------------------------------------------------
//  Copyright (c) 2004, Drew Davidson and Luke Blanshard
//  All rights reserved.
//
//  Redistribution and use in source and binary forms, with or without
//  modification, are permitted provided that the following conditions are
//  met:
//
//  Redistributions of source code must retain the above copyright notice,
//  this list of conditions and the following disclaimer.
//  Redistributions in binary form must reproduce the above copyright
//  notice, this list of conditions and the following disclaimer in the
//  documentation and/or other materials provided with the distribution.
//  Neither the name of the Drew Davidson nor the names of its contributors
//  may be used to endorse or promote products derived from this software
//  without specific prior written permission.
//
//  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
//  "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
//  LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
//  FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
//  COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
//  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
//  BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS
//  OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
//  AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
//  OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF
//  THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH
//  DAMAGE.
//--------------------------------------------------------------------------
package org.ognl.test;

import junit.framework.TestSuite;
import org.ognl.test.objects.CorrectedObject;

public class ClassMethodTest extends OgnlTestCase
{
    private static CorrectedObject  CORRECTED = new CorrectedObject();

    private static Object[][]       TESTS = {
                                          // Methods on Class
                                        { CORRECTED, "getClass().getName()", CORRECTED.getClass().getName() },
                                        { CORRECTED, "getClass().getInterfaces()", CORRECTED.getClass().getInterfaces() },
                                        { CORRECTED, "getClass().getInterfaces().length", new Integer(CORRECTED.getClass().getInterfaces().length) },
                                        { null, "@System@class.getInterfaces()", System.class.getInterfaces() },
                                        { null, "@Class@class.getName()", Class.class.getName() },
                                        { null, "@java.awt.image.ImageObserver@class.getName()", java.awt.image.ImageObserver.class.getName() },

                                    };

	/*===================================================================
		Public static methods
	  ===================================================================*/
    public static TestSuite suite()
    {
        TestSuite       result = new TestSuite();

        for (int i = 0; i < TESTS.length; i++) {
            result.addTest(new ClassMethodTest((String)TESTS[i][1], TESTS[i][0], (String)TESTS[i][1], TESTS[i][2]));
        }
        return result;
    }

	/*===================================================================
		Constructors
	  ===================================================================*/
	public ClassMethodTest()
	{
	    super();
	}

	public ClassMethodTest(String name)
	{
	    super(name);
	}

    public ClassMethodTest(String name, Object root, String expressionString, Object expectedResult, Object setValue, Object expectedAfterSetResult)
    {
        super(name, root, expressionString, expectedResult, setValue, expectedAfterSetResult);
    }

    public ClassMethodTest(String name, Object root, String expressionString, Object expectedResult, Object setValue)
    {
        super(name, root, expressionString, expectedResult, setValue);
    }

    public ClassMethodTest(String name, Object root, String expressionString, Object expectedResult)
    {
        super(name, root, expressionString, expectedResult);
    }
}
