package com.sergipetec.gestaoferiasAPI.repository;

import com.sergipetec.gestaoferiasAPI.entity.StatusSolicitacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<StatusSolicitacao, Long> {

    Optional<StatusSolicitacao> findByDescricaoIgnoreCase(String descricao);
}
