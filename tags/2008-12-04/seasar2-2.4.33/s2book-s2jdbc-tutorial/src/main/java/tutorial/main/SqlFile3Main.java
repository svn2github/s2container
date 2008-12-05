package tutorial.main;

import java.util.List;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.framework.container.SingletonS2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import tutorial.dto.EmployeeDto;
import tutorial.dto.SqlFileDto;

public class SqlFile3Main {

    private static final String SQL_FILE = "META-INF/sql/tutorial/entity/Employee/select3.sql";

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
        SqlFileDto dto = new SqlFileDto();
        dto.minSalary = 1200;
        dto.maxSalary = null;
        List<EmployeeDto> results = jdbcManager.selectBySqlFile(
            EmployeeDto.class,
            SQL_FILE,
            dto).getResultList();
        for (EmployeeDto e : results) {
            System.out.println(e.name + ", " + e.salary);
        }
    }
}