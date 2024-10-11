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

@if "%DEBUG%"=="" @echo off
@rem ##########################################################################
@rem
@rem  intellij-http-client startup script for Windows
@rem  (edited Gradle template)
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%
set MAIN_JAR_HOME=%APP_HOME%lib\

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if %ERRORLEVEL% equ 0 goto checkJavaVersion

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto checkJavaVersion

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:checkJavaVersion
for /f tokens^=2-5^ delims^=.-_^" %%j in ('"%JAVA_EXE%" -fullversion 2^>^&1') do set "JAVA_VERSION=%%j.%%k.%%l.%%m"

@rem checking for Java version (IDEA-310148)
if not "%JAVA_VERSION:~0,2%" geq "17" goto wrongJavaVersion

:execute
@rem Prepare environment for running
if not "%IDEA_INITIAL_DIRECTORY%" == "" set "MY_IDEA_TERMINAL=true"
if "%MY_IDEA_TERMINAL%" == "true" set "COLORTERM=truecolor"
set "IDEA_INITIAL_DIRECTORY="
set "__INTELLIJ_COMMAND_HISTFILE__="
set "TERMINAL_EMULATOR="
call chcp 65001 > nul

@rem Execute intellij-http-client
"%JAVA_EXE%" --add-opens=java.base/java.util=ALL-UNNAMED %IJHTTP_JAVA_OPTS% -cp "%MAIN_JAR_HOME%*" com.intellij.httpClient.cli.HttpClientMain %*

:end
@rem End local scope for the variables with windows NT shell
if %ERRORLEVEL% equ 0 goto mainEnd

:fail
rem the _cmd.exe /c_ return code!
set EXIT_CODE=%ERRORLEVEL%
if %EXIT_CODE% equ 0 set EXIT_CODE=1
exit /b %EXIT_CODE%

:wrongJavaVersion
echo IntelliJ HTTP Client requires Java version 17. But provided Java has version %JAVA_VERSION%
set EXIT_CODE=1
goto fail

:mainEnd
if "%OS%"=="Windows_NT" endlocal


:omega