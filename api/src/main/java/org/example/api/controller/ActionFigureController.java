package org.example.api.controller;

import org.example.api.model.ActionFigure;
import org.example.api.repository.ActionFigureRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/action-figure")
@Validated
public class ActionFigureController {

    private final ActionFigureRepository actionFigureRepository;

    public ActionFigureController(ActionFigureRepository actionFigureRepository) {
        this.actionFigureRepository = actionFigureRepository;
    }

    @GetMapping
    public ResponseEntity<List<ActionFigure>> getAll() {
        List<ActionFigure> actionFigures = actionFigureRepository.findAll();
        return ResponseEntity.ok(actionFigures);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActionFigure> getById(@PathVariable Integer id) {
        ActionFigure actionFigure = actionFigureRepository.findById(id);
        if (actionFigure == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(actionFigure);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ActionFigure actionFigure) {
        actionFigureRepository.save(actionFigure);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody ActionFigure actionFigure) {
        ActionFigure actionFigureExistente = actionFigureRepository.findById(id);
        if (actionFigureExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        actionFigureRepository.update(id, actionFigure);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        ActionFigure actionFigureExistente = actionFigureRepository.findById(id);
        if (actionFigureExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        actionFigureRepository.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // Filtro para buscar action figures por fabricante, categoria, universo ou personagem
    @GetMapping("/filtrar")
    public ResponseEntity<List<ActionFigure>> filter(
            @RequestParam(value = "idFabricante", required = false) Integer idFabricante,
            @RequestParam(value = "categoria", required = false) String categoria,
            @RequestParam(value = "universo", required = false) String universo,
            @RequestParam(value = "personagem", required = false) String personagem) {

        List<ActionFigure> actionFigures = actionFigureRepository.findWithFilters(idFabricante, categoria, universo, personagem);

        if (actionFigures.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(actionFigures);
    }
}
