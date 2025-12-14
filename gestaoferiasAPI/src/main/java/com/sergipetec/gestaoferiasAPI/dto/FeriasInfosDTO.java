package com.sergipetec.gestaoferiasAPI.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FeriasInfosDTO(
        Long id,
        LocalDate dataInicio,
        LocalDate dataFim,
        String status,
        BigDecimal valorBruto,
        BigDecimal valorLiquido,
        LocalDate dataPagamento
) {}
