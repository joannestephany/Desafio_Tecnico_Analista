package com.sergipetec.gestaoferiasAPI.dto;

import java.time.LocalDate;

public record FeriasResumoDTO(
        Long id,
        LocalDate dataInicio,
        LocalDate dataFim,
        String status
) {}
