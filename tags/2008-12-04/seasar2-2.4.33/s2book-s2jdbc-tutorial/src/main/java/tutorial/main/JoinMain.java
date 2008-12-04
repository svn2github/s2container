package tutorial.main;

import java.util.List;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.framework.container.SingletonS2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import tutorial.entity.Department;
import tutorial.entity.Employee;

public class JoinMain {

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
        List<Department> results = jdbcManager
            .from(Department.class)
            .leftOuterJoin("employeeList")
            .innerJoin("employeeList.address")
            .getResultList();
        for (Department d : results) {
            System.out.println(d.name);
            for (Employee e : d.employeeList) {
                System.out.println("  " + e.name + ", " + e.address.name);
            }
        }
    }
}