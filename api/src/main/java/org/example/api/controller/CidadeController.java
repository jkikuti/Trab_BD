package org.example.api.controller;

import org.example.api.model.Cidade;
import org.example.api.repository.CidadeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

@RestController
@RequestMapping("/cidades")
@Validated
public class CidadeController {

    private final CidadeRepository cidadeRepository;

    public CidadeController(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    @GetMapping
    public ResponseEntity<List<Cidade>> getAll() {
        return ResponseEntity.ok(cidadeRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cidade> getById(@PathVariable Integer id) {
        Cidade cidade = cidadeRepository.findById(id);
        if (cidade == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(cidade);
    }

    @PostMapping
    public ResponseEntity<Cidade> create(@RequestBody Cidade cidade) {
        Cidade novaCidade = cidadeRepository.save(cidade);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaCidade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cidade> update(@PathVariable Integer id, @RequestBody Cidade cidade) {
        Cidade cidadeAtualizada = cidadeRepository.update(id, cidade);
        return ResponseEntity.ok(cidadeAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        cidadeRepository.delete(id);
        return ResponseEntity.noContent().build();
    }
}
