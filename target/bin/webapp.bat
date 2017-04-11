@REM ----------------------------------------------------------------------------
@REM Copyright 2001-2004 The Apache Software Foundation.
@REM
@REM Licensed under the Apache License, Version 2.0 (the "License");
@REM you may not use this file except in compliance with the License.
@REM You may obtain a copy of the License at
@REM
@REM      http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM Unless required by applicable law or agreed to in writing, software
@REM distributed under the License is distributed on an "AS IS" BASIS,
@REM WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@REM See the License for the specific language governing permissions and
@REM limitations under the License.
@REM ----------------------------------------------------------------------------
@REM

@echo off

set ERROR_CODE=0

:init
@REM Decide how to startup depending on the version of windows

@REM -- Win98ME
if NOT "%OS%"=="Windows_NT" goto Win9xArg

@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" @setlocal

@REM -- 4NT shell
if "%eval[2+2]" == "4" goto 4NTArgs

@REM -- Regular WinNT shell
set CMD_LINE_ARGS=%*
goto WinNTGetScriptDir

@REM The 4NT Shell from jp software
:4NTArgs
set CMD_LINE_ARGS=%$
goto WinNTGetScriptDir

:Win9xArg
@REM Slurp the command line arguments.  This loop allows for an unlimited number
@REM of arguments (up to the command line limit, anyway).
set CMD_LINE_ARGS=
:Win9xApp
if %1a==a goto Win9xGetScriptDir
set CMD_LINE_ARGS=%CMD_LINE_ARGS% %1
shift
goto Win9xApp

:Win9xGetScriptDir
set SAVEDIR=%CD%
%0\
cd %0\..\.. 
set BASEDIR=%CD%
cd %SAVEDIR%
set SAVE_DIR=
goto repoSetup

:WinNTGetScriptDir
set BASEDIR=%~dp0\..

:repoSetup


if "%JAVACMD%"=="" set JAVACMD=java

if "%REPO%"=="" set REPO=%BASEDIR%\repo

set CLASSPATH="%BASEDIR%"\etc;"%REPO%"\org\eclipse\jetty\jetty-server\7.2.2.v20101205\jetty-server-7.2.2.v20101205.jar;"%REPO%"\org\eclipse\jetty\jetty-continuation\7.2.2.v20101205\jetty-continuation-7.2.2.v20101205.jar;"%REPO%"\org\eclipse\jetty\jetty-http\7.2.2.v20101205\jetty-http-7.2.2.v20101205.jar;"%REPO%"\org\eclipse\jetty\jetty-io\7.2.2.v20101205\jetty-io-7.2.2.v20101205.jar;"%REPO%"\org\eclipse\jetty\jetty-webapp\7.2.2.v20101205\jetty-webapp-7.2.2.v20101205.jar;"%REPO%"\org\eclipse\jetty\jetty-xml\7.2.2.v20101205\jetty-xml-7.2.2.v20101205.jar;"%REPO%"\org\eclipse\jetty\jetty-util\7.2.2.v20101205\jetty-util-7.2.2.v20101205.jar;"%REPO%"\org\eclipse\jetty\jetty-servlet\7.2.2.v20101205\jetty-servlet-7.2.2.v20101205.jar;"%REPO%"\org\eclipse\jetty\jetty-security\7.2.2.v20101205\jetty-security-7.2.2.v20101205.jar;"%REPO%"\org\eclipse\jetty\jetty-jsp-2.1\7.2.2.v20101205\jetty-jsp-2.1-7.2.2.v20101205.jar;"%REPO%"\org\mortbay\jetty\jsp-2.1-glassfish\2.1.v20100127\jsp-2.1-glassfish-2.1.v20100127.jar;"%REPO%"\org\eclipse\jdt\core\compiler\ecj\3.5.1\ecj-3.5.1.jar;"%REPO%"\org\mortbay\jetty\jsp-api-2.1-glassfish\2.1.v20100127\jsp-api-2.1-glassfish-2.1.v20100127.jar;"%REPO%"\ant\ant\1.6.5\ant-1.6.5.jar;"%REPO%"\javax\servlet\servlet-api\2.5\servlet-api-2.5.jar;"%REPO%"\mysql\mysql-connector-java\5.1.27\mysql-connector-java-5.1.27.jar;"%REPO%"\com\heroku\sdk\heroku-maven-plugin\1.1.3\heroku-maven-plugin-1.1.3.jar;"%REPO%"\com\heroku\sdk\heroku-deploy\1.1.3\heroku-deploy-1.1.3.jar;"%REPO%"\com\fasterxml\jackson\core\jackson-core\2.3.0\jackson-core-2.3.0.jar;"%REPO%"\com\fasterxml\jackson\core\jackson-databind\2.3.0\jackson-databind-2.3.0.jar;"%REPO%"\com\fasterxml\jackson\core\jackson-annotations\2.3.0\jackson-annotations-2.3.0.jar;"%REPO%"\org\apache\commons\commons-compress\1.12\commons-compress-1.12.jar;"%REPO%"\commons-io\commons-io\2.5\commons-io-2.5.jar;"%REPO%"\org\apache\commons\commons-lang3\3.5\commons-lang3-3.5.jar;"%REPO%"\org\eclipse\jgit\org.eclipse.jgit\4.5.0.201609210915-r\org.eclipse.jgit-4.5.0.201609210915-r.jar;"%REPO%"\com\jcraft\jsch\0.1.53\jsch-0.1.53.jar;"%REPO%"\com\googlecode\javaewah\JavaEWAH\0.7.9\JavaEWAH-0.7.9.jar;"%REPO%"\org\slf4j\slf4j-api\1.7.2\slf4j-api-1.7.2.jar;"%REPO%"\org\apache\httpcomponents\httpclient\4.5.2\httpclient-4.5.2.jar;"%REPO%"\org\apache\httpcomponents\httpcore\4.4.4\httpcore-4.4.4.jar;"%REPO%"\commons-logging\commons-logging\1.2\commons-logging-1.2.jar;"%REPO%"\commons-codec\commons-codec\1.9\commons-codec-1.9.jar;"%REPO%"\org\apache\maven\maven-plugin-api\3.2.3\maven-plugin-api-3.2.3.jar;"%REPO%"\org\apache\maven\maven-model\3.2.3\maven-model-3.2.3.jar;"%REPO%"\org\eclipse\sisu\org.eclipse.sisu.plexus\0.0.0.M5\org.eclipse.sisu.plexus-0.0.0.M5.jar;"%REPO%"\javax\enterprise\cdi-api\1.0\cdi-api-1.0.jar;"%REPO%"\javax\annotation\jsr250-api\1.0\jsr250-api-1.0.jar;"%REPO%"\javax\inject\javax.inject\1\javax.inject-1.jar;"%REPO%"\com\google\guava\guava\10.0.1\guava-10.0.1.jar;"%REPO%"\com\google\code\findbugs\jsr305\1.3.9\jsr305-1.3.9.jar;"%REPO%"\org\sonatype\sisu\sisu-guice\3.1.0\sisu-guice-3.1.0-no_aop.jar;"%REPO%"\aopalliance\aopalliance\1.0\aopalliance-1.0.jar;"%REPO%"\org\eclipse\sisu\org.eclipse.sisu.inject\0.0.0.M5\org.eclipse.sisu.inject-0.0.0.M5.jar;"%REPO%"\org\apache\maven\maven-core\3.2.3\maven-core-3.2.3.jar;"%REPO%"\org\apache\maven\maven-settings\3.2.3\maven-settings-3.2.3.jar;"%REPO%"\org\apache\maven\maven-settings-builder\3.2.3\maven-settings-builder-3.2.3.jar;"%REPO%"\org\apache\maven\maven-repository-metadata\3.2.3\maven-repository-metadata-3.2.3.jar;"%REPO%"\org\apache\maven\maven-model-builder\3.2.3\maven-model-builder-3.2.3.jar;"%REPO%"\org\apache\maven\maven-aether-provider\3.2.3\maven-aether-provider-3.2.3.jar;"%REPO%"\org\eclipse\aether\aether-spi\0.9.0.M2\aether-spi-0.9.0.M2.jar;"%REPO%"\org\eclipse\aether\aether-impl\0.9.0.M2\aether-impl-0.9.0.M2.jar;"%REPO%"\org\eclipse\aether\aether-api\0.9.0.M2\aether-api-0.9.0.M2.jar;"%REPO%"\org\eclipse\aether\aether-util\0.9.0.M2\aether-util-0.9.0.M2.jar;"%REPO%"\org\codehaus\plexus\plexus-interpolation\1.19\plexus-interpolation-1.19.jar;"%REPO%"\org\codehaus\plexus\plexus-utils\3.0.17\plexus-utils-3.0.17.jar;"%REPO%"\org\codehaus\plexus\plexus-classworlds\2.5.1\plexus-classworlds-2.5.1.jar;"%REPO%"\org\codehaus\plexus\plexus-component-annotations\1.5.5\plexus-component-annotations-1.5.5.jar;"%REPO%"\org\sonatype\plexus\plexus-sec-dispatcher\1.3\plexus-sec-dispatcher-1.3.jar;"%REPO%"\org\sonatype\plexus\plexus-cipher\1.4\plexus-cipher-1.4.jar;"%REPO%"\org\apache\maven\maven-artifact\3.2.3\maven-artifact-3.2.3.jar;"%REPO%"\org\apache\maven\plugins\maven-dependency-plugin\2.4\maven-dependency-plugin-2.4.jar;"%REPO%"\org\apache\maven\maven-project\2.0.9\maven-project-2.0.9.jar;"%REPO%"\org\apache\maven\maven-profile\2.0.9\maven-profile-2.0.9.jar;"%REPO%"\org\apache\maven\maven-plugin-registry\2.0.9\maven-plugin-registry-2.0.9.jar;"%REPO%"\org\apache\maven\maven-artifact-manager\2.0.9\maven-artifact-manager-2.0.9.jar;"%REPO%"\org\apache\maven\wagon\wagon-provider-api\1.0-beta-2\wagon-provider-api-1.0-beta-2.jar;"%REPO%"\org\apache\maven\reporting\maven-reporting-api\3.0\maven-reporting-api-3.0.jar;"%REPO%"\org\apache\maven\reporting\maven-reporting-impl\2.0.5\maven-reporting-impl-2.0.5.jar;"%REPO%"\org\apache\maven\doxia\doxia-core\1.0\doxia-core-1.0.jar;"%REPO%"\org\apache\maven\shared\maven-doxia-tools\1.0.2\maven-doxia-tools-1.0.2.jar;"%REPO%"\commons-validator\commons-validator\1.2.0\commons-validator-1.2.0.jar;"%REPO%"\commons-beanutils\commons-beanutils\1.7.0\commons-beanutils-1.7.0.jar;"%REPO%"\commons-digester\commons-digester\1.6\commons-digester-1.6.jar;"%REPO%"\oro\oro\2.0.8\oro-2.0.8.jar;"%REPO%"\xml-apis\xml-apis\1.0.b2\xml-apis-1.0.b2.jar;"%REPO%"\org\apache\maven\doxia\doxia-sink-api\1.0\doxia-sink-api-1.0.jar;"%REPO%"\org\apache\maven\doxia\doxia-site-renderer\1.0\doxia-site-renderer-1.0.jar;"%REPO%"\org\codehaus\plexus\plexus-i18n\1.0-beta-7\plexus-i18n-1.0-beta-7.jar;"%REPO%"\org\codehaus\plexus\plexus-velocity\1.1.7\plexus-velocity-1.1.7.jar;"%REPO%"\org\apache\velocity\velocity\1.5\velocity-1.5.jar;"%REPO%"\org\apache\maven\doxia\doxia-decoration-model\1.0\doxia-decoration-model-1.0.jar;"%REPO%"\org\apache\maven\doxia\doxia-module-apt\1.0\doxia-module-apt-1.0.jar;"%REPO%"\org\apache\maven\doxia\doxia-module-fml\1.0\doxia-module-fml-1.0.jar;"%REPO%"\org\apache\maven\doxia\doxia-module-xdoc\1.0\doxia-module-xdoc-1.0.jar;"%REPO%"\org\apache\maven\doxia\doxia-module-xhtml\1.0\doxia-module-xhtml-1.0.jar;"%REPO%"\org\codehaus\plexus\plexus-archiver\2.0\plexus-archiver-2.0.jar;"%REPO%"\org\apache\maven\shared\file-management\1.2.1\file-management-1.2.1.jar;"%REPO%"\org\apache\maven\shared\maven-shared-io\1.1\maven-shared-io-1.1.jar;"%REPO%"\org\codehaus\plexus\plexus-container-default\1.0-alpha-9-stable-1\plexus-container-default-1.0-alpha-9-stable-1.jar;"%REPO%"\junit\junit\3.8.1\junit-3.8.1.jar;"%REPO%"\org\codehaus\plexus\plexus-io\2.0.1\plexus-io-2.0.1.jar;"%REPO%"\org\apache\maven\shared\maven-dependency-analyzer\1.2\maven-dependency-analyzer-1.2.jar;"%REPO%"\asm\asm\3.0\asm-3.0.jar;"%REPO%"\org\apache\maven\shared\maven-dependency-tree\1.2\maven-dependency-tree-1.2.jar;"%REPO%"\org\apache\maven\shared\maven-common-artifact-filters\1.2\maven-common-artifact-filters-1.2.jar;"%REPO%"\org\apache\maven\shared\maven-plugin-testing-harness\1.1\maven-plugin-testing-harness-1.1.jar;"%REPO%"\org\apache\maven\shared\maven-invoker\2.0.11\maven-invoker-2.0.11.jar;"%REPO%"\commons-lang\commons-lang\2.6\commons-lang-2.6.jar;"%REPO%"\commons-collections\commons-collections\3.2.1\commons-collections-3.2.1.jar;"%REPO%"\classworlds\classworlds\1.1\classworlds-1.1.jar;"%REPO%"\org\twdata\maven\mojo-executor\1.5.2\mojo-executor-1.5.2.jar;"%REPO%"\com\example\JMUIdeaHub\1.0-SNAPSHOT\JMUIdeaHub-1.0-SNAPSHOT.jar
set EXTRA_JVM_ARGUMENTS=
goto endInit

@REM Reaching here means variables are defined and arguments have been captured
:endInit

%JAVACMD% %JAVA_OPTS% %EXTRA_JVM_ARGUMENTS% -classpath %CLASSPATH_PREFIX%;%CLASSPATH% -Dapp.name="webapp" -Dapp.repo="%REPO%" -Dbasedir="%BASEDIR%" root.StartWebApp %CMD_LINE_ARGS%
if ERRORLEVEL 1 goto error
goto end

:error
if "%OS%"=="Windows_NT" @endlocal
set ERROR_CODE=1

:end
@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" goto endNT

@REM For old DOS remove the set variables from ENV - we assume they were not set
@REM before we started - at least we don't leave any baggage around
set CMD_LINE_ARGS=
goto postExec

:endNT
@endlocal

:postExec

if "%FORCE_EXIT_ON_ERROR%" == "on" (
  if %ERROR_CODE% NEQ 0 exit %ERROR_CODE%
)

exit /B %ERROR_CODE%
