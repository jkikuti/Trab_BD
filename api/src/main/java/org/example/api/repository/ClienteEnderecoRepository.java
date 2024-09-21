package org.example.api.repository;

import org.example.api.model.ClienteEndereco;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClienteEnderecoRepository {

    private final JdbcTemplate jdbcTemplate;

    public ClienteEnderecoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ClienteEndereco> findAll() {
        String sql = "SELECT id_cliente, id_endereco FROM loja.cliente_endereco";
        return jdbcTemplate.query(sql, new ClienteEnderecoRowMapper());
    }

    public ClienteEndereco findById(Integer idCliente, Integer idEndereco) {
        String sql = "SELECT id_cliente, id_endereco FROM loja.cliente_endereco WHERE id_cliente = ? AND id_endereco = ?";
        return jdbcTemplate.queryForObject(sql, new ClienteEnderecoRowMapper(), idCliente, idEndereco);
    }

    public ClienteEndereco save(ClienteEndereco clienteEndereco) {
        String sql = "INSERT INTO loja.cliente_endereco (id_cliente, id_endereco) VALUES (?, ?)";
        jdbcTemplate.update(sql, clienteEndereco.getIdCliente(), clienteEndereco.getIdEndereco());
        return findById(clienteEndereco.getIdCliente(), clienteEndereco.getIdEndereco());
    }

    public void delete(Integer idCliente, Integer idEndereco) {
        String sql = "DELETE FROM loja.cliente_endereco WHERE id_cliente = ? AND id_endereco = ?";
        jdbcTemplate.update(sql, idCliente, idEndereco);
    }

    private static class ClienteEnderecoRowMapper implements RowMapper<ClienteEndereco> {
        @Override
        public ClienteEndereco mapRow(ResultSet rs, int rowNum) throws SQLException {
            ClienteEndereco clienteEndereco = new ClienteEndereco();
            clienteEndereco.setIdCliente(rs.getInt("id_cliente"));
            clienteEndereco.setIdEndereco(rs.getInt("id_endereco"));
            return clienteEndereco;
        }
    }
}
