package org.example.api.repository;

import org.example.api.model.Cliente;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClienteRepository {

    private final JdbcTemplate jdbcTemplate;

    public ClienteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Cliente> findAll() {
        String sql = "SELECT id, cpf, nome, email, senha FROM loja.cliente";
        return jdbcTemplate.query(sql, new ClienteRowMapper());
    }

    public Cliente findById(Integer id) {
        String sql = "SELECT id, cpf, nome, email, senha FROM loja.cliente WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new ClienteRowMapper(), id);
    }

    @Transactional
    public Cliente save(Cliente cliente) {
        String sql = "INSERT INTO loja.cliente (cpf, nome, email, senha) VALUES (?, ?, ?, ?) RETURNING id";
        Integer generatedId = jdbcTemplate.queryForObject(sql, new Object[]{
            cliente.getCpf(), cliente.getNome(), cliente.getEmail(), cliente.getSenha()}, Integer.class);
        return findById(generatedId);
    }

    @Transactional
    public Cliente update(Integer id, Cliente cliente) {
        String sql = "UPDATE loja.cliente SET cpf = ?, nome = ?, email = ?, senha = ? WHERE id = ?";
        jdbcTemplate.update(sql, cliente.getCpf(), cliente.getNome(), cliente.getEmail(), cliente.getSenha(), id);
        return findById(id);
    }

    @Transactional
    public void delete(Integer id) {
        String sql = "DELETE FROM loja.cliente WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Cliente findByEmail(String email) {
        String sql = "SELECT * FROM loja.cliente WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{email}, (rs, rowNum) -> {
            Cliente cliente = new Cliente();
            cliente.setId(rs.getInt("id"));
            cliente.setNome(rs.getString("nome"));
            cliente.setEmail(rs.getString("email"));
            cliente.setSenha(rs.getString("senha"));
            cliente.setCpf(rs.getString("cpf"));
            return cliente;
        });
    }

    private static class ClienteRowMapper implements RowMapper<Cliente> {
        @Override
        public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
            Cliente cliente = new Cliente();
            cliente.setId(rs.getInt("id"));
            cliente.setCpf(rs.getString("cpf"));
            cliente.setNome(rs.getString("nome"));
            cliente.setEmail(rs.getString("email"));
            cliente.setSenha(rs.getString("senha"));
            return cliente;
        }
    }
}
