package com.sergipetec.gestaoferiasAPI.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.sergipetec.gestaoferiasAPI.entity.StatusSolicitacao;

public record FeriasInfosDTO(
                Long id,
                LocalDate dataInicio,
                LocalDate dataFim,
                StatusSolicitacao status,
                BigDecimal valorBruto,
                BigDecimal valorLiquido,
                LocalDate dataPagamento) {
}
