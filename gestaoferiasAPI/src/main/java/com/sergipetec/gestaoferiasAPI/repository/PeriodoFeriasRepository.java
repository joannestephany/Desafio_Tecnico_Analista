package com.sergipetec.gestaoferiasAPI.repository;

import com.sergipetec.gestaoferiasAPI.entity.PeriodoFerias;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PeriodoFeriasRepository
        extends JpaRepository<PeriodoFerias, Long> {

    List<PeriodoFerias> findByServidorId(Long servidorId);
    List<PeriodoFerias> findByStatusDescricaoIgnoreCase(String descricao);
    List<PeriodoFerias> findByServidorIdAndStatusDescricaoIgnoreCase(
            Long servidorId,
            String descricao
    );


}
