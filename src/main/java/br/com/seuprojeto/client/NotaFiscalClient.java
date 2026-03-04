package br.com.seuprojeto.client;

import br.com.seuprojeto.config.AppConfig;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.net.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import br.com.seuprojeto.model.NotaFiscal;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Cliente HTTP para o endpoint GET /api/comercial/v1/nota-fiscal da API WK Radar.
 * Utiliza Bearer Token, timeout configurável e retry em falhas transitórias (5xx e rede).
 */
public class NotaFiscalClient {

    private static final Logger LOG = LoggerFactory.getLogger(NotaFiscalClient.class);
    private static final String PATH_NOTAS_FISCAIS = "/api/comercial/v1/nota-fiscal";
    private static final int MAX_RETRIES = 3;
    private static final long[] BACKOFF_MS = { 1000, 2000 };

    private final AppConfig config;
    private final ObjectMapper objectMapper;

    public NotaFiscalClient(AppConfig config) {
        this.config = config;
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Monta a URL base + path e adiciona os query parameters conforme config.
     */
    public URI buildUri() throws Exception {
        String base = config.getApiBaseUrl().replaceAll("/$", "");
        URIBuilder builder = new URIBuilder(base + PATH_NOTAS_FISCAIS);

        if (config.getFiltroDataEmissaoInicial() != null) {
            builder.setParameter("DataEmissaoInicial", config.getFiltroDataEmissaoInicial());
        }
        if (config.getFiltroDataEmissaoFinal() != null) {
            builder.setParameter("DataEmissaoFinal", config.getFiltroDataEmissaoFinal());
        }
        if (config.getFiltroNumeroInicial() != null) {
            builder.setParameter("NumeroInicial", String.valueOf(config.getFiltroNumeroInicial()));
        }
        if (config.getFiltroNumeroFinal() != null) {
            builder.setParameter("NumeroFinal", String.valueOf(config.getFiltroNumeroFinal()));
        }
        if (config.getFiltroIdFilial() != null) {
            builder.setParameter("IdFilial", config.getFiltroIdFilial());
        }
        for (String id : config.getFiltroIds()) {
            builder.addParameter("Ids", id);
        }
        for (String cod : config.getFiltroCodigosIntegrador()) {
            builder.addParameter("CodigosIntegrador", cod);
        }
        for (String field : config.getFiltroFields()) {
            builder.addParameter("Fields", field);
        }

        return builder.build();
    }

    /**
     * Executa a requisição GET com retry (até 3 tentativas) em caso de 5xx ou erro de rede.
     */
    public List<NotaFiscal> fetchNotasFiscais() throws Exception {
        URI uri = buildUri();
        int timeoutMs = config.getApiTimeoutMs();

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(timeoutMs, TimeUnit.MILLISECONDS)
                .setResponseTimeout(timeoutMs, TimeUnit.MILLISECONDS)
                .build();

        CloseableHttpClient client = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .build();

        HttpGet get = new HttpGet(uri);
        get.setHeader("Authorization", "Bearer " + config.getApiAuthToken());
        get.setHeader("Accept", "application/json");

        Exception lastException = null;
        for (int attempt = 1; attempt <= MAX_RETRIES; attempt++) {
            try {
                LOG.debug("Tentativa {} de {}: GET {}", attempt, MAX_RETRIES, uri);
                try (CloseableHttpResponse response = client.execute(get)) {
                    int code = response.getCode();
                    String body = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);

                    if (code >= 200 && code < 300) {
                        return objectMapper.readValue(body, new TypeReference<List<NotaFiscal>>() {});
                    }

                    if (code >= 400 && code < 500 && code != 429) {
                        LOG.error("Resposta HTTP {}: {}", code, body);
                        throw new NotaFiscalClientException("API retornou " + code + ": " + body);
                    }
                    // 429 (Too Many Requests): retry com backoff, conforme doc WK Radar (throttling 4 req/s)
                    if (code == 429) {
                        LOG.warn("Throttling 429 (tentativa {}/{}). Aguardando backoff.", attempt, MAX_RETRIES);
                    }

                    // 5xx ou 429: considerar retry
                    lastException = new NotaFiscalClientException("HTTP " + code + ": " + body);
                    LOG.warn("Resposta HTTP {} (tentativa {}/{}): {}", code, attempt, MAX_RETRIES, body);
                }
            } catch (NotaFiscalClientException e) {
                throw e;
            } catch (Exception e) {
                lastException = e;
                LOG.warn("Erro na tentativa {}/{}: {}", attempt, MAX_RETRIES, e.getMessage());
            }

            if (attempt < MAX_RETRIES) {
                long sleep = BACKOFF_MS[Math.min(attempt - 1, BACKOFF_MS.length - 1)];
                LOG.info("Aguardando {} ms antes da próxima tentativa.", sleep);
                Thread.sleep(sleep);
            }
        }

        if (lastException != null) {
            if (lastException instanceof RuntimeException) {
                throw (RuntimeException) lastException;
            }
            throw new Exception(lastException);
        }
        throw new NotaFiscalClientException("Falha após " + MAX_RETRIES + " tentativas.");
    }

    public static class NotaFiscalClientException extends RuntimeException {
        public NotaFiscalClientException(String message) {
            super(message);
        }
        public NotaFiscalClientException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
