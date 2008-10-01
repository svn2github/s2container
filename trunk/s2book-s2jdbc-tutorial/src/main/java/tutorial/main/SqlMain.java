package tutorial.main;

import java.util.List;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.framework.container.SingletonS2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import tutorial.dto.EmployeeDto;

public class SqlMain {

    private static final String SQL = "select name, salary from employee where salary > ? order by salary";

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
        List<EmployeeDto> results = jdbcManager.selectBySql(
            EmployeeDto.class,
            SQL,
            1000).getResultList();
        for (EmployeeDto e : results) {
            System.out.println(e.name + ", " + e.salary);
        }
    }
}