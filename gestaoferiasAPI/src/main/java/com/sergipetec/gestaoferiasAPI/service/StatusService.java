package com.sergipetec.gestaoferiasAPI.service;

import com.sergipetec.gestaoferiasAPI.entity.StatusSolicitacao;
import com.sergipetec.gestaoferiasAPI.repository.StatusRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StatusService {

    private final StatusRepository repository;

    public StatusService(StatusRepository repository) {
        this.repository = repository;
    }

    public List<StatusSolicitacao> listarTodos() {
        return repository.findAll();
    }

    public StatusSolicitacao buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Status não encontrado"));
    }

    public StatusSolicitacao buscarPorDescricao(String descricao) {
        return repository.findByDescricaoIgnoreCase(descricao)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Status não encontrado com a descrição informada"));
    }
}
