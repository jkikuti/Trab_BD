package org.example.api.repository;

import org.example.api.model.ActionFigureMaterial;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ActionFigureMaterialRepository {

    private final JdbcTemplate jdbcTemplate;

    public ActionFigureMaterialRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ActionFigureMaterial> findAll() {
        String sql = "SELECT id_action_figure, id_material FROM loja.action_figure_material";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            ActionFigureMaterial actionFigureMaterial = new ActionFigureMaterial();
            actionFigureMaterial.setIdActionFigure(rs.getInt("id_action_figure"));
            actionFigureMaterial.setIdMaterial(rs.getInt("id_material"));
            return actionFigureMaterial;
        });
    }

    public ActionFigureMaterial findById(Integer idActionFigure, Integer idMaterial) {
        String sql = "SELECT id_action_figure, id_material FROM loja.action_figure_material WHERE id_action_figure = ? AND id_material = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{idActionFigure, idMaterial}, (rs, rowNum) -> {
            ActionFigureMaterial actionFigureMaterial = new ActionFigureMaterial();
            actionFigureMaterial.setIdActionFigure(rs.getInt("id_action_figure"));
            actionFigureMaterial.setIdMaterial(rs.getInt("id_material"));
            return actionFigureMaterial;
        });
    }

    public ActionFigureMaterial save(ActionFigureMaterial actionFigureMaterial) {
        String sql = "INSERT INTO loja.action_figure_material (id_action_figure, id_material) VALUES (?, ?)";
        jdbcTemplate.update(sql, actionFigureMaterial.getIdActionFigure(), actionFigureMaterial.getIdMaterial());
        return actionFigureMaterial;
    }

    public void delete(Integer idActionFigure, Integer idMaterial) {
        String sql = "DELETE FROM loja.action_figure_material WHERE id_action_figure = ? AND id_material = ?";
        jdbcTemplate.update(sql, idActionFigure, idMaterial);
    }
}
