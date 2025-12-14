package com.sergipetec.gestaoferiasAPI.controller;

import com.sergipetec.gestaoferiasAPI.dto.FeriasResumoDTO;
import com.sergipetec.gestaoferiasAPI.entity.Servidor;
import com.sergipetec.gestaoferiasAPI.service.ServidorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servidores")
public class ServidorController {

    private final ServidorService service;

    public ServidorController(ServidorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Servidor>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servidor> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<Servidor> buscarPorMatricula(@PathVariable String matricula) {
        return ResponseEntity.ok(service.buscarPorMatricula(matricula));
    }

    @GetMapping("/{id}/ferias")
    public ResponseEntity<List<FeriasResumoDTO>> listarFerias(@PathVariable Long id) {
        return ResponseEntity.ok(service.listarFeriasDoServidor(id));
    }

    @PostMapping
    public ResponseEntity<Servidor> criar(@RequestBody Servidor servidor) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.criar(servidor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servidor> atualizar(
            @PathVariable Long id,
            @RequestBody Servidor servidor) {
        return ResponseEntity.ok(service.atualizar(id, servidor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
