@echo off
setlocal

call ant

set CLASSPATH=%CD%\build\classes
set LIBDIR=%CD%\lib
set MAIN=org.seasar.extension.jdbc.benchmark.BenchmarkMain
set SCANNER=org.seasar.extension.jdbc.benchmark.BenchmarkTestCaseScanner
set TESTCASE=org.seasar.extension.jdbc.benchmark.BenchmarkTestCase
set CLASSLISTFILE=%CD%\classlist.txt
set RESULTFILE=%CD%\result.txt

java -classpath %CLASSPATH% %MAIN% --command append --appendclasspath %LIBDIR% > append.bat

call append.bat
del append.bat

java -classpath %CLASSPATH% %SCANNER% > %CLASSLISTFILE%

java -classpath %CLASSPATH% %MAIN% --command info > %RESULTFILE%

for /F %%a in (%CLASSLISTFILE%) do (
	if "%%a" == "#" (
		echo. >> %RESULTFILE%
	) else (
		for /L %%b in (1,1,3) do (
			java -classpath %CLASSPATH% %TESTCASE% %%a %RESULTFILE%
		)
	)
)
goto :END

:END
endlocal