package org.example.api.controller;

import org.example.api.model.Bairro;
import org.example.api.repository.BairroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

@RestController
@RequestMapping("/bairros")
@Validated
public class BairroController {

    private final BairroRepository bairroRepository;

    public BairroController(BairroRepository bairroRepository) {
        this.bairroRepository = bairroRepository;
    }

    // Listar todos os bairros
    @GetMapping
    public ResponseEntity<List<Bairro>> getAll() {
        return ResponseEntity.ok(bairroRepository.findAll());
    }


    // Buscar bairro por ID
    @GetMapping("/{id}")
    public ResponseEntity<Bairro> getById(@PathVariable Integer id) {
        Bairro bairro = bairroRepository.findById(id);
        if (bairro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(bairro);
    }

    // Criar bairro
    @PostMapping
    public ResponseEntity<Bairro> create(@RequestBody Bairro bairro) {
        Bairro novoBairro = bairroRepository.save(bairro);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoBairro);
    }


    // Atualizar bairro por ID
    @PutMapping("/{id}")
    public ResponseEntity<Bairro> update(@PathVariable Integer id, @RequestBody Bairro bairro) {
        Bairro bairroAtualizado = bairroRepository.update(id, bairro);
        return ResponseEntity.ok(bairroAtualizado);
    }


    // Deletar bairro por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        bairroRepository.delete(id);
        return ResponseEntity.noContent().build();
    }
}
