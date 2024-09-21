package org.example.api.repository;

import org.example.api.model.Endereco;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EnderecoRepository {

    private final JdbcTemplate jdbcTemplate;

    public EnderecoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Endereco> findAll() {
        String sql = "SELECT id, numero, complemento, id_cep FROM loja.endereco";
        return jdbcTemplate.query(sql, new EnderecoRowMapper());
    }

    public Endereco findById(Integer id) {
        String sql = "SELECT id, numero, complemento, id_cep FROM loja.endereco WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new EnderecoRowMapper(), id);
    }

    public Endereco save(Endereco endereco) {
        String sql = "INSERT INTO loja.endereco (numero, complemento, id_cep) VALUES (?, ?, ?) RETURNING id";
        Integer generatedId = jdbcTemplate.queryForObject(sql, new Object[]{endereco.getNumero(), endereco.getComplemento(), endereco.getIdCep()}, Integer.class);
        return findById(generatedId);
    }

    public Endereco update(Integer id, Endereco endereco) {
        String sql = "UPDATE loja.endereco SET numero = ?, complemento = ?, id_cep = ? WHERE id = ?";
        jdbcTemplate.update(sql, endereco.getNumero(), endereco.getComplemento(), endereco.getIdCep(), id);
        return findById(id);
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM loja.endereco WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class EnderecoRowMapper implements RowMapper<Endereco> {
        @Override
        public Endereco mapRow(ResultSet rs, int rowNum) throws SQLException {
            Endereco endereco = new Endereco();
            endereco.setId(rs.getInt("id"));
            endereco.setNumero(rs.getInt("numero"));
            endereco.setComplemento(rs.getString("complemento"));
            endereco.setIdCep(rs.getInt("id_cep"));
            return endereco;
        }
    }
}
