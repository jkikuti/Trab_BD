package org.example.api.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Endereco {

    private Integer id;

    @NotNull(message = "Número do endereço não pode estar vazio")
    private Integer numero;

    @Size(max = 200, message = "Complemento não pode ter mais de 200 caracteres")
    private String complemento;

    private Integer idCep;

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Integer getIdCep() {
        return idCep;
    }

    public void setIdCep(Integer idCep) {
        this.idCep = idCep;
    }
}
