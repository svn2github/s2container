package tutorial.main;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.framework.container.SingletonS2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import tutorial.entity.Employee;

public class GetSingleResultMain {

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
        Employee result = jdbcManager
            .from(Employee.class)
            .id(1)
            .getSingleResult();
        System.out.println(result.name);
    }
}