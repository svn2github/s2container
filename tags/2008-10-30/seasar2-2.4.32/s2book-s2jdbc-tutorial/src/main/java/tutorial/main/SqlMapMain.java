package tutorial.main;

import java.util.List;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.framework.beans.util.BeanMap;
import org.seasar.framework.container.SingletonS2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

public class SqlMapMain {

    private static final String SQL = "select id, name from employee";

    public static void main(String[] args) {
        SingletonS2ContainerFactory.init();
        try {
            JdbcManager jdbcManager = SingletonS2Container
                .getComponent(JdbcManager.class);
            test(jdbcManager);
        } finally {
            SingletonS2ContainerFactory.destroy();
        }
    }

    public static void test(JdbcManager jdbcManager) {
        List<BeanMap> results = jdbcManager
            .selectBySql(BeanMap.class, SQL)
            .getResultList();
        for (BeanMap e : results) {
            System.out.println(e.get("id") + ", " + e.get("name"));
        }
    }
}