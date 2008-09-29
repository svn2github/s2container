package tutorial.main;

import java.util.List;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.framework.beans.util.BeanMap;
import org.seasar.framework.beans.util.Beans;
import org.seasar.framework.container.SingletonS2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import tutorial.entity.Employee;

public class WhereMapMain {

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
        MyForm form = new MyForm();
        form.salary_GE = "1200";
        form.salary_LE = "1800";
        BeanMap map = Beans.createAndCopy(BeanMap.class, form).execute();
        List<Employee> results = jdbcManager
            .from(Employee.class)
            .where(map)
            .orderBy("salary")
            .getResultList();
        for (Employee e : results) {
            System.out.println(e.name + ", " + e.salary);
        }
    }

    public static class MyForm {

        public String salary_GE;

        public String salary_LE;
    }
}