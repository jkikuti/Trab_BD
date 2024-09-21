package org.example.api.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


public class Estado {

    private Integer id;
    
    @NotEmpty(message = "Sigla do estado não pode estar vazia")
    @Size(max = 2, message = "Sigla do estado deve ter 2 caracteres")
    private String siglaEstado;

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSiglaEstado() {
        return siglaEstado;
    }

    public void setSiglaEstado(String siglaEstado) {
        this.siglaEstado = siglaEstado;
    }
}
