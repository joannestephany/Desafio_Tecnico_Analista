package com.sergipetec.gestaoferiasAPI.controller;

import com.sergipetec.gestaoferiasAPI.entity.Pagamento;
import com.sergipetec.gestaoferiasAPI.service.PagamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {

    private final PagamentoService service;

    public PagamentoController(PagamentoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Pagamento>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/periodo/{periodoId}")
    public ResponseEntity<Pagamento> buscarPorPeriodo(@PathVariable Long periodoId) {
        return ResponseEntity.ok(service.buscarPorPeriodo(periodoId));
    }

    @PostMapping
    public ResponseEntity<Pagamento> criar(
            @RequestBody Pagamento pagamento,
            @RequestParam Long periodoId) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.criar(pagamento, periodoId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pagamento> atualizar(
            @PathVariable Long id,
            @RequestBody Pagamento pagamento) {
        return ResponseEntity.ok(service.atualizar(id, pagamento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
