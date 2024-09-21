package org.example.api.repository;

import org.example.api.model.Bairro;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BairroRepository {

    private final JdbcTemplate jdbcTemplate;

    public BairroRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Bairro> findAll() {
        String sql = "SELECT id, nome, id_cidade FROM loja.bairro";
        return jdbcTemplate.query(sql, new BairroRowMapper());
    }

    public Bairro findById(Integer id) {
        String sql = "SELECT id, nome, id_cidade FROM loja.bairro WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BairroRowMapper(), id);
    }

    public Bairro findByNomeAndIdCidade(String nome, Integer idCidade) {
        String sql = "SELECT id, nome, id_cidade FROM loja.bairro WHERE nome = ? AND id_cidade = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BairroRowMapper(), nome, idCidade);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Bairro save(Bairro bairro) {
        String sql = "INSERT INTO loja.bairro (nome, id_cidade) VALUES (?, ?) RETURNING id";
        Integer generatedId = jdbcTemplate.queryForObject(sql, new Object[]{bairro.getNome(), bairro.getIdCidade()}, Integer.class);
        return findById(generatedId);
    }

    public Bairro update(Integer id, Bairro bairro) {
        String sql = "UPDATE loja.bairro SET nome = ?, id_cidade = ? WHERE id = ?";
        jdbcTemplate.update(sql, bairro.getNome(), bairro.getIdCidade(), id);
        return findById(id);
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM loja.bairro WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class BairroRowMapper implements RowMapper<Bairro> {
        @Override
        public Bairro mapRow(ResultSet rs, int rowNum) throws SQLException {
            Bairro bairro = new Bairro();
            bairro.setId(rs.getInt("id"));
            bairro.setNome(rs.getString("nome"));
            bairro.setIdCidade(rs.getInt("id_cidade"));
            return bairro;
        }
    }
}
