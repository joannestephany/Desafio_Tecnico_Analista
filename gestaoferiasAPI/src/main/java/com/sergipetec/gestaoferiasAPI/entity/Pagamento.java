package com.sergipetec.gestaoferiasAPI.entity;

import com.sergipetec.gestaoferiasAPI.entity.enums.TipoPagamento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "pagamento")
@Getter
@Setter
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @com.fasterxml.jackson.annotation.JsonIgnore
    @OneToOne
    @JoinColumn(name = "periodo_id", nullable = false)
    private PeriodoFerias periodo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPagamento tipoPagamento;

    @Column(nullable = false)
    private BigDecimal valorBruto;

    @Column(nullable = false)
    private BigDecimal valorLiquido;

    @Column(nullable = false)
    private LocalDate dataPagamento;
}
