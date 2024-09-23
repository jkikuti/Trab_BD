package org.example.api.repository;

import org.example.api.model.Material;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class MaterialRepository {

    private final JdbcTemplate jdbcTemplate;

    public MaterialRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Material> findAll() {
        String sql = "SELECT id, tipo_material FROM loja.material";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Material material = new Material();
            material.setId(rs.getInt("id"));
            material.setTipoMaterial(rs.getString("tipo_material"));
            return material;
        });
    }

    public Material findById(Integer id) {
        String sql = "SELECT id, tipo_material FROM loja.material WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            Material material = new Material();
            material.setId(rs.getInt("id"));
            material.setTipoMaterial(rs.getString("tipo_material"));
            return material;
        });
    }

    public Material save(Material material) {
        String sql = "INSERT INTO loja.material (tipo_material) VALUES (?) RETURNING id";
        Integer generatedId = jdbcTemplate.queryForObject(sql, new Object[]{material.getTipoMaterial()}, Integer.class);
        return findById(generatedId);
    }

    public Material update(Integer id, Material material) {
        String sql = "UPDATE loja.material SET tipo_material = ? WHERE id = ?";
        jdbcTemplate.update(sql, material.getTipoMaterial(), id);
        return findById(id);
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM loja.material WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
