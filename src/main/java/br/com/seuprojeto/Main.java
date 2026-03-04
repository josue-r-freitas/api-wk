package br.com.seuprojeto;

import br.com.seuprojeto.config.AppConfig;
import br.com.seuprojeto.report.ExcelReportGenerator;
import br.com.seuprojeto.service.NotaFiscalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import br.com.seuprojeto.model.NotaFiscal;

/**
 * Ponto de entrada da aplicação.
 * Orquestra: carregar configuração → buscar notas fiscais na API WK Radar → gerar planilha Excel.
 * Código de saída 0 = sucesso, 1 = falha (para o Task Scheduler detectar).
 */
public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LOG.info("Início da execução da rotina de exportação de notas fiscais.");
        int exitCode = 0;
        try {
            AppConfig config = new AppConfig();
            NotaFiscalService service = new NotaFiscalService(config);
            List<NotaFiscal> notas = service.buscarTodas();
            ExcelReportGenerator generator = new ExcelReportGenerator(config.getOutputDir());
            String path = generator.gerar(notas);
            LOG.info("Fim da execução. Arquivo gerado: {}", path);
        } catch (Throwable t) {
            LOG.error("Erro na execução: " + t.getMessage(), t);
            exitCode = 1;
        }
        System.exit(exitCode);
    }
}
