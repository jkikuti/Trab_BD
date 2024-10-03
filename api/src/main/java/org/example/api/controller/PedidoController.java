package org.example.api.controller;

import org.example.api.model.Pedido;
import org.example.api.model.PedidoTemActionFigure;
import org.example.api.model.ActionFigure;
import org.example.api.repository.PedidoRepository;
import org.example.api.repository.PedidoTemActionFigureRepository;
import org.example.api.repository.ActionFigureRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoRepository pedidoRepository;
    private final PedidoTemActionFigureRepository pedidoTemActionFigureRepository;
    private final ActionFigureRepository actionFigureRepository;

    public PedidoController(PedidoRepository pedidoRepository, PedidoTemActionFigureRepository pedidoTemActionFigureRepository, ActionFigureRepository actionFigureRepository) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoTemActionFigureRepository = pedidoTemActionFigureRepository;
        this.actionFigureRepository = actionFigureRepository;
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> getAll() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getById(@PathVariable Integer id) {
        Pedido pedido = pedidoRepository.findById(id);
        if (pedido == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(pedido);
    }

    @PostMapping
    public ResponseEntity<Pedido> create(@RequestBody Pedido pedido) {
        Pedido novoPedido = pedidoRepository.save(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> update(@PathVariable Integer id, @RequestBody Pedido pedido) {
        Pedido pedidoExistente = pedidoRepository.findById(id);
        if (pedidoExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        pedidoExistente.setValorTotal(pedido.getValorTotal());
        pedidoRepository.update(pedidoExistente);
        return ResponseEntity.ok(pedidoExistente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        pedidoRepository.delete(id);  // Certifique-se de que o método delete está implementado no PedidoRepository
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/confirmar-compra/{id}")
    public ResponseEntity<Pedido> confirmPurchase(@PathVariable Integer id) {
        Pedido pedido = pedidoRepository.findById(id);
        if (pedido == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // Recalcular o valor total com base nos itens do pedido
        List<PedidoTemActionFigure> itens = pedidoTemActionFigureRepository.findAllByPedidoId(id);
        BigDecimal valorTotal = itens.stream()
                .map(PedidoTemActionFigure::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        pedido.setValorTotal(valorTotal);
        pedidoRepository.update(pedido);

        // Atualizar o estoque das action figures
        for (PedidoTemActionFigure item : itens) {
            Integer idActionFigure = item.getIdActionFigure();
            Integer quantidadeVendida = item.getPedidoQuantidade();

            // Buscar a action figure e atualizar o estoque
            ActionFigure actionFigure = actionFigureRepository.findById(idActionFigure);
            if (actionFigure != null) {
                Integer novoEstoque = actionFigure.getEstoque() - quantidadeVendida;
                actionFigure.setEstoque(novoEstoque);
                actionFigureRepository.update(idActionFigure, actionFigure);
            }
        }

        return ResponseEntity.ok(pedido);
    }
}
