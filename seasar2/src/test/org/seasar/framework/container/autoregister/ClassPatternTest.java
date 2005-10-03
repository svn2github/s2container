package test.org.seasar.framework.container.autoregister;

import junit.framework.TestCase;

import org.seasar.framework.container.autoregister.ClassPattern;

/**
 * @author higa
 */
public class ClassPatternTest extends TestCase {

    public void testAppliedForShortClassNameNull() throws Exception {
        ClassPattern cp = new ClassPattern();
        assertTrue("1", cp.isApplied("Hoge"));
    }
    
    public void testAppliedForNormalPattern() throws Exception {
        ClassPattern cp = new ClassPattern();
        cp.setShortClassName(".*Impl");
        assertTrue("1", cp.isApplied("HogeImpl"));
        assertFalse("2", cp.isApplied("Hoge"));
    }
    
    public void testAppliedForMulti() throws Exception {
        ClassPattern cp = new ClassPattern();
        cp.setShortClassName("Hoge, HogeImpl");
        assertTrue("1", cp.isApplied("HogeImpl"));
        assertTrue("2", cp.isApplied("Hoge"));
        assertFalse("3", cp.isApplied("Hoge2"));
    }
}