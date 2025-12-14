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
                                                f.getStatus()))
                                .toList();
        }

        public List<FeriasResumoDTO> listarPorServidor(Long servidorId) {
                return repository.findByServidorId(servidorId)
                                .stream()
                                .map(f -> new FeriasResumoDTO(
                                                f.getId(),
                                                f.getDataInicio(),
                                                f.getDataFim(),
                                                f.getStatus()))
                                .toList();
        }

        public List<FeriasResumoDTO> buscarPorStatus(String status) {

                List<PeriodoFerias> ferias = repository
                                .findByStatusDescricaoIgnoreCase(status);

                if (ferias.isEmpty()) {
                        throw new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Nenhum período de férias encontrado para o status informado");
                }

                return ferias.stream()
                                .map(f -> new FeriasResumoDTO(
                                                f.getId(),
                                                f.getDataInicio(),
                                                f.getDataFim(),
                                                f.getStatus()))
                                .toList();
        }

        public List<FeriasResumoDTO> listarPorServidorEStatus(
                        Long servidorId,
                        String status) {

                List<PeriodoFerias> ferias = repository.findByServidorIdAndStatusDescricaoIgnoreCase(
                                servidorId, status);

                if (ferias.isEmpty()) {
                        throw new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Nenhum período de férias encontrado para o servidor e status informados");
                }

                return ferias.stream()
                                .map(f -> new FeriasResumoDTO(
                                                f.getId(),
                                                f.getDataInicio(),
                                                f.getDataFim(),
                                                f.getStatus()))
                                .toList();
        }

        public FeriasInfosDTO buscarPorId(Long id) {
                PeriodoFerias f = repository.findById(id)
                                .orElseThrow(() -> new ResponseStatusException(
                                                HttpStatus.NOT_FOUND, "Férias não encontradas"));

                Pagamento p = f.getPagamento();

                // Retorna dados mesmo sem pagamento
                return new FeriasInfosDTO(
                                f.getId(),
                                f.getDataInicio(),
                                f.getDataFim(),
                                f.getStatus(),
                                p != null ? p.getValorBruto() : null,
                                p != null ? p.getValorLiquido() : null,
                                p != null ? p.getDataPagamento() : null);
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
                                        HttpStatus.NOT_FOUND, "Férias não encontradas");
                }
                repository.deleteById(id);
        }
}
