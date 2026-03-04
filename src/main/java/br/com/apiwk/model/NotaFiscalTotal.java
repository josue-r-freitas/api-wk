package br.com.apiwk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Totais da nota fiscal (conforme API WK Radar ReadNotaFiscalTotalDto).
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotaFiscalTotal {
    private Double valorTotalProdutos;
    private Double valorDescontoProdutos;
    private Double percentualDescontoProdutos;
    private Boolean descontoProdutosEmPercentual;
    private Double valorFrete;
    private Double valorSeguro;
    private Double valorDespesasAcessorias;
    private Double valorTotalNotaFiscal;
    private Double valorLiquidoNotaFiscal;
    private Double baseCalculoIcms;
    private Double valorIcms;
    private Double valorTotalAproximadoTributos;

    public Double getValorTotalProdutos() { return valorTotalProdutos; }
    public void setValorTotalProdutos(Double valorTotalProdutos) { this.valorTotalProdutos = valorTotalProdutos; }
    public Double getValorDescontoProdutos() { return valorDescontoProdutos; }
    public void setValorDescontoProdutos(Double valorDescontoProdutos) { this.valorDescontoProdutos = valorDescontoProdutos; }
    public Double getPercentualDescontoProdutos() { return percentualDescontoProdutos; }
    public void setPercentualDescontoProdutos(Double percentualDescontoProdutos) { this.percentualDescontoProdutos = percentualDescontoProdutos; }
    public Boolean getDescontoProdutosEmPercentual() { return descontoProdutosEmPercentual; }
    public void setDescontoProdutosEmPercentual(Boolean descontoProdutosEmPercentual) { this.descontoProdutosEmPercentual = descontoProdutosEmPercentual; }
    public Double getValorFrete() { return valorFrete; }
    public void setValorFrete(Double valorFrete) { this.valorFrete = valorFrete; }
    public Double getValorSeguro() { return valorSeguro; }
    public void setValorSeguro(Double valorSeguro) { this.valorSeguro = valorSeguro; }
    public Double getValorDespesasAcessorias() { return valorDespesasAcessorias; }
    public void setValorDespesasAcessorias(Double valorDespesasAcessorias) { this.valorDespesasAcessorias = valorDespesasAcessorias; }
    public Double getValorTotalNotaFiscal() { return valorTotalNotaFiscal; }
    public void setValorTotalNotaFiscal(Double valorTotalNotaFiscal) { this.valorTotalNotaFiscal = valorTotalNotaFiscal; }
    public Double getValorLiquidoNotaFiscal() { return valorLiquidoNotaFiscal; }
    public void setValorLiquidoNotaFiscal(Double valorLiquidoNotaFiscal) { this.valorLiquidoNotaFiscal = valorLiquidoNotaFiscal; }
    public Double getBaseCalculoIcms() { return baseCalculoIcms; }
    public void setBaseCalculoIcms(Double baseCalculoIcms) { this.baseCalculoIcms = baseCalculoIcms; }
    public Double getValorIcms() { return valorIcms; }
    public void setValorIcms(Double valorIcms) { this.valorIcms = valorIcms; }
    public Double getValorTotalAproximadoTributos() { return valorTotalAproximadoTributos; }
    public void setValorTotalAproximadoTributos(Double valorTotalAproximadoTributos) { this.valorTotalAproximadoTributos = valorTotalAproximadoTributos; }
}
