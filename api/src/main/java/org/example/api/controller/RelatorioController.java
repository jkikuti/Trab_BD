package org.example.api.controller;

import org.example.api.repository.RelatorioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    private final RelatorioRepository relatorioRepository;

    public RelatorioController(RelatorioRepository relatorioRepository) {
        this.relatorioRepository = relatorioRepository;
    }

    @GetMapping("/receitas")
    public ResponseEntity<List<Map<String, Object>>> getRelatorioReceitas(
            @RequestParam String dataInicial,
            @RequestParam String dataFinal) {
        List<Map<String, Object>> receitas = relatorioRepository.getRelatorioReceitas(dataInicial, dataFinal);
        return ResponseEntity.ok(receitas);
    }

    @GetMapping("/mais-vendidas")
    public ResponseEntity<List<Map<String, Object>>> getActionFiguresMaisVendidas(
            @RequestParam String dataInicial,
            @RequestParam String dataFinal) {
        List<Map<String, Object>> actionFigures = relatorioRepository.getActionFiguresMaisVendidas(dataInicial, dataFinal);
        return ResponseEntity.ok(actionFigures);
    }

    @GetMapping("/vendas-periodo")
    public ResponseEntity<List<Map<String, Object>>> getRelatorioVendasPorPeriodo(
            @RequestParam String dataInicial,
            @RequestParam String dataFinal) {
        List<Map<String, Object>> vendas = relatorioRepository.getRelatorioVendasPorPeriodo(dataInicial, dataFinal);
        return ResponseEntity.ok(vendas);
    }
}
