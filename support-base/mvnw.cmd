@echo off
setlocal EnableExtensions
set "PROJECT_DIR=%~dp0"
set "PROPERTIES=%PROJECT_DIR%.mvn\wrapper\maven-wrapper.properties"
set "MAVEN_VERSION=3.9.11"
set "MAVEN_HOME=%USERPROFILE%\.m2\wrapper\dists\apache-maven-%MAVEN_VERSION%"
set "MAVEN_BIN=%MAVEN_HOME%\bin\mvn.cmd"
if exist "%MAVEN_BIN%" goto run_maven
echo [Maven Wrapper] Maven %MAVEN_VERSION% no se encuentra en este equipo.
echo [Maven Wrapper] Descargando y preparando Maven (requiere internet)...
powershell.exe -NoProfile -NonInteractive -Command "$ErrorActionPreference='Stop'; $url='https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.9.11/apache-maven-3.9.11-bin.zip'; $base=Join-Path $env:USERPROFILE '.m2\wrapper\dists'; $dest=Join-Path $base 'apache-maven-3.9.11'; $tmp=Join-Path $base 'apache-maven-3.9.11-bin.zip'; New-Item -ItemType Directory -Force -Path $base | Out-Null; try { Invoke-WebRequest -Uri $url -OutFile $tmp -UseBasicParsing; Expand-Archive -LiteralPath $tmp -DestinationPath $base -Force; if (-not (Test-Path (Join-Path $dest 'bin\mvn.cmd'))) { throw 'No se encontro mvn.cmd tras extraer Maven' } } finally { Remove-Item -LiteralPath $tmp -Force -ErrorAction SilentlyContinue }"
if errorlevel 1 (
  echo [ERROR] No se pudo descargar Maven. Verifica internet o ejecuta desde IntelliJ.
  exit /b 1
)
:run_maven
call "%MAVEN_BIN%" %*
exit /b %ERRORLEVEL%
