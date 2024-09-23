package org.example.api.controller;

import org.example.api.model.ActionFigureMaterial;
import org.example.api.repository.ActionFigureMaterialRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/action-figure-material")
public class ActionFigureMaterialController {

    private final ActionFigureMaterialRepository actionFigureMaterialRepository;

    public ActionFigureMaterialController(ActionFigureMaterialRepository actionFigureMaterialRepository) {
        this.actionFigureMaterialRepository = actionFigureMaterialRepository;
    }

    @GetMapping
    public ResponseEntity<List<ActionFigureMaterial>> getAll() {
        List<ActionFigureMaterial> associations = actionFigureMaterialRepository.findAll();
        return ResponseEntity.ok(associations);
    }

    @PostMapping
    public ResponseEntity<ActionFigureMaterial> create(@RequestBody ActionFigureMaterial actionFigureMaterial) {
        ActionFigureMaterial novaAssociacao = actionFigureMaterialRepository.save(actionFigureMaterial);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaAssociacao);
    }

    @DeleteMapping("af/{idActionFigure}/material/{idMaterial}")
    public ResponseEntity<Void> delete(@PathVariable Integer idActionFigure, @PathVariable Integer idMaterial) {
        actionFigureMaterialRepository.delete(idActionFigure, idMaterial);
        return ResponseEntity.noContent().build();
    }
}
