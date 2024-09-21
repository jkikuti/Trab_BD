package org.example.api.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Bairro {

    private Integer id;

    @NotEmpty(message = "Nome do bairro não pode estar vazio")
    @Size(max = 200, message = "Nome do bairro não pode ter mais de 200 caracteres")
    private String nome;

    private Integer idCidade;

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Integer idCidade) {
        this.idCidade = idCidade;
    }
}
