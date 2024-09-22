package org.example.api.controller;

import org.example.api.model.CepEnd;
import org.example.api.repository.CepEndRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

@RestController
@RequestMapping("/ceps")
@Validated
public class CepEndController {

    private final CepEndRepository cepEndRepository;

    public CepEndController(CepEndRepository cepEndRepository) {
        this.cepEndRepository = cepEndRepository;
    }

    // Listar todos os ceps
    @GetMapping
    public ResponseEntity<List<CepEnd>> getAll() {
        return ResponseEntity.ok(cepEndRepository.findAll());
    }

    // Buscar cep por ID
    @GetMapping("/{id}")
    public ResponseEntity<CepEnd> getById(@PathVariable Integer id) {
        CepEnd cepEnd = cepEndRepository.findById(id);
        if (cepEnd == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(cepEnd);
    }

    // Criar cep
    @PostMapping
    public ResponseEntity<CepEnd> create(@RequestBody CepEnd cepEnd) {
        CepEnd novoCepEnd = cepEndRepository.save(cepEnd);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCepEnd);
    }

    // Atualizar cep por ID
    @PutMapping("/{id}")
    public ResponseEntity<CepEnd> update(@PathVariable Integer id, @RequestBody CepEnd cepEnd) {
        CepEnd cepEndAtualizado = cepEndRepository.update(id, cepEnd);
        return ResponseEntity.ok(cepEndAtualizado);
    }

    // Deletar cep por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        cepEndRepository.delete(id);
        return ResponseEntity.noContent().build();
    }
}
