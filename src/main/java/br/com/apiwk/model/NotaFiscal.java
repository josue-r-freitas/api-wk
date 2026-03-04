package br.com.apiwk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Modelo principal da Nota Fiscal conforme resposta do endpoint
 * GET /api/comercial/v1/nota-fiscal (ReadNotaFiscalDto - API WK Radar).
 * Estrutura alinhada à documentação https://api-docs.wk.com.br/718/
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotaFiscal {

    private String id;
    private String codigoIntegrador;
    private String chave;
    private String origemNF;
    private List<NotaFiscalPedidoRef> pedidos;
    private String idFilial;
    private String tipo;           // Entrada, Saída (enum TipoNfDto)
    private String finalidade;
    private String finalidadeNfNormalComplemento;
    private String finalidadeNfAjusteComplemento;
    private String serie;
    private Integer numero;
    private String idDocumento;
    private String leiaute;
    private String dataEmissao;
    private String dataEntradaSaida;
    private String indicadorPresencaComprador;
    private String idClienteFornecedor;
    private String consumidorFinal;
    private NotaFiscalLocalEntrega localEntrega;
    private Object localEmbarque;   // ReadNotaFiscalLocalEmbarqueDto - deserializa como objeto genérico se necessário
    private List<NotaFiscalVendedorRepresentante> vendedores;
    private List<NotaFiscalVendedorRepresentante> representantes;
    private String idOperacaoComercial;
    private String idNaturezaOperacaoProduto;
    private String idNaturezaOperacaoServico;
    private String idClassificacao;
    private List<NotaFiscalItem> itens;
    private NotaFiscalFaturamento faturamento;
    private NotaFiscalTotal total;
    private Object transporte;
    private Object volume;
    private Object complemento;
    private Object nfe;
    private Integer itemNfReferida;
    private List<?> nfsReferidas;
    private String tipoRateio;
    private List<?> rateios;
    private Object informacoesExtras;
    private Object listaInfoPlus;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getCodigoIntegrador() { return codigoIntegrador; }
    public void setCodigoIntegrador(String codigoIntegrador) { this.codigoIntegrador = codigoIntegrador; }

    public String getChave() { return chave; }
    public void setChave(String chave) { this.chave = chave; }

    public String getOrigemNF() { return origemNF; }
    public void setOrigemNF(String origemNF) { this.origemNF = origemNF; }

    public List<NotaFiscalPedidoRef> getPedidos() { return pedidos; }
    public void setPedidos(List<NotaFiscalPedidoRef> pedidos) { this.pedidos = pedidos; }

    public String getIdFilial() { return idFilial; }
    public void setIdFilial(String idFilial) { this.idFilial = idFilial; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getFinalidade() { return finalidade; }
    public void setFinalidade(String finalidade) { this.finalidade = finalidade; }

    public String getFinalidadeNfNormalComplemento() { return finalidadeNfNormalComplemento; }
    public void setFinalidadeNfNormalComplemento(String finalidadeNfNormalComplemento) { this.finalidadeNfNormalComplemento = finalidadeNfNormalComplemento; }

    public String getFinalidadeNfAjusteComplemento() { return finalidadeNfAjusteComplemento; }
    public void setFinalidadeNfAjusteComplemento(String finalidadeNfAjusteComplemento) { this.finalidadeNfAjusteComplemento = finalidadeNfAjusteComplemento; }

    public String getSerie() { return serie; }
    public void setSerie(String serie) { this.serie = serie; }

    public Integer getNumero() { return numero; }
    public void setNumero(Integer numero) { this.numero = numero; }

    public String getIdDocumento() { return idDocumento; }
    public void setIdDocumento(String idDocumento) { this.idDocumento = idDocumento; }

    public String getLeiaute() { return leiaute; }
    public void setLeiaute(String leiaute) { this.leiaute = leiaute; }

    public String getDataEmissao() { return dataEmissao; }
    public void setDataEmissao(String dataEmissao) { this.dataEmissao = dataEmissao; }

    public String getDataEntradaSaida() { return dataEntradaSaida; }
    public void setDataEntradaSaida(String dataEntradaSaida) { this.dataEntradaSaida = dataEntradaSaida; }

    public String getIndicadorPresencaComprador() { return indicadorPresencaComprador; }
    public void setIndicadorPresencaComprador(String indicadorPresencaComprador) { this.indicadorPresencaComprador = indicadorPresencaComprador; }

    public String getIdClienteFornecedor() { return idClienteFornecedor; }
    public void setIdClienteFornecedor(String idClienteFornecedor) { this.idClienteFornecedor = idClienteFornecedor; }

    public String getConsumidorFinal() { return consumidorFinal; }
    public void setConsumidorFinal(String consumidorFinal) { this.consumidorFinal = consumidorFinal; }

    public NotaFiscalLocalEntrega getLocalEntrega() { return localEntrega; }
    public void setLocalEntrega(NotaFiscalLocalEntrega localEntrega) { this.localEntrega = localEntrega; }

    public Object getLocalEmbarque() { return localEmbarque; }
    public void setLocalEmbarque(Object localEmbarque) { this.localEmbarque = localEmbarque; }

    public List<NotaFiscalVendedorRepresentante> getVendedores() { return vendedores; }
    public void setVendedores(List<NotaFiscalVendedorRepresentante> vendedores) { this.vendedores = vendedores; }

    public List<NotaFiscalVendedorRepresentante> getRepresentantes() { return representantes; }
    public void setRepresentantes(List<NotaFiscalVendedorRepresentante> representantes) { this.representantes = representantes; }

    public String getIdOperacaoComercial() { return idOperacaoComercial; }
    public void setIdOperacaoComercial(String idOperacaoComercial) { this.idOperacaoComercial = idOperacaoComercial; }

    public String getIdNaturezaOperacaoProduto() { return idNaturezaOperacaoProduto; }
    public void setIdNaturezaOperacaoProduto(String idNaturezaOperacaoProduto) { this.idNaturezaOperacaoProduto = idNaturezaOperacaoProduto; }

    public String getIdNaturezaOperacaoServico() { return idNaturezaOperacaoServico; }
    public void setIdNaturezaOperacaoServico(String idNaturezaOperacaoServico) { this.idNaturezaOperacaoServico = idNaturezaOperacaoServico; }

    public String getIdClassificacao() { return idClassificacao; }
    public void setIdClassificacao(String idClassificacao) { this.idClassificacao = idClassificacao; }

    public List<NotaFiscalItem> getItens() { return itens; }
    public void setItens(List<NotaFiscalItem> itens) { this.itens = itens; }

    public NotaFiscalFaturamento getFaturamento() { return faturamento; }
    public void setFaturamento(NotaFiscalFaturamento faturamento) { this.faturamento = faturamento; }

    public NotaFiscalTotal getTotal() { return total; }
    public void setTotal(NotaFiscalTotal total) { this.total = total; }

    public Object getTransporte() { return transporte; }
    public void setTransporte(Object transporte) { this.transporte = transporte; }

    public Object getVolume() { return volume; }
    public void setVolume(Object volume) { this.volume = volume; }

    public Object getComplemento() { return complemento; }
    public void setComplemento(Object complemento) { this.complemento = complemento; }

    public Object getNfe() { return nfe; }
    public void setNfe(Object nfe) { this.nfe = nfe; }

    public Integer getItemNfReferida() { return itemNfReferida; }
    public void setItemNfReferida(Integer itemNfReferida) { this.itemNfReferida = itemNfReferida; }

    public List<?> getNfsReferidas() { return nfsReferidas; }
    public void setNfsReferidas(List<?> nfsReferidas) { this.nfsReferidas = nfsReferidas; }

    public String getTipoRateio() { return tipoRateio; }
    public void setTipoRateio(String tipoRateio) { this.tipoRateio = tipoRateio; }

    public List<?> getRateios() { return rateios; }
    public void setRateios(List<?> rateios) { this.rateios = rateios; }

    public Object getInformacoesExtras() { return informacoesExtras; }
    public void setInformacoesExtras(Object informacoesExtras) { this.informacoesExtras = informacoesExtras; }

    public Object getListaInfoPlus() { return listaInfoPlus; }
    public void setListaInfoPlus(Object listaInfoPlus) { this.listaInfoPlus = listaInfoPlus; }
}
