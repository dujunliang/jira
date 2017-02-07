@echo off & setlocal enabledelayedexpansion

set LIB_JARS=""
cd ..\lib
set dir=%cd%
for %%i in (*) do set LIB_JARS=!LIB_JARS!;%dir%\%%i
cd ..
echo %cd%
if ""%1"" == ""debug"" goto debug
if ""%1"" == ""jmx"" goto jmx
java -Xms64m -Xmx1024m -XX:MaxPermSize=64M -classpath %LIB_JARS% com.vss.sys.springmain.JiraStart
goto end

:debug
java -Xms64m -Xmx1024m -XX:MaxPermSize=64M -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n -classpath %LIB_JARS% com.vss.sys.springmain.JiraStart
goto end

:jmx
java -Xms64m -Xmx1024m -XX:MaxPermSize=64M -Dcom.sun.management.jmxremote.port=1099 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -classpath %LIB_JARS% com.vss.sys.springmain.JiraStart
:end
pause