package org.example.api.repository;

import org.example.api.model.Imagem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImagemRepository {

    private final JdbcTemplate jdbcTemplate;

    public ImagemRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Imagem> findAll() {
        String sql = "SELECT id, imagem_url, id_action_figure FROM loja.imagem";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Imagem imagem = new Imagem();
            imagem.setId(rs.getInt("id"));
            imagem.setImagemUrl(rs.getString("imagem_url"));
            imagem.setIdActionFigure(rs.getInt("id_action_figure"));
            return imagem;
        });
    }

    public Imagem findById(Integer id) {
        String sql = "SELECT id, imagem_url, id_action_figure FROM loja.imagem WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            Imagem imagem = new Imagem();
            imagem.setId(rs.getInt("id"));
            imagem.setImagemUrl(rs.getString("imagem_url"));
            imagem.setIdActionFigure(rs.getInt("id_action_figure"));
            return imagem;
        });
    }

    public Imagem save(Imagem imagem) {
        String sql = "INSERT INTO loja.imagem (imagem_url, id_action_figure) VALUES (?, ?) RETURNING id";
        Integer generatedId = jdbcTemplate.queryForObject(sql, new Object[]{imagem.getImagemUrl(), imagem.getIdActionFigure()}, Integer.class);
        return findById(generatedId);
    }

    public Imagem update(Integer id, Imagem imagem) {
        String sql = "UPDATE loja.imagem SET imagem_url = ?, id_action_figure = ? WHERE id = ?";
        jdbcTemplate.update(sql, imagem.getImagemUrl(), imagem.getIdActionFigure(), id);
        return findById(id);
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM loja.imagem WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
