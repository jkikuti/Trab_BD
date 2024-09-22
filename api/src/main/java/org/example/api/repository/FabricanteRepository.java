package org.example.api.repository;

import org.example.api.model.Fabricante;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class FabricanteRepository {

    private final JdbcTemplate jdbcTemplate;

    public FabricanteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Fabricante> findAll() {
        String sql = "SELECT id, nome_fabricante FROM loja.fabricante";
        return jdbcTemplate.query(sql, new FabricanteRowMapper());
    }

    public Fabricante findById(Integer id) {
        String sql = "SELECT id, nome_fabricante FROM loja.fabricante WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new FabricanteRowMapper(), id);
    }

    public void save(Fabricante fabricante) {
        String sql = "INSERT INTO loja.fabricante (nome_fabricante) VALUES (?)";
        jdbcTemplate.update(sql, fabricante.getNomeFabricante());
    }

    public void update(Integer id, Fabricante fabricante) {
        String sql = "UPDATE loja.fabricante SET nome_fabricante = ? WHERE id = ?";
        jdbcTemplate.update(sql, fabricante.getNomeFabricante(), id);
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM loja.fabricante WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class FabricanteRowMapper implements RowMapper<Fabricante> {
        @Override
        public Fabricante mapRow(ResultSet rs, int rowNum) throws SQLException {
            Fabricante fabricante = new Fabricante();
            fabricante.setId(rs.getInt("id"));
            fabricante.setNomeFabricante(rs.getString("nome_fabricante"));
            return fabricante;
        }
    }
}
