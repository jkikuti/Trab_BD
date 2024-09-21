package org.example.api.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Logradouro {

    private Integer id;

    @NotEmpty(message = "Nome do logradouro não pode estar vazio")
    @Size(max = 200, message = "Nome do logradouro não pode ter mais de 200 caracteres")
    private String nome;

    @NotEmpty(message = "Tipo do logradouro não pode estar vazio")
    @Size(max = 200, message = "Tipo do logradouro não pode ter mais de 200 caracteres")
    private String tipo;

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Integer idCidade) {
        this.idCidade = idCidade;
    }
}
