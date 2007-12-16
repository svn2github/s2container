@echo off
setlocal

call ant

set CLASSPATH=build\classes;lib\aopalliance-1.0.jar;lib\commons-logging-1.1.jar;lib\easymock-2.2.jar;lib\geronimo-annotation_1.0_spec-1.0.jar;lib\geronimo-ejb_3.0_spec-1.0.jar;lib\geronimo-interceptor_3.0_spec-1.0.jar;lib\geronimo-jpa_3.0_spec-1.0.jar;lib\geronimo-jta_1.1_spec-1.0.jar;lib\geronimo-j2ee_1.4_spec-1.0.jar;lib\hsqldb-1.8.0.1.jar;lib\javassist-3.4.ga.jar;lib\junit-4.3.1.jar;lib\junit-addons-1.4.jar;lib\log4j-1.2.13.jar;lib\ognl-2.6.9-patch-20070908.jar;lib\poi-3.0-FINAL.jar;lib\portlet-api-1.0.jar;lib\s2-extension-2.4.18.jar;lib\s2-framework-2.4.18.jar;lib\s2-tiger-2.4.18.jar;lib\ojdbc5.jar;lib\antlr-2.7.6.jar;lib\commons-collections-2.1.1.jar;lib\dom4j-1.6.1.jar;lib\ehcache-1.2.jar;lib\hibernate-3.2.3.ga.jar;lib\hibernate-annotations-3.2.1.ga.jar;lib\hibernate-entitymanager-3.2.1.ga.jar;lib\jboss-archive-browsing-5.0.0alpha-200607201-119.jar;lib\lucene-core-2.0.0.jar;lib\s2hibernate-jpa-1.0.1-rc1.jar;lib\s2-dao-1.0.47.jar;lib\s2-dao-tiger-1.0.47.jar;lib\h2-1.0.62.jar;lib\db2jcc.jar;lib\db2jcc_license_cu.jar
set SCANNER=org.seasar.extension.jdbc.benchmark.BenchmarkTestScanner
set INFO=org.seasar.extension.jdbc.benchmark.BenchmarkInfo
set TESTMAIN=org.seasar.extension.jdbc.benchmark.BenchmarkTestCase
set CLASSLISTFILE=%CD%\classlist.txt
set RESULTFILE=%CD%\result.txt

if exist %CLASSLISTFILE% (
	del %CLASSLISTFILE%
)

if exist %RESULTFILE% (
	del %RESULTFILE%
)

call :EXEC %SCANNER% %CLASSLISTFILE%

call :EXEC %INFO% %RESULTFILE%

for /F %%a in (%CLASSLISTFILE%) do (
	if "%%a" == "#" (
		echo. >> %RESULTFILE%
	) else (
		for /L %%b in (1,1,3) do (
			call :EXEC %TESTMAIN% %%a %RESULTFILE%
		)
	)
)
goto :END

:EXEC
java -classpath %CLASSPATH% %~1 %~2 %~3 %~4 %~5

:END
endlocal