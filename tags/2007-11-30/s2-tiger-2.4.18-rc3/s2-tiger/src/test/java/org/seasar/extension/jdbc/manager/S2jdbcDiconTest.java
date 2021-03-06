package org.seasar.extension.jdbc.manager;

import java.util.List;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.entity.Emp;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.beans.util.BeanMap;

/**
 * @author higa
 * 
 */
public class S2jdbcDiconTest extends S2TestCase {

    private JdbcManager jdbcManager;

    @Override
    protected void setUp() {
        include("s2jdbc.dicon");
    }

    /**
     * @throws Exception
     */
    public void testGetResultList() throws Exception {
        assertTrue(jdbcManager.from(Emp.class).getResultList().size() > 1);
    }

    /**
     * @throws Exception
     */
    public void testGetResultList_map() throws Exception {
        List<BeanMap> results = jdbcManager.selectBySql(BeanMap.class,
                "select * from emp").getResultList();
        assertNotNull(results);

    }
}
