package com.sergipetec.gestaoferiasAPI.dto;

import java.time.LocalDate;
import com.sergipetec.gestaoferiasAPI.entity.StatusSolicitacao;

public record FeriasResumoDTO(
                Long id,
                LocalDate dataInicio,
                LocalDate dataFim,
                StatusSolicitacao status) {
}
