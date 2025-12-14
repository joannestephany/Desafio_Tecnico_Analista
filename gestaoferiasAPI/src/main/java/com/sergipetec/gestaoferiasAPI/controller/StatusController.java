package com.sergipetec.gestaoferiasAPI.controller;

import com.sergipetec.gestaoferiasAPI.entity.StatusSolicitacao;
import com.sergipetec.gestaoferiasAPI.service.StatusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/status")
public class StatusController {

    private final StatusService service;

    public StatusController(StatusService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<StatusSolicitacao>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusSolicitacao> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }
}
