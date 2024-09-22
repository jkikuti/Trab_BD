package org.example.api.controller;

import org.example.api.model.Estado;
import org.example.api.repository.EstadoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

@RestController
@RequestMapping("/estados")
@Validated
public class EstadoController {

    private final EstadoRepository estadoRepository;

    public EstadoController(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    // Listar todos os estados
    @GetMapping
    public ResponseEntity<List<Estado>> getAll() {
        return ResponseEntity.ok(estadoRepository.findAll());
    }

    // Buscar estado por ID
    @GetMapping("/{id}")
    public ResponseEntity<Estado> getById(@PathVariable Integer id) {
        Estado estado = estadoRepository.findById(id);
        if (estado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(estado);
    }

    // Criar estado
    @PostMapping
    public ResponseEntity<Estado> create(@RequestBody Estado estado) {
        Estado novoEstado = estadoRepository.save(estado);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEstado);
    }

    // Atualizar estado por ID
    @PutMapping("/{id}")
    public ResponseEntity<Estado> update(@PathVariable Integer id, @RequestBody Estado estado) {
        Estado estadoAtualizado = estadoRepository.update(id, estado);
        return ResponseEntity.ok(estadoAtualizado);
    }

    // Deletar estado por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        estadoRepository.delete(id);
        return ResponseEntity.noContent().build();
    }
}
