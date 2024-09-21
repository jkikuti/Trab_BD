package org.example.api.repository;

import org.example.api.model.Cidade;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CidadeRepository {

    private final JdbcTemplate jdbcTemplate;

    public CidadeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Cidade> findAll() {
        String sql = "SELECT id, nome, id_estado FROM loja.cidade";
        return jdbcTemplate.query(sql, new CidadeRowMapper());
    }

    public Cidade findById(Integer id) {
        String sql = "SELECT id, nome, id_estado FROM loja.cidade WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new CidadeRowMapper(), id);
    }

    public Cidade findByNomeAndIdEstado(String nome, Integer idEstado) {
        String sql = "SELECT id, nome, id_estado FROM loja.cidade WHERE nome = ? AND id_estado = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new CidadeRowMapper(), nome, idEstado);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Cidade save(Cidade cidade) {
        String sql = "INSERT INTO loja.cidade (nome, id_estado) VALUES (?, ?) RETURNING id";
        Integer generatedId = jdbcTemplate.queryForObject(sql, new Object[]{cidade.getNome(), cidade.getIdEstado()}, Integer.class);
        return findById(generatedId);
    }

    public Cidade update(Integer id, Cidade cidade) {
        String sql = "UPDATE loja.cidade SET nome = ?, id_estado = ? WHERE id = ?";
        jdbcTemplate.update(sql, cidade.getNome(), cidade.getIdEstado(), id);
        return findById(id);
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM loja.cidade WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class CidadeRowMapper implements RowMapper<Cidade> {
        @Override
        public Cidade mapRow(ResultSet rs, int rowNum) throws SQLException {
            Cidade cidade = new Cidade();
            cidade.setId(rs.getInt("id"));
            cidade.setNome(rs.getString("nome"));
            cidade.setIdEstado(rs.getInt("id_estado"));
            return cidade;
        }
    }
}
