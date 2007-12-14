/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
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
package org.seasar.extension.jdbc.parameter;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import junit.framework.TestCase;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;

/**
 * @author taedium
 * 
 */
public class ParameterTest extends TestCase {

    /**
     * 
     * @throws Exception
     */
    public void testWrapIfNecessary() throws Exception {
        MyDto dto = new MyDto();
        dto.aaa = "hoge";
        dto.bbb = "foo";
        dto.ccc = new SimpleDateFormat("HH:mm:dd").parse("12:11:10");
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(MyDto.class);

        PropertyDesc pd = beanDesc.getPropertyDesc("aaa");
        Object value = Parameter.wrapIfNecessary(pd, pd.getValue(dto));
        assertEquals(String.class, value.getClass());

        pd = beanDesc.getPropertyDesc("bbb");
        value = Parameter.wrapIfNecessary(pd, pd.getValue(dto));
        assertEquals(LobParameter.class, value.getClass());

        pd = beanDesc.getPropertyDesc("ccc");
        value = Parameter.wrapIfNecessary(pd, pd.getValue(dto));
        assertEquals(TemporalParameter.class, value.getClass());

        pd = beanDesc.getPropertyDesc("ddd");
        value = Parameter.wrapIfNecessary(pd, pd.getValue(dto));
        assertEquals(String.class, value.getClass());
    }

    /**
     * 
     * @author taedium
     * 
     */
    public static class MyDto {

        /** */
        public String aaa;

        /** */
        @Lob
        public String bbb;

        /** */
        @Temporal(TemporalType.TIME)
        public Date ccc;

        /**
         * 
         * @return
         */
        public String getDdd() {
            return aaa;
        }
    }
}
