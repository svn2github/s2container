package tutorial.main;

import javax.transaction.UserTransaction;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.framework.container.SingletonS2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import tutorial.entity.Employee;

public class DeleteMain {

    public static void main(String[] args) throws Exception {
        SingletonS2ContainerFactory.init();
        try {
            JdbcManager jdbcManager = SingletonS2Container
                .getComponent(JdbcManager.class);
            UserTransaction ut = SingletonS2Container
                .getComponent(UserTransaction.class);
            ut.begin();
            try {
                test(jdbcManager);
            } finally {
                ut.rollback();
            }
        } finally {
            SingletonS2ContainerFactory.destroy();
        }
    }

    public static void test(JdbcManager jdbcManager) {
        Employee emp = jdbcManager.from(Employee.class).id(1).getSingleResult();
        jdbcManager.delete(emp).execute();
        emp = jdbcManager.from(Employee.class).id(1).getSingleResult();
        System.out.println(emp);
    }
}