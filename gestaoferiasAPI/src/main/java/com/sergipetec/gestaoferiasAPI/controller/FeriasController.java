package com.sergipetec.gestaoferiasAPI.controller;

import com.sergipetec.gestaoferiasAPI.dto.FeriasInfosDTO;
import com.sergipetec.gestaoferiasAPI.dto.FeriasResumoDTO;
import com.sergipetec.gestaoferiasAPI.entity.PeriodoFerias;
import com.sergipetec.gestaoferiasAPI.service.FeriasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/ferias")
public class FeriasController {

    private final FeriasService service;

    public FeriasController(FeriasService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<FeriasResumoDTO>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeriasInfosDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<PeriodoFerias> criar(@RequestBody PeriodoFerias ferias) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.criar(ferias));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PeriodoFerias> atualizar(
            @PathVariable Long id,
            @RequestBody PeriodoFerias ferias) {

        return ResponseEntity.ok(service.atualizar(id, ferias));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
