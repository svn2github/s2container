import benchmark.main.BenchmarkMain

start = new java.util.Date();
//format = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss_SSS");
format = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss");
timestamp = format.format(start)
outputDir = "target/bench/${timestamp}"
traceFileName = "${outputDir}/trace.txt"
memorySize = "1024M"
permSize = "384M"

execute()

def execute() {
	new java.io.File(outputDir).mkdirs()
	printSystemProperties()
	
	ant = new AntBuilder()
	ant.record(name:"${traceFileName}", append:"true", action:"start")
	
	ant.path(id:"benchmark.classpath") {
		fileset(dir:"lib") {
			include(name:"**/*.jar")
		}
		path(location:"target/classes")
	}
	
	runJava(ant, "benchmark.main.BenchmarkVersion", [])
	
	[
		"benchmark.main.AopWeavingBenchmark", 
		"benchmark.main.ManyBeansAopBenchmark", 
		"benchmark.main.AopBenchmark", 
		"benchmark.main.BeanDescBenchmark", 
		"benchmark.main.ManyBeansBenchmark", 
		"benchmark.main.NoDIBenchmark", 
		"benchmark.main.WireBenchmark", 
	].each { |className|
		clazz = Class.forName(className)
		shortClassName = org.seasar.framework.util.ClassUtil.getShortClassName(clazz)
		fileName = "${outputDir}/${shortClassName}.tsv"
		BenchmarkMain.getBenchmarkMethods(clazz).each { |method|
			5.times {
				runBenchmark(ant, clazz.name, method.name, fileName)
			}
		}
	}
	
	ant.record(name:"${traceFileName}", action:"stop")
}

def runBenchmark(ant, className, methodName, fileName) {
	ant.java(classname:"benchmark.main.BenchmarkMain", fork:"true", failonerror:"false") {
		arg(value:"-c ${className}")
		arg(value:"-m ${methodName}")
		arg(value:"-f ${fileName}")
		jvmarg(value:"-Xms${memorySize}")
		jvmarg(value:"-Xmx${memorySize}")
		jvmarg(value:"-XX:PermSize=${permSize}")
		jvmarg(value:"-XX:MaxPermSize=${permSize}")
		classpath(refid:"benchmark.classpath")
	}
}

def runJava(ant, className, args) {
	ant.java(classname:className, fork:"true", failonerror:"true") {
		args.each { |arg|
			ant.arg(value:arg)
		}
		jvmarg(value:"-Xms${memorySize}")
		jvmarg(value:"-Xmx${memorySize}")
		classpath(refid:"benchmark.classpath")
	}
}

def printSystemProperties() {
	new java.io.File(traceFileName).withPrintWriter { |writer|
		new java.util.TreeMap(System.properties).each { writer.println it }
	}
}
