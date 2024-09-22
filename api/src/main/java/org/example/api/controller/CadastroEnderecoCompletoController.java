package org.example.api.controller;

import org.example.api.dto.EnderecoDTO;
import org.example.api.model.Endereco;
import org.example.api.service.EnderecoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/endereco-completo")
@Validated
public class CadastroEnderecoCompletoController {

    private final EnderecoService enderecoService;

    public CadastroEnderecoCompletoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> getAll() {
        List<Endereco> enderecos = enderecoService.getAllEnderecos();
        return ResponseEntity.ok(enderecos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> getById(@PathVariable Integer id) {
        Endereco endereco = enderecoService.getEnderecoById(id);
        if (endereco == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(endereco);
    }

    @PostMapping
    public ResponseEntity<Endereco> inserirEnderecoCompleto(@Valid @RequestBody EnderecoDTO enderecoDTO) {
        Endereco novoEndereco = enderecoService.inserirEnderecoCompleto(enderecoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEndereco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> update(@PathVariable Integer id, @Valid @RequestBody EnderecoDTO enderecoDTO) {
        Endereco enderecoAtualizado = enderecoService.updateEnderecoCompleto(id, enderecoDTO);
        if (enderecoAtualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(enderecoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boolean deletado = enderecoService.deleteEnderecoCompleto(id);
        if (!deletado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.noContent().build();
    }
}
