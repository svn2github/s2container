package test.org.seasar.framework.container.deployer;

import junit.framework.TestCase;

import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.ContainerConstants;
import org.seasar.framework.container.deployer.ComponentDeployer;
import org.seasar.framework.container.deployer.ComponentDeployerFactory;
import org.seasar.framework.container.deployer.OuterComponentDeployer;
import org.seasar.framework.container.deployer.PrototypeComponentDeployer;
import org.seasar.framework.container.deployer.RequestComponentDeployer;
import org.seasar.framework.container.deployer.SessionComponentDeployer;
import org.seasar.framework.container.deployer.SingletonComponentDeployer;
import org.seasar.framework.container.impl.ComponentDefImpl;

/**
 * @author koichik
 */
public class ComponentDeployerFactoryTest extends TestCase {
    public static void main(String[] args) {
        junit.textui.TestRunner.run(ComponentDeployerFactoryTest.class);
    }

    public ComponentDeployerFactoryTest(String name) {
        super(name);
    }

    public void testSingleton() throws Exception {
        ComponentDef cd = new ComponentDefImpl();
        cd.setInstanceMode(ContainerConstants.INSTANCE_SINGLETON);
        ComponentDeployer deployer = ComponentDeployerFactory.create(cd);
        assertTrue("1", deployer instanceof SingletonComponentDeployer);
    }

    public void testPrototype() throws Exception {
        ComponentDef cd = new ComponentDefImpl();
        cd.setInstanceMode(ContainerConstants.INSTANCE_PROTOTYPE);
        ComponentDeployer deployer = ComponentDeployerFactory.create(cd);
        assertTrue("1", deployer instanceof PrototypeComponentDeployer);
    }

    public void testRequest() throws Exception {
        ComponentDef cd = new ComponentDefImpl();
        cd.setInstanceMode(ContainerConstants.INSTANCE_REQUEST);
        ComponentDeployer deployer = ComponentDeployerFactory.create(cd);
        assertTrue("1", deployer instanceof RequestComponentDeployer);
    }

    public void testSession() throws Exception {
        ComponentDef cd = new ComponentDefImpl();
        cd.setInstanceMode(ContainerConstants.INSTANCE_SESSION);
        ComponentDeployer deployer = ComponentDeployerFactory.create(cd);
        assertTrue("1", deployer instanceof SessionComponentDeployer);
    }

    public void testOuter() throws Exception {
        ComponentDef cd = new ComponentDefImpl();
        cd.setInstanceMode(ContainerConstants.INSTANCE_OUTER);
        ComponentDeployer deployer = ComponentDeployerFactory.create(cd);
        assertTrue("1", deployer instanceof OuterComponentDeployer);
    }
}
