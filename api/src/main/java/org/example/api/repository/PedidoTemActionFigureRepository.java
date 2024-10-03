package org.example.api.repository;

import org.example.api.model.PedidoTemActionFigure;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class PedidoTemActionFigureRepository {

    private final JdbcTemplate jdbcTemplate;

    public PedidoTemActionFigureRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<PedidoTemActionFigure> findAll() {
        String sql = "SELECT id_pedido, id_action_figure, pedido_quantidade, valor_unitario, subtotal FROM loja.pedido_tem_action_figure";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            PedidoTemActionFigure ptaf = new PedidoTemActionFigure();
            ptaf.setIdPedido(rs.getInt("id_pedido"));
            ptaf.setIdActionFigure(rs.getInt("id_action_figure"));
            ptaf.setPedidoQuantidade(rs.getInt("pedido_quantidade"));
            ptaf.setValorUnitario(rs.getBigDecimal("valor_unitario"));
            ptaf.setSubtotal(rs.getBigDecimal("subtotal"));
            return ptaf;
        });
    }

    public PedidoTemActionFigure findById(Integer idPedido, Integer idActionFigure) {
        String sql = "SELECT id_pedido, id_action_figure, pedido_quantidade, valor_unitario, subtotal FROM loja.pedido_tem_action_figure WHERE id_pedido = ? AND id_action_figure = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{idPedido, idActionFigure}, (rs, rowNum) -> {
            PedidoTemActionFigure ptaf = new PedidoTemActionFigure();
            ptaf.setIdPedido(rs.getInt("id_pedido"));
            ptaf.setIdActionFigure(rs.getInt("id_action_figure"));
            ptaf.setPedidoQuantidade(rs.getInt("pedido_quantidade"));
            ptaf.setValorUnitario(rs.getBigDecimal("valor_unitario"));
            ptaf.setSubtotal(rs.getBigDecimal("subtotal"));
            return ptaf;
        });
    }

    public List<PedidoTemActionFigure> findAllByPedidoId(Integer idPedido) {
        String sql = "SELECT id_pedido, id_action_figure, pedido_quantidade, valor_unitario, subtotal FROM loja.pedido_tem_action_figure WHERE id_pedido = ?";
        return jdbcTemplate.query(sql, new Object[]{idPedido}, (rs, rowNum) -> {
            PedidoTemActionFigure ptaf = new PedidoTemActionFigure();
            ptaf.setIdPedido(rs.getInt("id_pedido"));
            ptaf.setIdActionFigure(rs.getInt("id_action_figure"));
            ptaf.setPedidoQuantidade(rs.getInt("pedido_quantidade"));
            ptaf.setValorUnitario(rs.getBigDecimal("valor_unitario"));
            ptaf.setSubtotal(rs.getBigDecimal("subtotal"));
            return ptaf;
        });
    }

    public PedidoTemActionFigure save(PedidoTemActionFigure pedidoTemActionFigure) {
        String sqlPreco = "SELECT preco FROM loja.action_figure WHERE id = ?";
        BigDecimal preco = jdbcTemplate.queryForObject(sqlPreco, new Object[]{pedidoTemActionFigure.getIdActionFigure()}, BigDecimal.class);

        pedidoTemActionFigure.setValorUnitario(preco);
        pedidoTemActionFigure.setSubtotal(preco.multiply(new BigDecimal(pedidoTemActionFigure.getPedidoQuantidade())));

        String sql = "INSERT INTO loja.pedido_tem_action_figure (id_pedido, id_action_figure, pedido_quantidade, valor_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, pedidoTemActionFigure.getIdPedido(), pedidoTemActionFigure.getIdActionFigure(),
                pedidoTemActionFigure.getPedidoQuantidade(), pedidoTemActionFigure.getValorUnitario(), pedidoTemActionFigure.getSubtotal());

        return pedidoTemActionFigure;
    }

    public void update(PedidoTemActionFigure pedidoTemActionFigure) {
        String sql = "UPDATE loja.pedido_tem_action_figure SET pedido_quantidade = ?, subtotal = ? WHERE id_pedido = ? AND id_action_figure = ?";
        jdbcTemplate.update(sql, pedidoTemActionFigure.getPedidoQuantidade(), pedidoTemActionFigure.getSubtotal(),
                pedidoTemActionFigure.getIdPedido(), pedidoTemActionFigure.getIdActionFigure());
    }

    public void delete(Integer idPedido, Integer idActionFigure) {
        String sql = "DELETE FROM loja.pedido_tem_action_figure WHERE id_pedido = ? AND id_action_figure = ?";
        jdbcTemplate.update(sql, idPedido, idActionFigure);
    }
}
