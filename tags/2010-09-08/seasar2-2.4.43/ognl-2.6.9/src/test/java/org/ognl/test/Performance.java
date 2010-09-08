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

import java.lang.reflect.*;
import java.text.*;
import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;
import ognl.OgnlRuntime;
import ognl.SimpleNode;
import org.ognl.test.objects.Bean1;

public class Performance extends Object
{
    private static int              MAX_ITERATIONS = -1;
    private static boolean          ITERATIONS_MODE;
    private static long             MAX_TIME = -1L;
    private static boolean          TIME_MODE;
    private static NumberFormat     FACTOR_FORMAT = new DecimalFormat("0.0");

    private String                  name;
    private OgnlContext             context = (OgnlContext)Ognl.createDefaultContext(null);
    private Bean1                   root = new Bean1();
    private SimpleNode              expression;
    private Method                  method;
    private int                     iterations;
    private long                    t0;
    private long                    t1;

    /*===================================================================
        Private static classes
      ===================================================================*/
    private static class Results
    {
        int         iterations;
        long        time;

        public Results(int iterations, long time)
        {
            super();
            this.iterations = iterations;
            this.time = time;
        }

        public float getFactor(Results otherResults)
        {
            if (TIME_MODE) {
                return Math.max((float)otherResults.iterations, (float)iterations) / Math.min((float)otherResults.iterations, (float)iterations);
            }
            return Math.max((float)otherResults.time, (float)time) / Math.min((float)otherResults.time, (float)time);
        }
    }

    /*===================================================================
        Public static methods
      ===================================================================*/
    public static void main(String[] args)
    {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-time")) {
                TIME_MODE = true;
                MAX_TIME = Long.parseLong(args[++i]);
            } else if (args[i].equals("-iterations")) {
                ITERATIONS_MODE = true;
                MAX_ITERATIONS = Integer.parseInt(args[++i]);
            }
        }
        if (!TIME_MODE && !ITERATIONS_MODE) {
            TIME_MODE = true;
            MAX_TIME = 1000;
        }
        OgnlRuntime.setPropertyAccessor(Object.class, new CompilingPropertyAccessor());
        try {
            Performance[]   tests = new Performance[]
                                        {
                                            new Performance("Constant",
                                                            "100 + 20 * 5",
                                                            "testConstantExpression"),
                                            new Performance("Single Property",
                                                            "bean2",
                                                            "testSinglePropertyExpression"),
                                            new Performance("Property Navigation",
                                                            "bean2.bean3.value",
                                                            "testPropertyNavigationExpression"),
                                            new Performance("Property Navigation and Comparison",
                                                            "bean2.bean3.value <= 24",
                                                            "testPropertyNavigationAndComparisonExpression"),
                                            new Performance("Property Navigation with Indexed Access",
                                                            "bean2.bean3.indexedValue[25]",
                                                            "testIndexedPropertyNavigationExpression"),
                                            new Performance("Property Navigation with Map Access",
                                                            "bean2.bean3.map['foo']",
                                                            "testPropertyNavigationWithMapExpression"),
                                        };

            for (int i = 0; i < tests.length; i++) {
                Performance     perf = tests[i];

                try {
                    Results         javaResults = perf.testJava(),
                                    interpretedResults = perf.testExpression(false),
                                    compiledResults = perf.testExpression(true);

                    System.out.println(perf.getName() + ": " + perf.getExpression().toString());
                    System.out.println("       java: " + javaResults.iterations + " iterations in " + javaResults.time + " ms");
                    System.out.println("   compiled: " + compiledResults.iterations + " iterations in " + compiledResults.time + " ms (" + FACTOR_FORMAT.format(compiledResults.getFactor(javaResults)) + " times slower than java)");
                    System.out.println("interpreted: " + interpretedResults.iterations + " iterations in " + interpretedResults.time + " ms (" + FACTOR_FORMAT.format(interpretedResults.getFactor(javaResults)) + " times slower than java)");
                    System.out.println();
                } catch (OgnlException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /*===================================================================
        Constructors
      ===================================================================*/
    public Performance(String name, String expressionString, String javaMethodName) throws OgnlException
    {
        super();
        this.name = name;
        expression = (SimpleNode)Ognl.parseExpression(expressionString);
        try {
            method = getClass().getMethod(javaMethodName, new Class[] {});
        } catch (Exception ex) {
            throw new OgnlException("java method not found", ex);
        }
    }

    /*===================================================================
        Protected methods
      ===================================================================*/
    protected void startTest()
    {
        iterations = 0;
        t0 = t1 = System.currentTimeMillis();
    }

    protected Results endTest()
    {
        return new Results(iterations, t1 - t0);
    }

    protected boolean done()
    {
        iterations++;
        t1 = System.currentTimeMillis();
        if (TIME_MODE) {
            return (t1 - t0) >= MAX_TIME;
        } else {
            if (ITERATIONS_MODE) {
                return iterations >= MAX_ITERATIONS;
            } else {
                throw new RuntimeException("no maximums specified");
            }
        }
    }

    /*===================================================================
        Public methods
      ===================================================================*/
    public String getName()
    {
        return name;
    }

    public SimpleNode getExpression()
    {
        return expression;
    }

    public Results testExpression(boolean compiled) throws OgnlException
    {
        if (compiled) {
            context.put("_compile", Boolean.TRUE);
        } else {
            context.remove("_compile");
        }
        Ognl.getValue(expression, context, root);
        startTest();
        do {
            Ognl.getValue(expression, context, root);
        } while (!done());
        return endTest();
    }

    public Results testJava() throws OgnlException
    {
        try {
            return (Results)method.invoke(this, new Object[] {});
        } catch (Exception ex) {
            throw new OgnlException("invoking java method '" + method.getName() + "'", ex);
        }
    }

    public Results testConstantExpression() throws OgnlException
    {
        startTest();
        do {
            int     result = 100 + 20 * 5;
        } while (!done());
        return endTest();
    }

    public Results testSinglePropertyExpression() throws OgnlException
    {
        startTest();
        do {
            root.getBean2();
        } while (!done());
        return endTest();
    }

    public Results testPropertyNavigationExpression() throws OgnlException
    {
        startTest();
        do {
            root.getBean2().getBean3().getValue();
        } while (!done());
        return endTest();
    }

    public Results testPropertyNavigationAndComparisonExpression() throws OgnlException
    {
        startTest();
        do {
            boolean     result = root.getBean2().getBean3().getValue() < 24;
        } while (!done());
        return endTest();
    }

    public Results testIndexedPropertyNavigationExpression() throws OgnlException
    {
        startTest();
        do {
            root.getBean2().getBean3().getIndexedValue(25);
        } while (!done());
        return endTest();
    }

    public Results testPropertyNavigationWithMapExpression() throws OgnlException
    {
        startTest();
        do {
            root.getBean2().getBean3().getMap().get("foo");
        } while (!done());
        return endTest();
    }
}
