package br.com.seuprojeto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Item da nota fiscal (campos principais conforme API WK Radar ReadNotaFiscalItemDto).
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotaFiscalItem {
    private String idNaturezaOperacao;
    private String complemento;
    private String classificacaoFiscal;
    private Double quantidadeVenda;
    private Double quantidadeEstoque;
    private Double valorUnitario;
    private Double percentualDesconto;
    private Double valorDescontoTotal;
    private Double valorTotal;

    public String getIdNaturezaOperacao() { return idNaturezaOperacao; }
    public void setIdNaturezaOperacao(String idNaturezaOperacao) { this.idNaturezaOperacao = idNaturezaOperacao; }
    public String getComplemento() { return complemento; }
    public void setComplemento(String complemento) { this.complemento = complemento; }
    public String getClassificacaoFiscal() { return classificacaoFiscal; }
    public void setClassificacaoFiscal(String classificacaoFiscal) { this.classificacaoFiscal = classificacaoFiscal; }
    public Double getQuantidadeVenda() { return quantidadeVenda; }
    public void setQuantidadeVenda(Double quantidadeVenda) { this.quantidadeVenda = quantidadeVenda; }
    public Double getQuantidadeEstoque() { return quantidadeEstoque; }
    public void setQuantidadeEstoque(Double quantidadeEstoque) { this.quantidadeEstoque = quantidadeEstoque; }
    public Double getValorUnitario() { return valorUnitario; }
    public void setValorUnitario(Double valorUnitario) { this.valorUnitario = valorUnitario; }
    public Double getPercentualDesconto() { return percentualDesconto; }
    public void setPercentualDesconto(Double percentualDesconto) { this.percentualDesconto = percentualDesconto; }
    public Double getValorDescontoTotal() { return valorDescontoTotal; }
    public void setValorDescontoTotal(Double valorDescontoTotal) { this.valorDescontoTotal = valorDescontoTotal; }
    public Double getValorTotal() { return valorTotal; }
    public void setValorTotal(Double valorTotal) { this.valorTotal = valorTotal; }
}
