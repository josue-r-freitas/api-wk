package br.com.apiwk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

/**
 * Faturamento da nota fiscal (conforme API WK Radar ReadNotaFiscalFaturamentoDto).
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotaFiscalFaturamento {
    private Double valorTotalAPagar;
    private Double valorDesconto;
    private Double valorAcrescimo;
    private String idCobranca;
    private String idCondicaoPagamento;
    private List<NotaFiscalFaturamentoParcela> parcelas;

    public Double getValorTotalAPagar() { return valorTotalAPagar; }
    public void setValorTotalAPagar(Double valorTotalAPagar) { this.valorTotalAPagar = valorTotalAPagar; }
    public Double getValorDesconto() { return valorDesconto; }
    public void setValorDesconto(Double valorDesconto) { this.valorDesconto = valorDesconto; }
    public Double getValorAcrescimo() { return valorAcrescimo; }
    public void setValorAcrescimo(Double valorAcrescimo) { this.valorAcrescimo = valorAcrescimo; }
    public String getIdCobranca() { return idCobranca; }
    public void setIdCobranca(String idCobranca) { this.idCobranca = idCobranca; }
    public String getIdCondicaoPagamento() { return idCondicaoPagamento; }
    public void setIdCondicaoPagamento(String idCondicaoPagamento) { this.idCondicaoPagamento = idCondicaoPagamento; }
    public List<NotaFiscalFaturamentoParcela> getParcelas() { return parcelas; }
    public void setParcelas(List<NotaFiscalFaturamentoParcela> parcelas) { this.parcelas = parcelas; }
}
