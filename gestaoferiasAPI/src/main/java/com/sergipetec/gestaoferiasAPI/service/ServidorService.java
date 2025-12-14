package com.sergipetec.gestaoferiasAPI.service;

import com.sergipetec.gestaoferiasAPI.dto.FeriasResumoDTO;
import com.sergipetec.gestaoferiasAPI.entity.Servidor;
import com.sergipetec.gestaoferiasAPI.repository.ServidorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ServidorService {

    private final ServidorRepository repository;
    private final FeriasService feriasService;

    public ServidorService(ServidorRepository repository, FeriasService feriasService) {
        this.repository = repository;
        this.feriasService = feriasService;
    }

    public List<Servidor> listarTodos() {
        return repository.findAll();
    }

    public Servidor buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Servidor não encontrado"));
    }

    public Servidor buscarPorMatricula(String matricula) {
        return repository.findByMatricula(matricula)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Servidor não encontrado com a matrícula informada"));
    }

    public List<FeriasResumoDTO> listarFeriasDoServidor(Long servidorId) {
        if (!repository.existsById(servidorId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Servidor não encontrado");
        }
        return feriasService.listarPorServidor(servidorId);
    }

    public Servidor criar(Servidor servidor) {
        if (repository.existsByMatricula(servidor.getMatricula())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Já existe um servidor com esta matrícula");
        }
        return repository.save(servidor);
    }

    public Servidor atualizar(Long id, Servidor novoServidor) {
        Servidor atual = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Servidor não encontrado"));

        // Verifica se a matrícula já está em uso por outro servidor
        repository.findByMatricula(novoServidor.getMatricula())
                .ifPresent(s -> {
                    if (!s.getId().equals(id)) {
                        throw new ResponseStatusException(
                                HttpStatus.CONFLICT, "Já existe outro servidor com esta matrícula");
                    }
                });

        atual.setNome(novoServidor.getNome());
        atual.setMatricula(novoServidor.getMatricula());
        return repository.save(atual);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Servidor não encontrado");
        }
        repository.deleteById(id);
    }
}
