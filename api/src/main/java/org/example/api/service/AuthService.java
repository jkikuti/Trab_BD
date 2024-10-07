package org.example.api.service;

import org.example.api.model.Cliente;
import org.example.api.repository.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final ClienteRepository clienteRepository;

    public AuthService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente authenticate(String email, String senha) {
        Cliente cliente = clienteRepository.findByEmail(email);
        
        if (cliente != null && cliente.getSenha().equals(senha)) {
            return cliente;
        }
        
        return null; // Retorna null se a autenticação falhar
    }
}
