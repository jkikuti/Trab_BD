package org.example.api.controller;

import org.example.api.model.Cliente;
import org.example.api.repository.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clientes")
@Validated
public class ClienteController {

    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Listar todos os clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> getAll() {
        return ResponseEntity.ok(clienteRepository.findAll());
    }

    // Buscar cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable Integer id) {
        Cliente cliente = clienteRepository.findById(id);
        if (cliente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(cliente);
    }

    // Criar um novo cliente
    @PostMapping
    public ResponseEntity<Cliente> create(@Valid @RequestBody Cliente cliente) {
        Cliente novoCliente = clienteRepository.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
    }

    // Atualizar um cliente existente
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Integer id, @Valid @RequestBody Cliente cliente) {
        Cliente clienteAtualizado = clienteRepository.update(id, cliente);
        return ResponseEntity.ok(clienteAtualizado);
    }

    // Deletar cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        clienteRepository.delete(id);
        return ResponseEntity.noContent().build();
    }
}
