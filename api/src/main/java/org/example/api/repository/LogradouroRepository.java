package org.example.api.repository;

import org.example.api.model.Logradouro;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LogradouroRepository {

    private final JdbcTemplate jdbcTemplate;

    public LogradouroRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Logradouro> findAll() {
        String sql = "SELECT id, nome, tipo, id_cidade FROM loja.logradouro";
        return jdbcTemplate.query(sql, new LogradouroRowMapper());
    }

    public Logradouro findById(Integer id) {
        String sql = "SELECT id, nome, tipo, id_cidade FROM loja.logradouro WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new LogradouroRowMapper(), id);
    }

    public Logradouro findByNomeAndIdCidade(String nome, Integer idCidade) {
        String sql = "SELECT id, nome, tipo, id_cidade FROM loja.logradouro WHERE nome = ? AND id_cidade = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new LogradouroRowMapper(), nome, idCidade);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Logradouro save(Logradouro logradouro) {
        String sql = "INSERT INTO loja.logradouro (nome, tipo, id_cidade) VALUES (?, ?, ?) RETURNING id";
        Integer generatedId = jdbcTemplate.queryForObject(sql, new Object[]{logradouro.getNome(), logradouro.getTipo(), logradouro.getIdCidade()}, Integer.class);
        return findById(generatedId);
    }

    public Logradouro update(Integer id, Logradouro logradouro) {
        String sql = "UPDATE loja.logradouro SET nome = ?, tipo = ?, id_cidade = ? WHERE id = ?";
        jdbcTemplate.update(sql, logradouro.getNome(), logradouro.getTipo(), logradouro.getIdCidade(), id);
        return findById(id);
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM loja.logradouro WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class LogradouroRowMapper implements RowMapper<Logradouro> {
        @Override
        public Logradouro mapRow(ResultSet rs, int rowNum) throws SQLException {
            Logradouro logradouro = new Logradouro();
            logradouro.setId(rs.getInt("id"));
            logradouro.setNome(rs.getString("nome"));
            logradouro.setTipo(rs.getString("tipo"));
            logradouro.setIdCidade(rs.getInt("id_cidade"));
            return logradouro;
        }
    }
}
