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

import java.util.*;
import junit.framework.TestSuite;
import org.ognl.test.objects.Root;

public class MapCreationTest extends OgnlTestCase
{
    private static Root             ROOT = new Root();
    private static Map              fooBarMap1;
    private static Map              fooBarMap2;
    private static Map              fooBarMap3;
    private static Map              fooBarMap4;
    private static Map              fooBarMap5;

    static
    {
        fooBarMap1 = new HashMap();
        fooBarMap1.put("foo", "bar");
        fooBarMap2 = new HashMap();
        fooBarMap2.put("foo", "bar");
        fooBarMap2.put("bar", "baz");
        fooBarMap3 = new HashMap();
        fooBarMap3.put("foo", null);
        fooBarMap3.put("bar", "baz");
        fooBarMap4 = new LinkedHashMap();
        fooBarMap4.put("foo", "bar");
        fooBarMap4.put("bar", "baz");
        fooBarMap5 = new TreeMap();
        fooBarMap5.put("foo", "bar");
        fooBarMap5.put("bar", "baz");
    }

    private static Object[][]       TESTS = {
                                          // Map creation
                                        { ROOT, "#{ \"foo\" : \"bar\" }",                                               fooBarMap1 },
                                        { ROOT, "#{ \"foo\" : \"bar\", \"bar\" : \"baz\"  }",                           fooBarMap2 },
                                        { ROOT, "#{ \"foo\", \"bar\" : \"baz\"  }",                                     fooBarMap3 },
                                        { ROOT, "#@java.util.LinkedHashMap@{ \"foo\" : \"bar\", \"bar\" : \"baz\"  }",  fooBarMap4 },
                                        { ROOT, "#@java.util.TreeMap@{ \"foo\" : \"bar\", \"bar\" : \"baz\"  }",        fooBarMap5 },
                                    };

	/*===================================================================
		Public static methods
	  ===================================================================*/
    public static TestSuite suite()
    {
        TestSuite       result = new TestSuite();

        for (int i = 0; i < TESTS.length; i++) {
            if (TESTS[i].length == 3) {
                result.addTest(new MapCreationTest((String)TESTS[i][1], TESTS[i][0], (String)TESTS[i][1], TESTS[i][2]));
            } else {
                if (TESTS[i].length == 4) {
                    result.addTest(new MapCreationTest((String)TESTS[i][1], TESTS[i][0], (String)TESTS[i][1], TESTS[i][2], TESTS[i][3]));
                } else {
                    if (TESTS[i].length == 5) {
                        result.addTest(new MapCreationTest((String)TESTS[i][1], TESTS[i][0], (String)TESTS[i][1], TESTS[i][2], TESTS[i][3], TESTS[i][4]));
                    } else {
                        throw new RuntimeException("don't understand TEST format");
                    }
                }
            }
        }
        return result;
    }

	/*===================================================================
		Constructors
	  ===================================================================*/
	public MapCreationTest()
	{
	    super();
	}

	public MapCreationTest(String name)
	{
	    super(name);
	}

    public MapCreationTest(String name, Object root, String expressionString, Object expectedResult, Object setValue, Object expectedAfterSetResult)
    {
        super(name, root, expressionString, expectedResult, setValue, expectedAfterSetResult);
    }

    public MapCreationTest(String name, Object root, String expressionString, Object expectedResult, Object setValue)
    {
        super(name, root, expressionString, expectedResult, setValue);
    }

    public MapCreationTest(String name, Object root, String expressionString, Object expectedResult)
    {
        super(name, root, expressionString, expectedResult);
    }
}
