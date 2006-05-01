package benchmark.main;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.MethodUtil;

public class BenchmarkMain {

    public static void main(String[] args) throws Exception {

        Options options = createParserOptions();
        CommandLineParser parser = new GnuParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            HelpFormatter help = new HelpFormatter();
            help.printHelp("BenchmarkMain", options, true);
            return;
        }

        final String className = cmd.getOptionValue("c").trim();
        final String methodName = cmd.getOptionValue("m").trim();
        final String outFile = cmd.getOptionValue("f").trim();
        final Class clazz = ClassUtil.forName(className);
        final Object instance = clazz.newInstance();
        final Method method = ClassUtil.getMethod(clazz, methodName, null);
        final String benchmarkName = ClassUtil.getShortClassName(clazz) + "."
                + method.getName();

        final Map report = new LinkedHashMap();
        report.put("benchmarkName", benchmarkName);
        setPropertyFor(instance, "report", report);

        runGC();
        System.out
                .println("===========================================================");
        System.out.println(benchmarkName);
        System.out
                .println("===========================================================");
        System.out.println("BEFORE:" + new RuntimeMemory().toStringMegabyte());
        long start = System.currentTimeMillis();
        MethodUtil.invoke(method, instance, new Object[] {});
        long end = System.currentTimeMillis();
        System.out.println("AFTER:" + new RuntimeMemory().toStringMegabyte());
        System.out.println("total time:" + (end - start));
        report.put("total time", "" + (end - start));

        writeResult(report, outFile);
    }

    private static void writeResult(final Map report, final String outFile)
            throws IOException {
        CsvWriter csvWriter = new CsvWriter();
        csvWriter.setFile(new File(outFile));
        csvWriter.appendRecord(report);
    }

    private static void setPropertyFor(Object instance, String propertyName,
            Object parameter) {
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(instance.getClass());
        PropertyDesc propertyDesc = beanDesc.getPropertyDesc(propertyName);
        propertyDesc.setValue(instance, parameter);
    }

    private static Options createParserOptions() {
        Options options = new Options();
        {
            Option option = new Option("c", "className", true, "Class Name");
            option.setRequired(true);
            option.setArgName("FQCN");
            options.addOption(option);
        }
        {
            Option option = new Option("m", "methodName", true,
                    "Class's Method Name");
            option.setRequired(true);
            option.setArgName("method name");
            options.addOption(option);
        }
        {
            Option option = new Option("f", "outputFile", true,
                    "Benchmark result file");
            option.setRequired(true);
            option.setArgName("output file");
            options.addOption(option);
        }
        return options;
    }

    private static void runGC() {
        for (int i = 0; i < 5; i++) {
            System.runFinalization();
            System.gc();
        }
    }

    public static Method[] getBenchmarkMethods(Class clazz) {
        List l = new ArrayList();
        Method[] methods = clazz.getMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            if (isBenchmarkMethod(method)) {
                l.add(method);
            }
        }
        return (Method[]) l.toArray(new Method[l.size()]);
    }

    private static boolean isBenchmarkMethod(Method method) {
        return !isAccessor(method) && !isObjectMethod(method)
                && method.getParameterTypes().length == 0;
    }

    private static boolean isObjectMethod(Method method) {
        String name = method.getName();
        if ("toString".equals(name) || "notify".equals(name)
                || "notifyAll".equals(name) || "wait".equals(name)
                || "getClass".equals(name) || "hashCode".equals(name)
                || "equals".equals(name)) {
            return true;
        }
        return false;
    }

    private static boolean isAccessor(Method method) {
        String name = method.getName();
        if (name.startsWith("set")) {
            if (Void.TYPE == method.getReturnType()
                    && method.getParameterTypes().length == 1) {
                return true;
            }
        } else if (name.startsWith("get")) {
            if (Void.TYPE != method.getReturnType()
                    && method.getParameterTypes().length == 0) {
                return true;
            }
        } else if (name.startsWith("is")) {
            if (Boolean.class == method.getReturnType()
                    && method.getParameterTypes().length == 0) {
                return true;
            }
        }
        return false;
    }

}
