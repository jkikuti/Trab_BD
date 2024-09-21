package org.example.api.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Cidade {

    private Integer id;

    @NotEmpty(message = "Nome da cidade não pode estar vazio")
    @Size(max = 200, message = "Nome da cidade não pode ter mais de 200 caracteres")
    private String nome;

    private Integer idEstado;

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

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }
}
