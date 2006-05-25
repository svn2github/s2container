package benchmark.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.seasar.framework.exception.IORuntimeException;
import org.seasar.framework.util.FileInputStreamUtil;

public class CsvWriter {

    private File file_;

    private String delim_ = "\t";

    public void appendRecord(Map record) {
        List keys = new ArrayList();
        List values = new ArrayList();
        for (Iterator it = record.entrySet().iterator(); it.hasNext();) {
            Map.Entry entry = (Entry) it.next();
            keys.add(entry.getKey());
            values.add(entry.getValue());
        }

        if (file_.exists()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    FileInputStreamUtil.create(file_)));
            try {
                String firstLine = reader.readLine();
                String header = join(keys, delim_);
                if (!firstLine.equals(header)) {
                    throw new RuntimeException("different header");
                }
                split(firstLine, delim_);
            } catch (IOException e) {
                throw new IORuntimeException(e);
            }
            try {
                BufferedWriter writer = createWriter();
                writer.write(join(values, delim_));
                writer.newLine();
                writer.close();
            } catch (IOException e) {
                throw new IORuntimeException(e);
            }
        } else {
            try {
                BufferedWriter writer = createWriter();
                writer.write(join(keys, delim_));
                writer.newLine();
                writer.write(join(values, delim_));
                writer.newLine();
                writer.close();
            } catch (IOException e) {
                throw new IORuntimeException(e);
            }
        }
    }

    private BufferedWriter createWriter() {
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        try {
            fos = new FileOutputStream(file_, true);
            osw = new OutputStreamWriter(fos, "UTF-8");
            return new BufferedWriter(osw);
        } catch (IOException e) {
            if (osw != null) {
                close(osw);
            } else if (fos != null) {
                close(fos);
            }
            throw new IORuntimeException(e);
        }
    }

    private void close(OutputStream os) {
        try {
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void close(Writer writer) {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    List split(final String s, final String delim) {
        List l = new ArrayList();
        int left = 0;
        while (true) {
            int right = s.indexOf(delim, left);
            if (-1 < right) {
                l.add(s.substring(left, right));
                left = right + 1;
            } else {
                l.add(s.substring(left));
                break;
            }
        }
        return l;
    }

    String join(final List l, final String delim) {
        StringBuffer sb = new StringBuffer();
        boolean first = true;
        for (Iterator it = l.iterator(); it.hasNext();) {
            if (first) {
                first = false;
            } else {
                sb.append(delim);
            }
            Object o = it.next();
            sb.append(o);
        }
        return sb.toString();
    }

    public void setFile(File file) {
        file_ = file;
    }

}
