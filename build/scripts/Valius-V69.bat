@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  Valius-V69 startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and VALIUS_V69_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\Valius-V69.jar;%APP_HOME%\lib\everythingrs-api.jar;%APP_HOME%\lib\logback-classic-1.2.3.jar;%APP_HOME%\lib\Discord4J-2.10.1.jar;%APP_HOME%\lib\slf4j-api-1.7.25.jar;%APP_HOME%\lib\commons-compress-1.18.jar;%APP_HOME%\lib\guava-27.0.1-jre.jar;%APP_HOME%\lib\commons-lang3-3.8.1.jar;%APP_HOME%\lib\commons-io-2.6.jar;%APP_HOME%\lib\gson-2.8.5.jar;%APP_HOME%\lib\xstream-1.4.11.1.jar;%APP_HOME%\lib\netty-3.10.6.Final.jar;%APP_HOME%\lib\jackson-module-afterburner-2.9.2.jar;%APP_HOME%\lib\jackson-databind-2.9.7.jar;%APP_HOME%\lib\json-simple-1.1.1.jar;%APP_HOME%\lib\Commands4J-2.1.jar;%APP_HOME%\lib\mchange-commons-java-0.2.15.jar;%APP_HOME%\lib\jackson-annotations-2.9.7.jar;%APP_HOME%\lib\jsoup-1.12.1.jar;%APP_HOME%\lib\sql2o-1.6.0.jar;%APP_HOME%\lib\mysql-connector-java-8.0.13.jar;%APP_HOME%\lib\failureaccess-1.0.1.jar;%APP_HOME%\lib\listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar;%APP_HOME%\lib\jsr305-3.0.2.jar;%APP_HOME%\lib\checker-qual-2.5.2.jar;%APP_HOME%\lib\error_prone_annotations-2.2.0.jar;%APP_HOME%\lib\j2objc-annotations-1.1.jar;%APP_HOME%\lib\animal-sniffer-annotations-1.17.jar;%APP_HOME%\lib\logback-core-1.2.3.jar;%APP_HOME%\lib\xmlpull-1.1.3.1.jar;%APP_HOME%\lib\xpp3_min-1.1.4c.jar;%APP_HOME%\lib\jackson-core-2.9.7.jar;%APP_HOME%\lib\mp3spi-1.9.5.4.jar;%APP_HOME%\lib\jlayer-1.0.1.4.jar;%APP_HOME%\lib\junit-4.10.jar;%APP_HOME%\lib\httpmime-4.5.4.jar;%APP_HOME%\lib\httpclient-4.5.4.jar;%APP_HOME%\lib\httpcore-4.4.8.jar;%APP_HOME%\lib\websocket-client-9.4.8.v20171121.jar;%APP_HOME%\lib\typetools-0.5.0.jar;%APP_HOME%\lib\jna-4.5.0.jar;%APP_HOME%\lib\jorbis-0.0.17.jar;%APP_HOME%\lib\jflac-codec-1.5.2.jar;%APP_HOME%\lib\tritonus-share-0.3.7.4.jar;%APP_HOME%\lib\tritonus-dsp-0.3.6.jar;%APP_HOME%\lib\emoji-java-4.0.0.jar;%APP_HOME%\lib\koloboke-impl-common-jdk8-1.0.0.jar;%APP_HOME%\lib\protobuf-java-3.6.1.jar;%APP_HOME%\lib\hamcrest-core-1.1.jar;%APP_HOME%\lib\commons-logging-1.2.jar;%APP_HOME%\lib\commons-codec-1.10.jar;%APP_HOME%\lib\jetty-client-9.4.8.v20171121.jar;%APP_HOME%\lib\jetty-xml-9.4.8.v20171121.jar;%APP_HOME%\lib\websocket-common-9.4.8.v20171121.jar;%APP_HOME%\lib\jetty-http-9.4.8.v20171121.jar;%APP_HOME%\lib\jetty-io-9.4.8.v20171121.jar;%APP_HOME%\lib\jetty-util-9.4.8.v20171121.jar;%APP_HOME%\lib\json-20170516.jar;%APP_HOME%\lib\koloboke-api-jdk8-1.0.0.jar;%APP_HOME%\lib\websocket-api-9.4.8.v20171121.jar

@rem Execute Valius-V69
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %VALIUS_V69_OPTS%  -classpath "%CLASSPATH%" valius.Server %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable VALIUS_V69_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%VALIUS_V69_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
