package br.com.seuprojeto.report;

import br.com.seuprojeto.model.NotaFiscal;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Gera arquivo Excel (.xlsx) com os dados das notas fiscais.
 * Nome do arquivo: notas-fiscais_YYYY-MM-DD_HH-mm.xlsx
 * Diretório de saída configurável (output.dir); criado se não existir.
 */
public class ExcelReportGenerator {

    private static final Logger LOG = LoggerFactory.getLogger(ExcelReportGenerator.class);
    private static final DateTimeFormatter FILE_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm");
    private static final String SHEET_NAME = "Notas Fiscais";

    private final String outputDir;

    public ExcelReportGenerator(String outputDir) {
        this.outputDir = outputDir;
    }

    /**
     * Gera o arquivo .xlsx com uma aba contendo os registros e retorna o caminho absoluto do arquivo.
     */
    public String gerar(List<NotaFiscal> notas) throws IOException {
        Path dir = Paths.get(outputDir);
        if (!Files.exists(dir)) {
            Files.createDirectories(dir);
            LOG.info("Diretório de saída criado: {}", dir.toAbsolutePath());
        }

        String fileName = "notas-fiscais_" + LocalDateTime.now().format(FILE_DATE) + ".xlsx";
        Path filePath = dir.resolve(fileName);

        try (Workbook wb = new XSSFWorkbook()) {
            Sheet sheet = wb.createSheet(SHEET_NAME);
            CellStyle headerStyle = createHeaderStyle(wb);

            int rowNum = 0;
            // Cabeçalho: nomes dos campos alinhados à API
            Row headerRow = sheet.createRow(rowNum++);
            String[] headers = {
                    "id", "codigoIntegrador", "chave", "origemNF", "idFilial", "tipo", "finalidade",
                    "serie", "numero", "idDocumento", "leiaute", "dataEmissao", "dataEntradaSaida",
                    "indicadorPresencaComprador", "idClienteFornecedor", "consumidorFinal",
                    "localEntrega_nome", "localEntrega_cpfCnpj", "localEntrega_logradouro", "localEntrega_numero", "localEntrega_bairro", "localEntrega_cep", "localEntrega_municipio",
                    "idOperacaoComercial", "idNaturezaOperacaoProduto", "idNaturezaOperacaoServico", "idClassificacao",
                    "total_valorTotalNotaFiscal", "total_valorLiquidoNotaFiscal", "total_valorTotalProdutos", "total_valorIcms",
                    "faturamento_valorTotalAPagar", "quantidadeItens"
            };
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            for (NotaFiscal nf : notas) {
                Row row = sheet.createRow(rowNum++);
                int col = 0;
                setCell(row, col++, nf.getId());
                setCell(row, col++, nf.getCodigoIntegrador());
                setCell(row, col++, nf.getChave());
                setCell(row, col++, nf.getOrigemNF());
                setCell(row, col++, nf.getIdFilial());
                setCell(row, col++, nf.getTipo());
                setCell(row, col++, nf.getFinalidade());
                setCell(row, col++, nf.getSerie());
                setCell(row, col++, nf.getNumero());
                setCell(row, col++, nf.getIdDocumento());
                setCell(row, col++, nf.getLeiaute());
                setCell(row, col++, nf.getDataEmissao());
                setCell(row, col++, nf.getDataEntradaSaida());
                setCell(row, col++, nf.getIndicadorPresencaComprador());
                setCell(row, col++, nf.getIdClienteFornecedor());
                setCell(row, col++, nf.getConsumidorFinal());
                if (nf.getLocalEntrega() != null) {
                    setCell(row, col++, nf.getLocalEntrega().getNome());
                    setCell(row, col++, nf.getLocalEntrega().getCpfCnpj());
                    setCell(row, col++, nf.getLocalEntrega().getLogradouro());
                    setCell(row, col++, nf.getLocalEntrega().getNumero());
                    setCell(row, col++, nf.getLocalEntrega().getBairro());
                    setCell(row, col++, nf.getLocalEntrega().getCep());
                    setCell(row, col++, nf.getLocalEntrega().getIdMunicipio());
                } else {
                    col += 7;
                }
                setCell(row, col++, nf.getIdOperacaoComercial());
                setCell(row, col++, nf.getIdNaturezaOperacaoProduto());
                setCell(row, col++, nf.getIdNaturezaOperacaoServico());
                setCell(row, col++, nf.getIdClassificacao());
                if (nf.getTotal() != null) {
                    setCell(row, col++, nf.getTotal().getValorTotalNotaFiscal());
                    setCell(row, col++, nf.getTotal().getValorLiquidoNotaFiscal());
                    setCell(row, col++, nf.getTotal().getValorTotalProdutos());
                    setCell(row, col++, nf.getTotal().getValorIcms());
                } else {
                    col += 4;
                }
                if (nf.getFaturamento() != null) {
                    setCell(row, col++, nf.getFaturamento().getValorTotalAPagar());
                } else {
                    col++;
                }
                setCell(row, col, nf.getItens() != null ? nf.getItens().size() : 0);
            }

            try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
                wb.write(fos);
            }
        }

        String absolutePath = filePath.toAbsolutePath().toString();
        LOG.info("Planilha gerada: {}", absolutePath);
        return absolutePath;
    }

    private static CellStyle createHeaderStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setBold(true);
        style.setFont(font);
        return style;
    }

    private static void setCell(Row row, int column, Object value) {
        Cell cell = row.createCell(column);
        if (value == null) {
            cell.setBlank();
        } else if (value instanceof Number) {
            cell.setCellValue(((Number) value).doubleValue());
        } else {
            cell.setCellValue(String.valueOf(value));
        }
    }
}
