package org.example.api.controller;

import org.example.api.model.ClienteEndereco;
import org.example.api.repository.ClienteEnderecoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente-enderecos")
@Validated
public class ClienteEnderecoController {

    private final ClienteEnderecoRepository clienteEnderecoRepository;

    public ClienteEnderecoController(ClienteEnderecoRepository clienteEnderecoRepository) {
        this.clienteEnderecoRepository = clienteEnderecoRepository;
    }

    @GetMapping
    public ResponseEntity<List<ClienteEndereco>> getAll() {
        return ResponseEntity.ok(clienteEnderecoRepository.findAll());
    }

    @GetMapping("/{idCliente}/{idEndereco}")
    public ResponseEntity<ClienteEndereco> getById(@PathVariable Integer idCliente, @PathVariable Integer idEndereco) {
        ClienteEndereco clienteEndereco = clienteEnderecoRepository.findById(idCliente, idEndereco);
        if (clienteEndereco == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(clienteEndereco);
    }

    @PostMapping
    public ResponseEntity<ClienteEndereco> create(@RequestBody ClienteEndereco clienteEndereco) {
        ClienteEndereco novoClienteEndereco = clienteEnderecoRepository.save(clienteEndereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoClienteEndereco);
    }

    @DeleteMapping("/{idCliente}/{idEndereco}")
    public ResponseEntity<Void> delete(@PathVariable Integer idCliente, @PathVariable Integer idEndereco) {
        clienteEnderecoRepository.delete(idCliente, idEndereco);
        return ResponseEntity.noContent().build();
    }
}
