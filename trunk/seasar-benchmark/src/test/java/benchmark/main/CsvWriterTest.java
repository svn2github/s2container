package benchmark.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import junit.framework.TestCase;

import org.seasar.framework.exception.IORuntimeException;
import org.seasar.framework.util.FileInputStreamUtil;
import org.seasar.framework.util.ReaderUtil;

public class CsvWriterTest extends TestCase {

    public void testSplit1() throws Exception {
        CsvWriter csvWriter = new CsvWriter();
        List l = csvWriter.split("a", ",");
        assertEquals(1, l.size());
        assertEquals("a", l.get(0));
    }

    public void testSplit2() throws Exception {
        CsvWriter csvWriter = new CsvWriter();
        List l = csvWriter.split("a,b", ",");
        assertEquals(2, l.size());
        assertEquals("a", l.get(0));
        assertEquals("b", l.get(1));
    }

    public void testSplit3() throws Exception {
        CsvWriter csvWriter = new CsvWriter();
        List l = csvWriter.split("a\tbb\tccc", "\t");
        assertEquals(3, l.size());
        assertEquals("a", l.get(0));
        assertEquals("bb", l.get(1));
        assertEquals("ccc", l.get(2));
    }

    public void testJoin1() throws Exception {
        CsvWriter csvWriter = new CsvWriter();
        assertEquals("a", csvWriter.join(Arrays.asList(new String[] { "a" }),
                ","));
        assertEquals("a,b", csvWriter.join(Arrays.asList(new String[] { "a",
                "b" }), ","));
        assertEquals("a\tbb\tccc", csvWriter.join(Arrays.asList(new String[] {
                "a", "bb", "ccc" }), "\t"));
    }

    public void test1() throws Exception {
        // ## Arrange ##
        File tempFile = File.createTempFile(getName(), null);
        tempFile.delete();

        CsvWriter csvWriter = new CsvWriter();
        csvWriter.setFile(tempFile);

        // ## Act ##
        Map record = new TreeMap();
        record.put("key1", "value1");
        record.put("key2", "value2");
        csvWriter.appendRecord(record);

        // ## Assert ##
        String text = readText(tempFile);
        assertEquals("key1\tkey2\r\nvalue1\tvalue2\r\n", text);
    }

    public void test2() throws Exception {
        // ## Arrange ##
        File tempFile = File.createTempFile(getName(), null);
        tempFile.delete();

        CsvWriter csvWriter = new CsvWriter();
        csvWriter.setFile(tempFile);

        // ## Act ##
        {
            Map record = new TreeMap();
            record.put("k1", "v11");
            record.put("k2", "v21");
            csvWriter.appendRecord(record);
        }
        {
            Map record = new TreeMap();
            record.put("k1", "v12");
            record.put("k2", "v22");
            csvWriter.appendRecord(record);
        }

        // ## Assert ##
        String text = readText(tempFile);
        assertEquals("k1\tk2" + "\r\n" + "v11\tv21" + "\r\n" + "v12\tv22"
                + "\r\n", text);
    }

    public void testDifferentKey() throws Exception {
        // ## Arrange ##
        File tempFile = File.createTempFile(getName(), null);
        tempFile.delete();

        CsvWriter csvWriter = new CsvWriter();
        csvWriter.setFile(tempFile);

        // ## Act ##
        {
            Map record = new TreeMap();
            record.put("k1", "v11");
            record.put("k2", "v21");
            csvWriter.appendRecord(record);
        }
        {
            Map record = new TreeMap();
            record.put("ka", "v12");
            record.put("k2", "v22");
            // ## Assert ##
            try {
                csvWriter.appendRecord(record);
                String text = readText(tempFile);
                fail(text);
            } catch (RuntimeException e) {
            }
        }

    }

    private String readText(File f) {
        FileInputStream fis = FileInputStreamUtil.create(f);
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(fis, "UTF-8");
            String text = ReaderUtil.readText(isr);
            isr = null;
            fis = null;
            return text;
        } catch (IOException e) {
            throw new IORuntimeException(e);
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
