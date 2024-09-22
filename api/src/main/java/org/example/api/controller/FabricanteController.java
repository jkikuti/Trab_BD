package org.example.api.controller;

import org.example.api.model.Fabricante;
import org.example.api.repository.FabricanteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fabricante")
@Validated
public class FabricanteController {

    private final FabricanteRepository fabricanteRepository;

    public FabricanteController(FabricanteRepository fabricanteRepository) {
        this.fabricanteRepository = fabricanteRepository;
    }

    @GetMapping
    public ResponseEntity<List<Fabricante>> listarFabricantes() {
        List<Fabricante> fabricantes = fabricanteRepository.findAll();
        return ResponseEntity.ok(fabricantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fabricante> buscarFabricantePorId(@PathVariable Integer id) {
        Fabricante fabricante = fabricanteRepository.findById(id);
        if (fabricante == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(fabricante);
    }

    @PostMapping
    public ResponseEntity<Void> criarFabricante(@RequestBody Fabricante fabricante) {
        fabricanteRepository.save(fabricante);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarFabricante(@PathVariable Integer id, @RequestBody Fabricante fabricante) {
        Fabricante fabricanteExistente = fabricanteRepository.findById(id);
        if (fabricanteExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        fabricanteRepository.update(id, fabricante);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFabricante(@PathVariable Integer id) {
        Fabricante fabricanteExistente = fabricanteRepository.findById(id);
        if (fabricanteExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        fabricanteRepository.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
