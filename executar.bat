@echo off
REM Script para executar o JAR do job de notas fiscais.
REM Use no Task Scheduler apontando para este .bat
REM Caminhos com espacos devem estar entre aspas.

setlocal
cd /d "%~dp0"

REM Opcional: apontar para arquivo de config externo
REM set CONFIG_FILE=-Dconfig.file=C:\caminho\config.properties

REM JAR gerado por mvn package (fat JAR em target/api-wk.jar)
set JAR=target\api-wk.jar
if not exist "%JAR%" (
    echo Erro: JAR nao encontrado. Execute: mvn clean package
    exit /b 1
)

java %CONFIG_FILE% -jar "%JAR%"
exit /b %ERRORLEVEL%
