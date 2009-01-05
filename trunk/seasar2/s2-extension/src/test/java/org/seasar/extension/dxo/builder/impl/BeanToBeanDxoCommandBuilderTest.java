/*
 * Copyright 2004-2009 the Seasar Foundation and the Others.
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
package org.seasar.extension.dxo.builder.impl;

import java.util.List;

import org.seasar.framework.unit.S2FrameworkTestCase;

/**
 * @author koichik
 * 
 */
public class BeanToBeanDxoCommandBuilderTest extends S2FrameworkTestCase {

    private BeanToBeanDxoCommandBuilder builder;

    protected void setUp() throws Exception {
        super.setUp();
        include("dxo.dicon");
    }

    /**
     * @throws Exception
     */
    public void testToScalar() throws Exception {
        assertNotNull(builder.createDxoCommand(ToScalarDxo.class,
                ToScalarDxo.class.getMethod("convert",
                        new Class[] { Object.class })));

        assertNull(builder.createDxoCommand(ToScalarDxo.class,
                ToScalarDxo.class.getMethod("convert",
                        new Class[] { Object[].class })));
    }

    /**
     * @throws Exception
     */
    public void testToArray() throws Exception {
        assertNotNull(builder.createDxoCommand(ToArrayDxo.class,
                ToArrayDxo.class.getMethod("convert",
                        new Class[] { Object[].class })));
        assertNotNull(builder.createDxoCommand(ToArrayDxo.class,
                ToArrayDxo.class.getMethod("convert",
                        new Class[] { List.class })));

        assertNull(builder.createDxoCommand(ToArrayDxo.class, ToArrayDxo.class
                .getMethod("convert", new Class[] { Object.class })));
    }

    /**
     * @throws Exception
     */
    public void testToList() throws Exception {
        assertNull(builder.createDxoCommand(ToListDxo.class, ToListDxo.class
                .getMethod("convert", new Class[] { Object[].class })));
        assertNull(builder.createDxoCommand(ToListDxo.class, ToListDxo.class
                .getMethod("convert", new Class[] { List.class })));
        assertNull(builder.createDxoCommand(ToListDxo.class, ToListDxo.class
                .getMethod("convert", new Class[] { Object.class })));
    }

    /**
     *
     */
    public interface ToScalarDxo {
        /**
         * @param src
         * @return
         */
        String convert(Object src); // applicable

        /**
         * @param src
         * @return
         */
        Object convert(Object[] src); // not applicable
    }

    /**
     *
     */
    public interface ToArrayDxo {
        /**
         * @param src
         * @return
         */
        String[] convert(Object[] src); // applicable

        /**
         * @param src
         * @return
         */
        String[] convert(List src); // applicable

        /**
         * @param src
         * @return
         */
        String[] convert(Object src); // not applicable
    }

    /**
     *
     */
    public interface ToListDxo {
        /**
         * @param src
         * @return
         */
        List convert(Object[] src); // not applicable

        /**
         * @param src
         * @return
         */
        List convert(List src); // not applicable

        /**
         * @param src
         * @return
         */
        List convert(Object src); // not applicable
    }
}
