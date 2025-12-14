package com.sergipetec.gestaoferiasAPI.service;

import com.sergipetec.gestaoferiasAPI.entity.Pagamento;
import com.sergipetec.gestaoferiasAPI.entity.PeriodoFerias;
import com.sergipetec.gestaoferiasAPI.repository.PagamentoRepository;
import com.sergipetec.gestaoferiasAPI.repository.PeriodoFeriasRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PagamentoService {

    private final PagamentoRepository repository;
    private final PeriodoFeriasRepository periodoRepository;

    public PagamentoService(PagamentoRepository repository, PeriodoFeriasRepository periodoRepository) {
        this.repository = repository;
        this.periodoRepository = periodoRepository;
    }

    public List<Pagamento> listarTodos() {
        return repository.findAll();
    }

    public Pagamento buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Pagamento não encontrado"));
    }

    public Pagamento buscarPorPeriodo(Long periodoId) {
        return repository.findByPeriodoId(periodoId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Pagamento não encontrado para este período"));
    }

    public Pagamento criar(Pagamento pagamento, Long periodoId) {
        // Verifica se o período existe
        PeriodoFerias periodo = periodoRepository.findById(periodoId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Período de férias não encontrado"));

        // Verifica se já existe pagamento para este período
        if (repository.existsByPeriodoId(periodoId)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Já existe um pagamento registrado para este período");
        }

        pagamento.setPeriodo(periodo);
        return repository.save(pagamento);
    }

    public Pagamento atualizar(Long id, Pagamento novoPagamento) {
        Pagamento atual = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Pagamento não encontrado"));

        atual.setTipoPagamento(novoPagamento.getTipoPagamento());
        atual.setValorBruto(novoPagamento.getValorBruto());
        atual.setValorLiquido(novoPagamento.getValorLiquido());
        atual.setDataPagamento(novoPagamento.getDataPagamento());

        return repository.save(atual);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Pagamento não encontrado");
        }
        repository.deleteById(id);
    }
}
