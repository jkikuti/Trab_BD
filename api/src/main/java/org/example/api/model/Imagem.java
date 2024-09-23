package org.example.api.model;

public class Imagem {
    private Integer id;
    private String imagemUrl;
    private Integer idActionFigure;

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public Integer getIdActionFigure() {
        return idActionFigure;
    }

    public void setIdActionFigure(Integer idActionFigure) {
        this.idActionFigure = idActionFigure;
    }
}
