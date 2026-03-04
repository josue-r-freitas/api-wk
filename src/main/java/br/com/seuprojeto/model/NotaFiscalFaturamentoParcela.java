package br.com.apiwk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NotaFiscalFaturamentoParcela {
    private Integer numeroParcela;
    private String idFormaPagamento;
    private String dataVencimento;
    private Double valor;
    private String observacoes;

    public Integer getNumeroParcela() { return numeroParcela; }
    public void setNumeroParcela(Integer numeroParcela) { this.numeroParcela = numeroParcela; }
    public String getIdFormaPagamento() { return idFormaPagamento; }
    public void setIdFormaPagamento(String idFormaPagamento) { this.idFormaPagamento = idFormaPagamento; }
    public String getDataVencimento() { return dataVencimento; }
    public void setDataVencimento(String dataVencimento) { this.dataVencimento = dataVencimento; }
    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
}
