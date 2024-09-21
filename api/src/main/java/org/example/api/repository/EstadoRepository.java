package org.example.api.repository;

import org.example.api.model.Estado;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EstadoRepository {

    private final JdbcTemplate jdbcTemplate;

    public EstadoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Estado> findAll() {
        String sql = "SELECT id, sigla_estado FROM loja.estado";
        return jdbcTemplate.query(sql, new EstadoRowMapper());
    }

    public Estado findById(Integer id) {
        String sql = "SELECT id, sigla_estado FROM loja.estado WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new EstadoRowMapper(), id);
    }

    public Estado findBySigla(String siglaEstado) {
        String sql = "SELECT id, sigla_estado FROM loja.estado WHERE sigla_estado = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new EstadoRowMapper(), siglaEstado);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Estado save(Estado estado) {
        String sql = "INSERT INTO loja.estado (sigla_estado) VALUES (?) RETURNING id";
        Integer generatedId = jdbcTemplate.queryForObject(sql, new Object[]{estado.getSiglaEstado()}, Integer.class);
        return findById(generatedId);
    }

    public Estado update(Integer id, Estado estado) {
        String sql = "UPDATE loja.estado SET sigla_estado = ? WHERE id = ?";
        jdbcTemplate.update(sql, estado.getSiglaEstado(), id);
        return findById(id);
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM loja.estado WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class EstadoRowMapper implements RowMapper<Estado> {
        @Override
        public Estado mapRow(ResultSet rs, int rowNum) throws SQLException {
            Estado estado = new Estado();
            estado.setId(rs.getInt("id"));
            estado.setSiglaEstado(rs.getString("sigla_estado"));
            return estado;
        }
    }
}
