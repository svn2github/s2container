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
package org.seasar.extension.jdbc.it.sqlfile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.persistence.NonUniqueResultException;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.it.entity.Employee;
import org.seasar.extension.unit.S2TestCase;

/**
 * @author taedium
 * 
 */
public class SqlFileSelectTest extends S2TestCase {

    private static String PATH =
        SqlFileSelectTest.class.getName().replace(".", "/") + "_paging.sql";

    private static String PATH2 =
        SqlFileSelectTest.class.getName().replace(".", "/") + "_paging2.sql";

    private JdbcManager jdbcManager;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        include("jdbc.dicon");
    }

    /**
     * 
     * @throws Exception
     */
    public void testBean_paging() throws Exception {
        List<Employee> list =
            jdbcManager.selectBySqlFile(Employee.class, PATH).getResultList();
        assertEquals(14, list.size());
    }

    /**
     * 
     * @throws Exception
     */
    public void testBean_paging_offsetOnly() throws Exception {
        List<Employee> list =
            jdbcManager
                .selectBySqlFile(Employee.class, PATH)
                .offset(3)
                .getResultList();
        assertEquals(11, list.size());
        assertEquals(4, list.get(0).employeeId);
        assertEquals(14, list.get(10).employeeId);
    }

    /**
     * 
     * @throws Exception
     */
    public void testBean_paging_limitOnly() throws Exception {
        List<Employee> list =
            jdbcManager
                .selectBySqlFile(Employee.class, PATH)
                .limit(3)
                .getResultList();
        assertEquals(3, list.size());
        assertEquals(1, list.get(0).employeeId);
        assertEquals(3, list.get(2).employeeId);
    }

    /**
     * 
     * @throws Exception
     */
    public void testBean_paging_offsetZero_limitZero() throws Exception {
        List<Employee> list =
            jdbcManager
                .selectBySqlFile(Employee.class, PATH)
                .offset(0)
                .limit(0)
                .getResultList();
        assertEquals(14, list.size());
    }

    /**
     * 
     * @throws Exception
     */
    public void testBean_paging_offset_limitZero() throws Exception {
        List<Employee> list =
            jdbcManager
                .selectBySqlFile(Employee.class, PATH)
                .offset(3)
                .limit(0)
                .getResultList();
        assertEquals(11, list.size());
        assertEquals(4, list.get(0).employeeId);
        assertEquals(14, list.get(10).employeeId);
    }

    /**
     * 
     * @throws Exception
     */
    public void testBean_paging_offsetZero_limit() throws Exception {
        List<Employee> list =
            jdbcManager
                .selectBySqlFile(Employee.class, PATH)
                .offset(0)
                .limit(3)
                .getResultList();
        assertEquals(3, list.size());
        assertEquals(1, list.get(0).employeeId);
        assertEquals(3, list.get(2).employeeId);
    }

    /**
     * 
     * @throws Exception
     */
    public void testBean_paging_offset_limit() throws Exception {
        List<Employee> list =
            jdbcManager
                .selectBySqlFile(Employee.class, PATH)
                .offset(3)
                .limit(5)
                .getResultList();
        assertEquals(5, list.size());
        assertEquals(4, list.get(0).employeeId);
        assertEquals(8, list.get(4).employeeId);
    }

    /**
     * 
     * @throws Exception
     */
    public void testBean_parameter_none() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_no.sql";
        List<Employee> list =
            jdbcManager.selectBySqlFile(Employee.class, path).getResultList();
        assertEquals(14, list.size());
    }

    /**
     * 
     * @throws Exception
     */
    public void testBean_parameter_simpleType() throws Exception {
        String path =
            getClass().getName().replace(".", "/") + "_simpleType.sql";
        List<Employee> list =
            jdbcManager
                .selectBySqlFile(Employee.class, path, 3)
                .getResultList();
        assertEquals(6, list.size());
        assertEquals("ALLEN", list.get(0).employeeName);
        assertEquals("BLAKE", list.get(1).employeeName);
        assertEquals("JAMES", list.get(2).employeeName);
        assertEquals("MARTIN", list.get(3).employeeName);
        assertEquals("TURNER", list.get(4).employeeName);
        assertEquals("WARD", list.get(5).employeeName);
    }

    /**
     * 
     * @throws Exception
     */
    public void testBean_parameter_dto() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_dto.sql";
        Param param = new Param();
        param.departmentId = 3;
        param.salary = new BigDecimal(1000);
        param.orderBy = "employee_name";
        param.offset = 1;
        param.limit = 3;
        List<Employee> list =
            jdbcManager
                .selectBySqlFile(Employee.class, path, param)
                .getResultList();
        assertEquals(3, list.size());
        assertEquals("BLAKE", list.get(0).employeeName);
        assertEquals("MARTIN", list.get(1).employeeName);
        assertEquals("TURNER", list.get(2).employeeName);
    }

    /**
     * 
     * @throws Exception
     */
    public void testBean_getSingleResult() throws Exception {
        String path =
            getClass().getName().replace(".", "/") + "_getSingleResult.sql";
        Employee employee =
            jdbcManager.selectBySqlFile(Employee.class, path).getSingleResult();
        assertNotNull(employee);
    }

    /**
     * 
     * @throws Exception
     */
    public void testBean_getSingleResult_null() throws Exception {
        String path =
            getClass().getName().replace(".", "/")
                + "_getSingleResult_null.sql";
        Employee employee =
            jdbcManager.selectBySqlFile(Employee.class, path).getSingleResult();
        assertNull(employee);
    }

    /**
     * 
     * @throws Exception
     */
    public void testBean_getSingleResult_NonUniqueResultException()
            throws Exception {
        String path =
            getClass().getName().replace(".", "/")
                + "_getSingleResult_NonUniqueResultException.sql";
        try {
            jdbcManager.selectBySqlFile(Employee.class, path).getSingleResult();
            fail();
        } catch (NonUniqueResultException e) {
        }
    }

    /**
     * 
     * @throws Exception
     */
    public void testMap_paging() throws Exception {
        @SuppressWarnings("unchecked")
        List<Map> list =
            jdbcManager.selectBySqlFile(Map.class, PATH).getResultList();
        assertEquals(14, list.size());
    }

    /**
     * 
     * @throws Exception
     */
    public void testMap_paging_offsetOnly() throws Exception {
        @SuppressWarnings("unchecked")
        List<Map> list =
            jdbcManager
                .selectBySqlFile(Map.class, PATH)
                .offset(3)
                .getResultList();
        assertEquals(11, list.size());
        assertEquals(4, ((Number) list.get(0).get("employeeId")).intValue());
        assertEquals(14, ((Number) list.get(10).get("employeeId")).intValue());
    }

    /**
     * 
     * @throws Exception
     */
    public void testMap_paging_limitOnly() throws Exception {
        @SuppressWarnings("unchecked")
        List<Map> list =
            jdbcManager
                .selectBySqlFile(Map.class, PATH)
                .limit(3)
                .getResultList();
        assertEquals(3, list.size());
        assertEquals(1, ((Number) list.get(0).get("employeeId")).intValue());
        assertEquals(3, ((Number) list.get(2).get("employeeId")).intValue());
    }

    /**
     * 
     * @throws Exception
     */
    public void testMap_paging_offsetZero_limitZero() throws Exception {
        @SuppressWarnings("unchecked")
        List<Map> list =
            jdbcManager
                .selectBySqlFile(Map.class, PATH)
                .offset(0)
                .limit(0)
                .getResultList();
        assertEquals(14, list.size());
    }

    /**
     * 
     * @throws Exception
     */
    public void testMap_paging_offset_limitZero() throws Exception {
        @SuppressWarnings("unchecked")
        List<Map> list =
            jdbcManager
                .selectBySqlFile(Map.class, PATH)
                .offset(3)
                .limit(0)
                .getResultList();
        assertEquals(11, list.size());
        assertEquals(4, ((Number) list.get(0).get("employeeId")).intValue());
        assertEquals(14, ((Number) list.get(10).get("employeeId")).intValue());
    }

    /**
     * 
     * @throws Exception
     */
    public void testMap_paging_offsetZero_limit() throws Exception {
        @SuppressWarnings("unchecked")
        List<Map> list =
            jdbcManager
                .selectBySqlFile(Map.class, PATH)
                .offset(0)
                .limit(3)
                .getResultList();
        assertEquals(3, list.size());
        assertEquals(1, ((Number) list.get(0).get("employeeId")).intValue());
        assertEquals(3, ((Number) list.get(2).get("employeeId")).intValue());
    }

    /**
     * 
     * @throws Exception
     */
    public void testMap_paging_offset_limit() throws Exception {
        @SuppressWarnings("unchecked")
        List<Map> list =
            jdbcManager
                .selectBySqlFile(Map.class, PATH)
                .offset(3)
                .limit(5)
                .getResultList();
        assertEquals(5, list.size());
        assertEquals(4, ((Number) list.get(0).get("employeeId")).intValue());
        assertEquals(8, ((Number) list.get(4).get("employeeId")).intValue());
    }

    /**
     * 
     * @throws Exception
     */
    public void testMap_parameter_none() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_no.sql";
        @SuppressWarnings("unchecked")
        List<Map> list =
            jdbcManager.selectBySqlFile(Map.class, path).getResultList();
        assertEquals(14, list.size());
    }

    /**
     * 
     * @throws Exception
     */
    public void testMap_parameter_simpleType() throws Exception {
        String path =
            getClass().getName().replace(".", "/") + "_simpleType.sql";
        @SuppressWarnings("unchecked")
        List<Map> list =
            jdbcManager.selectBySqlFile(Map.class, path, 3).getResultList();
        assertEquals(6, list.size());
        assertEquals("ALLEN", list.get(0).get("employeeName"));
        assertEquals("BLAKE", list.get(1).get("employeeName"));
        assertEquals("JAMES", list.get(2).get("employeeName"));
        assertEquals("MARTIN", list.get(3).get("employeeName"));
        assertEquals("TURNER", list.get(4).get("employeeName"));
        assertEquals("WARD", list.get(5).get("employeeName"));
    }

    /**
     * 
     * @throws Exception
     */
    public void testMap_parameter_dto() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_dto.sql";
        Param param = new Param();
        param.departmentId = 3;
        param.salary = new BigDecimal(1000);
        param.orderBy = "employee_name";
        param.offset = 1;
        param.limit = 3;
        @SuppressWarnings("unchecked")
        List<Map> list =
            jdbcManager.selectBySqlFile(Map.class, path, param).getResultList();
        assertEquals(3, list.size());
        assertEquals("BLAKE", list.get(0).get("employeeName"));
        assertEquals("MARTIN", list.get(1).get("employeeName"));
        assertEquals("TURNER", list.get(2).get("employeeName"));
    }

    /**
     * 
     * @throws Exception
     */
    public void testMap_getSingleResult() throws Exception {
        String path =
            getClass().getName().replace(".", "/") + "_getSingleResult.sql";
        Map<?, ?> employee =
            jdbcManager.selectBySqlFile(Map.class, path).getSingleResult();
        assertNotNull(employee);
    }

    /**
     * 
     * @throws Exception
     */
    public void testMap_getSingleResult_null() throws Exception {
        String path =
            getClass().getName().replace(".", "/")
                + "_getSingleResult_null.sql";
        Map<?, ?> employee =
            jdbcManager.selectBySqlFile(Map.class, path).getSingleResult();
        assertNull(employee);
    }

    /**
     * 
     * @throws Exception
     */
    public void testMap_getSingleResult_NonUniqueResultException()
            throws Exception {
        String path =
            getClass().getName().replace(".", "/")
                + "_getSingleResult_NonUniqueResultException.sql";
        try {
            jdbcManager.selectBySqlFile(Map.class, path).getSingleResult();
            fail();
        } catch (NonUniqueResultException e) {
        }
    }

    /**
     * 
     * @throws Exception
     */
    public void testObject_paging() throws Exception {
        List<Integer> list =
            jdbcManager.selectBySqlFile(Integer.class, PATH2).getResultList();
        assertEquals(14, list.size());
    }

    /**
     * 
     * @throws Exception
     */
    public void testObject_paging_offsetOnly() throws Exception {
        List<Integer> list =
            jdbcManager
                .selectBySqlFile(Integer.class, PATH2)
                .offset(3)
                .getResultList();
        assertEquals(11, list.size());
        assertEquals(4, list.get(0).intValue());
        assertEquals(14, list.get(10).intValue());
    }

    /**
     * 
     * @throws Exception
     */
    public void testObject_paging_limitOnly() throws Exception {
        List<Integer> list =
            jdbcManager
                .selectBySqlFile(Integer.class, PATH2)
                .limit(3)
                .getResultList();
        assertEquals(3, list.size());
        assertEquals(1, list.get(0).intValue());
        assertEquals(3, list.get(2).intValue());
    }

    /**
     * 
     * @throws Exception
     */
    public void testObject_paging_offsetZero_limitZero() throws Exception {
        List<Integer> list =
            jdbcManager
                .selectBySqlFile(Integer.class, PATH2)
                .offset(0)
                .limit(0)
                .getResultList();
        assertEquals(14, list.size());
    }

    /**
     * 
     * @throws Exception
     */
    public void testObject_paging_offset_limitZero() throws Exception {
        List<Integer> list =
            jdbcManager
                .selectBySqlFile(Integer.class, PATH2)
                .offset(3)
                .limit(0)
                .getResultList();
        assertEquals(11, list.size());
        assertEquals(4, list.get(0).intValue());
        assertEquals(14, list.get(10).intValue());
    }

    /**
     * 
     * @throws Exception
     */
    public void testObject_paging_offsetZero_limit() throws Exception {
        List<Integer> list =
            jdbcManager
                .selectBySqlFile(Integer.class, PATH2)
                .offset(0)
                .limit(3)
                .getResultList();
        assertEquals(3, list.size());
        assertEquals(1, list.get(0).intValue());
        assertEquals(3, list.get(2).intValue());
    }

    /**
     * 
     * @throws Exception
     */
    public void testObject_paging_offset_limit() throws Exception {
        List<Integer> list =
            jdbcManager
                .selectBySqlFile(Integer.class, PATH2)
                .offset(3)
                .limit(5)
                .getResultList();
        assertEquals(5, list.size());
        assertEquals(4, list.get(0).intValue());
        assertEquals(8, list.get(4).intValue());
    }

    /**
     * 
     * @throws Exception
     */
    public void testObject_parameter_none() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_no2.sql";
        List<Integer> list =
            jdbcManager.selectBySqlFile(Integer.class, path).getResultList();
        assertEquals(14, list.size());
    }

    /**
     * 
     * @throws Exception
     */
    public void testObject_parameter_simpleType() throws Exception {
        String path =
            getClass().getName().replace(".", "/") + "_simpleType2.sql";
        List<Integer> list =
            jdbcManager.selectBySqlFile(Integer.class, path, 3).getResultList();
        assertEquals(6, list.size());
        assertEquals(2, list.get(0).intValue());
        assertEquals(6, list.get(1).intValue());
        assertEquals(12, list.get(2).intValue());
        assertEquals(5, list.get(3).intValue());
        assertEquals(10, list.get(4).intValue());
        assertEquals(3, list.get(5).intValue());
    }

    /**
     * 
     * @throws Exception
     */
    public void testObject_parameter_dto() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_dto2.sql";
        Param param = new Param();
        param.departmentId = 3;
        param.salary = new BigDecimal(1000);
        param.orderBy = "employee_name";
        param.offset = 1;
        param.limit = 3;
        List<Integer> list =
            jdbcManager
                .selectBySqlFile(Integer.class, path, param)
                .getResultList();
        assertEquals(3, list.size());
        assertEquals(6, list.get(0).intValue());
        assertEquals(5, list.get(1).intValue());
        assertEquals(10, list.get(2).intValue());
    }

    /**
     * 
     * @throws Exception
     */
    public void testObject_getSingleResult() throws Exception {
        String path =
            getClass().getName().replace(".", "/") + "_getSingleResult2.sql";
        Integer employeeId =
            jdbcManager.selectBySqlFile(Integer.class, path).getSingleResult();
        assertNotNull(employeeId);
    }

    /**
     * 
     * @throws Exception
     */
    public void testBObject_getSingleResult_null() throws Exception {
        String path =
            getClass().getName().replace(".", "/")
                + "_getSingleResult_null2.sql";
        Integer employeeId =
            jdbcManager.selectBySqlFile(Integer.class, path).getSingleResult();
        assertNull(employeeId);
    }

    /**
     * 
     * @throws Exception
     */
    public void testObject_getSingleResult_NonUniqueResultException()
            throws Exception {
        String path =
            getClass().getName().replace(".", "/")
                + "_getSingleResult_NonUniqueResultException2.sql";
        try {
            jdbcManager.selectBySqlFile(Integer.class, path).getSingleResult();
            fail();
        } catch (NonUniqueResultException e) {
        }
    }

    /**
     * 
     * @author taedium
     */
    public static class Param {

        /** */
        public int departmentId;

        /** */
        public BigDecimal salary;

        /** */
        public String orderBy;

        /** */
        public int offset;

        /** */
        public int limit;
    }

}
