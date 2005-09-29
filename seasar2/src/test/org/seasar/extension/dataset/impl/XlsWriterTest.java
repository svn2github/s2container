package test.org.seasar.extension.dataset.impl;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.seasar.extension.dataset.DataSet;
import org.seasar.extension.dataset.DataWriter;
import org.seasar.extension.dataset.impl.XlsReader;
import org.seasar.extension.dataset.impl.XlsWriter;

public class XlsWriterTest extends TestCase {

	private static final String PATH =
		"test/org/seasar/extension/dataset/impl/XlsReaderImplTest.xls";
	private static final String PATH2 =
		"test/org/seasar/extension/dataset/impl/XlsWriterImplTest.xls";

	private DataSet dataSet_;
	private DataWriter writer_;

	public XlsWriterTest(String name) {
		super(name);
	}

	public void testWrite() throws Exception {
		writer_.write(dataSet_);
	}

	protected void setUp() throws Exception {
		dataSet_ = new XlsReader(PATH).read();
		writer_ = new XlsWriter(PATH2);
	}

	protected void tearDown() throws Exception {
	}

	public static Test suite() {
		return new TestSuite(XlsWriterTest.class);
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.main(
			new String[] { XlsWriterTest.class.getName()});
	}
}