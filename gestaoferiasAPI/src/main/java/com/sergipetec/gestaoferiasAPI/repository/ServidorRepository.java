package com.sergipetec.gestaoferiasAPI.repository;

import com.sergipetec.gestaoferiasAPI.entity.Servidor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServidorRepository extends JpaRepository<Servidor, Long> {

    Optional<Servidor> findByMatricula(String matricula);

    boolean existsByMatricula(String matricula);
}
