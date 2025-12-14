package com.sergipetec.gestaoferiasAPI.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "status_solicitacao")
@Getter
@Setter
public class StatusSolicitacao {

    @Id
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @OneToMany(mappedBy = "status")
    private List<PeriodoFerias> periodosFerias;
}
