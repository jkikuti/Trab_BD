package org.example.api.controller;

import org.example.api.model.Cliente;
import org.example.api.repository.ClienteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping("/")
    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cliente getById(@PathVariable Integer id) {
        return clienteRepository.findById(id);
    }

    @PostMapping("/")
    public Cliente create(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @PutMapping("/{id}")
    public Cliente update(@PathVariable Integer id, @RequestBody Cliente cliente) {
        return clienteRepository.update(id, cliente);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        clienteRepository.delete(id);
    }
}
