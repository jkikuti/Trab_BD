package org.example.api.repository;

import org.example.api.model.CepEnd;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CepEndRepository {

    private final JdbcTemplate jdbcTemplate;

    public CepEndRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CepEnd> findAll() {
        String sql = "SELECT id, cep, id_logradouro, id_bairro FROM loja.cep_end";
        return jdbcTemplate.query(sql, new CepEndRowMapper());
    }

    public CepEnd findById(Integer id) {
        String sql = "SELECT id, cep, id_logradouro, id_bairro FROM loja.cep_end WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new CepEndRowMapper(), id);
    }

    public CepEnd findByCep(String cep) {
        String sql = "SELECT id, cep, id_logradouro, id_bairro FROM loja.cep_end WHERE cep = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new CepEndRowMapper(), cep);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public CepEnd save(CepEnd cepEnd) {
        String sql = "INSERT INTO loja.cep_end (cep, id_logradouro, id_bairro) VALUES (?, ?, ?) RETURNING id";
        Integer generatedId = jdbcTemplate.queryForObject(sql, new Object[]{cepEnd.getCep(), cepEnd.getIdLogradouro(), cepEnd.getIdBairro()}, Integer.class);
        return findById(generatedId);
    }

    public CepEnd update(Integer id, CepEnd cepEnd) {
        String sql = "UPDATE loja.cep_end SET cep = ?, id_logradouro = ?, id_bairro = ? WHERE id = ?";
        jdbcTemplate.update(sql, cepEnd.getCep(), cepEnd.getIdLogradouro(), cepEnd.getIdBairro(), id);
        return findById(id);
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM loja.cep_end WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class CepEndRowMapper implements RowMapper<CepEnd> {
        @Override
        public CepEnd mapRow(ResultSet rs, int rowNum) throws SQLException {
            CepEnd cepEnd = new CepEnd();
            cepEnd.setId(rs.getInt("id"));
            cepEnd.setCep(rs.getString("cep"));
            cepEnd.setIdLogradouro(rs.getInt("id_logradouro"));
            cepEnd.setIdBairro(rs.getInt("id_bairro"));
            return cepEnd;
        }
    }
}
