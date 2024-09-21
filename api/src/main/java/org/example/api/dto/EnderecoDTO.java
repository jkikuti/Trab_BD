package org.example.api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class EnderecoDTO {

    @NotEmpty(message = "Estado não pode estar vazio")
    private String estado;

    @NotEmpty(message = "Cidade não pode estar vazia")
    private String cidade;

    @NotEmpty(message = "CEP não pode estar vazio")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP deve estar no formato 00000-000")
    private String cep;

    @NotEmpty(message = "Logradouro não pode estar vazio")
    private String logradouro;

    @NotEmpty(message = "Tipo de logradouro não pode estar vazio")
    private String tipoLogradouro;

    @NotNull(message = "Número não pode estar vazio")
    private Integer numero;

    private String complemento;

    @NotEmpty(message = "Bairro não pode estar vazio")
    private String bairro;

    // Getters e Setters
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    public String getLogradouro() { return logradouro; }
    public void setLogradouro(String logradouro) { this.logradouro = logradouro; }

    public String getTipoLogradouro() { return tipoLogradouro; }
    public void setTipoLogradouro(String tipoLogradouro) { this.tipoLogradouro = tipoLogradouro; }

    public Integer getNumero() { return numero; }
    public void setNumero(Integer numero) { this.numero = numero; }

    public String getComplemento() { return complemento; }
    public void setComplemento(String complemento) { this.complemento = complemento; }

    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }
}
