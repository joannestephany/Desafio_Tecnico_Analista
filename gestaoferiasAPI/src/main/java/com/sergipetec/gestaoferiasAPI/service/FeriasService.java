package com.sergipetec.gestaoferiasAPI.service;

import com.sergipetec.gestaoferiasAPI.dto.FeriasInfosDTO;
import com.sergipetec.gestaoferiasAPI.dto.FeriasResumoDTO;
import com.sergipetec.gestaoferiasAPI.entity.Pagamento;
import com.sergipetec.gestaoferiasAPI.entity.PeriodoFerias;
import com.sergipetec.gestaoferiasAPI.repository.PeriodoFeriasRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FeriasService {

    private final PeriodoFeriasRepository repository;

    public FeriasService(PeriodoFeriasRepository repository) {
        this.repository = repository;
    }

    public List<FeriasResumoDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(f -> new FeriasResumoDTO(
                        f.getId(),
                        f.getDataInicio(),
                        f.getDataFim(),
                        f.getStatus().getDescricao()
                ))
                .toList();
    }

    public List<FeriasResumoDTO> listarPorServidor(Long servidorId) {
        return repository.findByServidorId(servidorId)
                .stream()
                .map(f -> new FeriasResumoDTO(
                        f.getId(),
                        f.getDataInicio(),
                        f.getDataFim(),
                        f.getStatus().getDescricao()
                ))
                .toList();
    }

    public FeriasInfosDTO buscarPorId(Long id) {
        PeriodoFerias f = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Férias não encontradas"));

        Pagamento p = f.getPagamento();

        if (p == null) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Pagamento ainda não registrado para este período"
            );
        }

        return new FeriasInfosDTO(
                f.getId(),
                f.getDataInicio(),
                f.getDataFim(),
                f.getStatus().getDescricao(),
                p.getValorBruto(),
                p.getValorLiquido(),
                p.getDataPagamento()
        );
    }

    public PeriodoFerias criar(PeriodoFerias ferias) {
        return repository.save(ferias);
    }

    public PeriodoFerias atualizar(Long id, PeriodoFerias nova) {
        PeriodoFerias atual = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Férias não encontradas"));

        atual.setDataInicio(nova.getDataInicio());
        atual.setDataFim(nova.getDataFim());
        atual.setStatus(nova.getStatus());

        return repository.save(atual);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Férias não encontradas"
            );
        }
        repository.deleteById(id);
    }
}
