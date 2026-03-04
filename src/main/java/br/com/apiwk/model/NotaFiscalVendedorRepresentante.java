package br.com.apiwk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NotaFiscalVendedorRepresentante {
    private String id;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
}
