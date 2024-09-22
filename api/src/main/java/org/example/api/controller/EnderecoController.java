package org.example.api.controller;

import org.example.api.model.Endereco;
import org.example.api.repository.EnderecoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
@Validated
public class EnderecoController {

    private final EnderecoRepository enderecoRepository;

    public EnderecoController(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    // Listar todos os enderecos
    @GetMapping
    public ResponseEntity<List<Endereco>> getAll() {
        return ResponseEntity.ok(enderecoRepository.findAll());
    }

    // Buscar endereco por ID
    @GetMapping("/{id}")
    public ResponseEntity<Endereco> getById(@PathVariable Integer id) {
        Endereco endereco = enderecoRepository.findById(id);
        if (endereco == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(endereco);
    }

    // Criar endereco
    @PostMapping
    public ResponseEntity<Endereco> create(@RequestBody Endereco endereco) {
        Endereco novoEndereco = enderecoRepository.save(endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEndereco);
    }

    // Atualizar endereco por ID
    @PutMapping("/{id}")
    public ResponseEntity<Endereco> update(@PathVariable Integer id, @RequestBody Endereco endereco) {
        Endereco enderecoAtualizado = enderecoRepository.update(id, endereco);
        return ResponseEntity.ok(enderecoAtualizado);
    }

    // Deletar endereco por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        enderecoRepository.delete(id);
        return ResponseEntity.noContent().build();
    }
}
