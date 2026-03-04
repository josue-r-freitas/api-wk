package br.com.seuprojeto.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Carrega e expõe as configurações da aplicação a partir de config.properties.
 * Ordem de carregamento: 1) -Dconfig.file=... 2) config.properties no classpath.
 */
public class AppConfig {

    private static final Logger LOG = LoggerFactory.getLogger(AppConfig.class);
    private static final String CONFIG_FILE_PROP = "config.file";
    private static final String CONFIG_CLASSPATH = "config.properties";

    private final Properties props;

    public AppConfig() throws IOException {
        this.props = new Properties();
        load();
        validate();
    }

    private void load() throws IOException {
        String configPath = System.getProperty(CONFIG_FILE_PROP);
        if (configPath != null && !configPath.isBlank()) {
            Path path = Paths.get(configPath);
            if (Files.isRegularFile(path)) {
                try (InputStream is = Files.newInputStream(path)) {
                    props.load(is);
                    LOG.info("Configuração carregada de: {}", path.toAbsolutePath());
                    return;
                }
            }
            LOG.warn("Arquivo de config não encontrado: {}, usando classpath", configPath);
        }
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(CONFIG_CLASSPATH)) {
            if (is != null) {
                props.load(is);
                LOG.info("Configuração carregada do classpath: {}", CONFIG_CLASSPATH);
            }
        }
    }

    private void validate() {
        if (getApiBaseUrl() == null || getApiBaseUrl().isBlank()) {
            throw new IllegalStateException("api.base.url é obrigatório em config.properties (ou -Dconfig.file=...)");
        }
        if (getApiAuthToken() == null || getApiAuthToken().isBlank()) {
            throw new IllegalStateException("api.auth.token é obrigatório em config.properties (nunca hardcode no código)");
        }
    }

    public String getApiBaseUrl() {
        return props.getProperty("api.base.url", "").trim();
    }

    public String getApiAuthToken() {
        return props.getProperty("api.auth.token", "").trim();
    }

    public int getApiTimeoutMs() {
        String v = props.getProperty("api.timeout.ms", "30000").trim();
        try {
            return Integer.parseInt(v);
        } catch (NumberFormatException e) {
            return 30000;
        }
    }

    public String getOutputDir() {
        return props.getProperty("output.dir", "./output").trim();
    }

    public String getFiltroDataEmissaoInicial() {
        return emptyToNull(props.getProperty("filtro.data.emissao.inicial"));
    }

    public String getFiltroDataEmissaoFinal() {
        return emptyToNull(props.getProperty("filtro.data.emissao.final"));
    }

    public Integer getFiltroNumeroInicial() {
        return parseInteger(props.getProperty("filtro.numero.inicial"));
    }

    public Integer getFiltroNumeroFinal() {
        return parseInteger(props.getProperty("filtro.numero.final"));
    }

    public String getFiltroIdFilial() {
        return emptyToNull(props.getProperty("filtro.id.filial"));
    }

    /** Lista de IDs para query parameter Ids (array). */
    public List<String> getFiltroIds() {
        return splitList(props.getProperty("filtro.ids"));
    }

    /** Lista para query parameter CodigosIntegrador (array). */
    public List<String> getFiltroCodigosIntegrador() {
        return splitList(props.getProperty("filtro.codigos.integrador"));
    }

    /** Lista para query parameter Fields (Data Shaping). */
    public List<String> getFiltroFields() {
        return splitList(props.getProperty("filtro.fields"));
    }

    private static String emptyToNull(String s) {
        if (s == null || s.isBlank()) return null;
        return s.trim();
    }

    private static Integer parseInteger(String s) {
        if (s == null || s.isBlank()) return null;
        try {
            return Integer.parseInt(s.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static List<String> splitList(String s) {
        if (s == null || s.isBlank()) return Collections.emptyList();
        return Stream.of(s.split(","))
                .map(String::trim)
                .filter(v -> !v.isEmpty())
                .collect(Collectors.toList());
    }
}
