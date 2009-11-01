package spring.learning;

import junit.framework.TestCase;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class SpringFirstTest extends TestCase {

    public void test1() throws Exception {
        // ## Arrange ##
        XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource(
                "spring/learning/SpringFirstTest_beans.xml"));

        // ## Act ##
        Greeting foo = (Greeting) factory.getBean("greeting");

        // ## Assert ##
        System.out.println(foo.getText());
        assertEquals("Spring comes here!", foo.getText());
    }

}
