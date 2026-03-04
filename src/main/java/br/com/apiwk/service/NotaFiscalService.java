package br.com.apiwk.service;

import br.com.apiwk.client.NotaFiscalClient;
import br.com.apiwk.config.AppConfig;
import br.com.apiwk.model.NotaFiscal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Camada de serviço que orquestra a obtenção das notas fiscais via NotaFiscalClient.
 * A API GET /api/comercial/v1/nota-fiscal retorna um array único (sem paginação documentada);
 * se no futuro a API suportar paginação, o loop de páginas pode ser implementado aqui.
 */
public class NotaFiscalService {

    private static final Logger LOG = LoggerFactory.getLogger(NotaFiscalService.class);

    private final AppConfig config;

    public NotaFiscalService(AppConfig config) {
        this.config = config;
    }

    /**
     * Busca todas as notas fiscais conforme filtros da configuração.
     * Uma única chamada à API; quantidade total é a size da lista retornada.
     */
    public List<NotaFiscal> buscarTodas() throws Exception {
        NotaFiscalClient client = new NotaFiscalClient(config);
        String url = config.getApiBaseUrl().replaceAll("/$", "") + "/api/comercial/v1/nota-fiscal";
        LOG.info("Chamando API: {} (filtros conforme config.properties)", url);

        List<NotaFiscal> lista = client.fetchNotasFiscais();
        LOG.info("Total de registros obtidos: {}", lista == null ? 0 : lista.size());
        return lista;
    }
}
