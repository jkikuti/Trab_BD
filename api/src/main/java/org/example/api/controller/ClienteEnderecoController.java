package org.example.api.controller;

import org.example.api.model.ClienteEndereco;
import org.example.api.repository.ClienteEnderecoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente-endereco")
@Validated
public class ClienteEnderecoController {

    private final ClienteEnderecoRepository clienteEnderecoRepository;

    public ClienteEnderecoController(ClienteEnderecoRepository clienteEnderecoRepository) {
        this.clienteEnderecoRepository = clienteEnderecoRepository;
    }

    // Associar cliente a um endereço
    @PostMapping("/cliente/{idCliente}/endereco/{idEndereco}")
    public ResponseEntity<Void> associarClienteEndereco(
            @PathVariable Integer idCliente, @PathVariable Integer idEndereco) {
        ClienteEndereco clienteEndereco = new ClienteEndereco();
        clienteEndereco.setIdCliente(idCliente);
        clienteEndereco.setIdEndereco(idEndereco);
        clienteEnderecoRepository.save(clienteEndereco);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Listar todos os endereços de um cliente
    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<ClienteEndereco>> listarEnderecosPorCliente(@PathVariable Integer idCliente) {
        List<ClienteEndereco> clienteEnderecos = clienteEnderecoRepository.findByClienteId(idCliente);
        if (clienteEnderecos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(clienteEnderecos);
    }

    // Remover associação entre cliente e endereço
    @DeleteMapping("/cliente/{idCliente}/endereco/{idEndereco}")
    public ResponseEntity<Void> removerAssociacaoClienteEndereco(
            @PathVariable Integer idCliente, @PathVariable Integer idEndereco) {
        clienteEnderecoRepository.deleteByClienteIdAndEnderecoId(idCliente, idEndereco);
        return ResponseEntity.noContent().build();
    }
}
