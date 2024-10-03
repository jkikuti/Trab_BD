package org.example.api.repository;

import org.example.api.model.Pedido;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.math.BigDecimal;

@Repository
public class PedidoRepository {

    private final JdbcTemplate jdbcTemplate;

    public PedidoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Pedido> findAll() {
        String sql = "SELECT id, valor_total, data_pedido, id_cliente FROM loja.pedido";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Pedido pedido = new Pedido();
            pedido.setId(rs.getInt("id"));
            pedido.setValorTotal(rs.getBigDecimal("valor_total"));
            pedido.setDataPedido(rs.getTimestamp("data_pedido").toLocalDateTime());
            pedido.setIdCliente(rs.getInt("id_cliente"));
            return pedido;
        });
    }

    public Pedido findById(Integer id) {
        String sql = "SELECT id, valor_total, data_pedido, id_cliente FROM loja.pedido WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            Pedido pedido = new Pedido();
            pedido.setId(rs.getInt("id"));
            pedido.setValorTotal(rs.getBigDecimal("valor_total"));
            pedido.setDataPedido(rs.getTimestamp("data_pedido").toLocalDateTime());
            pedido.setIdCliente(rs.getInt("id_cliente"));
            return pedido;
        });
    }

    public Pedido save(Pedido pedido) {
        String sql = "INSERT INTO loja.pedido (valor_total, data_pedido, id_cliente) VALUES (?, ?, ?) RETURNING id";
        Integer id = jdbcTemplate.queryForObject(sql, new Object[]{
                pedido.getValorTotal(), Timestamp.valueOf(LocalDateTime.now()), pedido.getIdCliente()}, Integer.class);
        return findById(id);
    }

    public void update(Pedido pedido) {
        String sql = "UPDATE loja.pedido SET valor_total = ? WHERE id = ?";
        jdbcTemplate.update(sql, pedido.getValorTotal(), pedido.getId());
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM loja.pedido WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
