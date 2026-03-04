# api-wk – WK Radar API

Aplicação Java que consome o endpoint **GET /api/comercial/v1/nota-fiscal** da API WK Radar e gera planilhas Excel (`.xlsx`) com os dados retornados. Preparada para execução agendada no Windows (Task Scheduler).

## Requisitos

- **Java 17+**
- **Maven 3.6+** (para compilar e empacotar)

## Configuração

1. Edite `src/main/resources/config.properties` (ou crie um arquivo externo e use `-Dconfig.file=...`):
   - **api.base.url** – URL base da API (obrigatório). Ex.: `https://api.exemplo.wk.com.br`
   - **api.auth.token** – Bearer token (obrigatório). Nunca commitar valor real.
   - **api.timeout.ms** – Timeout HTTP em ms (opcional, default 30000).
   - **output.dir** – Diretório de saída dos arquivos Excel (ex.: `./output`).
   - Filtros opcionais: `filtro.data.emissao.inicial`, `filtro.data.emissao.final`, `filtro.numero.inicial`, `filtro.numero.final`, `filtro.id.filial`, `filtro.ids`, `filtro.codigos.integrador`, `filtro.fields`.

2. Para usar arquivo de config externo na execução:
   ```bat
   java -Dconfig.file=C:\caminho\config.properties -jar api-wk.jar
   ```

## Compilação e empacotamento

Na pasta do projeto (`api-wk`):

```bash
mvn clean package
```

O JAR com dependências (fat JAR) será gerado em:

- `target/api-wk.jar`

## Execução manual

```bash
java -jar target/api-wk.jar
```

Ou com config externo:

```bash
java -Dconfig.file=C:\caminho\config.properties -jar target/api-wk.jar
```

No Windows pode usar o script:

```bat
executar.bat
```

O script chama o JAR em `target\api-wk.jar`. Para usar config externo, edite `executar.bat` e defina `CONFIG_FILE=-Dconfig.file=...`.

## Agendamento no Windows (Task Scheduler)

1. Abra o **Agendador de Tarefas** (taskschd.msc).
2. Criar Tarefa Básica (ou Nova Tarefa).
3. **Gatilho**: defina a periodicidade desejada (ex.: a cada 1, 2, 4 ou 6 horas).
4. **Ação**: Iniciar um programa.
   - **Programa/script**: informe o caminho completo do `executar.bat`.  
     Ex.: `C:\Trabalhos\api-wk\executar.bat`
   - **Iniciar em**: opcionalmente o diretório do projeto (ex.: `C:\Trabalhos\api-wk`).
5. Em **Configurações** (ou Propriedades da tarefa):
   - Marque **Executar mesmo que o usuário não esteja conectado**, se desejar que rode sem login.
   - Caminhos com espaços devem estar entre aspas no script.
6. O script e o JAR usam o código de saída: **0** = sucesso, **1** = falha. O Task Scheduler pode ser configurado para reagir a falhas (histórico da tarefa).

## Saída

- **Arquivo Excel**: `output.dir/notas-fiscais_YYYY-MM-DD_HH-mm.xlsx` (data/hora da execução).
- **Logs**: arquivos em `logs/` (rolling diário, ex.: `app_yyyy-MM-dd.log`).

## Documentação da API

- [WK Radar API – Nota Fiscal](https://api-docs.wk.com.br/718/#tag/NotaFiscal/paths/~1api~1comercial~1v1~1nota-fiscal/get)
