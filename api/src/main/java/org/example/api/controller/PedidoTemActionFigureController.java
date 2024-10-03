package org.example.api.controller;

import org.example.api.model.PedidoTemActionFigure;
import org.example.api.repository.PedidoTemActionFigureRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.math.BigDecimal;

@RestController
@RequestMapping("/pedido-action-figure")
public class PedidoTemActionFigureController {

    private final PedidoTemActionFigureRepository pedidoTemActionFigureRepository;

    public PedidoTemActionFigureController(PedidoTemActionFigureRepository pedidoTemActionFigureRepository) {
        this.pedidoTemActionFigureRepository = pedidoTemActionFigureRepository;
    }

    @GetMapping
    public ResponseEntity<List<PedidoTemActionFigure>> getAll() {
        List<PedidoTemActionFigure> associacoes = pedidoTemActionFigureRepository.findAll();
        return ResponseEntity.ok(associacoes);
    }

    @GetMapping("/{idPedido}/{idActionFigure}")
    public ResponseEntity<PedidoTemActionFigure> getById(@PathVariable Integer idPedido, @PathVariable Integer idActionFigure) {
        PedidoTemActionFigure associacao = pedidoTemActionFigureRepository.findById(idPedido, idActionFigure);
        if (associacao == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(associacao);
    }

    @PostMapping
    public ResponseEntity<PedidoTemActionFigure> create(@RequestBody PedidoTemActionFigure pedidoTemActionFigure) {
        PedidoTemActionFigure novaAssociacao = pedidoTemActionFigureRepository.save(pedidoTemActionFigure);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaAssociacao);
    }

    @PutMapping("/{idPedido}/{idActionFigure}")
    public ResponseEntity<PedidoTemActionFigure> update(
            @PathVariable Integer idPedido, 
            @PathVariable Integer idActionFigure, 
            @RequestBody PedidoTemActionFigure pedidoTemActionFigure) {

        PedidoTemActionFigure itemExistente = pedidoTemActionFigureRepository.findById(idPedido, idActionFigure);
        if (itemExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // Atualizar a quantidade e recalcular o subtotal
        itemExistente.setPedidoQuantidade(pedidoTemActionFigure.getPedidoQuantidade());

        // Converter a quantidade de Integer para BigDecimal antes da multiplicação
        BigDecimal quantidade = new BigDecimal(itemExistente.getPedidoQuantidade());
        BigDecimal subtotal = quantidade.multiply(itemExistente.getValorUnitario());

        itemExistente.setSubtotal(subtotal);

        pedidoTemActionFigureRepository.update(itemExistente);
        return ResponseEntity.ok(itemExistente);
    }


    @DeleteMapping("/{idPedido}/{idActionFigure}")
    public ResponseEntity<Void> delete(@PathVariable Integer idPedido, @PathVariable Integer idActionFigure) {
        pedidoTemActionFigureRepository.delete(idPedido, idActionFigure);
        return ResponseEntity.noContent().build();
    }
}
