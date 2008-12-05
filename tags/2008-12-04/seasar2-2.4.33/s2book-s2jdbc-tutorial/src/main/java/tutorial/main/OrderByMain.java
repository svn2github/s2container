package tutorial.main;

import java.util.List;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.framework.container.SingletonS2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import tutorial.entity.Employee;

public class OrderByMain {

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
        List<Employee> results = jdbcManager
            .from(Employee.class)
            .leftOuterJoin("department")
            .orderBy("department.name, name desc")
            .getResultList();
        for (Employee e : results) {
            System.out.println(e.department.name + ", " + e.name);
        }
    }
}