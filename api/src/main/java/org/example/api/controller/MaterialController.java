package org.example.api.controller;

import org.example.api.model.Material;
import org.example.api.repository.MaterialRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materiais")
public class MaterialController {

    private final MaterialRepository materialRepository;

    public MaterialController(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @GetMapping
    public ResponseEntity<List<Material>> getAll() {
        return ResponseEntity.ok(materialRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Material> getById(@PathVariable Integer id) {
        Material material = materialRepository.findById(id);
        if (material == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(material);
    }

    @PostMapping
    public ResponseEntity<Material> create(@RequestBody Material material) {
        Material novoMaterial = materialRepository.save(material);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoMaterial);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Material> update(@PathVariable Integer id, @RequestBody Material material) {
        Material materialAtualizado = materialRepository.update(id, material);
        return ResponseEntity.ok(materialAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        materialRepository.delete(id);
        return ResponseEntity.noContent().build();
    }
}
