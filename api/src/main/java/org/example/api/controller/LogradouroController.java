package org.example.api.controller;

import org.example.api.model.Logradouro;
import org.example.api.repository.LogradouroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

@RestController
@RequestMapping("/logradouros")
@Validated
public class LogradouroController {

    private final LogradouroRepository logradouroRepository;

    public LogradouroController(LogradouroRepository logradouroRepository) {
        this.logradouroRepository = logradouroRepository;
    }

    @GetMapping
    public ResponseEntity<List<Logradouro>> getAll() {
        return ResponseEntity.ok(logradouroRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Logradouro> getById(@PathVariable Integer id) {
        Logradouro logradouro = logradouroRepository.findById(id);
        if (logradouro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(logradouro);
    }

    @PostMapping
    public ResponseEntity<Logradouro> create(@RequestBody Logradouro logradouro) {
        Logradouro novoLogradouro = logradouroRepository.save(logradouro);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoLogradouro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Logradouro> update(@PathVariable Integer id, @RequestBody Logradouro logradouro) {
        Logradouro logradouroAtualizado = logradouroRepository.update(id, logradouro);
        return ResponseEntity.ok(logradouroAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        logradouroRepository.delete(id);
        return ResponseEntity.noContent().build();
    }
}
