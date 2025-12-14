package com.sergipetec.gestaoferiasAPI.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "periodo_ferias")
@Getter
@Setter
public class PeriodoFerias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "servidor_id", nullable = false)
    private Servidor servidor;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private StatusSolicitacao status;

    @Column(nullable = false)
    private LocalDate dataInicio;

    @Column(nullable = false)
    private LocalDate dataFim;

    @OneToOne(mappedBy = "periodo", cascade = CascadeType.ALL)
    private Pagamento pagamento;
}
