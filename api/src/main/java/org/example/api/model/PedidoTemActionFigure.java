package org.example.api.model;

import java.math.BigDecimal;

public class PedidoTemActionFigure {

    private Integer idPedido;
    private Integer idActionFigure;
    private Integer pedidoQuantidade;
    private BigDecimal valorUnitario;
    private BigDecimal subtotal;

    // Getters e Setters
    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdActionFigure() {
        return idActionFigure;
    }

    public void setIdActionFigure(Integer idActionFigure) {
        this.idActionFigure = idActionFigure;
    }

    public Integer getPedidoQuantidade() {
        return pedidoQuantidade;
    }

    public void setPedidoQuantidade(Integer pedidoQuantidade) {
        this.pedidoQuantidade = pedidoQuantidade;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}
