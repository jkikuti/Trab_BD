package org.example.api.controller;

import org.example.api.model.Imagem;
import org.example.api.repository.ImagemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/imagens")
public class ImagemController {

    private final ImagemRepository imagemRepository;

    public ImagemController(ImagemRepository imagemRepository) {
        this.imagemRepository = imagemRepository;
    }

    @GetMapping
    public ResponseEntity<List<Imagem>> getAll() {
        return ResponseEntity.ok(imagemRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Imagem> getById(@PathVariable Integer id) {
        Imagem imagem = imagemRepository.findById(id);
        if (imagem == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(imagem);
    }

    @PostMapping
    public ResponseEntity<Imagem> create(@RequestBody Imagem imagem) {
        Imagem novaImagem = imagemRepository.save(imagem);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaImagem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Imagem> update(@PathVariable Integer id, @RequestBody Imagem imagem) {
        Imagem imagemAtualizada = imagemRepository.update(id, imagem);
        return ResponseEntity.ok(imagemAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        imagemRepository.delete(id);
        return ResponseEntity.noContent().build();
    }
}
